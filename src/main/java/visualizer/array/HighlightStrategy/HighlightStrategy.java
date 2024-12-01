package visualizer.array.HighlightStrategy;

import javafx.scene.canvas.GraphicsContext;
import visualizer.array.Box;

public interface HighlightStrategy {
    void highlight(Box<?> box);
    void reset(Box<?> box);
}
