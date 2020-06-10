package SlayTheSpider.Model.Sprites;

import SlayTheSpider.Model.Position.Position2D;

import java.awt.*;

public class ImageSprite implements Sprite {
    private final String name;
    private final Image image;

    public ImageSprite(String name, Image image) {
        this.name = name;
        this.image = image;
    }

    @Override
    public void draw(SpriteGraphics graphics, Position2D position) {
        graphics.drawImage(image, position.getX(), position.getY());
    }

    @Override
    public int getHeight() {
        return image.getHeight(null);
    }

    @Override
    public int getWidth() {
        return image.getWidth(null);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Sprite endarken() {
        return this;
    }

    @Override
    public Sprite scale(int width, int height) {
        String newName = width + "/" + height + "Scaled" + name;
        return new ImageSprite(newName, image.getScaledInstance(width, height, Image.SCALE_SMOOTH));
    }
}
