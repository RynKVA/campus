package org.example.iolabs.fileanalizer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;

class FileAnalyzerITest {
    private File fileWithTextContainWord;
    private File fileWithNotContainWord;
    private File emptyTFile;
    private String word = "собака";

    @BeforeEach
    void before() throws IOException {
        fileWithTextContainWord = new File("testiolab", "TestTextFile");
        fileWithTextContainWord.createNewFile();
        FileOutputStream streamFileWithText = new FileOutputStream(fileWithTextContainWord);
        streamFileWithText.write("Сегодня утром я вышел на улицу и там была большая дружелюбная собака. Она мчалась по аллее, за ней громко лаял маленький щенок! Какое забавное зрелище! Я радовался каждому их движению и остановке. Подумалось мне, что жизнь, в которой есть собака, была бы настоящим приключением! Вдруг собака остановилась, посмотрела на меня внимательно и кивнула головой? Я не мог отказать такой милой просьбе и пошел бы на прогулку с новыми друзьями. Весь день мы бы шли по парку, играли, бегали и наслаждались природой.".getBytes());
        streamFileWithText.close();
        fileWithNotContainWord = new File("testiolab", "TestTextFile2");
        fileWithNotContainWord.createNewFile();
        FileOutputStream streamFileNotContainWord = new FileOutputStream(fileWithNotContainWord);
        streamFileNotContainWord.write("Рыжий кот по имени Томас обожает лежать на солнышке и мурлыкать в такт пение птиц! Его мягкая шерсть блестит на солнце, словно огонь! Томас часто прячется в кустах, чтобы подкараулить прохожих и испугать их! Когда Томасу хочется веселиться, он начинает ловить бабочек и играть с ними до упаду!. А все ли соседи завидуют Томасу за его изящную осанку и яркий рыжий окрас?".getBytes());
        streamFileNotContainWord.close();
        emptyTFile = new File("testiolab", "TestTextFile3");
        emptyTFile.createNewFile();
        FileOutputStream streamFileWithoutText = new FileOutputStream(emptyTFile);
        streamFileWithoutText.write("".getBytes());
        streamFileWithoutText.close();
    }

    @DisplayName("Analyzed file with text and found three touching word \"собака\", return 3")
    @Test
    void countWordThree() throws IOException {
        FileInputStream stream = new FileInputStream(fileWithTextContainWord.getPath());
        assertEquals(3, FileAnalyzer.countSpecifiedWord(stream.readAllBytes(), word));
        stream.close();
    }

    @DisplayName("Analyzed file without finding word, return 0")
    @Test
    void countWordZero() throws IOException {
        FileInputStream stream = new FileInputStream(fileWithNotContainWord.getPath());
        assertEquals(0, FileAnalyzer.countSpecifiedWord(stream.readAllBytes(), word));
        stream.close();
    }
    @DisplayName("Analyzed empty file, return 0")
    @Test
    void countWordInEmptyFileReturnZero() throws IOException {
        FileInputStream stream = new FileInputStream(emptyTFile.getPath());
        assertEquals(0, FileAnalyzer.countSpecifiedWord(stream.readAllBytes(), word));
        stream.close();
    }

    @DisplayName("When file on specified path is not exist expect NoSuchElementException")
    @Test
    void fileNotExist() {
        NoSuchElementException exception = assertThrows(NoSuchElementException.class,
                () -> FileAnalyzer.validatePathCorrectness("testiolab\\TESTLAB\\NOEXISTPATH"));
        assertEquals("File on this path is not exist.",exception.getMessage());
    }

    @DisplayName("When divine on sentences than return list of sentences contain word \"собака\" with length 3")
    @Test
    void divineOnSentences() throws IOException {
        FileInputStream stream = new FileInputStream(fileWithTextContainWord.getPath());
        ArrayList<String> listOfNeededSentences = FileAnalyzer.divineOnNeededSentences(stream.readAllBytes(), word);
        assertEquals(3, listOfNeededSentences.size());
        stream.close();

        assertEquals("Сегодня утром я вышел на улицу и там была большая дружелюбная собака.", listOfNeededSentences.get(0));
        assertEquals(" Подумалось мне, что жизнь, в которой есть собака, была бы настоящим приключением!", listOfNeededSentences.get(1));
        assertEquals(" Вдруг собака остановилась, посмотрела на меня внимательно и кивнула головой?", listOfNeededSentences.get(2));

    }

    @DisplayName("When divine on sentences which don't contain word \"собака\" than return empty list with length 0")
    @Test
    void divineOnSentencesWithFileNotContainWord() throws IOException {
        FileInputStream stream = new FileInputStream(fileWithNotContainWord.getPath());
        assertEquals(0, FileAnalyzer.divineOnNeededSentences(stream.readAllBytes(), word).size());
        stream.close();


    }
    @DisplayName("When divine on sentences on empty file than return empty list with length 0")
    @Test
    void divineOnSentencesWithEmptyFile() throws IOException {
        FileInputStream stream = new FileInputStream(emptyTFile.getPath());
        assertEquals(0, FileAnalyzer.divineOnNeededSentences(stream.readAllBytes(), word).size());
        stream.close();

    }

    @AfterEach
    void after(){
        fileWithTextContainWord.delete();
        fileWithNotContainWord.delete();
        emptyTFile.delete();
    }
}

