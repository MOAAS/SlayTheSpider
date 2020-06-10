package SlayTheSpider.View.Drawers.LanternaDrawers;

import SlayTheSpider.Model.Game.HealthBar.HealthBar;
import SlayTheSpider.Model.Game.HealthBar.PlayerHealthBar;
import SlayTheSpider.View.TextWindow.LanternaGraphics;
import SlayTheSpider.Model.Position.Position2D;

public class LanternaPlayerHealthBarDrawer extends LanternaHealthBarDrawer {
    private final Position2D position;
    private PlayerHealthBar healthBar;
    
    public LanternaPlayerHealthBarDrawer(PlayerHealthBar healthBar, Position2D position) {
        this.healthBar = healthBar;
        this.position = position;
    }

    @Override
    public void draw(LanternaGraphics drawer) {
        this.drawHealth(10, drawer);
        this.drawShield(drawer);
        this.drawMana(drawer);
    }

    private void drawShield(LanternaGraphics drawer) {
        Position2D shieldPos = this.position.left(1);
        if (this.healthBar.getShield() >= 10)
            shieldPos.setLeft(1);
        drawer.setTextColor("00ffff");
        drawer.drawString(Integer.toString(this.healthBar.getShield()), shieldPos);
    }

    private void drawMana(LanternaGraphics drawer) {
        drawer.setTextColor("ffff00");
        drawer.drawString(this.healthBar.getCurrentMana() + "/" + this.healthBar.getMaxMana(), this.position.down(1));
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