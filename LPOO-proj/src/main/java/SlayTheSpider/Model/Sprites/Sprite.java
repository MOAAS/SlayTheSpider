package SlayTheSpider.Model.Sprites;

import SlayTheSpider.Model.Position.Position2D;

public interface Sprite {
    void draw(SpriteGraphics graphics, Position2D position);
    int getHeight();
    int getWidth();
    String getName();

    Sprite endarken();

    Sprite scale(int width, int height);
}