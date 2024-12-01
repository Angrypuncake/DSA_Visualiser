package visualizer.array;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Box<T> {
    private double x, y, boxSize;
    private T value;

    // JavaFX Nodes
    private final Rectangle node;  // Rectangle representing the box
    private final Text textNode;   // Text displaying the value inside the box

    public Box(double x, double y, double boxSize, T value) {
        this.x = x;
        this.y = y;
        this.boxSize = boxSize;
        this.value = value;

        // Initialize the Rectangle
        this.node = new Rectangle(x, y, boxSize, boxSize);
        this.node.setFill(Color.LIGHTGRAY); // Default fill color
        this.node.setStroke(Color.BLACK);  // Default border color

        // Initialize the Text
        this.textNode = new Text(x + boxSize / 2, y + boxSize / 2, String.valueOf(value));
        this.textNode.setFont(new Font("Arial", 16));
        this.textNode.setFill(Color.BLACK);

        // Center the text inside the box
        centerText();
    }

    public Rectangle getNode() {
        return node;
    }

    public Text getTextNode() {
        return textNode;
    }

    public void setValue(T value) {
        this.value = value;
        this.textNode.setText(String.valueOf(value)); // Update text
        centerText(); // Re-center text
    }

    public T getValue() {
        return value;
    }

    public void setX(double x) {
        this.x = x;
        this.node.setX(x); // Update Rectangle's position
        centerText();      // Re-center the text
    }

    public void setY(double y) {
        this.y = y;
        this.node.setY(y); // Update Rectangle's position
        centerText();      // Re-center the text
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

    public void highlight(Color fillColor, Color borderColor) {
        this.node.setFill(fillColor);
        this.node.setStroke(borderColor);
    }

    public void resetHighlight() {
        this.node.setFill(Color.LIGHTGRAY);
        this.node.setStroke(Color.BLACK);
    }

    // Center the text inside the rectangle
    private void centerText() {
        double textWidth = textNode.getBoundsInLocal().getWidth();
        double textHeight = textNode.getBoundsInLocal().getHeight();
        textNode.setX(x + (boxSize - textWidth) / 2);
        textNode.setY(y + (boxSize + textHeight) / 2 - textHeight / 4); // Adjust for baseline
    }

    public void draw() {
        textNode.setFill(Color.BLACK); // Text should always be visible
        node.setX(x);
        node.setY(y);
        node.setWidth(boxSize);
        node.setHeight(boxSize);
        centerText();
    }
}
