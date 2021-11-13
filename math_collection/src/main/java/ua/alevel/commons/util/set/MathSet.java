package ua.alevel.commons.util.set;

import ua.alevel.commons.util.MathCollection;
import ua.alevel.commons.util.sort.MathCollectionUtils;

import java.util.Arrays;
import java.util.Objects;


public class MathSet<N extends Number & Comparable<N>> implements MathCollection<N> {

    private static final int DEFAULT_SIZE = 10;
    private int size;
    private int collectionSize;
    private Number[] numbers;

    public MathSet() {
        computeSizeAndCreateNumbers(0);
    }

    public MathSet(int capacity) {
        computeSizeAndCreateNumbers(capacity);
    }

    public MathSet(N[] numbers) {
        computeSizeAndCreateNumbers(numbers.length);
        addToNumbers(numbers);
    }

    public MathSet(N[]... numberArrays) {
        int currentSize = 0;
        for (N[] numberArray : numberArrays) {
            currentSize += numberArray.length;
        }
        computeSizeAndCreateNumbers(currentSize);
        for (N[] numberArray : numberArrays) {
            addToNumbers(numberArray);
        }
    }

    public MathSet(MathSet<N> numbers) {
        computeSizeAndCreateNumbers(numbers.toArray().length);
        addToNumbers(numbers.toArray());
    }

    public MathSet(MathSet<N>... numberArrays) {
        int currentSize = 0;
        for (MathSet<N> numberArray : numberArrays) {
            currentSize += numberArray.toArray().length;
        }
        computeSizeAndCreateNumbers(currentSize);
        for (MathSet<N> numberArray : numberArrays) {
            addToNumbers(numberArray.toArray());
        }
    }

    @Override
    public void add(N number) {
        if (isNotDuplicate(number)) {
            resize();
            numbers[size] = number;
            size++;
        }
    }

    @Override
    public void add(N... numbers) {
        for (N number : numbers) {
            add(number);
        }
    }

    @Override
    public void join(MathCollection<N> mathCollection) {
        add(mathCollection.toArray());
    }

    @Override
    public void join(MathCollection<N>... mathCollections) {
        for (MathCollection<N> mathCollection : mathCollections) {
            join(mathCollection);
        }
    }

