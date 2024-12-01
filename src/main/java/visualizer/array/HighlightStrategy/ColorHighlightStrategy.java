package visualizer.array.HighlightStrategy;

import javafx.scene.paint.Color;
import visualizer.array.Box;
import visualizer.array.HighlightStrategy.HighlightStrategy;

public class ColorHighlightStrategy implements HighlightStrategy {
    private final Color fillColor;
    private final Color borderColor;

    public ColorHighlightStrategy(Color fillColor, Color borderColor) {
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }

    @Override
    public void highlight(Box<?> box) {
        box.highlight(fillColor, borderColor);
    }

    @Override
    public void reset(Box<?> box) {
        box.resetHighlight();
    }
}
