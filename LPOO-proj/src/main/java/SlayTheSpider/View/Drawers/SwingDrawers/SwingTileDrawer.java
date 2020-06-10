package SlayTheSpider.View.Drawers.SwingDrawers;

import SlayTheSpider.Model.Game.Overworld.OverWorldElements.Tile;

import java.awt.*;

public class SwingTileDrawer implements SwingDrawer {
    private final Tile tile;

    public SwingTileDrawer(Tile tile) {
        this.tile = tile;
    }

    @Override
    public void draw(Graphics g) {
        switch (tile.getVisibilitiy().getType()) {
            case VisibleFar:
                g.setColor(Color.decode("#" + tile.getColor()).darker());
                break;
            case VisibleNear:
                g.setColor(Color.decode("#" + tile.getColor()));
                break;
            default: return;
        }

        g.fillRect(tile.getPosition().getX() * 10, tile.getPosition().getY() * 10, 10, 10);
    }
}
