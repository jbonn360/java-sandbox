package com.example.actual;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TransferQueue;

public class MorseMessageConsumer implements Runnable {

	private TransferQueue<Character> transferQueue;

	private Map<String, Character> morseCodeCharacterMap;

	private final DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.FULL)
			.withLocale(Locale.UK).withZone(ZoneId.systemDefault());

	public MorseMessageConsumer(TransferQueue<Character> transferQueue) {
		this.transferQueue = transferQueue;

		morseCodeCharacterMap = new HashMap<>();
		morseCodeCharacterMap.put("O-", 'A');
		morseCodeCharacterMap.put("-OOO", 'B');
		morseCodeCharacterMap.put("-O-O", 'C');
	}

//	the space between signal elements 
//	within 1 character is 1 unit interval, 
//	between 2 characters 3 unit intervals, and
//	the space between 2 words or groups consists of 7 unit intervals.

	@Override
	public void run() {
		final Duration unitInterval = Duration.ofSeconds(1);

		Instant lastSignalReceivedAt = null;
		Character thisSignal = null;
		try {
			while (true) {
				thisSignal = transferQueue.take();

				if (thisSignal != null) {
					lastSignalReceivedAt = Instant.now();
					System.out.println("Received character: " + thisSignal.charValue() + " at "
							+ formatter.format(lastSignalReceivedAt));
					thisSignal = null;
				} else
					System.out.println("Did NOT receive any signal");

				// chill();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void chill() {
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
