package visualizer.array;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Box{
    private double x,y;
    private double boxSize;
    private int value;

    public Box(double x, double y, double boxSize, int value){
        this.x = x;
        this.y = y;
        this.boxSize = boxSize;
        this.value = value;
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

    public int getValue(){
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

    public void setValue(int value){
        this.value = value;
    }

    public void draw(GraphicsContext gc){
        gc.strokeRect(x, y, boxSize, boxSize);
        gc.setFill(Color.BLACK);
        gc.setFont(new Font("Arial", 16));
        double textX = x + boxSize / 2 - (gc.getFont().getSize() / 4) * String.valueOf(value).length();
        double textY = y + boxSize / 2 + gc.getFont().getSize() / 4;
        gc.fillText(String.valueOf(value), textX, textY);
    }
}
