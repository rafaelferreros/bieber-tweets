package org.interview.biebertweets.component;

import org.interview.biebertweets.entity.Author;
import org.interview.biebertweets.entity.Tweet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TweetJsonDeserializerTest {

    private String tweetString =
        """
        {
            "created_at":"Mon Jun 07 04:25:20 +0000 2021",
            "id":5678,
            "id_str":"5678",
            "text":"Enfim uma",
            "user":{
                "id":1234,
                "id_str":"1234",
                "name":"TestName",
                "screen_name":"TestScreenName",
                "created_at":"Wed Nov 04 18:57:24 +0000 2020"
            }
        }
        """;

    Author author = new Author(
        "1234",
        1604516244000L,
        "TestName",
        "TestScreenName"
    );

    Tweet tweet = new Tweet(
        author,
        "5678",
        1623039920000L,
        "Enfim uma"
    );

    @Test
    void deserializeListTest() {
        TweetJsonDeserializer deserializer = new TweetJsonDeserializer();
        List<String> tweetsStringList = new ArrayList<>();
        tweetsStringList.add(tweetString);
        tweetsStringList.add(tweetString);
        List<Tweet> tweets = deserializer.deserialize(tweetsStringList);
        assertEquals(2,tweets.size());
        assertEquals(tweet,tweets.get(0));
        assertEquals(tweet,tweets.get(1));
    }
}