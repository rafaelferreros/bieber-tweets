package org.interview.biebertweets.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record Result(
    @JsonProperty("author")
    Author author,
    @JsonProperty("messages")
    List<Message> messages
) {
}

