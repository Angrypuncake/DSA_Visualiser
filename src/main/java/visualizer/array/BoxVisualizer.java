package visualizer.array;

import javafx.scene.canvas.GraphicsContext;

public class BoxVisualizer extends ArrayVisualizer {
    private Box[] boxes;

    public BoxVisualizer(int[] array) {
        super(array);
        createBoxes();
    }

    private void createBoxes(){
        boxes = new Box[array.length];
        double boxSize = 100;
        for (int i = 0; i < array.length; i++) {
            double x = i * boxSize + defaultWidth / 2 - array.length * boxSize / 2;
            double y = defaultHeight / 2;
            boxes[i] = new Box(x, y, boxSize, array[i]);
        }
    }



    @Override
    public void draw(GraphicsContext gc) {
        gc.clearRect(0, 0, defaultWidth, defaultHeight);

        for (Box box : boxes) {
            box.draw(gc);
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
