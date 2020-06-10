package SlayTheSpider.View.TextWindow;

import SlayTheSpider.Model.Sprites.SpriteGraphics;
import SlayTheSpider.Model.Position.Position2D;

import java.awt.*;
import java.util.List;

public class SwingGraphics implements SpriteGraphics {
    private Graphics graphics;

    public SwingGraphics(Graphics graphics) {
        this.graphics = graphics;
    }

    public void drawString(String string, Position2D position) {
        graphics.drawString(string, position.getX(), position.getY() + 10);
    }

    @Override
    public void drawImage(Image image, int x, int y) {
        graphics.drawImage(image, x, y, null);
    }

    @Override
    public void drawStringList(List<String> strings, Position2D position, String color) {
        graphics.setColor(Color.decode("#" + color));
        for (int i = 0; i < strings.size(); i++) {
            this.drawString(strings.get(i), position.down(10 * i));
        }
    }

    public void writeInBox(List<String> strings, Position2D position, int minX, int maxX) {
        Position2D currPos = position.copy();
        for (String s : strings) {
            this.writeStringInBox(s, currPos, minX, maxX);
            currPos.setX(minX);
            currPos.setDown(this.graphics.getFontMetrics().getHeight());
        }
    }

    public void writeStringInBox(String string, Position2D currPos, int minX, int maxX) {
        String[] words = string.split(" ");
        StringBuilder buffer = new StringBuilder(" - ");

        for (String word : words) {
            if (currPos.getX() + this.graphics.getFontMetrics().stringWidth(buffer.toString() + " " + word) > maxX) {
                this.graphics.drawString(buffer.toString(), currPos.getX(), currPos.getY());
                buffer = new StringBuilder();
                currPos.setX(minX);
                currPos.setDown(this.graphics.getFontMetrics().getHeight());
            }

            buffer.append(word).append(" ");
        }
        this.graphics.drawString(buffer.toString(), currPos.getX(), currPos.getY());
    }

    public void drawStringCentered(String string, Position2D pos) {
        int width = this.graphics.getFontMetrics().stringWidth(string);
        this.graphics.drawString(string, pos.getX() - width / 2, pos.getY());
    }
}
