package ua.nure.cs.util;

import ua.alevel.commons.util.sort.MathCollectionUtils;

import java.util.Arrays;
import java.util.Map;

public class ArrayUtils {

    private ArrayUtils() {
    }

    public static Map<String, Long> testSortMethods(int arrayLength, boolean isPrintResult) {
        Integer[] arrayForSelectionSort = MathCollectionUtils.generateRandomIntegerArray(arrayLength, 0, 65535);
        Integer[] arrayForHeapSort = Arrays.copyOf(arrayForSelectionSort, arrayForSelectionSort.length);
        Integer[] arrayForMergeSort = Arrays.copyOf(arrayForSelectionSort, arrayForSelectionSort.length);

        long startSelectionSortTime = System.currentTimeMillis();
        MathCollectionUtils.selectionSort(arrayForSelectionSort);
        long endSelectionSortTime = System.currentTimeMillis() - startSelectionSortTime;

        long startMergeSortTime = System.currentTimeMillis();
        MathCollectionUtils.mergeSort(arrayForMergeSort);
        long endMergeSortTime = System.currentTimeMillis() - startMergeSortTime;

        long startHeapSortTime = System.currentTimeMillis();
        MathCollectionUtils.heapSort(arrayForHeapSort);
        long endHeapSortTime = System.currentTimeMillis() - startHeapSortTime;

        if (isPrintResult) {
            System.out.println("Array length: " + arrayLength);
            System.out.println("Time by selection sorting: " + endSelectionSortTime);
            System.out.println("Time by merge sorting: " + endMergeSortTime);
            System.out.println("Time by heap sorting: " + endHeapSortTime);

            if (arrayLength <= 20) {
                System.out.println("Array sorted using selection sort:" + Arrays.toString(arrayForSelectionSort));
                System.out.println("Array sorted using merge sort:" + Arrays.toString(arrayForMergeSort));
                System.out.println("Array sorted using heap sort:" + Arrays.toString(arrayForHeapSort));
            } else {
                System.out.println("Array sorted using selection sort:" + Arrays.toString(Arrays.copyOfRange(arrayForSelectionSort, 0, 20)));
                System.out.println("Array sorted using merge sort:" + Arrays.toString(Arrays.copyOfRange(arrayForMergeSort, 0, 20)));
                System.out.println("Array sorted using heap sort:" + Arrays.toString(Arrays.copyOfRange(arrayForHeapSort, 0, 20)));
            }
        }
        return Map.of(
                "selection", endSelectionSortTime,
                "merge", endMergeSortTime,
                "heap", endHeapSortTime);
    }

