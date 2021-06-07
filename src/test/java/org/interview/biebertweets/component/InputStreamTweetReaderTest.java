package org.interview.biebertweets.component;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

class InputStreamTweetReaderTest {

    InputStreamMessageReader inputStreamMessageReader;

    @Test
    void readMessages_simpleLine() throws ExecutionException, InterruptedException {
        inputStreamMessageReader = new InputStreamMessageReader(2, 10);
        String initialString = "text";
        InputStream inputStream = new ByteArrayInputStream(initialString.getBytes());
        List<String> result = inputStreamMessageReader.readMessages(inputStream);
        assertEquals(1, result.size());
        assertEquals(initialString, result.get(0));
    }

    @Test
    void readMessages_IgnoreNewLine() throws ExecutionException, InterruptedException {
        inputStreamMessageReader = new InputStreamMessageReader(2, 10);
        String initialString = "text\n";

        InputStream inputStream = new ByteArrayInputStream(initialString.getBytes());
        List<String> result = inputStreamMessageReader.readMessages(inputStream);
        assertEquals(1, result.size());
        assertEquals("text", result.get(0));
    }

    @Test
    void readMessages_IgnoreEmpty() throws ExecutionException, InterruptedException {
        inputStreamMessageReader = new InputStreamMessageReader(2, 10);
        String initialString =
                "text1\n" +
                "\n" +
                "text2\n";
        InputStream inputStream = new ByteArrayInputStream(initialString.getBytes());
        List<String> result = inputStreamMessageReader.readMessages(inputStream);
        assertEquals(2, result.size());
        assertEquals("text1", result.get(0));
        assertEquals("text2", result.get(1));
    }

    @Test
    void readMessages_IgnoreExtraMessages() throws ExecutionException, InterruptedException {
        inputStreamMessageReader = new InputStreamMessageReader(2, 10);
        String initialString =
                "text1\n" +
                "\n" +
                "text2\n" +
                "text3\n";
        InputStream inputStream = new ByteArrayInputStream(initialString.getBytes());
        List<String> result = inputStreamMessageReader.readMessages(inputStream);
        assertEquals(2, result.size());
        assertEquals("text1", result.get(0));
        assertEquals("text2", result.get(1));
    }

    @Test
    void readMessages_IgnoreAfterTimeMessages() throws ExecutionException, InterruptedException {
        inputStreamMessageReader = new InputStreamMessageReader(2, 0);
        String initialString =
                "text1\n" +
                "text2\n";
        InputStream inputStream = new ByteArrayInputStream(initialString.getBytes());
        List<String> result = inputStreamMessageReader.readMessages(inputStream);
        assertEquals(0, result.size());
    }
}