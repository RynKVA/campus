package org.example.iolabs.filemanager;
import org.junit.jupiter.api.*;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class FileManagerITest {
    private File textFile;
    private File textFile2;
    private File textFile3;
    private File textFile4;
    private File textFile5;
    private File textFile6;
    private File textFile7;
    private File textFile8;

    @BeforeEach
    void before() throws IOException {
        File mainDirectory = new File("testiolab", "TESTLAB");
        mainDirectory.mkdir();

        textFile = new File(mainDirectory.getPath(), "TestTextFile");
        FileOutputStream fileOutputStream = new FileOutputStream(textFile);
        fileOutputStream.write("Hello dear reader from textFile!".getBytes());
        fileOutputStream.close();
        textFile2 = new File(mainDirectory.getPath(), "TestTextFile2");
        FileOutputStream fileOutputStream1 = new FileOutputStream(textFile2);
        fileOutputStream1.write("Hello dear reader from textFile2!".getBytes());
        fileOutputStream1.close();
        textFile3 = new File(mainDirectory.getPath(), "TestTextFile3");
        FileOutputStream fileOutputStream2 = new FileOutputStream(textFile3);
        fileOutputStream2.write("Hello dear reader from textFile3!".getBytes());
        fileOutputStream2.close();

        File testDirectory = new File(mainDirectory.getPath(), "Dir1");
        testDirectory.mkdir();

        textFile4 = new File(testDirectory.getPath(), "TestTextFile4");
        textFile4.createNewFile();
        textFile5 = new File(testDirectory.getPath(), "TestTextFile5");
        textFile5.createNewFile();
        textFile6 = new File(testDirectory.getPath(), "TestTextFile6");
        textFile6.createNewFile();
        textFile7 = new File(testDirectory.getPath(), "TestTextFile7");
        textFile7.createNewFile();

        File testDirectory2 = new File(mainDirectory.getPath(), "Dir2");
        testDirectory2.mkdir();
        File testInDirectory2 = new File(testDirectory2.getPath(), "InDir2");
        testInDirectory2.mkdir();
        textFile8 = new File(testInDirectory2.getPath(), "TestTextFile8");
        textFile8.createNewFile();

        File testDirectory3 = new File(mainDirectory.getPath(), "Dir3");
        testDirectory3.mkdir();
    }


    @Test
    void testMethodCountFilesInDirectoryWithSevenFilesReturnSeven() throws IOException {
        assertEquals(8, FileManager.countFile("testiolab"));
    }

    @Test
    void testMethodCountFilesInDirectoryWithoutFilesReturnZero() throws IOException {
        assertEquals(0, FileManager.countFile("testiolab\\TESTLAB\\Dir3"));
    }

    @Test
    void testMethodCountFilesWithNotExistPathExpectIOException() {
        IOException exception = assertThrows(IOException.class,
                () -> FileManager.countFile("e: labs"));
        assertEquals("Specified path:e: labs is not exist.", exception.getMessage());
    }

    @Test
    void testMethodCountDirsInDirectoryWithFourDirectoriesReturnFour() throws IOException {
        assertEquals(5, FileManager.countDirs("testiolab"));
    }

    @Test
    void testMethodCountDirsInDirectoryWithoutDirectoriesReturnZero() throws IOException {
        assertEquals(0, FileManager.countDirs("testiolab\\TESTLAB\\Dir3"));
    }

    @Test
    void testMethodCountDirsWithNotExistPathExpectIOException() {
        IOException exception = assertThrows(IOException.class,
                () -> FileManager.countDirs("e: labs"));
        assertEquals("Specified path:e: labs is not exist.", exception.getMessage());
    }

    @Test
    void whenDirectoryOrFileWitchUsedIsNotExistExpectIOException() {
        IOException exception = assertThrows(IOException.class,
                () -> FileManager.checkPathOnSatisfactory(new File("e: labs"), new File("testiolab\\TESTLAB\\Dir3")));
        assertEquals("Specified path:e: labs is not exist.", exception.getMessage());
    }

    @Test
    void whenDirectoryToWhichMovingOrCopyingIsNotExistExpectIOException() {
        IOException exception = assertThrows(IOException.class,
                () -> FileManager.checkPathOnSatisfactory(textFile, new File("e: labs")));
        assertEquals("Specified path:e: labs is not exist.", exception.getMessage());
    }

    @Test
    void whenPathToWhichMovingOrCopyingIsNotDirectoryExpectIOException() throws IOException {
        IOException exception = assertThrows(IOException.class,
                () -> FileManager.checkPathOnSatisfactory(new File("testiolab\\TESTLAB\\Dir3"), textFile));
        assertEquals("Path where you moving or copying is not Directory.", exception.getMessage());

    }

    @Test
    void whenPathToWhichMovingOrCopyingIsSameAsPathFromWhereExpectIOException() throws IOException {
        File sameDirectory = new File("testiolab\\TESTLAB\\Dir3");
        IOException exception = assertThrows(IOException.class,
                () -> FileManager.checkPathOnSatisfactory(sameDirectory, sameDirectory));
        assertEquals("Path where you moving or copying is the same as path destination.", exception.getMessage());

    }

    @Test
    void whenMoveFileThanFileDeleteInDirectoryFromWhereMovingAndAppearInTargetDirectoryReturnTrue() throws IOException {
        // return true as confirmation moved targetFile
        assertTrue(FileManager.move(textFile.getPath(), "testiolab\\TESTLAB\\Dir2"));

        // checking exist new moving file's path
        assertTrue(new File("testiolab\\TESTLAB\\Dir2\\TestTextFile").exists());
        File targetFile = new File("testiolab\\TESTLAB\\Dir2\\TestTextFile");

        //checking not exist file with specified name  in directory from where moved
        File directoryFrom = new File("testiolab\\TESTLAB");
        File[] files = directoryFrom.listFiles();
        assert files != null;
        for (File file : files) {
            assertNotEquals("TestTextFile", file.getName());
        }

        // delete moving file
        targetFile.delete();


    }

    @Test
    void whenMoveTargetDirectoryThanTargetDeleteInDirectoryFromWhereMovingAndAppearInDestinationDirectoryWithAllFilesWithinReturnTrue() throws IOException {
        // quantity files in targetDirectory before moving
        File[] filesBeforeMove = new File("testiolab\\TESTLAB\\Dir1").listFiles();
        assertEquals(4, filesBeforeMove.length);

        // return true as confirmation moved targetDirectory
        assertTrue(FileManager.move("testiolab\\TESTLAB\\Dir1", "testiolab\\TESTLAB\\Dir3"));

        File targetDirectory = new File("testiolab\\TESTLAB\\Dir3\\Dir1");
        assertEquals("testiolab\\TESTLAB\\Dir3\\Dir1", targetDirectory.getPath());

        // iterating in directoryFrom to know that targetDirectory was deleted
        File directoryFrom = new File("testiolab\\TESTLAB");
        File[] files = directoryFrom.listFiles();
        assert files != null;
        for (File file : files) {
            assertNotEquals("Dir1", file.getName());
        }

        // quantity files in targetDirectory after moving
        File[] filesAfterMove = new File("testiolab\\TESTLAB\\Dir3\\Dir1").listFiles();
        assertEquals(4, filesAfterMove.length);

        // delete all files
        for (int i = 0; i < filesAfterMove.length; i++) {
            filesAfterMove[i].delete();
        }
        targetDirectory.delete();
    }

    @Test
    void whenMethodCopyUsedOnFileToTheTargetDirectoryThanFileCopiedWithAllTextWithinAndReturnTrue() throws IOException {
        FileManager.copy(textFile.getPath(), "testiolab\\TESTLAB\\Dir3");

        // compare expected file's path and name with actual
        File targetFile = new File("testiolab\\TESTLAB\\Dir3\\TestTextFile");
        assertEquals("testiolab\\TESTLAB\\Dir3\\TestTextFile", targetFile.getPath());
        assertEquals(textFile.getName(), targetFile.getName());

        // compare text with text in file witch copied
        FileInputStream fileInputStream = new FileInputStream(targetFile.getPath());
        try (fileInputStream) {
            byte[] bytesOfText = fileInputStream.readAllBytes();
            assertEquals("Hello dear reader from textFile!", new String(bytesOfText, StandardCharsets.UTF_8));
        }

        //delete copied file
        targetFile.delete();
    }

    @Test
    void whenMethodCopyUsingOnDirectoryToTheTargetDirectoryThanFileCopiedWithAllFilesAndDirectoriesWithinAndReturnTrue() throws IOException {
        FileManager.copy("testiolab\\TESTLAB\\Dir2", "testiolab\\TESTLAB\\Dir3");

        // compare expected directory's path and name with actual
        assertTrue(new File("testiolab\\TESTLAB\\Dir3\\Dir2").exists());
        assertEquals("Dir2", new File("testiolab\\TESTLAB\\Dir3\\Dir2").getName());

        //  compare expected innerDirectory's path and name with actual
        assertTrue(new File("testiolab\\TESTLAB\\Dir3\\Dir2\\InDir2").exists());
        assertEquals("InDir2", new File("testiolab\\TESTLAB\\Dir3\\Dir2\\InDir2").getName());

        // compare expected innerFile's path and name with actual
        assertTrue(new File("testiolab\\TESTLAB\\Dir3\\Dir2\\InDir2\\TestTextFile8").exists());
        assertEquals("TestTextFile8", new File("testiolab\\TESTLAB\\Dir3\\Dir2\\InDir2\\TestTextFile8").getName());


        // delete copied files
        File innerFile = new File("testiolab\\TESTLAB\\Dir3\\Dir2\\InDir2\\TestTextFile8");
        innerFile.delete();
        File innerInnerDirectory = new File("testiolab\\TESTLAB\\Dir3\\Dir2\\InDir2");
        innerInnerDirectory.delete();
        File ousterDirectory = new File("testiolab\\TESTLAB\\Dir3\\Dir2");
        ousterDirectory.delete();


    }

    @AfterEach
    void afterEachMethodDeleteAllFiles() throws IOException {
        textFile4.delete();
        textFile5.delete();
        textFile6.delete();
        textFile7.delete();
        textFile8.delete();
        textFile.delete();
        textFile2.delete();
        textFile3.delete();
        File testDirectory = new File("testiolab\\TESTLAB\\Dir1");
        testDirectory.delete();
        File testInDirectory2 = new File("testiolab\\TESTLAB\\Dir2\\InDir2");
        testInDirectory2.delete();
        File testDirectory2 = new File("testiolab\\TESTLAB\\Dir2");
        testDirectory2.delete();

        File testDirectory3 = new File("testiolab\\TESTLAB\\Dir3");
        testDirectory3.delete();
        File mainDirectory = new File("testiolab\\TESTLAB");
        mainDirectory.delete();

    }
}