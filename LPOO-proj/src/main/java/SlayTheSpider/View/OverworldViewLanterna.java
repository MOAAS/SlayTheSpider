package SlayTheSpider.View;

import SlayTheSpider.View.Drawers.LanternaDrawers.LanternaObstacleDrawer;
import SlayTheSpider.View.Drawers.LanternaDrawers.LanternaOverworldHealthBarDrawer;
import SlayTheSpider.View.Drawers.LanternaDrawers.LanternaOverworldPlayerDrawer;
import SlayTheSpider.View.Drawers.LanternaDrawers.LanternaTileDrawer;
import SlayTheSpider.Model.Position.Position;
import SlayTheSpider.Model.Position.Position2D;
import SlayTheSpider.Model.OverworldModel;
import SlayTheSpider.View.TextWindow.View.LanternaView;
import SlayTheSpider.Model.Sprites.Sprite;
import SlayTheSpider.View.TextWindow.LanternaGraphics;
import SlayTheSpider.View.TextWindow.LanternaWindow;
import SlayTheSpider.Model.Game.Overworld.FloorStructure;
import SlayTheSpider.Model.Game.Overworld.OverWorldElements.OverworldObstacle;
import SlayTheSpider.Model.Game.Overworld.OverWorldElements.Tile;

import java.util.List;

public class OverworldViewLanterna extends LanternaView {
    private static final Position2D FLOOR_NUM_POS = new Position(3, 2);
    private final OverworldModel model;
    private Sprite playerSpriteOW;

    public OverworldViewLanterna(OverworldModel model, LanternaWindow window) {
        super(window);
        this.playerSpriteOW = model.getSpriteStorage().getSprite("PlayerOW");
        this.model = model;
    }

    @Override
    public void draw() {
        LanternaGraphics g = this.getWindow().getTextDrawer();
        this.drawUnwalkables(g);
        this.drawStructures(g);
        new LanternaOverworldPlayerDrawer(model.getPlayer().getOWPlayer(), playerSpriteOW).draw(g);
        new LanternaOverworldHealthBarDrawer(model.getPlayer().getHealthBar(), this.getWindow().getHeight()).draw(g);
        this.drawTitle(g);
        this.drawFloorNum(g);
    }

    private void drawFloorNum(LanternaGraphics g) {
        g.setTextColor("ffbb88");
        g.setBackgroundColor("000000");
        g.drawString("Floor -" + model.getFloorNum(), FLOOR_NUM_POS);

    }

    private void drawStructures(LanternaGraphics g) {
        List<FloorStructure> floorStructures = model.getFloor().getStructures().getList();
        for(FloorStructure structure : floorStructures) {
            for (Tile tile : structure.getTiles()) {
                new LanternaTileDrawer(tile).draw(g);
            }
            for (OverworldObstacle obstacle : structure.getObstacles()) {
                new LanternaObstacleDrawer(obstacle).draw(g);
            }
        }
    }

    private void drawUnwalkables(LanternaGraphics g) {
        List<Tile> unwalkables = model.getFloor().getStructures().getUnwalkableTiles();
        for (Tile tile : unwalkables)
            new LanternaTileDrawer(tile).draw(g);
    }

}
