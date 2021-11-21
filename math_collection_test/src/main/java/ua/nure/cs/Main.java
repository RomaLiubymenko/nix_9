package ua.nure.cs;

import ua.nure.cs.sort.SortFrame;
import ua.nure.cs.util.ArrayUtils;

public class Main {

    public static void main(String[] args) {
        SortFrame sortFrame = new SortFrame("Array Sorting Testing");
        sortFrame.showSortFrame();
        ArrayUtils.testSearchMethods();
    }
}
