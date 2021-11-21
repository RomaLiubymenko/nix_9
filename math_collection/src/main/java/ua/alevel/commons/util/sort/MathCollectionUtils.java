package ua.alevel.commons.util.sort;

import java.util.Map;
import java.util.Random;

public class MathCollectionUtils {

    private MathCollectionUtils() {
    }

    public static Integer[] generateRandomIntegerArray(int length, int minElement, int maxElement) {
        Random random = new Random();
        Integer[] randomArray = new Integer[length];
        for (int i = 0; i < length; i++) {
            randomArray[i] = random.nextInt((maxElement - minElement) + minElement);
        }
        return randomArray;
    }

    public static Integer[] generateIntegerArray(int length) {
        Integer[] array = new Integer[length];
        for (int i = 0; i < length; i++) {
            array[i] = i;
        }
        return array;
    }

    public static Map<String, Integer> binarySearch(Integer[] array, int keyToSearch) {
        int countOfIterations = 0;
        int numberOfComparisonsWithKey = 0;
        int index = -1;
        int lowerIndex = 0;
        int higherIndex = array.length - 1;
        while (lowerIndex <= higherIndex) {
            countOfIterations++;
            int middleIndex = lowerIndex + ((higherIndex - lowerIndex) / 2);
            if (array[middleIndex] < keyToSearch) {
                numberOfComparisonsWithKey++;
                lowerIndex = middleIndex + 1;
            } else if (array[middleIndex] > keyToSearch) {
                numberOfComparisonsWithKey++;
                higherIndex = middleIndex - 1;
            } else if (array[middleIndex] == keyToSearch) {
                numberOfComparisonsWithKey++;
                index = middleIndex;
                break;
            }
        }
        return Map.of(
                "index", index,
                "countOfIterations", countOfIterations,
                "numberOfComparisonsWithKey", numberOfComparisonsWithKey
        );
    }

    public static Map<String, Integer> interpolationSearch(Integer[] array, int keyToSearch) {
        int countOfIterations = 0;
        int numberOfComparisonsWithKey = 0;
        int index = -1;
        int lowerIndex = 0;
        int higherIndex = array.length - 1;
        while (keyToSearch >= array[lowerIndex] && keyToSearch <= array[higherIndex] && lowerIndex <= higherIndex) {
            countOfIterations++;
            if (higherIndex == lowerIndex) {
                numberOfComparisonsWithKey++;
                if (array[lowerIndex] == keyToSearch) {
                    index = lowerIndex;
                } else {
                    index = -1;
                }
                break;
            }
            int probeIndex = lowerIndex + (((higherIndex - lowerIndex) /
                    (array[higherIndex] - array[lowerIndex])) *
                    (keyToSearch - array[lowerIndex]));
            if (array[probeIndex] == keyToSearch) {
                numberOfComparisonsWithKey++;
                index = probeIndex;
                break;
            } else if (array[probeIndex] < keyToSearch) {
                numberOfComparisonsWithKey++;
                lowerIndex = probeIndex + 1;
            } else {
                numberOfComparisonsWithKey++;
                higherIndex = probeIndex - 1;
            }
        }
        return Map.of(
                "index", index,
                "countOfIterations", countOfIterations,
                "numberOfComparisonsWithKey", numberOfComparisonsWithKey
        );
    }

    public static <N extends Number & Comparable<N>> void mergeSort(N[] mathArray) {
        int length = mathArray.length;
        if (length < 2) {
            return;
        }
        int middleIndex = length / 2;
        N[] leftArray = (N[]) new Number[middleIndex];
        N[] rightArray = (N[]) new Number[length - middleIndex];
        System.arraycopy(mathArray, 0, leftArray, 0, middleIndex);
        if (length - middleIndex >= 0)
            System.arraycopy(mathArray, middleIndex, rightArray, 0, length - middleIndex);
        mergeSort(leftArray);
        mergeSort(rightArray);
        merge(mathArray, leftArray, rightArray, middleIndex, length - middleIndex);
    }

    public static <N extends Number & Comparable<N>> void heapSort(N[] numbers) {
        int length = numbers.length;
        if (length == 0) {
            return;
        }
        for (int i = length / 2 - 1; i >= 0; i--) {
            heapify(numbers, length, i);
        }
        for (int i = length - 1; i >= 0; i--) {
            N tmp = numbers[0];
            numbers[0] = numbers[i];
            numbers[i] = tmp;
            heapify(numbers, i, 0);
        }
    }

    public static <N extends Number & Comparable<N>> void selectionSort(N[] numbers) {
        for (int arrayIndex = 0; arrayIndex < numbers.length - 1; arrayIndex++) {
            int minElementIndex = arrayIndex;
            for (int sortArrayIndex = arrayIndex + 1; sortArrayIndex < numbers.length; sortArrayIndex++) {
                if (numbers[minElementIndex].compareTo(numbers[sortArrayIndex]) > 0) {
                    minElementIndex = sortArrayIndex;
                }
            }
            if (minElementIndex != arrayIndex) {
                N temp = numbers[arrayIndex];
                numbers[arrayIndex] = numbers[minElementIndex];
                numbers[minElementIndex] = temp;
            }
        }
    }

    // helper methods

    private static <N extends Number & Comparable<N>> void merge(N[] mathArray, N[] leftMathArray, N[] rightMathArray, int leftIndex, int rightIndex) {
        int leftSubarrayIndex = 0;
        int rightSubarrayIndex = 0;
        int resultingIndex = 0;
        while (leftSubarrayIndex < leftIndex && rightSubarrayIndex < rightIndex) {
            int compareResult = leftMathArray[leftSubarrayIndex].compareTo(rightMathArray[rightSubarrayIndex]);
            if (compareResult <= 0) {
                mathArray[resultingIndex++] = leftMathArray[leftSubarrayIndex++];
            } else {
                mathArray[resultingIndex++] = rightMathArray[rightSubarrayIndex++];
            }
        }
        while (leftSubarrayIndex < leftIndex) {
            mathArray[resultingIndex++] = leftMathArray[leftSubarrayIndex++];
        }
        while (rightSubarrayIndex < rightIndex) {
            mathArray[resultingIndex++] = rightMathArray[rightSubarrayIndex++];
        }
    }

    private static <N extends Number & Comparable<N>> void heapify(N[] numbers, int length, int parentIndex) {
        int leftChildIndex = 2 * parentIndex + 1;
        int rightChildIndex = 2 * parentIndex + 2;
        int largest = parentIndex;
        if ((leftChildIndex < length) && (numbers[leftChildIndex].compareTo(numbers[largest]) > 0)) {
            largest = leftChildIndex;
        }
        if ((rightChildIndex < length) && (numbers[rightChildIndex].compareTo(numbers[largest]) > 0)) {
            largest = rightChildIndex;
        }
        if (largest != parentIndex) {
            N tmp = numbers[parentIndex];
            numbers[parentIndex] = numbers[largest];
            numbers[largest] = tmp;
            heapify(numbers, length, largest);
        }
    }
}
