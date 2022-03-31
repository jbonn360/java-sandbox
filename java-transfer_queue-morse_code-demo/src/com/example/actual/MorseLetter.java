package com.example.actual;

import java.util.ArrayList;
import java.util.List;

public class MorseLetter {
	private List<MorseElement> elements;

	public MorseLetter() {
		elements = new ArrayList<MorseElement>();
	}

	public List<MorseElement> getElements() {
		return elements;
	}

	public void addElement(MorseElement element) {
		elements.add(element);
	}
}
