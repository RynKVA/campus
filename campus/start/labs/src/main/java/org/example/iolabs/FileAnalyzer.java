package org.example.iolabs;

import java.awt.*;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class FileAnalyzer {
    public static void main(String[] args) throws IOException {
        fileAnalyzer();
    }


    public static void fileAnalyzer() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();
        String word = scanner.nextLine();
        FileInputStream stream = new FileInputStream(path);
        byte[] allBytes = stream.readAllBytes();
        System.out.println("The quantity of word in file: " + quantityFindWord(allBytes, word));
        String[] divineOnSentences = divineOnSentences(allBytes, word);
        for (String divineOnSentence : divineOnSentences) {
            System.out.println(divineOnSentence);
            stream.close();
        }
    }

    private static int quantityFindWord(byte[] allBytes, String word) {
        int index = 0;
        int quantityWord = 0;
        byte[] wordBytes = word.getBytes();
        for (int i = 0; i < allBytes.length; i++) {
            if (allBytes[i] == wordBytes[index]) {
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

    private static String[] divineOnSentences(byte[] allBytes, String word) throws IOException {
        String str = new String(allBytes, StandardCharsets.UTF_8);
        String[] split = str.split("[.!?]");
        int quantitySentences = 0;
        for (int i = 0; i < split.length; i++) {
            if (split[i].contains(word)) {
                quantitySentences++;
            }

        }
        int index = 0;
        String[] stringContainWord = new String[quantitySentences];
        for (int i = 0; i < split.length; i++) {
            if (split[i].contains(word)) {
                stringContainWord[index] = split[i];
                index++;
            }

        }
        return stringContainWord;
    }

}
