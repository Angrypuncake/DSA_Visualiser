package visualizer.array.arraysorters;

import java.util.Comparator;

public class BubbleSort implements ArraySortingStrategy {

    @Override
    public <T> void sort(T[] array, AnimationCallback callback, Comparator<T> comparator) {
        T[] workingCopy = array.clone();
        for (int i = 0; i < workingCopy.length; i++) {
            System.out.print(workingCopy[i] + " ");
        }
        System.out.println("Bubble sort");
        int n = workingCopy.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (comparator.compare(workingCopy[j], workingCopy[j + 1]) > 0) {
                    System.out.println("Swapping " + workingCopy[j] + " and " + workingCopy[j + 1]);
                    // Notify the visualizer about the swap
                    T temp = workingCopy[j];
                    workingCopy[j] = workingCopy[j + 1];
                    workingCopy[j + 1] = temp;
                    callback.onSwap(j, j + 1);

                    swapped = true;
                }
            }
            // Exit early if no swaps were made
            if (!swapped) break;
        }
    }
}
