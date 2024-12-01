package visualizer.array.arraysorters;

import java.util.HashMap;
import java.util.Map;

public class SortingStrategyRegistry {
    private static final Map<String, SortingStrategyFactory> registry = new HashMap<>();

    public interface SortingStrategyFactory {
        ArraySortingStrategy create();
    }

    public static void register(String name, SortingStrategyFactory factory) {
        registry.put(name, factory);
    }

    public static ArraySortingStrategy getStrategy(String name) {
        SortingStrategyFactory factory = registry.get(name);
        if (factory == null) {
            throw new IllegalArgumentException("No sorting strategy registered with name: " + name);
        }
        return factory.create();
    }
}
