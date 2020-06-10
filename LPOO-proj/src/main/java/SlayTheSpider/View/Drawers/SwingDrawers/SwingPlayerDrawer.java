package SlayTheSpider.View.Drawers.SwingDrawers;

import SlayTheSpider.Model.Sprites.PlayerSpriteSheet;
import SlayTheSpider.View.TextWindow.SwingGraphics;
import SlayTheSpider.Model.Game.Character;
import SlayTheSpider.Model.Game.Enemy;
import SlayTheSpider.Model.Game.Player;
import SlayTheSpider.Model.Game.StatusEffect.StatusEffectListEntry;
import SlayTheSpider.Model.Position.Position;
import SlayTheSpider.Model.Position.Position2D;

import java.awt.*;

public class SwingPlayerDrawer implements SwingDrawer {
    private static final Position2D PLAYER_POS = new Position(30, 180);

    private final Player player;
    private final PlayerSpriteSheet spriteSheet;

    public SwingPlayerDrawer(Player player, PlayerSpriteSheet spriteSheet) {
        this.player = player;
        this.spriteSheet = spriteSheet;
    }

    public void draw(Graphics g) {
        this.spriteSheet.getPlayer().draw(new SwingGraphics(g), PLAYER_POS);
        this.drawStatusEffects(g);
        this.drawHealthBar(g);
        this.drawTarget(g);
    }

    private void drawTarget(Graphics g) {
        Position2D targetPos = PLAYER_POS;
        Character target = player.getTarget();

        for (Enemy enemy : player.getTargets().getEnemies()) {
            if (target == enemy)
                targetPos = enemy.getPosition().mult(10, 15);
        }
        targetPos = targetPos.right(target.getSprite().getWidth() / 2);
        g.setColor(Color.WHITE);
        g.drawString("|", targetPos.getX(), targetPos.getY() - 20);
        g.drawString("|", targetPos.getX(), targetPos.getY() - 10);
        g.drawString("V", targetPos.getX(), targetPos.getY());
    }

    private void drawHealthBar(Graphics g) {
        Position2D healthBarPos = PLAYER_POS.down(player.getSprite().getHeight());
        new SwingPlayerHealthBarDrawer(player.getHealthBar(), healthBarPos, this.spriteSheet.getManaFull(), this.spriteSheet.getManaEmpty()).draw(g);
    }

    private void drawStatusEffects(Graphics g) {
        Position2D position = new Position(PLAYER_POS.getX(), PLAYER_POS.getY());
        for (StatusEffectListEntry effect : player.getStatusEffects().getList()) {
            new SwingStatusEffectDrawer(effect, position).draw(g);
            position.setRight(20);
        }
    }
}
