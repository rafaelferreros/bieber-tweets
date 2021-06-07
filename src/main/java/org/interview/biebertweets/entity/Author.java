package org.interview.biebertweets.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Author(
    @JsonProperty("id_str")
    String userId,
    @JsonDeserialize(using = StringToEpocDeserializer.class)
    @JsonProperty("created_at")
    Long creationDate,
    @JsonProperty("name")
    String name,
    @JsonProperty("screen_name")
    String screenName
) {
}
