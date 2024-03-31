package org.example;

public class Main {
    public static void main(String[] args) {
        byte[] array = {1, 5, -7, 9, 122, -7};
        System.out.println(hasDuplicates(array));
    }

    static void print(char[] array) {
        for (char elem : array)
            System.out.print(elem + " ");
    }

    static char[] toCharArray(int[] array) {
        char[] charArray = new char[array.length];
        for (int i = 0; i < array.length; i++) {
            charArray[i] = (char) array[i];
        }
        return charArray;
    }

    static int max(int a, int b) {
        return a > b ? a : b;
    }

    static int max(int a, int b, int c) {
        return max(max(a, b), c);
    }

    static int max(int a, int b, int c, int d, int e) {
        return max(max(a, b), max(c, d), e);

    }

    static String toString(char[] array) {
        String string = "";
        for (int i = 0; i < array.length; i++) {
            string += array[i];
        }
        return string;
    }

    static int indexOf(int[] array, int element) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == element) {
                return i;
            }
        }
        return -1;
    }

    static int lastIndexOf(int[] array, int element) {
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] == element) {
                return i;
            }
        }
        return -1;
    }

    static int factorial(int i) {
        if (i != 0) {
            return i * factorial(i - 1);
        }
        return 1;
    }

    static boolean isYearLeap(int year) {
        if (year % 4 == 0) {
            return true;
        }
        return false;
    }

    static void printDivideElements(int[] array, int divider) {
        for (int elem : array) {
            if (elem % divider == 0) {
                System.out.print(elem + " ");
            }
        }
    }

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
    static  boolean hasDuplicates(byte[] array){
        boolean [] hasDuplicate = new boolean[256];
        for (byte elem : array){
            int index = elem +128;
            if(hasDuplicate[index]){
                return true;
            }else {
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

    static int[] multiplyArrays(int[] firstArray, int[] secondArray) {
        int[] multiplyArray = new int[firstArray.length];
        for (int i = 0; i < firstArray.length; i++) {
            multiplyArray[i] = firstArray[i] * secondArray[i];
        }
        return multiplyArray;
    }

    static int[] notMatchElements(int[] firstArray, int[] secondArray) {
        int count = 0;
        for (int i = 0; i < firstArray.length; i++) {
            if (firstArray[i] != secondArray[i]) {
                count++;
            }
        }
        int[] targetArray = new int[count];
        int index = 0;
        for (int i = 0; i < firstArray.length; i++) {
            if (firstArray[i] != secondArray[i]) {
                targetArray[index] = firstArray[i];
                index++;
            }
        }
        return targetArray;
    }

    static int[] reversArray(int[] array) {
        for (int i = 0, j = array.length - 1; i < array.length / 2; i++, j--) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
        return array;
    }

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

    static boolean isContainCommonPart(char[] firstArray, char[] secondArray) {
        int index = 0;
        for (char elem : firstArray) {
            if (index != secondArray.length) {
                if (elem == secondArray[index]) {
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

}