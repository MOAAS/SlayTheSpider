package SlayTheSpider.View.Drawers.SwingDrawers;

import SlayTheSpider.View.TextWindow.SwingGraphics;
import SlayTheSpider.Model.Game.Enemy;
import SlayTheSpider.Model.Game.StatusEffect.StatusEffectListEntry;
import SlayTheSpider.Model.Position.Position2D;

import java.awt.*;

public class SwingEnemyDrawer implements SwingDrawer {
    private final Enemy enemy;

    public SwingEnemyDrawer(Enemy enemy) {
        this.enemy = enemy;
    }

    @Override
    public void draw(Graphics g) {
        Position2D enemyPos = enemy.getPosition().mult(10, 20);
        this.enemy.getSprite().draw(new SwingGraphics(g), enemyPos);
        this.drawHealthBar(g, enemyPos);
        this.drawEnemyDamage(g, enemyPos);
        this.drawStatusEffects(g, enemyPos);
    }

    private void drawEnemyDamage(Graphics g, Position2D enemyPos) {
        g.setColor(Color.RED);
        g.drawString(enemy.getDamage() + " DMG", enemyPos.getX(), enemyPos.getY());
    }

    private void drawHealthBar(Graphics g, Position2D enemyPos) {
        int HPbarWidth = (int)Math.ceil(this.enemy.getSprite().getWidth() * 1.25);
        Position2D healthBarPos = enemyPos.down(this.enemy.getSprite().getHeight() + 5).left(HPbarWidth / 8);
        new SwingEnemyHealthBarDrawer(enemy.getHealthBar(), healthBarPos, HPbarWidth).draw(g);
    }

    private void drawStatusEffects(Graphics g, Position2D enemyPos) {
        Position2D position = enemyPos.up(10).left(10);
        for (StatusEffectListEntry effect : enemy.getStatusEffects().getList()) {
            new SwingStatusEffectDrawer(effect, position).draw(g);
            position.setRight(20);
        }
    }
}