    public static void testSearchMethods() {
        Integer[] arrayWithLength100 = MathCollectionUtils.generateRandomIntegerArray(100, 1, Integer.MAX_VALUE);
        Integer[] arrayWithLength1000 = MathCollectionUtils.generateRandomIntegerArray(1000, 1, Integer.MAX_VALUE);
        Integer[] arrayWithLength10000 = MathCollectionUtils.generateRandomIntegerArray(10000, 1, Integer.MAX_VALUE);
        Integer[] arrayWithLength100000 = MathCollectionUtils.generateRandomIntegerArray(100000, 1, Integer.MAX_VALUE);

        MathCollectionUtils.mergeSort(arrayWithLength100);
        MathCollectionUtils.mergeSort(arrayWithLength1000);
        MathCollectionUtils.mergeSort(arrayWithLength10000);
        MathCollectionUtils.mergeSort(arrayWithLength100000);

        testBinarySearch(arrayWithLength100);
        testBinarySearch(arrayWithLength1000);
        testBinarySearch(arrayWithLength10000);
        testBinarySearch(arrayWithLength100000);

        testInterpolationSearch(arrayWithLength100);
        testInterpolationSearch(arrayWithLength1000);
        testInterpolationSearch(arrayWithLength10000);
        testInterpolationSearch(arrayWithLength100000);

        System.out.println("Search by individual key; Index key = 10*variant -1 = 99 \n");

        int keyInArrayWithLength100 = arrayWithLength100[99];
        int keyInArrayWithLength1000 = arrayWithLength1000[99];
        int keyInArrayWithLength10000 = arrayWithLength10000[99];
        int keyInArrayWithLength100000 = arrayWithLength100000[99];

        long startBinarySearchForArrayWithLength100 = System.nanoTime();
        Map<String, Integer> resultBSForArrayWithLength100 = MathCollectionUtils.binarySearch(arrayWithLength100, keyInArrayWithLength100);
        long endBinarySearchForArrayWithLength100 = System.nanoTime() - startBinarySearchForArrayWithLength100;

        long startBinarySearchForArrayWithLength1000 = System.nanoTime();
        Map<String, Integer> resultBSForArrayWithLength1000 = MathCollectionUtils.binarySearch(arrayWithLength1000, keyInArrayWithLength1000);
        long endBinarySearchForArrayWithLength1000 = System.nanoTime() - startBinarySearchForArrayWithLength1000;

        long startBinarySearchForArrayWithLength10000 = System.nanoTime();
        Map<String, Integer> resultBSForArrayWithLength10000 = MathCollectionUtils.binarySearch(arrayWithLength10000, keyInArrayWithLength10000);
        long endBinarySearchForArrayWithLength10000 = System.nanoTime() - startBinarySearchForArrayWithLength10000;

        long startBinarySearchForArrayWithLength100000 = System.nanoTime();
        Map<String, Integer> resultBSForArrayWithLength100000 = MathCollectionUtils.binarySearch(arrayWithLength100000, keyInArrayWithLength100000);
        long endBinarySearchForArrayWithLength100000 = System.nanoTime() - startBinarySearchForArrayWithLength100000;

        long startInterpolationSearchForArrayWithLength100 = System.nanoTime();
        Map<String, Integer> resultISForArrayWithLength100 = MathCollectionUtils.interpolationSearch(arrayWithLength100, keyInArrayWithLength100);
        long endInterpolationSearchForArrayWithLength100 = System.nanoTime() - startInterpolationSearchForArrayWithLength100;

        long startInterpolationSearchForArrayWithLength1000 = System.nanoTime();
        Map<String, Integer> resultISForArrayWithLength1000 = MathCollectionUtils.interpolationSearch(arrayWithLength1000, keyInArrayWithLength1000);
        long endInterpolationSearchForArrayWithLength1000 = System.nanoTime() - startInterpolationSearchForArrayWithLength1000;

        long startInterpolationSearchForArrayWithLength10000 = System.nanoTime();
        Map<String, Integer> resultISForArrayWithLength10000 = MathCollectionUtils.interpolationSearch(arrayWithLength10000, keyInArrayWithLength10000);
        long endInterpolationSearchForArrayWithLength10000 = System.nanoTime() - startInterpolationSearchForArrayWithLength10000;

        long startInterpolationSearchForArrayWithLength100000 = System.nanoTime();
        Map<String, Integer> resultISForArrayWithLength100000 = MathCollectionUtils.interpolationSearch(arrayWithLength100000, keyInArrayWithLength100000);
        long endInterpolationSearchForArrayWithLength100000 = System.nanoTime() - startInterpolationSearchForArrayWithLength100000;

        System.out.println("Result binary search for array with length 100:\n" + resultBSForArrayWithLength100);
        System.out.println("Result interpolation search for array with length 100:\n" + resultISForArrayWithLength100);

        System.out.println("Result binary search for array with length 1000:\n" + resultBSForArrayWithLength1000);
        System.out.println("Result interpolation search for array with length 1000:\n" + resultISForArrayWithLength1000);

        System.out.println("Result binary search for array with length 10000:\n" + resultBSForArrayWithLength10000);
        System.out.println("Result interpolation search for array with length 10000:\n" + resultISForArrayWithLength10000);

        System.out.println("Result binary search for array with length 100000:\n" + resultBSForArrayWithLength100000);
        System.out.println("Result interpolation search for array with length 100000:\n" + resultISForArrayWithLength100000);

        System.out.println("Search time for binary search for array with length 100:" + endBinarySearchForArrayWithLength100);
        System.out.println("Search time for interpolation search for array with length 100:" + endInterpolationSearchForArrayWithLength100);

        System.out.println("Search time for binary search for array with length 1000:" + endBinarySearchForArrayWithLength1000);
        System.out.println("Search time for interpolation search for array with length 1000:" + endInterpolationSearchForArrayWithLength1000);

        System.out.println("Search time for binary search for array with length 10000:" + endBinarySearchForArrayWithLength10000);
        System.out.println("Search time for interpolation search for array with length 10000:" + endInterpolationSearchForArrayWithLength10000);

        System.out.println("Search time for binary search for array with length 100000:" + endBinarySearchForArrayWithLength100000);
        System.out.println("Search time for interpolation search for array with length 100000:" + endInterpolationSearchForArrayWithLength100000);
    }

    private static void testBinarySearch(Integer[] array) {
        int length = array.length;
        Map<String, Integer> resultBS;
        int countOfIterations = 0;
        int numberOfComparisonsWithKey = 0;
        long endBinarySearch = 0;
        for (Integer integer : array) {
            long startBinarySearch = System.nanoTime();
            resultBS = MathCollectionUtils.binarySearch(array, integer);
            endBinarySearch += System.nanoTime() - startBinarySearch;
            countOfIterations += resultBS.get("countOfIterations");
            numberOfComparisonsWithKey += resultBS.get("numberOfComparisonsWithKey");
        }
        System.out.println("******* Binary search *******");
        System.out.println("Array with length: " + length);
        System.out.println("Average count of iteration: " + countOfIterations / length);
        System.out.println("Average number of comparisons with key: " + numberOfComparisonsWithKey / length);
        System.out.println("Average search time: " + endBinarySearch / length + "\n");
    }

    private static void testInterpolationSearch(Integer[] array) {
        int length = array.length;
        Map<String, Integer> resultIS;
        int countOfIterations = 0;
        int numberOfComparisonsWithKey = 0;
        long endBinarySearch = 0;
        for (Integer integer : array) {
            long startBinarySearch = System.nanoTime();
            resultIS = MathCollectionUtils.interpolationSearch(array, integer);
            endBinarySearch += System.nanoTime() - startBinarySearch;
            countOfIterations += resultIS.get("countOfIterations");
            numberOfComparisonsWithKey += resultIS.get("numberOfComparisonsWithKey");
        }
        System.out.println("******* Interpolation search *******");
        System.out.println("Array with length: " + length);
        System.out.println("Average count of iteration: " + countOfIterations / length);
        System.out.println("Average number of comparisons with key: " + numberOfComparisonsWithKey / length);
        System.out.println("Average search time: " + endBinarySearch / length + "\n");
    }
}
