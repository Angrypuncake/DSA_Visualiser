package visualizer.array;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import visualizer.CanvasConfig;
import visualizer.array.HighlightStrategy.HighlightStrategy;

public class BoxVisualizer<T> extends ArrayVisualizer {
    private Box[] boxes;
    private final HighlightStrategy highlightStrategy;

    public BoxVisualizer(Pane pane, T[] array, HighlightStrategy highlightStrategy) {
        super(pane, array);
        boxes = new Box[array.length]; // Initialize the boxes array
        createBoxes();
        this.highlightStrategy = highlightStrategy;
    }

    private void createBoxes() {
        double boxSize = CanvasConfig.BOX_SIZE;
        for (int i = 0; i < array.length; i++) {
            double x = i * boxSize + defaultWidth / 2 - array.length * boxSize / 2;
            double y = defaultHeight / 2;

            // Update or recreate the boxes based on the array
            if (boxes[i] == null) {
                boxes[i] = new Box(x, y, boxSize, array[i]); // Create a new box if it doesn't exist\
            } else {
                boxes[i].setValue(array[i]); // Update the value in the existing box
            }
            pane.getChildren().addAll(boxes[i].getNode(), boxes[i].getTextNode()); // Add to pane
        }

    }

    @Override
    public void draw() {
        pane.getChildren().clear(); // Clear any existing nodes in the pane

        // Recreate or update boxes to reflect the current array
        createBoxes();

        // Draw the updated boxes
        for (Box<T> box : boxes) {
            box.draw();
        }
    }

    @Override
    void animateSwap(int i, int j) {

        Box<?> box1 = boxes[i];
        Box<?> box2 = boxes[j];

        animationManager.addSwapAnimation(
                i,
                j,
                box1,
                box2,
                () -> highlight(i, j), // Highlight boxes being swapped
                this::resetHighlight // Reset highlights after swapping
        );

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
