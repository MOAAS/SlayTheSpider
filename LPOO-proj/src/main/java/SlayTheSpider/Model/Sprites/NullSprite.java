package SlayTheSpider.Model.Sprites;

import SlayTheSpider.Model.Position.Position2D;

public class NullSprite implements Sprite {
    @Override
    public void draw(SpriteGraphics graphics, Position2D position) {

    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public Sprite endarken() {
        return this;
    }

    @Override
    public Sprite scale(int width, int height) {
        return this;
    }
}
