package visualizer.array;

import javafx.scene.canvas.GraphicsContext;
import visualizer.CanvasConfig;

public abstract class ArrayVisualizer {
    protected int[] array; // Array to visualize
    protected static double defaultWidth = CanvasConfig.DEFAULT_WIDTH; // Default canvas width
    protected static double defaultHeight = CanvasConfig.DEFAULT_HEIGHT; // Default canvas height

    public ArrayVisualizer(int[] array) {
        this.array = array;
    }

    // Abstract method to draw the array on the canvas
    public abstract void draw(GraphicsContext gc);

    // Optional: Update the array dynamically
    public void setArray(int[] array) {
        this.array = array;
    }
}
