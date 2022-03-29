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

	private final List<MorseElement> rawMorseMessage;

	// private final DateTimeFormatter formatter = DateTimeFormatter.of

	public MorseMessageConsumer(TransferQueue<Boolean> transferQueue) {
		this.transferQueue = transferQueue;

		morseCodeCharacterMap = new HashMap<>();
		morseCodeCharacterMap.put("01", 'A');
		morseCodeCharacterMap.put("1000", 'B');
		morseCodeCharacterMap.put("1010", 'C');

		rawMorseMessage = new ArrayList<>();
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
					if (lastSignal && !thisSignal) {
						// if 1 unit interval
						if (compareDurationsGracefully(thisInterval, morseUnitInterval))
							rawMorseMessage.add(MorseElement.DOT);
						// if 3 unit interval
						else if (compareDurationsGracefully(thisInterval, morseUnitInterval.multipliedBy(3)))
							rawMorseMessage.add(MorseElement.DASH);
						// anything else doesn't make sense
						else
							System.out.println("Signal unusable, ignoring...");
					}
					// indicates a space between morse units/letters/words
					else if (!lastSignal && thisSignal) {
						// if 1 unit interval
						if (compareDurationsGracefully(thisInterval, morseUnitInterval))
							rawMorseMessage.add(MorseElement.SEPARATOR_ELEMENT);
						// if 3 unit interval
						else if (compareDurationsGracefully(thisInterval, morseUnitInterval.multipliedBy(3)))
							rawMorseMessage.add(MorseElement.SEPARATOR_LETTER);
						// if 3 unit interval
						else if (compareDurationsGracefully(thisInterval, morseUnitInterval.multipliedBy(7))
								|| (thisInterval.compareTo(morseUnitInterval.multipliedBy(7)) > 0)) // if greater than 7 units, assume 
							rawMorseMessage.add(MorseElement.SEPARATOR_WORD);													// that this is a word separator
					}

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

	// synchronized methods allow only one thread to execute at any given time
	public synchronized String getRawMessage() {
		final StringBuilder sb = new StringBuilder(rawMorseMessage.size());
		
		rawMorseMessage.forEach(element -> sb.append(element.getValue()));
		
		return sb.toString();
	}

	public synchronized String getParsedMessage() {
		final List<MorseWord> rawWordsList = new ArrayList<MorseWord>();
	
		final StringBuilder sb = new StringBuilder();
		
		for(MorseElement element : rawMorseMessage) {
			//sb.append(MorseCodeTable.getAlphabeticalCharacter(element));
		}
		
//		MorseWord morseWordContainer = new MorseWord();
//		for(MorseElement element : rawMorseMessage){
//			if(element != MorseElement.SEPARATOR_WORD)
//				morseWordContainer.addElement(element);
//			else {
//				rawWordsList.add(morseWordContainer);
//				morseWordContainer = new MorseWord();
//			}	
//		}
//		
//		for(MorseWord morseWord : rawWordsList) {
//			for(MorseElement)
//		}
		
		return "";
	}
}
