package org.example.iolabs;

import java.io.*;

public class FileManager {
    public static void main(String[] args) throws IOException {
        System.out.println(countFile("D:\\Photos"));
        System.out.println(countDirs("D:\\Photos"));
    }


    public static int countFile(String path) throws IOException {
        int countFiles = 0;
        File pathFile = new File(path);
        checkExistPath(pathFile, path);
        if (pathFile.isDirectory()) {
            for (File file : pathFile.listFiles()) {
                if (file.isDirectory()) {
                    String dirPath = file.getPath();
                    countFiles += countFile(dirPath);
                }else {
                    countFiles++;
                }
            }
        }
        return countFiles;
    }

    public static int countDirs(String path) throws IOException {
        int countDirs = 0;
        File pathFile = new File(path);
        checkExistPath(pathFile, path);
        if (pathFile.isDirectory()) {
            for (File file : pathFile.listFiles()) {
                if (file.isDirectory()) {
                    String dirPath = file.getPath();
                    countDirs += countDirs(dirPath);
                    countDirs++;
                }else {

                }
            }
        }
        return countDirs;
    }
    private static void checkExistPath(File file, String path) throws IOException {
        if (!file.exists()){
            throw new IOException("Specified path:" + path + " is not exist.");
        }
    }
}
