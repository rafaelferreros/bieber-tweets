package org.interview.biebertweets.component;

import org.interview.biebertweets.entity.Author;
import org.interview.biebertweets.entity.Message;
import org.interview.biebertweets.entity.Result;
import org.interview.biebertweets.entity.Tweet;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Component
public class TweetsProcessor {

    public List<Result> process(List<Tweet> tweets) {
        Map<Author, List<Tweet>> unsortedMap = tweets.stream().collect(groupingBy(Tweet::author));
        Map<Author, List<Message>> sortedMap = new TreeMap<>(Comparator.comparing(Author::creationDate));
        unsortedMap.forEach((author, tweetList) -> sortedMap.put(author, sortAndTransformToMessage(tweetList)));

        return sortedMap.entrySet()
            .stream()
            .map(entry -> new Result(entry.getKey(), entry.getValue()))
            .collect(Collectors.toList());
    }

    private List<Message> sortAndTransformToMessage(List<Tweet> tweets) {
        tweets.sort(Comparator.comparing(Tweet::creationDate));
        return tweets.stream().map(Tweet::getMessage).collect(Collectors.toList());
    }

}
