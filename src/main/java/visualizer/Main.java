package visualizer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import visualizer.controller.VisualizerController;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/VisualizerView.fxml"));
        VBox root = loader.load();

        Scene scene = new Scene(root, CanvasConfig.DEFAULT_WIDTH, CanvasConfig.DEFAULT_HEIGHT + 50);
        stage.setScene(scene);
        stage.setTitle("Array Visualizer");
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}
