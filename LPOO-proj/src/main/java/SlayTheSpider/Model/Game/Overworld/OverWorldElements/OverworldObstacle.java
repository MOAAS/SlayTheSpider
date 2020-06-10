package SlayTheSpider.Model.Game.Overworld.OverWorldElements;

import SlayTheSpider.Model.Sprites.Sprite;
import SlayTheSpider.Model.Position.Position2D;

public abstract class OverworldObstacle extends OverworldElement {
    protected Sprite sprite;

    public OverworldObstacle(Position2D position, int size, Sprite sprite) {
        super(position, size);
        this.sprite = sprite;
    }

    public Sprite getSprite(){
        return sprite;
    }
}
