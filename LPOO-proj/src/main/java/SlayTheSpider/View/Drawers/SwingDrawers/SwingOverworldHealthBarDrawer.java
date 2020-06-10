package SlayTheSpider.View.Drawers.SwingDrawers;

import SlayTheSpider.Model.Game.HealthBar.HealthBar;
import SlayTheSpider.Model.Game.HealthBar.PlayerHealthBar;
import SlayTheSpider.Model.Position.Position;

import java.awt.*;

public class SwingOverworldHealthBarDrawer extends SwingHealthbarDrawer {
    private final PlayerHealthBar healthBar;
    private final int windowHeight;

    public SwingOverworldHealthBarDrawer(PlayerHealthBar healthBar, int windowHeight) {
        this.healthBar = healthBar;
        this.windowHeight = windowHeight;
    }

    @Override
    public void draw(Graphics g) {
        this.drawHealth(150, 15, g);
    }

    @Override
    protected Position getPosition() {
        return new Position(20, windowHeight - 100);
    }

    @Override
    protected HealthBar getHealthBar() {
        return healthBar;
    }

}
