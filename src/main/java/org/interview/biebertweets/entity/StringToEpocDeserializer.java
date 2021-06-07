package org.interview.biebertweets.entity;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

class StringToEpocDeserializer extends StdDeserializer<Long> {

    protected StringToEpocDeserializer() {
        super(Long.class);
    }

    @Override
    public Long deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {
        //Example: "Wed Nov 04 18:57:24 +0000 2020"
        String stringDate = jsonParser.getText();
        return ZonedDateTime.parse(
            stringDate,
            DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss ZZZ yyyy")
        ).toInstant().toEpochMilli();
    }
}
