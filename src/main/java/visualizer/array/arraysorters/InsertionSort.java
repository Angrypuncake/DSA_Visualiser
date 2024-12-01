package visualizer.array.arraysorters;

import java.util.Comparator;

public class InsertionSort implements ArraySortingStrategy {
    @Override
    public <T> void sort(T[] array, AnimationCallback callback, Comparator<T> comparator) {
        System.out.println("Insertion sort");
        // The array is cloned to avoid modifying the original array
        // Insertion sort works by repeatedly inserting an element from the unsorted portion of the array
        // into its correct position in the sorted portion
        T[] workingCopy = array.clone();
        for (int i = 1; i < workingCopy.length; i++) {
            T key = workingCopy[i];
            int j = i - 1;
            while (j >= 0 && comparator.compare(workingCopy[j], key) > 0) {
                workingCopy[j + 1] = workingCopy[j];
                callback.onSwap(j, j + 1);
                j--;
            }
            workingCopy[j + 1] = key;
        }
    }
}
