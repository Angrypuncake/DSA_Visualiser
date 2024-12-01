package visualizer.array.arraysorters.comparators;

import java.util.Comparator;

public class IntegerComparator implements Comparator<Integer> {
    @Override
    public int compare(Integer a, Integer b) {
        return a - b; // Ascending order
    }
}

