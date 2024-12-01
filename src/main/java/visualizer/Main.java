package visualizer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import visualizer.controller.VisualizerController;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        int[] intArray = {5, 3, 8, 6, 2};
        VisualizerController controller = new VisualizerController(intArray);

        // Set up the scene
        Scene scene = new Scene(controller.getRoot(), CanvasConfig.DEFAULT_WIDTH, CanvasConfig.DEFAULT_HEIGHT + 50);
        stage.setScene(scene);
        stage.setTitle("Array Visualizer");
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}
