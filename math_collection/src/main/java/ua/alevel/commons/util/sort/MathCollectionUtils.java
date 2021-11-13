package ua.alevel.commons.util.sort;

public class MathCollectionUtils {

    private MathCollectionUtils() {
    }

    public static <N extends Number & Comparable<N>> void mergeSort(N[] numbers) {
        mergeSortNumbers(numbers);
    }

    public static <N extends Number & Comparable<N>> void heapSort(N[] numbers, boolean isAscendingOrder) {
        int length = numbers.length;
        if (length == 0) {
            return;
        }
        for (int i = length / 2 - 1; i >= 0; i--) {
            heapify(numbers, length, i, isAscendingOrder);
        }
        for (int i = length - 1; i >= 0; i--) {
            N tmp = numbers[0];
            numbers[0] = numbers[i];
            numbers[i] = tmp;
            heapify(numbers, i, 0, isAscendingOrder);
        }
    }

    public static <N extends Number & Comparable<N>> void selectionSort(N[] numbers) {
        for (int arrayIndex = 0; arrayIndex < numbers.length - 1; arrayIndex++) {
            int minElementIndex = arrayIndex;
            for (int sortArrayIndex = arrayIndex + 1; sortArrayIndex < numbers.length; sortArrayIndex++) {
                if (numbers[minElementIndex].compareTo(numbers[sortArrayIndex]) >= 0) {
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

    private static <N extends Number & Comparable<N>> void mergeSortNumbers(N[] mathArray) {
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
        mergeSortNumbers(leftArray);
        mergeSortNumbers(rightArray);
        merge(mathArray, leftArray, rightArray, middleIndex, length - middleIndex);
    }

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

    private static <N extends Number & Comparable<N>> void heapify(N[] numbers, int length, int parentIndex, boolean isAscendingOrder) {
        int leftChildIndex = 2 * parentIndex + 1;
        int rightChildIndex = 2 * parentIndex + 2;
        int largest = parentIndex;
        if (isAscendingOrder) {
            if ((leftChildIndex < length) && (numbers[leftChildIndex].compareTo(numbers[largest]) > 0)) {
                largest = leftChildIndex;
            }
            if ((rightChildIndex < length) && (numbers[rightChildIndex].compareTo(numbers[largest]) > 0)) {
                largest = rightChildIndex;
            }
        } else {
            if ((leftChildIndex < length) && (numbers[leftChildIndex].compareTo(numbers[largest]) < 0)) {
                largest = leftChildIndex;
            }
            if ((rightChildIndex < length) && (numbers[rightChildIndex].compareTo(numbers[largest]) < 0)) {
                largest = rightChildIndex;
            }
        }
        if (largest != parentIndex) {
            N tmp = numbers[parentIndex];
            numbers[parentIndex] = numbers[largest];
            numbers[largest] = tmp;
            heapify(numbers, length, largest, isAscendingOrder);
        }
    }
}
