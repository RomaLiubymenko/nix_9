package ua.com.alevel.util;

public interface MathCollection<N extends Number> {

    void set(N number, int index);

    void remove(int index);

    boolean isEmpty();

    int getSize();

    void add(N number);

    void add(N... numbers);

    void join(MathCollection<N> mathCollection);

    void join(MathCollection<N>... mathCollections);

    void intersection(MathCollection<N> mathCollection);

    void intersection(MathCollection<N>... mathCollections);

    void sortDesc();

    void sortDesc(int firstIndex, int lastIndex);

    void sortDesc(N number);

    void sortAsc();

    void sortAsc(int firstIndex, int lastIndex);

    void sortAsc(N number);

    N get(int index);

    N getMax();

    N getMin();

    N getAverage();

    N getMedian();

    N[] toArray();

    N[] toArray(int firstIndex, int lastIndex);

    MathCollection<N> cut(int firstIndex, int lastIndex);

    void clear();

    void clear(N[] numbers);
}
