package SlayTheSpider.View.TextWindow;

import SlayTheSpider.Model.Sprites.SpriteGraphics;
import SlayTheSpider.Model.Position.Position2D;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.awt.*;
import java.util.List;

public class LanternaGraphics implements SpriteGraphics {
    private TextGraphics graphics;

    public LanternaGraphics(TextGraphics graphics) {
        this.graphics = graphics;
        this.graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        this.graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
    }

    public void setTextColor(String color) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#" + color));
    }

    public void setBackgroundColor(String color) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#" + color));
    }

    public void drawString(String string, Position2D position) {
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(position.getX(), position.getY(), string);
    }

    public void drawRect(Position2D position, int width, int height) {
        graphics.fillRectangle(new TerminalPosition(position.getX(), position.getY()), new TerminalSize(width, height), ' ');
    }

    @Override
    public void drawImage(Image image, int x, int y) {
        throw new NullPointerException();
    }

    @Override
    public void drawStringList(List<String> strings, Position2D position, String color) {
        this.setBackgroundColor("000000");
        this.setTextColor(color);
        for (int i = 0; i < strings.size(); i++)
            this.drawString(strings.get(i), position.down(i));
    }
}
