package org.example.iolabs.filemanager;

import java.io.*;

public class FileManager {
    public static int countFile(String path) throws IOException {
        int countFiles = 0;
        File pathFile = new File(path);
        checkExistPath(pathFile, path);
        if (pathFile.isDirectory()) {
            for (File file : pathFile.listFiles()) {
                if (file.isDirectory()) {
                    String dirPath = file.getPath();
                    countFiles += countFile(dirPath);
                } else {
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
                } else {

                }
            }
        }
        return countDirs;
    }

    public static boolean move(String pathFrom, String pathTo) throws IOException {
        boolean isMoved = false;
        File fileOrDirectory = new File(pathFrom);
        File targetDirectory = new File(pathTo);
        checkPathOnSatisfactory(fileOrDirectory, targetDirectory);
        if (targetDirectory.isDirectory()) {
            if (fileOrDirectory.isDirectory()) {
                isMoved = fileOrDirectory.renameTo(new File(pathTo, fileOrDirectory.getName()));
            } else {
                isMoved = fileOrDirectory.renameTo(new File(pathTo, fileOrDirectory.getName()));
            }
        }
        return isMoved;
    }

    public static void copy(String pathFrom, String pathTo) throws IOException {

        File fileOrDirectory = new File(pathFrom);
        File targetDirectory = new File(pathTo);
        checkPathOnSatisfactory(fileOrDirectory, targetDirectory);
        if (fileOrDirectory.isDirectory()) {
            File copyDirectory = new File(targetDirectory, fileOrDirectory.getName());
            copyDir(fileOrDirectory, copyDirectory);
        } else {
            String pathCopyFile = new File(targetDirectory, fileOrDirectory.getName()).getPath();
            copyFile(pathFrom, pathCopyFile);
        }
    }

    private static void copyFile(String pathFrom, String pathTo) throws IOException {
        FileInputStream src = new FileInputStream(pathFrom);
        FileOutputStream dest = new FileOutputStream(pathTo);
        try (src; dest) {
            byte[] allBytes = src.readAllBytes();
            dest.write(allBytes);
        }
    }

    private static void copyDir(File directoryFrom, File pathTo) throws IOException {
        pathTo.mkdir();
        File[] files = directoryFrom.listFiles();
        assert files != null;
        for (File file : files) {
            copy(file.getPath(), pathTo.getPath());
        }

    }

    public static void checkPathOnSatisfactory(File fileOrDirectory, File targetDirectory) throws IOException {
        checkExistPath(fileOrDirectory, fileOrDirectory.getPath());
        checkExistPath(targetDirectory, targetDirectory.getPath());
        if (fileOrDirectory == targetDirectory){
            throw  new IOException("Path where you moving or copying is the same as path destination.");
        } else if (!targetDirectory.isDirectory()) {
            throw new IOException("Path where you moving or copying is not Directory.");
        }
    }

    private static void checkExistPath(File file, String path) throws IOException {
        if (!file.exists()) {
            throw new IOException("Specified path:" + path + " is not exist.");
        }
    }
}