    @Override
    public void intersection(MathCollection<N> mathCollection) {
        MathSet<N> intersectionResult = new MathSet<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < mathCollection.getSize(); j++) {
                if (Objects.equals(this.get(i), mathCollection.get(j))) {
                    intersectionResult.add(this.get(i));
                }
            }
        }
        clear();
        addToNumbers(intersectionResult.toArray());
    }

    @Override
    public void intersection(MathCollection<N>... mathCollections) {
        for (MathCollection<N> mathCollection : mathCollections) {
            intersection(mathCollection);
        }
    }

    @Override
    public void sortDesc() {
        N[] array = this.toArray();
        MathCollectionUtils.heapSort(array, false);
        clear();
        addToNumbers(array);
    }

    @Override
    public void sortDesc(int firstIndex, int lastIndex) {
        N[] array = this.toArray(firstIndex, lastIndex);
        MathCollectionUtils.heapSort(array, false);
        for (int index = firstIndex, arrayIndex = 0; index <= lastIndex; index++, arrayIndex++) {
            set(array[arrayIndex], index);
        }
    }

    @Override
    public void sortDesc(N number) {
        int index = indexOf(number);
        if (index >= 0) {
            sortDesc(index, size - 1);
        }
    }

    @Override
    public void sortAsc() {
        N[] array = this.toArray();
        MathCollectionUtils.heapSort(array, true);
        clear();
        addToNumbers(array);
    }

    @Override
    public void sortAsc(int firstIndex, int lastIndex) {
        N[] array = this.toArray(firstIndex, lastIndex);
        MathCollectionUtils.heapSort(array, true);
        for (int index = firstIndex, arrayIndex = 0; index <= lastIndex; index++, arrayIndex++) {
            set(array[arrayIndex], index);
        }
    }

    @Override
    public void sortAsc(N number) {
        int index = indexOf(number);
        if (index >= 0) {
            sortAsc(index, size - 1);
        }
    }

    @Override
    public N get(int index) {
        if (isIndexInBounds(index)) {
            return (N) numbers[index];
        } else {
            throw new IndexOutOfBoundsException(index);
        }
    }

    @Override
    public N getMax() {
        N[] array = this.toArray();
        MathCollectionUtils.heapSort(array, false);
        return array[0];
    }

    @Override
    public N getMin() {
        N[] array = this.toArray();
        MathCollectionUtils.heapSort(array, true);
        return array[0];
    }

    @Override
    public N getAverage() {
        Number average = 0;
        for (int index = 0; index < size; index++) {
            average = average.doubleValue() + numbers[index].doubleValue();
        }
        Number result = average.doubleValue() / 2;
        return (N) result;
    }

    @Override
    public N getMedian() {
        Number median = -1;
        if (size > 0) {
            N[] array = this.toArray();
            MathCollectionUtils.heapSort(array, true);
            if (size % 2 == 0) {
                Number sumOfMiddleElements = (array[size / 2].doubleValue() + array[size / 2 - 1].doubleValue()) / 2;
                median = sumOfMiddleElements;
            } else {
                median = array[array.length / 2];
            }
        }
        return (N) median;
    }

    @Override
    public N[] toArray() {
        return (N[]) Arrays.copyOf(numbers, size);
    }

    @Override
    public N[] toArray(int firstIndex, int lastIndex) {
        if (isIndexInBounds(firstIndex, lastIndex)) {
            return (N[]) Arrays.copyOfRange(numbers, firstIndex, lastIndex + 1);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public MathCollection<N> cut(int firstIndex, int lastIndex) {
        return new MathSet<>(toArray(firstIndex, lastIndex));
    }

    @Override
    public void clear() {
        computeSizeAndCreateNumbers(0);
        size = 0;
    }

    @Override
    public void clear(N[] numbers) {
        for (N number : numbers) {
            int index = indexOf(number);
            if (index >= 0) {
                remove(index);
            }
        }
    }

    @Override
    public void remove(int index) {
        System.arraycopy(this.numbers, index + 1, this.numbers, index, size - index - 1);
        size--;
        this.numbers[size] = null;
    }

    @Override
    public void set(N number, int index) {
        if (isIndexInBounds(index)) {
            numbers[index] = number;
        } else {
            throw new IndexOutOfBoundsException(index);
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        return "MathSet{ " + Arrays.toString(Arrays.copyOfRange(numbers, 0, size)) + " }";
    }

    // helper methods

    private boolean isNotDuplicate(N number) {
        for (Number num : numbers) {
            if (Objects.equals(num, number)) return false;
        }
        return true;
    }

    private void resize() {
        if (size >= collectionSize) {
            Number[] newNumbers = new Number[size * 3 / 2 + 1];
            System.arraycopy(numbers, 0, newNumbers, 0, size);
            numbers = newNumbers;
        }
        if (size >= DEFAULT_SIZE && size < collectionSize / 4) {
            Number[] newNumbers = new Number[size * 3 / 2 + 1];
            System.arraycopy(numbers, 0, newNumbers, 0, size);
            numbers = newNumbers;
        }
    }

    private void computeSizeAndCreateNumbers(int capacity) {
        numbers = new Number[DEFAULT_SIZE + capacity];
        this.collectionSize = DEFAULT_SIZE + capacity;
    }

    private void addToNumbers(N[] numbers) {
        for (N number : numbers) {
            add(number);
        }
    }

    private boolean isIndexInBounds(int index) {
        return index >= 0 && index < size;
    }

    private boolean isIndexInBounds(int firstIndex, int lastIndex) {
        if (firstIndex < 0 || firstIndex >= size) {
            return false;
        }
        if (lastIndex < 0 || lastIndex >= size) {
            return false;
        }
        return firstIndex <= lastIndex;
    }

    private int indexOf(Number number) {
        for (int i = 0; i < size; i++) {
            if (numbers[i].equals(number)) {
                return i;
            }
        }
        return -1;
    }
}
