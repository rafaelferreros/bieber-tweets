package org.interview.biebertweets.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import org.interview.biebertweets.entity.Result;
import org.interview.biebertweets.entity.Tweet;
import org.interview.oauth.twitter.TwitterAuthenticationException;
import org.interview.oauth.twitter.TwitterAuthenticator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.*;

@Component
public class TwitterTracker {

    private static final String STREAM_TWITTER_FILTER_URL = "https://stream.twitter.com/1.1/statuses/filter.json";
    private static final String TRACK = "track";

    private final String consumerKey;
    private final String consumerSecret;
    private final String trackValue;
    private final String outputDir;
    private final InputStreamMessageReader inputStreamMessageReader;
    private final TweetJsonDeserializer tweetJsonDeserializer;
    private final TweetsProcessor tweetsProcessor;

    public TwitterTracker(
        @Value("${consumerKey}") String consumerKey,
        @Value("${consumerSecret}") String consumerSecret,
        @Value("${trackValue}") String trackValue,
        @Value("${outputDir}") String outputDir,
        InputStreamMessageReader inputStreamMessageReader,
        TweetJsonDeserializer tweetJsonDeserializer,
        TweetsProcessor tweetsProcessor
    ) {
        this.consumerKey = consumerKey;
        this.consumerSecret = consumerSecret;
        this.trackValue = trackValue;
        this.inputStreamMessageReader = inputStreamMessageReader;
        this.tweetJsonDeserializer = tweetJsonDeserializer;
        this.tweetsProcessor = tweetsProcessor;
        this.outputDir = outputDir;
    }

    public void track() throws TwitterAuthenticationException, IOException, ExecutionException, InterruptedException {
        List<String> tweetsStringList = readTweets();
        List<Tweet> tweets = tweetJsonDeserializer.deserialize(tweetsStringList);
        List<Result> resultList = tweetsProcessor.process(tweets);

        String filename = LocalDateTime.now() + ".json";
        File file = new File(outputDir,filename);

        new ObjectMapper().writerWithDefaultPrettyPrinter().writeValue(file, resultList);
    }

    private List<String> readTweets() throws TwitterAuthenticationException, IOException, ExecutionException, InterruptedException {
        HttpRequestFactory httpRequestFactory = getRequestFactory();
		HttpRequest request = httpRequestFactory.buildGetRequest(buildUrl());
        HttpResponse response = request.execute();
        System.out.println("Reading Stream...");
        try {
            return inputStreamMessageReader.readMessages(response.getContent());
        }
        finally {
            response.disconnect();
        }
    }

    private HttpRequestFactory getRequestFactory() throws TwitterAuthenticationException {
        TwitterAuthenticator twitterAuthenticator = new TwitterAuthenticator(
                System.out,
                this.consumerKey,
                this.consumerSecret
        );
        return twitterAuthenticator.getAuthorizedHttpRequestFactory();
    }

    private GenericUrl buildUrl() {
        return new GenericUrl(STREAM_TWITTER_FILTER_URL).set(TRACK,trackValue);
    }
}
