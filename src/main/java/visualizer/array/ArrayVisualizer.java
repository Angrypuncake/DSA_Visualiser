package visualizer.array;

import javafx.animation.KeyFrame;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.util.Duration;
import visualizer.CanvasConfig;
import visualizer.array.arraysorters.ArraySortingStrategy;

import java.util.Arrays;
import java.util.Comparator;

public abstract class ArrayVisualizer<T> {
    protected T[] array; // Array to visualize
    protected final ArrayAnimationManager animationManager = new ArrayAnimationManager();
    protected ArraySortingStrategy sortingStrategy;
    protected GraphicsContext gc;

    protected double defaultWidth = CanvasConfig.DEFAULT_WIDTH;
    protected double defaultHeight = CanvasConfig.DEFAULT_HEIGHT;

    public ArrayVisualizer(GraphicsContext gc, T[] array){
        this.array = array;
        this.gc = gc;
    }

    public void sortAndVisualize(Comparator<T> comparator) {
        if (sortingStrategy == null) {
            throw new IllegalStateException("Sorting strategy not set");
        }
        sortingStrategy.sort(array, this::animateSwap, comparator);
    }

    private void animateSwap(int i, int j) {
        animationManager.addSwapAnimation(
                () -> highlight(i, j),
                () -> swap(i, j),
                this::resetHighlight
        );
    }

    public void playAnimations() {
        animationManager.playAnimations(this::draw);
    }

    public void resetAnimations() {
        animationManager.resetAnimations();
    }

    // Abstract methods for highlighting, swapping, and drawing
    protected abstract void highlight(int... indices);
    protected abstract void resetHighlight();
    protected void swap(int i, int j) {
        System.out.println("Swapping indices " + i + " and " + j);
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
}
