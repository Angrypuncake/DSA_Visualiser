package visualizer.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import visualizer.CanvasConfig;
import visualizer.SortingStrategyRegistrar;
import visualizer.array.ArrayVisualizer;
import visualizer.array.BoxVisualizer;
import visualizer.array.HighlightStrategy.ColorHighlightStrategy;
import visualizer.array.HighlightStrategy.HighlightStrategy;
import visualizer.array.arraysorters.SortingStrategyRegistry;

import java.util.Arrays;
import java.util.Collections;

public class VisualizerController {
    @FXML
    private VBox root; // The root VBox from the FXML
    @FXML
    private Pane visualizerPane; // Pane for rendering boxes

    @FXML
    private ComboBox<String> strategySelector;

    @FXML
    private Button randomizeButton;

    @FXML
    private Button sortButton;

    private ArrayVisualizer<Integer> visualizer;

    public void initialize() {

        visualizerPane.setPrefWidth(CanvasConfig.DEFAULT_WIDTH);
        visualizerPane.setPrefHeight(CanvasConfig.DEFAULT_HEIGHT);


        Integer[] array = {5, 3, 8, 6, 2};

        SortingStrategyRegistrar.registerAll();
        // Set up the scene

        HighlightStrategy hs = new ColorHighlightStrategy(Color.LIGHTBLUE, Color.DARKBLUE);
        visualizer = new BoxVisualizer<>(visualizerPane, array, hs);

        // Populate the strategy selector
        strategySelector.getItems().addAll("Bubble Sort", "Insertion Sort", "Selection Sort");
        strategySelector.setValue("Bubble Sort");
        strategySelector.setOnAction(event -> updateSortingStrategy(strategySelector.getValue()));

        // Set button actions
        randomizeButton.setOnAction(event -> randomizeArray());
        sortButton.setOnAction(event -> sortArray());

        // Draw initial state
        visualizer.draw();

        // Debug: Print children in the Pane
        System.out.println("Children in visualizerPane after initialization: ");
        visualizerPane.getChildren().forEach(child -> {
            System.out.println("Node: " + child.getClass().getSimpleName());
        });
        System.out.println("VisualizerController Pane: " + visualizerPane);
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
