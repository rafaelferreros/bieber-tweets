package org.interview.biebertweets.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Tweet(
    @JsonProperty("user")
    Author author,
    @JsonProperty("id_str")
    String messageId,
    @JsonDeserialize(using = StringToEpocDeserializer.class)
    @JsonProperty("created_at")
    Long creationDate,
    @JsonProperty("text")
    String text
) {
    public Message getMessage() {
        return new Message(messageId, creationDate, text);
    }
}
