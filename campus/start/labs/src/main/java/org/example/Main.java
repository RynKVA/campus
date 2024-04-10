package org.example;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        testIsYearLeapPassed();
    }

    //# 1
    static String print(char[] array) {
        String values = "";
        for (char element : array) {
            values += element + " ";
        }
        System.out.println(values);
        return values;
    }

    static void printTest(String id, char[] actual, String expected) {
        if (print(actual).equals(expected)) {
            System.out.println("TEST " + id + " PASSED!");
        } else {
            System.out.println("TEST" + id + " FAILED! Actual :" + print(actual) + ", Expected:" + expected);
        }
    }

    static void printTestPassed() {
        char[] actual = {'H', 'e', 'l', 'l', 'o'};
        String expected = "H e l l o ";
        printTest("#1", actual, expected);
    }

    static void printTestFailed() {
        char[] actual = {'H', 'e', 'l', 'l', 'o'};
        String expected = "H e l l ";
        printTest("#1", actual, expected);
    }

    //# 2
    static char[] toCharArray(int[] array) {
        char[] charArray = new char[array.length];
        for (int i = 0; i < array.length; i++) {
            charArray[i] = (char) array[i];
        }
        return charArray;
    }

    static void toCharArrayTest(String id, int[] actual, char[] expected) {
        if (actual.length == expected.length) {
            char[] chars = toCharArray(actual);
            boolean sameArray = true;
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] != expected[i]) {
                    sameArray = false;
                }
            }
            if (sameArray) {
                System.out.println("TEST " + id + " PASSED!");
            } else {
                System.out.println("TEST" + id + " FAILED! Actual :" + Arrays.toString(toCharArray(actual)) + ", Expected:" + Arrays.toString(expected));
            }
        } else {
            System.out.println("TEST" + id + " FAILED! Actual :" + Arrays.toString(toCharArray(actual)) + ", Expected:" + Arrays.toString(expected));
        }
    }

    static void testToCharArrayPassed() {
        int[] actual = {72, 101, 108, 108, 111};
        char[] expected = {'H', 'e', 'l', 'l', 'o'};
        toCharArrayTest("#2", actual, expected);
    }

    static void testToCharArrayFailed() {
        int[] actual = {72, 101, 108, 108, 111};
        char[] expected = {'H', 'e', 'l', 'l'};
        toCharArrayTest("#2", actual, expected);
    }


    //# 3
    static int max(int a, int b) {
        return a > b ? a : b;
    }

    static void testMax(String id, int a, int b, int max) {
        if (max(a, b) == max) {
            System.out.println("TEST " + id + " PASSED!");
        } else {
            System.out.println("TEST" + id + " FAILED! Actual :" + max(a, b) + ", Expected:" + max);
        }
    }

    static void testMaxPassed() {
        int a = 10;
        int b = 12;
        testMax("#3", a, b, 12);
    }

    //# 4
    static int max(int a, int b, int c) {
        return max(max(a, b), c);
    }

    //# 5
    static int max(int a, int b, int c, int d, int e) {
        return max(max(a, b), max(c, d), e);

    }

    //# 6
    static String toString(char[] array) {
        String string = "";
        for (char element : array) {
            string += element;
        }
        return string;
    }

    static void toStringTest(String id, char[] actual, String expected) {
        if (toString(actual).equals(expected)) {
            System.out.println("TEST " + id + " PASSED!");
        } else {
            System.out.println("TEST" + id + " FAILED! Actual :" + toString(actual) + ", Expected:" + expected);
        }
    }

    static void toStringTestPassed() {
        char[] actual = {'H', 'e', 'l', 'l', 'o'};
        String expected = "Hello";
        toStringTest("#6", actual, expected);
    }

    //#8
    static int indexOf(int[] array, int element) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == element) {
                return i;
            }
        }
        return -1;
    }

    static void testIndexOf(String id, int[] targetArray, int target) {
        int index = indexOf(targetArray, target);
        int targetIndex = 0;
        boolean isUnique = false;
        for (int i = 0; i < targetArray.length; i++) {
            if (targetArray[i] != target) {
                isUnique = true;
            } else if (targetArray[i] == target) {
                targetIndex = i;
            }
        }
        if (isUnique && index == -1) {
            System.out.println("TEST " + id + " PASSED!");
        } else if (targetArray[index] == target) {
            System.out.println("TEST " + id + " PASSED!");
        } else {
            System.out.println("TEST" + id + " FAILED! Actual :" + index + ", Expected:" + targetIndex);
        }
    }

    static void testIndexOfPassed() {
        int[] targetArray = {72, 101, 108, 108, 111};
        int target = 108;
        testIndexOf("#8", targetArray, target);
    }

    static void testIndexOfWithoutTargetElementInArrayReturnMinusOnePassed() {
        int[] targetArray = {72, 101, 108, 108, 111};
        int target = 109;
        testIndexOf("#8", targetArray, target);
    }


    //#9
    static int lastIndexOf(int[] array, int element) {
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] == element) {
                return i;
            }
        }
        return -1;
    }

    static void testLastIndexOf(String id, int[] targetArray, int target){
        int index = lastIndexOf(targetArray, target);
        int targetIndex = 0;
        boolean isUnique = false;
        for (int i = targetArray.length-1; i >=0; i--) {
            if (targetArray[i] != target) {
                isUnique = true;
            } else if (targetArray[i] == target) {
                targetIndex = i;
            }
        }
        if (isUnique && index == -1) {
            System.out.println("TEST " + id + " PASSED!");
        } else if (targetArray[index] == target) {
            System.out.println("TEST " + id + " PASSED!");
        } else {
            System.out.println("TEST" + id + " FAILED! Actual :" + index + ", Expected:" + targetIndex);
        }
    }
    static void testLastIndexOfPassed() {
        int[] targetArray = {72, 101, 108, 108, 111};
        int target = 108;
        testLastIndexOf("#9", targetArray, target);
    }

    static void testLastIndexOfWithoutTargetElementInArrayReturnMinusOnePassed() {
        int[] targetArray = {72, 101, 108, 108, 111};
        int target = 109;
        testLastIndexOf("#9", targetArray, target);
    }


    //#10
    static int factorial(int value) {
        if (value != 0) {
            return value * factorial(value - 1);
        }
        return 1;
    }
    static void assertFactorialEquals(String id, int value, int expected){
        if (factorial(value) == expected){
            System.out.println("TEST " + id + " PASSED!");
        }else {
            System.out.println("TEST" + id + " FAILED! Actual :" + factorial(value) + ", Expected:" + expected);
        }
    }
    static void testFactorialPassed(){
        int value = 5;
        int expected = 120;
        assertFactorialEquals("#10", value, expected);
    }

    //#11
    static boolean isYearLeap(int year) {
        return !(year % 4 != 0 || year % 100 == 0 && year % 400 != 0);
    }
    static void testIsYearLeap(String id, int year, boolean isLeap){
        if (isYearLeap(year) == isLeap){
            System.out.println("TEST " + id + " PASSED!");
        }else{
            System.out.println("TEST" + id + " FAILED! Actual :" + isYearLeap(year) + ", Expected:" + isLeap);
        }
    }
    static void testIsYearLeapPassed(){
        int year = 2000;
        testIsYearLeap("#11", year, true);
    }

    //#12
    static void printDivideElements(int[] array, int divider) {
        for (int elem : array) {
            if (elem % divider == 0) {
                System.out.print(elem + " ");
            }
        }
    }

    //#13
    static void sortArray(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    int temp = array[j];
                    array[j] = array[i];
                    array[i] = temp;
                }
            }
        }
    }

    static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);
            quickSort(array, pi + 1, high);
            quickSort(array, low, pi - 1);
        }
    }

    private static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;

                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }

    //#14
    /* static boolean hasDuplicates(byte[] array) {
         for (int i = 0; i < array.length / 2; i++) {
             for (int j = i + 1; j < array.length; j++) {
                 if (array[i] == array[j]) {
                     return true;
                 }
             }
         }
         return false;
     }*/
    static boolean hasDuplicates(byte[] array) {
        boolean[] hasDuplicate = new boolean[256];
        for (byte elem : array) {
            int index = elem + 128;
            if (hasDuplicate[index]) {
                return true;
            } else {
                hasDuplicate[index] = true;
            }
        }
        return false;
    }

    /* static boolean isContainMatchElements2(byte[] array) {
         for (int i = 0; i < array.length / 2; i++) {
             for (int j = i + 1, k = array.length - 1; j < array.length; j++, k--) {
                 if (array[i] == array[j] || array[i] == array[k]) {
                     return true;
                 }
             }
         }
         return false;
     }*/
    //#15
    static int[] multiplyArrays(int[] firstArray, int[] secondArray) {
        int[] multiplyArray = new int[firstArray.length];
        for (int i = 0; i < firstArray.length; i++) {
            multiplyArray[i] = firstArray[i] * secondArray[i];
        }
        return multiplyArray;
    }

    //#16
    static int[] uniqueElements(int[] firstArray, int[] secondArray) {
        int count = 0;
        boolean isUnique = false;
        int[] commonArray = new int[firstArray.length + secondArray.length];
        for (int i = 0; i < firstArray.length; i++) {
            for (int j = 0; j < secondArray.length; j++) {
                if (firstArray[i] == secondArray[j]) {
                    isUnique = false;
                    break;
                } else {
                    isUnique = true;
                }
            }
            if (isUnique) {
                commonArray[count] = firstArray[i];
                count++;
            }
        }

        for (int i = 0; i < secondArray.length; i++) {
            for (int j = 0; j < firstArray.length; j++) {
                if (secondArray[i] == firstArray[j]) {
                    isUnique = false;
                    break;
                } else {
                    isUnique = true;
                }
            }
            if (isUnique) {
                commonArray[count] = secondArray[i];
                count++;
            }
        }
        int[] targetArray = new int[count];
        System.arraycopy(commonArray, 0, targetArray, 0, count);
        return targetArray;

    }

    //#17
    static int[] reversArray(int[] array) {
        for (int i = 0, j = array.length - 1; i < array.length / 2; i++, j--) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
        return array;
    }

    //#18
    static int[] randomArray(int length, int min, int max) {
        int[] array = new int[length];
        for (int i = 0; i < array.length; i++) {
            array[i] = random(min, max);
        }
        return array;
    }


    private static int random(int min, int max) {
        double x = Math.random();
        int delta = max - min + 1;

        return (int) (x * delta + min);
    }

    //#19
    static boolean isContainCommonPart(char[] string, char[] subString) {
        int index = 0;
        for (char elem : string) {
            if (index != subString.length) {
                if (elem == subString[index]) {
                    index++;
                } else {
                    index = 0;
                }
            } else {
                return true;
            }
        }
        return false;
    }

    static int[] sumValueOfDiagonals(int[][] matrix) {
        int[] sumArray = new int[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            sumArray[i] = matrix[i][i];
        }
        for (int i = 0, j = matrix.length - 1; i < matrix.length; i++, j--) {
            sumArray[i] += matrix[i][j];
        }
        return sumArray;
    }

    static int sumDiagonals(int[][] matrix) {
        int sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            sum += matrix[i][i];
        }
        for (int i = 0, j = matrix.length - 1; i < matrix.length; i++, j--) {
            sum += matrix[i][j];
        }
        return sum;
    }

    static long[] filter(long[][] values, boolean[][] flags) {
        int count = 0;
        for (int i = 0; i < flags.length; i++) {
            for (int j = 0; j < flags[i].length; j++) {
                if (flags[i][j]) {
                    count++;
                }
            }
        }
        long[] trueValue = new long[count];
        int index = 0;
        for (int i = 0; i < flags.length; i++) {
            for (int j = 0; j < flags[i].length; j++) {
                if (flags[i][j]) {
                    trueValue[index] = values[i][j];
                    index++;
                }
            }
        }
        return trueValue;
    }

}