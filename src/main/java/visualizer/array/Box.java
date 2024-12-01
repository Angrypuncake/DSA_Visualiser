package visualizer.array;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Box<T> {
    private double x, y;           // Position
    private double boxSize;        // Size of the box
    private T value;               // Value inside the box
    private boolean isHighlighted; // Tracks if the box is highlighted
    private Color fillColor;       // Fill color (can change for highlighting)
    private Color borderColor;     // Border color (can change for highlighting)

    private final Color defaultFillColor = Color.LIGHTGRAY; // Default box color
    private final Color defaultBorderColor = Color.BLACK;  // Default border color

    public Box(double x, double y, double boxSize, T value) {
        this.x = x;
        this.y = y;
        this.boxSize = boxSize;
        this.value = value;
        this.isHighlighted = false; // Default: Not highlighted
        this.fillColor = defaultFillColor;
        this.borderColor = defaultBorderColor;
    }

    // Highlight the box with custom colors
    public void highlight(Color fillColor, Color borderColor) {
        this.fillColor = fillColor;
        this.borderColor = borderColor;
        this.isHighlighted = true;
    }

    // Reset to default colors
    public void resetHighlight() {
        this.fillColor = defaultFillColor;
        this.borderColor = defaultBorderColor;
        this.isHighlighted = false;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getBoxSize() {
        return boxSize;
    }

    public void draw(GraphicsContext gc) {
        // Fill the box
        gc.setFill(fillColor);
        gc.fillRect(x, y, boxSize, boxSize);

        // Draw the border
        gc.setStroke(borderColor);
        gc.strokeRect(x, y, boxSize, boxSize);

        // Draw the value inside the box
        gc.setFill(Color.BLACK); // Text color
        gc.setFont(new Font("Arial", 16));
        double textX = x + boxSize / 2 - (gc.getFont().getSize() / 4) * String.valueOf(value).length();
        double textY = y + boxSize / 2 + gc.getFont().getSize() / 4;
        gc.fillText(String.valueOf(value), textX, textY);
    }
}
