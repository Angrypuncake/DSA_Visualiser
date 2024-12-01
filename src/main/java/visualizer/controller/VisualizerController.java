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
    public ComboBox speedSelector;
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

    double speedFactor = 1;

    public void initialize() {

        visualizerPane.setPrefWidth(CanvasConfig.DEFAULT_WIDTH);
        visualizerPane.setPrefHeight(CanvasConfig.DEFAULT_HEIGHT);


        Integer[] array = {
                45, 23, 89, 76, 12, 34, 56, 78, 90, 67, 3, 15, 8, 6, 2, 98, 65, 54, 43, 32,
                21, 11, 9, 10, 88, 77, 66, 55, 44, 33, 22, 19, 29, 39, 49, 59, 69, 79, 85, 92
        };

        CanvasConfig.setBoxSize((CanvasConfig.DEFAULT_WIDTH / array.length) * 0.9);


        SortingStrategyRegistrar.registerAll();
        // Set up the scene

        HighlightStrategy hs = new ColorHighlightStrategy(Color.LIGHTBLUE, Color.DARKBLUE);
        visualizer = new BoxVisualizer<>(visualizerPane, array, hs);

        // Populate the strategy selector
        strategySelector.getItems().addAll("Bubble Sort", "Insertion Sort", "Selection Sort");
        strategySelector.setValue("Bubble Sort");
        strategySelector.setOnAction(event -> updateSortingStrategy(strategySelector.getValue()));

        // Populator speed selector

        speedSelector.getItems().addAll("0.5x", "1x", "1.5x", "2x", "5x", "10x");
        speedSelector.setValue("1x");
        speedSelector.setOnAction(event -> updateSpeedFactor());
        

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

    private void updateSpeedFactor() {
        String selectedSpeed = (String) speedSelector.getValue();
        switch (selectedSpeed) {
            case "0.5x":
                speedFactor = 0.5;
                break;
            case "1x":
                speedFactor = 1.0;
                break;
            case "1.5x":
                speedFactor = 1.5;
                break;
            case "2x":
                speedFactor = 2.0;
                break;
            case "5x":
                speedFactor = 2.0;
                break;
            case "10x":
                speedFactor = 2.0;
                break;
            default:
                speedFactor = 1.0;
        }
        System.out.println("Speed factor updated to: " + speedFactor);
        visualizer.setSpeedFactor(speedFactor); // Update the animation manager
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
