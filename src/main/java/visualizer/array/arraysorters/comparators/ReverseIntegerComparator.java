package visualizer.array.arraysorters.comparators;

import java.util.Comparator;

public class ReverseIntegerComparator implements Comparator<Integer> {
    @Override
    public int compare(Integer a, Integer b) {
        return b - a; // Descending order
    }
}
