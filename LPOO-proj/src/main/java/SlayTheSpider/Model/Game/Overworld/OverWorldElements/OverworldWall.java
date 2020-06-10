package SlayTheSpider.Model.Game.Overworld.OverWorldElements;

import SlayTheSpider.Model.Position.Position;
import SlayTheSpider.Model.Sprites.Sprite;

public class OverworldWall extends OverworldObstacle {
    public OverworldWall(Position position, Sprite sprite) {
        super(position, 1, sprite);
    }
}
