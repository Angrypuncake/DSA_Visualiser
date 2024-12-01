package visualizer.array;

import javafx.animation.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class ArrayAnimationManager<T> {
    private final SequentialTransition sequentialTransition = new SequentialTransition();
    private ArrayVisualizer AV;
    double speedFactor;
    public ArrayAnimationManager(ArrayVisualizer AV){
        this.AV = AV;
        this.speedFactor = 1;
    }

    public void addSwapAnimation(int index1, int index2, Box<T> box1, Box<T> box2, Runnable highlightAction, Runnable resetAction) {
        Runnable moveAction = () -> {
            // Swap the positions visually

            double oldBox1X = box1.getX();
            double oldBox1Y = box1.getY();
            addMovementAnimation(box1, box2.getX(), box2.getY());
            addMovementAnimation(box2, oldBox1X, oldBox1Y);

            AV.swap(index1, index2);
            AV.draw();
        };

        // Add the timeline with highlighting, movement, and reset actions
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(500 / speedFactor), event -> {
                    highlightAction.run(); //
                    PulseUp(box1);
//                    addConnectionLine(box1, box2, AV.getPane());
                })
                ,
                new KeyFrame(Duration.millis(1000 / speedFactor), event -> moveAction.run()),
                new KeyFrame(Duration.millis(1500 / speedFactor), event -> resetAction.run())
        );

        sequentialTransition.getChildren().add(timeline);
    }

    public void playAnimations(Runnable onComplete) {
        sequentialTransition.setOnFinished(event -> onComplete.run());
        sequentialTransition.play();

    }

    public void resetAnimations() {
        sequentialTransition.getChildren().clear();
    }

    private void addMovementAnimation(Box<T> box, double targetX, double targetY) {
        TranslateTransition transition = new TranslateTransition(Duration.millis(500), box.getNode());
        transition.setToX(targetX - box.getX()); // Apply visual translation offset
        transition.setToY(targetY - box.getY()); // Apply visual translation offset

        // Reset the translation offsets after the animation finishes
        transition.setOnFinished(event -> {
            box.getNode().setTranslateX(0); // Reset the visual translation offset
            box.getNode().setTranslateY(0);
        });

        transition.play();
    }

    private void PulseUp(Box<T> box) {
        ScaleTransition scaleUp = new ScaleTransition(Duration.millis(250), box.getNode());
        scaleUp.setToX(1.2);
        scaleUp.setToY(1.2);

        ScaleTransition scaleDown = new ScaleTransition(Duration.millis(250), box.getNode());
        scaleDown.setToX(1.0);
        scaleDown.setToY(1.0);

        SequentialTransition pulse = new SequentialTransition(scaleUp, scaleDown);
        pulse.play();
    }

    private void addConnectionLine(Box<T> box1, Box<T> box2, Pane pane) {
        Line line = new Line(
                box1.getX() + box1.getBoxSize() / 2,
                box1.getY() + box1.getBoxSize() / 2,
                box2.getX() + box2.getBoxSize() / 2,
                box2.getY() + box2.getBoxSize() / 2
        );
        line.setStroke(Color.BLUE);
        line.setStrokeWidth(2.0);

        pane.getChildren().add(line);

        FadeTransition fadeOut = new FadeTransition(Duration.millis(500), line);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0);
        fadeOut.setOnFinished(event -> pane.getChildren().remove(line));
        fadeOut.play();
    }


    public void setSpeedFactor(double speedFactor) {
        this.speedFactor = speedFactor;
    }
}
