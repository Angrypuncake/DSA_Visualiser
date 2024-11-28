package visualizer.array;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import visualizer.ArrayVisualizer;

public class BarChartVisualizer extends ArrayVisualizer {

    public BarChartVisualizer(int[] array) {
        super(array);
    }

    @Override
    public void draw(GraphicsContext gc, double canvasWidth, double canvasHeight) {
        gc.clearRect(0, 0, canvasWidth, canvasHeight); // Clear the canvas

        double barWidth = canvasWidth / array.length; // Calculate bar width
        int maxValue = getMaxValue(); // Find maximum value in the array

        for (int i = 0; i < array.length; i++) {
            double barHeight = (array[i] / (double) maxValue) * canvasHeight; // Scale height
            double x = i * barWidth;
            double y = canvasHeight - barHeight;

            gc.setFill(Color.LIGHTBLUE);
            gc.fillRect(x, y, barWidth - 2, barHeight); // Draw each bar
        }
    }

    private int getMaxValue() {
        int max = array[0];
        for (int value : array) {
            if (value > max) max = value;
        }
        return max;
    }
}
