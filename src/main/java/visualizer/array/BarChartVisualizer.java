package visualizer.array;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class BarChartVisualizer extends ArrayVisualizer {
    public BarChartVisualizer(int[] array) {
        super(array);
    }

    @Override
    public void draw(GraphicsContext gc) {

        gc.clearRect(0, 0, defaultWidth, defaultHeight); // Clear the canvas

        double barWidth = defaultWidth / array.length; // Calculate bar width
        int maxValue = getMaxValue(); // Find maximum value in the array

        for (int i = 0; i < array.length; i++) {
            double barHeight = (array[i] / (double) maxValue) * defaultHeight; // Scale height
            double x = i * barWidth;
            double y = defaultHeight - barHeight;

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
