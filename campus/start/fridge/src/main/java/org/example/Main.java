package org.example;

public class Main {
    public static void main(String[] args) {
    /*    int[][] switches = {
                {1, 1, 1},
                {0, 1, 1},
                {0, 1, 1}};
        Fridge fridge = new Fridge(switches);
        System.out.println(fridge.fastSwitch());*/
        int[] switches = {
                1, 0, 1,
                0, 0, 0,
                1, 0, 1
        };
        FridgeArray fridgeArray = new FridgeArray(switches);
        System.out.println(fridgeArray.switchOne());

    }
}