package SlayTheSpider.View.Drawers.SwingDrawers;

import SlayTheSpider.View.TextWindow.SwingGraphics;
import SlayTheSpider.Model.Game.Overworld.OverWorldElements.OverworldObstacle;
import SlayTheSpider.Model.Game.Overworld.OverWorldElements.Visibility.ElementVisibility;

import java.awt.*;

public class SwingObstacleDrawer implements SwingDrawer {
    private final OverworldObstacle obstacle;

    public SwingObstacleDrawer(OverworldObstacle obstacle) {
        this.obstacle = obstacle;
    }

    @Override
    public void draw(Graphics g) {
        if (obstacle.getVisibilitiy().getType() == ElementVisibility.VisibilityType.Invisible)
            return;
        obstacle.getSprite().draw(new SwingGraphics(g), obstacle.getPosition().mult(10, 10));
    }
}
