package org.interview.biebertweets.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

@Component
public class InputStreamMessageReader {

    private final Integer maxMessagesToRead;
    private final Integer timeoutInSeconds;

    public InputStreamMessageReader(
        @Value("${maxMessages}") Integer maxMessagesToRead,
        @Value("${timeoutSeconds}") Integer timeoutInSeconds
    ) {
        this.maxMessagesToRead = maxMessagesToRead;
        this.timeoutInSeconds = timeoutInSeconds;
    }

    public List<String> readMessages(InputStream inputStream) throws ExecutionException, InterruptedException {
        final List<String> messages = Collections.synchronizedList(new ArrayList<>());

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<?> future = executor.submit(() -> {
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            try {
                while ((line = in.readLine()) != null && messages.size() < maxMessagesToRead) {
                    if(!line.isEmpty())
                        messages.add(line);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        try {
            future.get(timeoutInSeconds, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            future.cancel(true);
        }
        return messages;
    }


}
