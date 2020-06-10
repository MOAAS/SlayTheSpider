package SlayTheSpider.View.Drawers.LanternaDrawers;

import SlayTheSpider.View.TextWindow.LanternaGraphics;
import SlayTheSpider.Model.Game.Overworld.OverWorldElements.Tile;

import java.awt.*;

public class LanternaTileDrawer implements LanternaDrawer {
    private final Tile tile;

    public LanternaTileDrawer(Tile tile) {
        this.tile = tile;
    }

    @Override
    public void draw(LanternaGraphics drawer) {
        switch (tile.getVisibilitiy().getType()) {
            case VisibleFar:
                Color c = Color.decode("#" + tile.getColor()).darker();
                drawer.setBackgroundColor(Integer.toHexString(c.getRGB()).substring(2));
                drawer.drawRect(tile.getPosition(), 1, 1);
                break;
            case VisibleNear:
                drawer.setBackgroundColor(tile.getColor());
                drawer.drawRect(tile.getPosition(), 1, 1);
                break;
            default: break;
        }
    }
}
