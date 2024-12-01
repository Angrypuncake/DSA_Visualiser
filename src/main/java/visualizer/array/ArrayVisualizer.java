package visualizer.array;

import javafx.animation.KeyFrame;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import visualizer.CanvasConfig;
import visualizer.array.arraysorters.ArraySortingStrategy;

import java.util.Arrays;
import java.util.Comparator;

public abstract class ArrayVisualizer<T> {
    protected T[] array; // Array to visualize
    protected ArrayAnimationManager animationManager;
    protected ArraySortingStrategy sortingStrategy;
    protected Pane pane;

    protected double defaultWidth = CanvasConfig.DEFAULT_WIDTH;
    protected double defaultHeight = CanvasConfig.DEFAULT_HEIGHT;

    public ArrayVisualizer(Pane pane, T[] array){
        this.array = array;
        this.pane = pane;
        animationManager = new ArrayAnimationManager(this);
    }

    public void setSpeedFactor(double speedFactor) {
        animationManager.setSpeedFactor(speedFactor);
    }

    public void sortAndVisualize(Comparator<T> comparator) {
        if (sortingStrategy == null) {
            throw new IllegalStateException("Sorting strategy not set");
        }
        sortingStrategy.sort(array, this::animateSwap, comparator);
    }

    abstract void animateSwap(int i, int j);


    public void playAnimations() {
        animationManager.playAnimations(this::draw);
        animationManager.resetAnimations();

    }

    // Abstract methods for highlighting, swapping, and drawing
    protected abstract void highlight(int... indices);
    protected abstract void resetHighlight();
    protected void swap(int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        draw(); // Redraw with updated values
    }
    public abstract void draw();

    public T[] getArray() {
        return this.array;
    }

    public void setArray(T[] array) {
        this.array = array;
    }

    public void setSortingStrategy(ArraySortingStrategy strategy) {
        this.sortingStrategy = strategy;
    }

    public Pane getPane() {
        return this.pane;
    }
}
