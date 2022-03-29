package com.example.actual;

import static com.example.actual.MorseElement.parse;

import java.util.HashMap;
import java.util.Map;

public class MorseCodeTable {
	private static Map<MorseElement[], Character> morseMap;
	
	static {
		morseMap = new HashMap<MorseElement[], Character>();
		
		morseMap.put(parse(". _"), 'A');
		morseMap.put(parse("_ . . ."), 'B');
		morseMap.put(parse("_ . _ ."), 'C');
		morseMap.put(parse("_ . ."), 'D');
		morseMap.put(parse("."), 'E');
		morseMap.put(parse(". . _ ."), 'F');
		morseMap.put(parse("_ _ ."), 'G');
		morseMap.put(parse(". . . ."), 'H');
		morseMap.put(parse(". ."), 'I');
		morseMap.put(parse(". _ _ _"), 'J');
		morseMap.put(parse("_ . _"), 'K');
		morseMap.put(parse(". _ . ."), 'L');
		morseMap.put(parse("_ _"), 'M');
		morseMap.put(parse("_ ."), 'N');
		morseMap.put(parse("_ _ _"), 'O');
		morseMap.put(parse(". _ _ ."), 'P');
		morseMap.put(parse("_ _ . _"), 'Q');
		morseMap.put(parse(". _ ."), 'R');
		morseMap.put(parse(". . . "), 'S');
		morseMap.put(parse("_"), 'T');
		morseMap.put(parse(". . _"), 'U');
		morseMap.put(parse(". . . _"), 'V');
		morseMap.put(parse(". _ _"), 'W');
		morseMap.put(parse("_ . . _"), 'X');
		morseMap.put(parse("_ . _ _"), 'Y');
		morseMap.put(parse("_ _ . ."), 'Z');
	}
	
	public static Character getAlphabeticalCharacter(MorseElement[] morseSequence) {
		return morseMap.get(morseSequence);
	}
}
