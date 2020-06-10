package SlayTheSpider.View;

import SlayTheSpider.View.Drawers.SwingDrawers.SwingDeckDrawer;
import SlayTheSpider.View.Drawers.SwingDrawers.SwingEnemyDrawer;
import SlayTheSpider.View.Drawers.SwingDrawers.SwingPlayerDrawer;
import SlayTheSpider.Model.Sprites.PlayerSpriteSheet;
import SlayTheSpider.Model.Sprites.Sprite;
import SlayTheSpider.Model.FightModel;
import SlayTheSpider.View.TextWindow.SwingGraphics;
import SlayTheSpider.View.TextWindow.SwingWindow;
import SlayTheSpider.Model.Game.Enemy;
import SlayTheSpider.Model.Position.Position;
import SlayTheSpider.View.TextWindow.View.SwingView;

import java.awt.*;


public class FightViewSwing extends SwingView {
    private final FightModel model;
    private final Sprite background;
    private PlayerSpriteSheet playerSpriteSheet;

    public FightViewSwing(FightModel model, SwingWindow window) {
        super(window);

        this.model = model;
        this.background = model.getSpriteStorage().getSprite("Background").scale(window.getWidth(), window.getHeight());

        this.playerSpriteSheet = new PlayerSpriteSheet(
                model.getSpriteStorage().getSprite("Player"),
                model.getSpriteStorage().getSprite("ManaFull"),
                model.getSpriteStorage().getSprite("ManaEmpty"));
    }

    @Override
    public void draw(Graphics g) {
        this.background.draw(new SwingGraphics(g), new Position(0, 0));

        for (Enemy enemy : model.getFight().getEnemies()) {
            new SwingEnemyDrawer(enemy).draw(g);
        }

        new SwingPlayerDrawer(model.getPlayer(), playerSpriteSheet).draw(g);
        new SwingDeckDrawer(model.getPlayer().getDeck()).draw(g);

        this.drawTitle(g);
    }
}
