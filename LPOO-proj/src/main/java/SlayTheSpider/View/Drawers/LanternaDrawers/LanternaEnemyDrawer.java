package SlayTheSpider.View.Drawers.LanternaDrawers;

import SlayTheSpider.Model.Game.StatusEffect.StatusEffectListEntry;
import SlayTheSpider.Model.Game.Enemy;
import SlayTheSpider.View.TextWindow.LanternaGraphics;
import SlayTheSpider.Model.Position.Position2D;

public class LanternaEnemyDrawer implements LanternaDrawer {
    private final Enemy enemy;

    public LanternaEnemyDrawer(Enemy enemy) {
        this.enemy = enemy;
    }

    public void draw(LanternaGraphics drawer) {
        this.enemy.getSprite().draw(drawer, enemy.getPosition());
        this.drawHealthBar(drawer);
        this.drawEnemyDamage(drawer);
        this.drawStatusEffects(drawer);
    }

    private void drawEnemyDamage(LanternaGraphics drawer) {
        drawer.setTextColor("ff0000");
        drawer.drawString( enemy.getDamage() + " DMG", enemy.getPosition().up(1));
    }

    private void drawHealthBar(LanternaGraphics drawer) {
        int HPbarWidth = (int)Math.ceil(this.enemy.getSprite().getWidth() / 1.5);
        Position2D healthBarPos = enemy.getPosition().down(this.enemy.getSprite().getHeight()).right(HPbarWidth / 4);
        new LanternaEnemyHealthBarDrawer(enemy.getHealthBar(), healthBarPos, HPbarWidth).draw(drawer);
    }

    private void drawStatusEffects(LanternaGraphics drawer) {
        Position2D position = enemy.getPosition().up(2);
        for (StatusEffectListEntry effect : enemy.getStatusEffects().getList()) {
            new LanternaStatusEffectDrawer(effect, position).draw(drawer);
            position.setRight(2);
        }
    }
}
