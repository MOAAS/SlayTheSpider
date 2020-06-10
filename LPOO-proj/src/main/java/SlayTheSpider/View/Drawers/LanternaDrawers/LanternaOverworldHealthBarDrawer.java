package SlayTheSpider.View.Drawers.LanternaDrawers;

import SlayTheSpider.View.TextWindow.LanternaGraphics;
import SlayTheSpider.Model.Game.HealthBar.HealthBar;
import SlayTheSpider.Model.Game.HealthBar.PlayerHealthBar;
import SlayTheSpider.Model.Position.Position;

public class LanternaOverworldHealthBarDrawer extends LanternaHealthBarDrawer {
    private final int windowHeight;
    private PlayerHealthBar healthBar;

    public LanternaOverworldHealthBarDrawer(PlayerHealthBar healthBar, int windowHeight) {
        this.healthBar = healthBar;
        this.windowHeight = windowHeight;
    }

    public void draw(LanternaGraphics drawer) {
        this.drawHealth(15, drawer);
    }

    @Override
    protected HealthBar getHealthBar() {
        return healthBar;
    }

    @Override
    protected Position getPosition() {
        return new Position(0, windowHeight - 1);
    }

}
