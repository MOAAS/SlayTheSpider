package SlayTheSpider.View.Drawers.LanternaDrawers;

import SlayTheSpider.Model.Sprites.Sprite;
import SlayTheSpider.View.TextWindow.LanternaGraphics;
import SlayTheSpider.Model.Game.Overworld.OverWorldElements.OverworldObstacle;

public class LanternaObstacleDrawer implements LanternaDrawer {
    private final OverworldObstacle obstacle;

    public LanternaObstacleDrawer(OverworldObstacle obstacle) {
        this.obstacle = obstacle;
    }

    @Override
    public void draw(LanternaGraphics drawer) {
        Sprite sprite = obstacle.getSprite();
        switch (obstacle.getVisibilitiy().getType()) {
            case Invisible: return;
            case VisibleFar: sprite = sprite.endarken();
            case VisibleNear: break;
        }
        sprite.draw(drawer, obstacle.getPosition());
    }
}
