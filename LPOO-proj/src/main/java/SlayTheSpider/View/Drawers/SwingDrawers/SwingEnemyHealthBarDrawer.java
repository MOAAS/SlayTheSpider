package SlayTheSpider.View.Drawers.SwingDrawers;

import SlayTheSpider.Model.Game.HealthBar.EnemyHealthBar;
import SlayTheSpider.Model.Game.HealthBar.HealthBar;
import SlayTheSpider.Model.Position.Position2D;

import java.awt.*;

public class SwingEnemyHealthBarDrawer extends SwingHealthbarDrawer {
    private final EnemyHealthBar healthBar;
    private final int width;
    private final Position2D position;

    public SwingEnemyHealthBarDrawer(EnemyHealthBar healthBar, Position2D healthBarPos, int width) {
        this.healthBar = healthBar;
        this.position = healthBarPos;
        this.width = width;
    }

    @Override
    protected Position2D getPosition() {
        return position;
    }

    @Override
    protected HealthBar getHealthBar() {
        return healthBar;
    }

    @Override
    public void draw(Graphics g) {
        this.drawHealth(width, 10, g);
    }
}
