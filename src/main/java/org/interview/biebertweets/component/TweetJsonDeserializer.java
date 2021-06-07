package org.interview.biebertweets.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.interview.biebertweets.entity.Tweet;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TweetJsonDeserializer {

    public List<Tweet> deserialize(List<String> stringMessages) {
        return stringMessages.stream()
                .map(this::deserialize)
                .filter(t -> t != null && t.author() != null)
                .collect(Collectors.toList());
    }

    private Tweet deserialize(String jsonTweet) {
        Tweet tweet = null;
        try {
            tweet = new ObjectMapper().readValue(jsonTweet, Tweet.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return tweet;
    }

}
