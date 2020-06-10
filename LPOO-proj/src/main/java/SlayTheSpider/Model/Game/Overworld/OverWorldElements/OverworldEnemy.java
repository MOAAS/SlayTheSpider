package SlayTheSpider.Model.Game.Overworld.OverWorldElements;

import SlayTheSpider.Model.Game.Fight;
import SlayTheSpider.Model.Sprites.Sprite;
import SlayTheSpider.Model.Position.Position2D;

public class OverworldEnemy extends OverworldObstacle {
    private Fight fight;

    public OverworldEnemy(Position2D position, Sprite sprite, Fight fight) {
        super(position, 1, sprite);
        this.fight = fight;
    }

    public Fight getFight() {
        return fight;
    }
}
