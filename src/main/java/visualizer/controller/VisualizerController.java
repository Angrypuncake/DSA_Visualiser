package visualizer.controller;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import visualizer.CanvasConfig;
import visualizer.array.ArrayVisualizer;
import visualizer.array.BoxVisualizer;
import visualizer.array.arraysorters.BubbleSort;
import visualizer.array.arraysorters.comparators.IntegerComparator;
import visualizer.array.arraysorters.comparators.ReverseIntegerComparator;

import java.util.Arrays;
import java.util.Comparator;

public class VisualizerController {
    private final Canvas canvas;
    private final ArrayVisualizer<Integer> visualizer;

    public VisualizerController(int[] intArray) {
        // Convert int[] to Integer[]
        Integer[] array = toIntegerArray(intArray);

        // Create the canvas
        this.canvas = new Canvas(CanvasConfig.DEFAULT_WIDTH, CanvasConfig.DEFAULT_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Create the visualizer
        this.visualizer = new BoxVisualizer<>(gc, array);
        visualizer.setSortingStrategy(new BubbleSort());
    }

    public VBox getRoot() {
        // Draw the initial state of the array
        visualizer.draw();

        // Create a button for sorting
        Button sortButton = new Button("Sort");
        sortButton.setOnAction(event -> {
            sortArray();
            System.out.println("Sort button pressed."); // Debug in console
        });
        sortButton.setPrefWidth(100); // Set button size
        sortButton.setStyle("-fx-font-size: 14; -fx-padding: 5;");

        // Add the canvas and button to the layout
        VBox root = new VBox(10, canvas, sortButton);
        root.setStyle("-fx-padding: 20; -fx-alignment: center; -fx-border-color: black; -fx-border-width: 2;"); // Debug style
        return root;
    }

    private void sortArray() {
        // Sort and visualize the array
        Comparator<Integer> comparator = new IntegerComparator();
        visualizer.sortAndVisualize(comparator);
        visualizer.playAnimations();
    }

    private static Integer[] toIntegerArray(int[] intArray) {
        return Arrays.stream(intArray).boxed().toArray(Integer[]::new);
    }
}
