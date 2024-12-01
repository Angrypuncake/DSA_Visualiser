package visualizer.controller;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import visualizer.CanvasConfig;
import visualizer.SortingStrategyRegistrar;
import visualizer.array.ArrayVisualizer;
import visualizer.array.BoxVisualizer;
import visualizer.array.HighlightStrategy.BorderHighlightStrategy;
import visualizer.array.HighlightStrategy.ColorHighlightStrategy;
import visualizer.array.HighlightStrategy.HighlightStrategy;
import visualizer.array.arraysorters.BubbleSort;
import visualizer.array.arraysorters.InsertionSort;
import visualizer.array.arraysorters.SelectionSort;
import visualizer.array.arraysorters.SortingStrategyRegistry;
import visualizer.array.arraysorters.comparators.IntegerComparator;
import visualizer.array.arraysorters.comparators.ReverseIntegerComparator;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class VisualizerController {
    @FXML
    private VBox root; // The root VBox from the FXML
    @FXML
    private Canvas canvas;

    @FXML
    private ComboBox<String> strategySelector;

    @FXML
    private Button randomizeButton;

    @FXML
    private Button sortButton;

    private ArrayVisualizer<Integer> visualizer;

    public void initialize() {

        canvas = new Canvas(CanvasConfig.DEFAULT_WIDTH, CanvasConfig.DEFAULT_HEIGHT);

        // Add the canvas to the VBox (at the top, before other elements)
        root.getChildren().add(0, canvas);
        // Initialize the visualizer

        Integer[] array = {5, 3, 8, 6, 2};

        SortingStrategyRegistrar.registerAll();
        // Set up the scene
        GraphicsContext gc = canvas.getGraphicsContext2D();
        HighlightStrategy hs = new ColorHighlightStrategy(Color.LIGHTBLUE, Color.DARKBLUE);
        visualizer = new BoxVisualizer<>(gc, array, hs);

        // Populate the strategy selector
        strategySelector.getItems().addAll("Bubble Sort", "Insertion Sort", "Selection Sort");
        strategySelector.setValue("Bubble Sort");
        strategySelector.setOnAction(event -> updateSortingStrategy(strategySelector.getValue()));

        // Set button actions
        randomizeButton.setOnAction(event -> randomizeArray());
        sortButton.setOnAction(event -> sortArray());

        // Draw initial state
        visualizer.draw();
    }

    private void randomizeArray() {
        // Randomize the array
        Integer[] randomizedArray = Arrays.copyOf(visualizer.getArray(), visualizer.getArray().length);
        Collections.shuffle(Arrays.asList(randomizedArray));
        visualizer.setArray(randomizedArray);
        visualizer.draw(); // Redraw the updated array
    }

    private void sortArray() {
        visualizer.sortAndVisualize(Integer::compareTo);
        visualizer.playAnimations();
    }

    private void updateSortingStrategy(String strategyName) {
        visualizer.setSortingStrategy(SortingStrategyRegistry.getStrategy(strategyName));
    }

    private static Integer[] toIntegerArray(int[] intArray) {
        return Arrays.stream(intArray).boxed().toArray(Integer[]::new);
    }
}
