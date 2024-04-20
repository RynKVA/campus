package org.example.iolabs.fileanalizer;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class FileAnalyzer {
    public static NeededContent fileAnalyzer() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();
        String word = scanner.nextLine();
        validatePathCorrectness(path);
        FileInputStream stream = new FileInputStream(path);
        byte[] allBytesFromFile = stream.readAllBytes();
        ArrayList<String> divineOnSentences = divineOnNeededSentences(allBytesFromFile, word);
        int countSpecifiedWord = countSpecifiedWord(allBytesFromFile, word);
        stream.close();
        return new NeededContent(divineOnSentences, countSpecifiedWord);
    }

    static int countSpecifiedWord(byte[] allBytes, String word) {
        int index = 0;
        int quantityWord = 0;
        byte[] wordBytes = word.getBytes();
        for (byte oneByte : allBytes) {
            if (oneByte == wordBytes[index]) {
                index++;
                if (index == wordBytes.length) {
                    quantityWord++;
                    index = 0;
                }
            } else {
                index = 0;
            }
        }
        return quantityWord;
    }


    static ArrayList<String> divineOnNeededSentences(byte[] allBytes, String word) {
        String str = new String(allBytes, StandardCharsets.UTF_8);
        String[] split = str.split("[.!?]");
        ArrayList<String> stringContainWord = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            if (split[i].contains(word)) {
                stringContainWord.add(split[i]);
            }
        }
        return stringContainWord;
    }

    static void validatePathCorrectness(String path) {
        if (!new File(path).exists()) {
            throw new NoSuchElementException("File on this path is not exist.");
        }
    }

}
