package visualizer.array;

import javafx.animation.KeyFrame;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public class ArrayAnimationManager {
    private final SequentialTransition sequentialTransition = new SequentialTransition();

    public void addSwapAnimation(Runnable highlightAction, Runnable swapAction, Runnable resetAction) {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(500), event -> highlightAction.run()),
                new KeyFrame(Duration.millis(1000), event -> swapAction.run()),
                new KeyFrame(Duration.millis(1500), event -> resetAction.run())
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

    public void addMovementAnimation(Box<?> box, double targetX, double targetY) {
        TranslateTransition transition = new TranslateTransition(Duration.millis(500), box.getNode());
        transition.setToX(targetX - box.getX());
        transition.setToY(targetY - box.getY());
        transition.setOnFinished(event -> {
            box.setX(targetX);
            box.setY(targetY);
        });
        transition.play();
    }

}
