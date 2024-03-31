package org.example;

public class Fridge {
    private int ind1;
    private int ind2;
    private final int[][] switches;

    public Fridge(int[][] switches) {
        this.switches = switches;
    }

    public boolean fastSwitch() {
        return switchTwoSwitches();
    }

    private boolean checkSwitchesFridge(int[][] sw) {
        for (int[] a : sw) {
            for (int s : a) {
                if (s == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean switchOneSwitch(int[][] switches) {
        int[][] copySwitch;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                copySwitch = switchLineAndColumn(i, j, switches);
                if (checkSwitchesFridge(copySwitch)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean switchTwoSwitches() {
        int[][] copySwitch;
        int[][] copySwitch2;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                copySwitch = switchLineAndColumn(i, j, switches);
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        if (k <= i && l <= j) {
                            continue;
                        }
                        copySwitch2 = switchLineAndColumn(k, l, copySwitch);
                        if (checkSwitchesFridge(copySwitch2)){
                            return true;
                        }

                    }

                }
            }

        }
        return false;
    }


        private int[][] switchLineAndColumn ( int ind1, int ind2, int[][] switches){
            int[][] copySwitches = new int[switches.length][];
            for (int i = 0; i < switches.length; i++) {
                int[] matrix = switches[i];
                int length = matrix.length;
                copySwitches[i] = new int[length];
                System.arraycopy(matrix, 0, copySwitches[i], 0, length);
            }
            for (int i = 0; i < 3; i++) {
                if (copySwitches[ind1][i] == 0) {
                    copySwitches[ind1][i] = 1;
                } else {
                    copySwitches[ind1][i] = 0;
                }
            }
            for (int i = 0; i < 3; i++) {
                if (ind1 == i) {
                    continue;
                }
                if (copySwitches[i][ind2] == 0) {
                    copySwitches[i][ind2] = 1;
                } else {
                    copySwitches[i][ind2] = 0;
                }
            }
            return copySwitches;
        }

    }
