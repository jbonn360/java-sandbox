package com.example.actual;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TransferQueue;

public class MorseMessageConsumer implements Runnable {
	private TransferQueue<Boolean> transferQueue;
	private Map<String, Character> morseCodeCharacterMap;

	final Duration morseUnitInterval = Duration.ofSeconds(1);
	final Duration morseUnitGraceAmount = Duration.ofMillis(200);

	private final StringBuilder sb;

	// private final DateTimeFormatter formatter = DateTimeFormatter.of

	public MorseMessageConsumer(TransferQueue<Boolean> transferQueue) {
		this.transferQueue = transferQueue;

		morseCodeCharacterMap = new HashMap<>();
		morseCodeCharacterMap.put("01", 'A');
		morseCodeCharacterMap.put("1000", 'B');
		morseCodeCharacterMap.put("1010", 'C');

		sb = new StringBuilder();
	}
	
//	https://www.quora.com/What-are-the-basic-rules-of-morse-code
//
//	1. Length of a dot is 1 unit
//	2. A dash is three units
//	3. The space between parts of the same letter is one unit
//	4. The space between letters of the same word is three units
//	5. The space between words is seven units
	@Override
	public void run() {
		Instant lastSignalReceivedAt = Instant.MIN;
		Instant thisSignalReceivedAt = null;
		
		Boolean lastSignal = false;
		Boolean thisSignal = false;

		Duration thisInterval = null;

		try {
			while (true) {
				thisSignal = transferQueue.take();

				// signals must always be alternating
				if (thisSignal != lastSignal) {
					thisSignalReceivedAt = Instant.now();
					thisInterval = Duration.between(lastSignalReceivedAt, thisSignalReceivedAt);

					// parse duration between reception of true > false signals into 0s and 1s AKA
					// .'s and _'s
					
					// indicates the completion of a morse unit
					if(lastSignal && !thisSignal) {
						// if 1 unit interval
						if (compareDurationsGracefully(thisInterval, morseUnitInterval))
							sb.append('0');		
						// if 3 unit interval
						else if (compareDurationsGracefully(thisInterval, morseUnitInterval.multipliedBy(3)))
							sb.append('1');
						// anything else doesn't make sense
						else 
							System.out.println("Signal unusable, ignoring...");							
					} 
					//indicates a space between morse units/letters/words
					else if (!lastSignal && thisSignal)
						
					

					// assign 'this' params to 'last' params
					lastSignalReceivedAt = thisSignalReceivedAt;
					lastSignal = thisSignal;
				} else {
					System.out.println("Error: Signal corruption occurred, ignoring signal.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private boolean compareDurationsGracefully(Duration duration1, Duration duration2) {
		final Duration difference = duration1.minus(duration2).abs();

		// is the difference within the allowed grace amount?
		return difference.compareTo(morseUnitGraceAmount) < 1;
	}

	private void chill() {
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
