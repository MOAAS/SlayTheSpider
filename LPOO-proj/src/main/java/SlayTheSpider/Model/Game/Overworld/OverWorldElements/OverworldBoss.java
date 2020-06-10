package SlayTheSpider.Model.Game.Overworld.OverWorldElements;

import SlayTheSpider.Model.Game.Fight;
import SlayTheSpider.Model.Sprites.Sprite;
import SlayTheSpider.Model.Position.Position2D;

public class OverworldBoss extends OverworldObstacle {
    private Fight bossFight;

    public OverworldBoss(Position2D position, Sprite sprite, Fight bossFight) {
        super(position, 3, sprite);
        this.bossFight = bossFight;
    }

    public Fight getBossFight() {
        return bossFight;
    }
}
