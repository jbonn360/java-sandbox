package com.baeldung.javac;

import java.util.List;
import java.util.ArrayList;

public class Data {
    private List<String> textList = new ArrayList();

    public void addText(String text) {
        textList.add(text);
    }

    public List getTextList() {
        return this.textList;
    }
}
