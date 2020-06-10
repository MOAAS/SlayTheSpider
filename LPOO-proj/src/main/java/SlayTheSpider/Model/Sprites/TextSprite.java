package SlayTheSpider.Model.Sprites;

import SlayTheSpider.Model.Position.Position2D;

import java.awt.*;
import java.util.List;

public class TextSprite implements Sprite {
    private final String name;
    private List<String> strings;
    private String color;
    private int height;
    private int width;

    public TextSprite(String name, List<String> sprite, String color){
        this.name = name;
        this.strings = sprite;
        this.color = color;
        this.height = sprite.size();
        this.width = calcWidth();
    }

    public TextSprite(TextSprite textSprite){
        this.name = textSprite.name;
        this.strings = textSprite.strings;
        this.color = textSprite.color;
        this.height = textSprite.height;
        this.width = textSprite.width;
    }

    private int calcWidth() {
        int width = 0;
        for (String string : strings)
            width = Math.max(width, string.length());
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public void draw(SpriteGraphics graphics, Position2D position) {
        graphics.drawStringList(strings, position, color);
    }

    @Override
    public TextSprite endarken() {
        TextSprite darkerSprite = new TextSprite(this);
        Color c = Color.decode("#"+this.color);
        darkerSprite.color = Integer.toHexString(c.darker().getRGB()).substring(2);
        return darkerSprite;
    }

    @Override
    public Sprite scale(int width, int height) {
        return this;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }
}
