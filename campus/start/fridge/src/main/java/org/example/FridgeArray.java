package org.example;

public class FridgeArray {
    private int[] switches;

    public FridgeArray(int[] switches) {
        this.switches = switches;
    }

    public boolean switchOne() {
        for (int i = 0; i < switches.length; i++) {
            int[] copyArraySwitches = switchRowAndColumn(i);
            if (checkFridge(copyArraySwitches)) {
                return true;
            }
        }
        return false;
    }

    public void switchTwo() {

    }

    private int[] switchRowAndColumn(int sw) {
        int[] copyArraySwitches = new int[switches.length];
        System.arraycopy(switches, 0, copyArraySwitches, 0, switches.length);
        switchRow(sw, copyArraySwitches);
        switchColumn(sw, copyArraySwitches);
        /*for (int i = sw, j = sw; i < 3; i++, j += 3) {
            if (sw % 3 == 0) {
                copyArraySwitches[i] = copyArraySwitches[i] == 0 ? 1 : 0;
                if (j != i) {
                    copyArraySwitches[j] = copyArraySwitches[j] == 0 ? 1 : 0;
                }
            } else if (sw % 3 == 1) {
                copyArraySwitches[i - 1] = copyArraySwitches[i - 1] == 0 ? 1 : 0;
                copyArraySwitches[j + 3] = copyArraySwitches[j + 3] == 0 ? 1 : 0;

            } else if (sw % 3 == 2) {
                copyArraySwitches[i - 2] = copyArraySwitches[i - 2] == 0 ? 1 : 0;
                copyArraySwitches[j - 6] = copyArraySwitches[j - 6] == 0 ? 1 : 0;

            }
        }*/
        return copyArraySwitches;
    }

    private void switchRow(int sw, int[] copyArraySwitches) {
        int copySw = sw;
        for (int i = 0; i < 3; i++) {
            if (sw % 3 == 0) {
                copyArraySwitches[copySw] = copyArraySwitches[copySw] == 0 ? 1 : 0;
                copySw++;
            } else if (sw % 3 == 1) {
                copyArraySwitches[copySw - 1] = copyArraySwitches[copySw - 1] == 0 ? 1 : 0;
                copySw++;
            } else if (sw % 3 == 2) {
                copyArraySwitches[copySw - 2] = copyArraySwitches[copySw - 2] == 0 ? 1 : 0;
                copySw++;
            }
        }
    }

    private void switchColumn(int sw, int[] copyArraySwitches) {
        int copySw = sw;
        for (int i = 0; i < 3; i++) {
            if (copySw == sw) {
                copySw +=3;
                continue;
            }
            if (sw <= 2) {
                copyArraySwitches[copySw] = copyArraySwitches[copySw] == 0 ? 1 : 0;
                copySw += 3;
            } else if (sw <= 5) {
                copyArraySwitches[copySw - 6] = copyArraySwitches[copySw - 6] == 0 ? 1 : 0;
                copySw += 3;
            } else {
                copyArraySwitches[copySw - 9] = copyArraySwitches[copySw - 9] == 0 ? 1 : 0;
                copySw += 3;
            }

        }
    }


    private boolean checkFridge(int[] switches) {
        for (int sw : switches) {
            if (sw == 0) {
                return false;
            }
        }
        return true;
    }
}
