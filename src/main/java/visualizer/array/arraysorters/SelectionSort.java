package visualizer.array.arraysorters;

import java.util.Comparator;

public class SelectionSort implements ArraySortingStrategy {

    @Override
    public <T> void sort(T[] array, AnimationCallback callback, Comparator<T> comparator) {
        System.out.println("Selection sort");
        // The array is cloned to avoid modifying the original array
        // Selection sort works by finding the minimum element in the unsorted portion of the array
        // and swapping it with the first element of the unsorted portion
        T[] workingCopy = array.clone();

        int n = workingCopy.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++){
                if (comparator.compare(workingCopy[j], workingCopy[minIndex]) < 0) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                System.out.println("Swapping " + workingCopy[i] + " and " + workingCopy[minIndex]);
                // Notify the visualizer about the swap
                T temp = workingCopy[i];
                workingCopy[i] = workingCopy[minIndex];
                workingCopy[minIndex] = temp;
                callback.onSwap(i, minIndex);
            }
        }
    }
}
