package com.example.actual;

import java.util.List;

public class MorseWord {
	private List<MorseLetter> letters;
	
	public MorseWord() {
	}
	
	public List<MorseLetter> getLetters() {
		return letters;
	}
	
	public void addLetter(MorseLetter element) {
		letters.add(element);
	}
}
