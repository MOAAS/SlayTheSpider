package SlayTheSpider.View;

import SlayTheSpider.View.Drawers.SwingDrawers.SwingObstacleDrawer;
import SlayTheSpider.View.Drawers.SwingDrawers.SwingOverworldHealthBarDrawer;
import SlayTheSpider.View.Drawers.SwingDrawers.SwingOverworldPlayerDrawer;
import SlayTheSpider.View.Drawers.SwingDrawers.SwingTileDrawer;
import SlayTheSpider.Model.Sprites.PlayerOWSpriteSheet;
import SlayTheSpider.Model.OverworldModel;
import SlayTheSpider.View.TextWindow.SwingWindow;
import SlayTheSpider.Model.Game.Overworld.FloorStructure;
import SlayTheSpider.Model.Game.Overworld.OverWorldElements.OverworldObstacle;
import SlayTheSpider.Model.Game.Overworld.OverWorldElements.Tile;
import SlayTheSpider.View.TextWindow.View.SwingView;

import java.awt.*;
import java.util.List;

public class OverworldViewSwing extends SwingView {
    private static final int FLOOR_NUM_X = 30;
    private static final int FLOOR_NUM_Y = 50;
    private static final Font FLOOR_NUM_FONT = new Font("Papyrus", Font.BOLD, 18);

    private final OverworldModel model;
    private final PlayerOWSpriteSheet playerOWSpriteSheet;

    public OverworldViewSwing(OverworldModel model, SwingWindow window) {
        super(window);
        this.model = model;

        this.playerOWSpriteSheet = new PlayerOWSpriteSheet(
                model.getSpriteStorage().getSprite("PlayerOWD"),
                model.getSpriteStorage().getSprite("PlayerOWU"),
                model.getSpriteStorage().getSprite("PlayerOWL"),
                model.getSpriteStorage().getSprite("PlayerOWR"));
    }

    @Override
    public void draw(Graphics g) {
        this.drawTiles(g, model.getFloor().getStructures().getUnwalkableTiles());
        this.drawStructures(g);

        new SwingOverworldPlayerDrawer(model.getPlayer().getOWPlayer(), playerOWSpriteSheet).draw(g);
        new SwingOverworldHealthBarDrawer(model.getPlayer().getHealthBar(), this.getWindow().getHeight()).draw(g);
        this.drawTitle(g);
        this.drawLevelNum(g);
    }

    private void drawLevelNum(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(FLOOR_NUM_FONT);
        g.drawString("Floor -" + model.getFloorNum(), FLOOR_NUM_X, FLOOR_NUM_Y);
    }

    private void drawStructures(Graphics g) {
        List<FloorStructure> floorStructures = model.getFloor().getStructures().getList();
        for(FloorStructure structure : floorStructures) {
            this.drawTiles(g, structure.getTiles());

            for (OverworldObstacle obstacle : structure.getObstacles()) {
                new SwingObstacleDrawer(obstacle).draw(g);
            }
        }
    }

    private void drawTiles(Graphics g, List<Tile> tiles) {
        for (Tile tile : tiles) {
            new SwingTileDrawer(tile).draw(g);
        }
    }
}
