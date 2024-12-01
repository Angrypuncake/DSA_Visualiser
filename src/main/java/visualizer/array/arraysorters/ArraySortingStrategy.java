package visualizer.array.arraysorters;

import java.util.Comparator;

public interface ArraySortingStrategy {
    <T> void sort(T[] array, AnimationCallback callback, Comparator<T> comparator);

    interface AnimationCallback {
        void onSwap(int i, int j);
    }
}
