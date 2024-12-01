package visualizer.array;

import javafx.scene.canvas.GraphicsContext;
import visualizer.array.HighlightStrategy.HighlightStrategy;

public class BoxVisualizer<T> extends ArrayVisualizer {
    private Box[] boxes;
    private final HighlightStrategy highlightStrategy;

    public BoxVisualizer(GraphicsContext gc, T[] array, HighlightStrategy highlightStrategy) {
        super(gc, array);
        boxes = new Box[array.length]; // Initialize the boxes array
        createBoxes();
        this.highlightStrategy = highlightStrategy;
    }

    private void createBoxes() {
        double boxSize = 100;
        for (int i = 0; i < array.length; i++) {
            double x = i * boxSize + defaultWidth / 2 - array.length * boxSize / 2;
            double y = defaultHeight / 2;

            // Update or recreate the boxes based on the array
            if (boxes[i] == null) {
                boxes[i] = new Box(x, y, boxSize, array[i]); // Create a new box if it doesn't exist
            } else {
                boxes[i].setValue(array[i]); // Update the value in the existing box
            }
        }
    }

    @Override
    public void draw() {
        gc.clearRect(0, 0, defaultWidth, defaultHeight);

        // Recreate or update boxes to reflect the current array
        createBoxes();

        // Draw the updated boxes
        for (Box box : boxes) {
            box.draw(gc);
        }
    }

    public void highlight(int... indices) {
        for (int index : indices) {
            highlightStrategy.highlight(boxes[index]);
        }
    }

    @Override
    protected void resetHighlight() {
        for (int i = 0; i < array.length; i++) {
            highlightStrategy.reset(boxes[i]);
        }
    }

    public void updateBoxValues() {
        for (int i = 0; i < array.length; i++) {
            boxes[i].setValue(array[i]);
        }
    }
}
