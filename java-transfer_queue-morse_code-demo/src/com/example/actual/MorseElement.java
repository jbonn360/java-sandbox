package com.example.actual;

public enum MorseElement {
	SEPARATOR_ELEMENT(" "),
	SEPARATOR_LETTER("  "),
	SEPARATOR_WORD("       "),
	DOT("."),
	DASH("_"),
	;
	
	private String value;
	
	private MorseElement(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
//	public static MorseElement[] parse(String sequence) {
//		MorseElement[] result = new MorseElement[sequence.length()];
//		
//		for(int i = 0; i < sequence.length(); i++) {
//			result[i] = MorseElement.valueOf(Character.toString(sequence.charAt(i)));
//		}
//		
//		return result;
//	}
}
