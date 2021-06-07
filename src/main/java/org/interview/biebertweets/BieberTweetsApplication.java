package org.interview.biebertweets;

import org.interview.biebertweets.component.TwitterTracker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BieberTweetsApplication implements CommandLineRunner {

	private final TwitterTracker twitterTracker;

	public BieberTweetsApplication(TwitterTracker twitterTracker) {
		this.twitterTracker = twitterTracker;
	}

	public static void main(String[] args) {
		SpringApplication.run(BieberTweetsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		twitterTracker.track();
	}
}
