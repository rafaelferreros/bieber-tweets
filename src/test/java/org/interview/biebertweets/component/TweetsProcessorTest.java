package org.interview.biebertweets.component;

import org.interview.biebertweets.entity.Author;
import org.interview.biebertweets.entity.Result;
import org.interview.biebertweets.entity.Tweet;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TweetsProcessorTest {

    private TweetsProcessor tweetsProcessor;

    private Author author1 = new Author("USERID1", 1L, "TEST_NAME1", "TEST_SCREEN_NAME2");
    private Author author2 = new Author("USERID2", 2L, "TEST_NAME2", "TEST_SCREEN_NAME2");

    private Tweet tweet1 = new Tweet(author1, "MESSAGE_ID_1", 10L, "TEXT1");
    private Tweet tweet2 = new Tweet(author1, "MESSAGE_ID_2", 11L, "TEXT2");
    private Tweet tweet3 = new Tweet(author1, "MESSAGE_ID_3", 12L, "TEXT3");
    private Tweet tweet4 = new Tweet(author2, "MESSAGE_ID_4", 10L, "TEXT4");
    private Tweet tweet5 = new Tweet(author2, "MESSAGE_ID_5", 11L, "TEXT5");
    private Tweet tweet6 = new Tweet(author2, "MESSAGE_ID_6", 12L, "TEXT6");

    @Test
    void processTest() {
        tweetsProcessor = new TweetsProcessor();
        List<Tweet> tweets = new ArrayList<>();
        tweets.add(tweet1);
        tweets.add(tweet6);
        tweets.add(tweet2);
        tweets.add(tweet5);
        tweets.add(tweet3);
        tweets.add(tweet4);

        List<Result> result = tweetsProcessor.process(tweets);

        assertEquals(2, result.size());
        assertEquals(author1, result.get(0).author());
        assertEquals(tweet1.getMessage(), result.get(0).messages().get(0));
        assertEquals(tweet2.getMessage(), result.get(0).messages().get(1));
        assertEquals(tweet3.getMessage(), result.get(0).messages().get(2));

        assertEquals(author2, result.get(1).author());
        assertEquals(tweet4.getMessage(), result.get(1).messages().get(0));
        assertEquals(tweet5.getMessage(), result.get(1).messages().get(1));
        assertEquals(tweet6.getMessage(), result.get(1).messages().get(2));
    }
}
