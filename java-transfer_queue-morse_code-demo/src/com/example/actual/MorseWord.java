package com.example.actual;

import java.util.List;

public class MorseWord {
	private List<MorseElement> elements;
	
	public MorseWord() {
	}
	
	public List<MorseElement> getElements() {
		return elements;
	}
	
	public void addElement(MorseElement element) {
		elements.add(element);
	}
}
