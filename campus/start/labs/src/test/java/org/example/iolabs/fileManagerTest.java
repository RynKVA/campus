package org.example.iolabs;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileManagerTest {

    @Test
    void testMethodCountFilesInDirectoryWithEightyFiles() throws IOException {
        assertEquals(80, FileManager.countFile("C:\\Users\\seraf\\OneDrive\\Документы\\java\\campus\\start\\labs\\testiolab"));
    }

    @Test
    void testMethodCountFilesInDirectoryWithoutFilesReturnZero() throws IOException {
        assertEquals(0, FileManager.countFile("C:\\Users\\seraf\\OneDrive\\Документы\\java\\campus\\start\\labs\\testiolab\\dirwithoutfiles"));
    }

    @Test
    void testMethodCountFilesWithNotExistPathExpectIOException() {
        IOException exception = assertThrows(IOException.class,
                () -> FileManager.countFile("e: labs"));
        assertEquals("Specified path:e: labs is not exist.", exception.getMessage());
    }

    @Test
    void testMethodCountDirsInDirectoryWithFourDirectoriesReturnFour() throws IOException {
        assertEquals(4, FileManager.countDirs("C:\\Users\\seraf\\OneDrive\\Документы\\java\\campus\\start\\labs\\testiolab"));
    }

    @Test
    void testMethodCountDirsInDirectoryWithoutDirectoriesReturnZero() throws IOException {
        assertEquals(0, FileManager.countDirs("C:\\Users\\seraf\\OneDrive\\Документы\\java\\campus\\start\\labs\\testiolab\\dirwithoutfiles"));
    }

    @Test
    void testMethodDirsWithNotExistPathExpectIOException(){
        IOException exception = assertThrows(IOException.class,
                () -> FileManager.countDirs("e: labs"));
        assertEquals("Specified path:e: labs is not exist.", exception.getMessage());
    }
}