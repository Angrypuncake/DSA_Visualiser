package visualizer;

import visualizer.array.arraysorters.*;

public class SortingStrategyRegistrar {
    public static void registerAll() {
        SortingStrategyRegistry.register("Bubble Sort", BubbleSort::new);
        SortingStrategyRegistry.register("Insertion Sort", InsertionSort::new);
        SortingStrategyRegistry.register("Selection Sort", SelectionSort::new);
    }
}
