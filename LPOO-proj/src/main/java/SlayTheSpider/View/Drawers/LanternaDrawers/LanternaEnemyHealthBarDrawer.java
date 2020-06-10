package SlayTheSpider.View.Drawers.LanternaDrawers;

import SlayTheSpider.View.TextWindow.LanternaGraphics;
import SlayTheSpider.Model.Game.HealthBar.EnemyHealthBar;
import SlayTheSpider.Model.Game.HealthBar.HealthBar;
import SlayTheSpider.Model.Position.Position2D;

public class LanternaEnemyHealthBarDrawer extends LanternaHealthBarDrawer {
    private final int width;
    private final Position2D position;
    private EnemyHealthBar healthBar;

    public LanternaEnemyHealthBarDrawer(EnemyHealthBar healthBar, Position2D position, int width) {
        this.healthBar = healthBar;
        this.position = position;
        this.width = width;
    }

    @Override
    public void draw(LanternaGraphics drawer) {
        this.drawHealth(width, drawer);
    }

    @Override
    protected HealthBar getHealthBar() {
        return healthBar;
    }

    @Override
    protected Position2D getPosition() {
        return position;
    }

}