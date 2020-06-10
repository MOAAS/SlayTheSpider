package SlayTheSpider.View;

import SlayTheSpider.View.Drawers.LanternaDrawers.LanternaDeckDrawer;
import SlayTheSpider.View.Drawers.LanternaDrawers.LanternaEnemyDrawer;
import SlayTheSpider.View.Drawers.LanternaDrawers.LanternaPlayerDrawer;
import SlayTheSpider.Model.FightModel;
import SlayTheSpider.View.TextWindow.View.LanternaView;
import SlayTheSpider.Model.Sprites.Sprite;
import SlayTheSpider.View.TextWindow.LanternaWindow;
import SlayTheSpider.Model.Game.Enemy;

public class FightViewLanterna extends LanternaView {
    private final FightModel model;
    private Sprite playerSprite;

    public FightViewLanterna(FightModel model, LanternaWindow window) {
        super(window);
        this.playerSprite = model.getSpriteStorage().getSprite("Player");
        this.model = model;
    }

    @Override
    public void draw() {
        for (Enemy enemy : model.getFight().getEnemies()) {
            new LanternaEnemyDrawer(enemy).draw(this.getWindow().getTextDrawer());
        }

        new LanternaPlayerDrawer(model.getPlayer(), playerSprite).draw(this.getWindow().getTextDrawer());
        new LanternaDeckDrawer(model.getPlayer().getDeck(), this.getWindow().getWidth(), this.getWindow().getHeight()).draw(this.getWindow().getTextDrawer());

        this.drawTitle(this.getWindow().getTextDrawer());
    }
}
