package visualizer;

import javafx.scene.canvas.GraphicsContext;

public abstract class ArrayVisualizer {
    protected int[] array; // Array to visualize
    protected double defaultWidth = CanvasConfig.DEFAULT_WIDTH; // Default canvas width
    protected double defaultHeight = CanvasConfig.DEFAULT_HEIGHT; // Default canvas height

    public ArrayVisualizer(int[] array) {
        this.array = array;
    }

    // Abstract method to draw the array on the canvas
    public abstract void draw(GraphicsContext gc, double canvasWidth, double canvasHeight);

    // Optional: Update the array dynamically
    public void setArray(int[] array) {
        this.array = array;
    }
}
