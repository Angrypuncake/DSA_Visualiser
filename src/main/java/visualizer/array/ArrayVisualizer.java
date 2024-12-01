package visualizer.array;

import javafx.animation.KeyFrame;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.util.Duration;
import visualizer.CanvasConfig;
import visualizer.array.arraysorters.ArraySortingStrategy;

import java.util.Comparator;

public abstract class ArrayVisualizer<T> {
    protected T[] array; // Array to visualize
    protected static double defaultWidth = CanvasConfig.DEFAULT_WIDTH; // Default canvas width
    protected static double defaultHeight = CanvasConfig.DEFAULT_HEIGHT; // Default canvas height
    private ArraySortingStrategy sortingStrategy;
    protected GraphicsContext gc;

    public ArrayVisualizer(GraphicsContext gc, T[] array) {
        this.array = array;
        this.gc = gc;
    }

    // Abstract method to draw the array on the canvas
    public abstract void draw();

    public void setSortingStrategy(ArraySortingStrategy strategy) {
        this.sortingStrategy = strategy;
    }

    public void sortAndVisualize(Comparator<T> comparator) {
        if (sortingStrategy == null) {
            throw new IllegalStateException("Sorting strategy not set");
        }

        // Pass in the AnimationCallback implementation that will animate the swap
        sortingStrategy.sort(array, this::animateSwap, comparator);
    }

    private SequentialTransition sequentialTransition = new SequentialTransition();

    private void animateSwap(int i, int j) {
        System.out.println("Animating swap for indices " + i + " of value " + array[i] + " and " + j + " of value " + array[j]);

        // Step 1: Highlight the swapped cells
        Timeline highlightTimeline = new Timeline(new KeyFrame(Duration.millis(500), event -> {
            if (this instanceof BoxVisualizer) {
                BoxVisualizer<T> boxVisualizer = (BoxVisualizer<T>) this;
                boxVisualizer.highlight(i, j); // Highlight the swapped cells
                boxVisualizer.draw();
            }
        }));

        // Step 2: Perform the swap
        Timeline swapTimeline = new Timeline(new KeyFrame(Duration.millis(500), event -> {
            // Perform the actual swap in the array
            T temp = array[i];
            array[i] = array[j];
            array[j] = temp;

            // Redraw with updated values
            if (this instanceof BoxVisualizer) {
                BoxVisualizer<T> boxVisualizer = (BoxVisualizer<T>) this;
                boxVisualizer.updateBoxValues(); // Update the visual representation of the boxes
                boxVisualizer.draw();
            }
        }));

        // Step 3: Reset the highlight after a delay
        Timeline resetHighlightTimeline = new Timeline(new KeyFrame(Duration.millis(500), event -> {
            if (this instanceof BoxVisualizer) {
                BoxVisualizer<T> boxVisualizer = (BoxVisualizer<T>) this;
                boxVisualizer.resetHighlight(); // Remove highlighting
                boxVisualizer.draw();
            }
        }));

        // Add timelines to the sequential transition
        sequentialTransition.getChildren().addAll(highlightTimeline, swapTimeline, resetHighlightTimeline);
    }



    // Call this after sorting is complete to start the animation
    public void playAnimations() {
        sequentialTransition.play();
        resetAnimations();
    }

    public void resetAnimations() {
        sequentialTransition.getChildren().clear();
    }



}
