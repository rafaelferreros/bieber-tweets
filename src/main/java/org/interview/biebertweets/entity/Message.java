package org.interview.biebertweets.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Message(
    @JsonProperty("id_str")
    String messageId,
    @JsonProperty("created_at")
    Long creationDate,
    @JsonProperty("text")
    String text
) {
}
