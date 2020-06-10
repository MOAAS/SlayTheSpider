package SlayTheSpider.Model.Sprites;

import SlayTheSpider.Model.Position.Position2D;

import java.awt.*;
import java.util.List;

public interface SpriteGraphics {
    void drawImage(Image image, int x, int y);
    void drawStringList(List<String> strings, Position2D position, String color);
}
