package visualizer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import visualizer.array.BarChartVisualizer;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        int[] array = {5, 3, 8, 6, 2};

        // Create the canvas using default sizes
        Canvas canvas = new Canvas(CanvasConfig.DEFAULT_WIDTH, CanvasConfig.DEFAULT_HEIGHT);
        BarChartVisualizer visualizer = new BarChartVisualizer(array);

        // Draw the array on the canvas
        visualizer.draw(canvas.getGraphicsContext2D(), canvas.getWidth(), canvas.getHeight());

        // Set up the scene
        VBox root = new VBox(canvas);
        Scene scene = new Scene(root, CanvasConfig.DEFAULT_WIDTH, CanvasConfig.DEFAULT_HEIGHT);
        stage.setScene(scene);
        stage.setTitle("Array Visualizer");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}