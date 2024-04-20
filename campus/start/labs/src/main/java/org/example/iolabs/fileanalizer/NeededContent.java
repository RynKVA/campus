package org.example.iolabs.fileanalizer;

import java.util.ArrayList;

public class NeededContent {
    private ArrayList <String> neededSentences = new ArrayList<>();
    private final int countSpecifiedWord;

    public NeededContent(ArrayList<String> neededSentences, int countSpecifiedWord) {
        this.neededSentences = neededSentences;
        this.countSpecifiedWord = countSpecifiedWord;
    }

    public ArrayList<String> getNeededSentences() {
        return neededSentences;
    }

    public int getCountSpecifiedWord() {
        return countSpecifiedWord;
    }
}
