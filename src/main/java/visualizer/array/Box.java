package visualizer.array;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Box<T> {
    private double x,y;
    private double boxSize;
    private T value;
    private boolean isHighlighted; // Tracks if the box is highlighted
    private Color defaultColor = Color.LIGHTGRAY;

    public Box(double x, double y, double boxSize, T value){
        this.x = x;
        this.y = y;
        this.boxSize = boxSize;
        this.value = value;
        this.isHighlighted = false; // Default: Not highlighted
    }

    public void setHighlighted(boolean highlighted) {
        this.isHighlighted = highlighted;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public double getBoxSize(){
        return boxSize;
    }

    public T getValue(){
        return value;
    }

    public void setX(double x){
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
    }

    public void setBoxSize(double boxSize){
        this.boxSize = boxSize;
    }

    public void setValue(T value){
        this.value = value;
    }

    public void draw(GraphicsContext gc) {
        // Set the box's fill color based on the highlight state
        gc.setFill(isHighlighted ? Color.RED : Color.LIGHTGRAY);
        gc.fillRect(x, y, boxSize, boxSize); // Fill the box with the appropriate color

        // Draw the box border
        gc.setStroke(Color.BLACK);
        gc.strokeRect(x, y, boxSize, boxSize);

        // Draw the value inside the box
        gc.setFill(Color.BLACK); // Text color
        gc.setFont(new Font("Arial", 16));
        double textX = x + boxSize / 2 - (gc.getFont().getSize() / 4) * String.valueOf(value).length();
        double textY = y + boxSize / 2 + gc.getFont().getSize() / 4;
        gc.fillText(String.valueOf(value), textX, textY);
    }


    public void moveTo(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
