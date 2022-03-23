package com.baeldung.javac;

import java.util.ArrayList;
import java.util.List;

public class Data {
    private List<String> textList = new ArrayList<String>();

    public void addText(String text) {
        textList.add(text);
    }

    public List<String> getTextList() {
        return this.textList;
    }
}
