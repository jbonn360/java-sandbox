package com.example.actual;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MorseTable {
	private static Map<String, Character> morseMap;
	
	static {
		morseMap = new HashMap<String, Character>();
		
		morseMap.put(". _", 'A');
		morseMap.put("_ . . .", 'B');
		morseMap.put("_ . _ .", 'C');
		morseMap.put("_ . .", 'D');
		morseMap.put(".", 'E');
		morseMap.put(". . _ .", 'F');
		morseMap.put("_ _ .", 'G');
		morseMap.put(". . . .", 'H');
		morseMap.put(". .", 'I');
		morseMap.put(". _ _ _", 'J');
		morseMap.put("_ . _", 'K');
		morseMap.put(". _ . .", 'L');
		morseMap.put("_ _", 'M');
		morseMap.put("_ .", 'N');
		morseMap.put("_ _ _", 'O');
		morseMap.put(". _ _ .", 'P');
		morseMap.put("_ _ . _", 'Q');
		morseMap.put(". _ .", 'R');
		morseMap.put(". . . ", 'S');
		morseMap.put("_", 'T');
		morseMap.put(". . _", 'U');
		morseMap.put(". . . _", 'V');
		morseMap.put(". _ _", 'W');
		morseMap.put("_ . . _", 'X');
		morseMap.put("_ . _ _", 'Y');
		morseMap.put("_ _ . .", 'Z');
	}
	
	public static Character getAlphabeticalCharacter(List<MorseElement> morseSequence) {
		final StringBuilder sb = new StringBuilder();
		
		morseSequence.forEach(element -> sb.append(element.getValue()));
		
		final Optional<Character> result = Optional.ofNullable(morseMap.get(sb.toString()));
		
		return result.orElse(Character.MIN_VALUE);
	}
}
