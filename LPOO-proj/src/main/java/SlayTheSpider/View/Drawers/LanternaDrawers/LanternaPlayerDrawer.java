package SlayTheSpider.View.Drawers.LanternaDrawers;

import SlayTheSpider.Model.Sprites.Sprite;
import SlayTheSpider.Model.Game.*;
import SlayTheSpider.Model.Game.Character;
import SlayTheSpider.Model.Game.StatusEffect.StatusEffectListEntry;
import SlayTheSpider.View.TextWindow.LanternaGraphics;
import SlayTheSpider.Model.Position.Position;
import SlayTheSpider.Model.Position.Position2D;

public class LanternaPlayerDrawer implements LanternaDrawer {
    private static final Position2D PLAYER_POS = new Position(2, 10);

    private final Sprite playerSprite;
    private Player player;

    public LanternaPlayerDrawer(Player player, Sprite playerSprite) {
        this.player = player;
        this.playerSprite = playerSprite;
    }


    public void draw(LanternaGraphics drawer) {
        this.drawStatusEffects(drawer);
        this.drawHealthBar(drawer);
        this.playerSprite.draw(drawer, PLAYER_POS);
        this.drawTarget(drawer);
    }

    private void drawTarget(LanternaGraphics drawer) {
        Position2D targetPos = PLAYER_POS;
        Character target = player.getTarget();

        for (Enemy enemy : player.getTargets().getEnemies()) {
            if (target == enemy)
                targetPos = enemy.getPosition();
        }
        drawer.setTextColor("ffffff");
        drawer.drawString(" | ", targetPos.up(3).right(1));
        drawer.drawString(" | ", targetPos.up(2).right(1));
        drawer.drawString(" V", targetPos.up(1).right(1));
    }

    private void drawHealthBar(LanternaGraphics drawer) {
        Position2D healthBarPos = PLAYER_POS.down(this.player.getSprite().getHeight());
        new LanternaPlayerHealthBarDrawer(player.getHealthBar(), healthBarPos).draw(drawer);
    }

    private void drawStatusEffects(LanternaGraphics drawer) {
        Position2D position = PLAYER_POS.up(1);
        for (StatusEffectListEntry effect : player.getStatusEffects().getList()) {
            new LanternaStatusEffectDrawer(effect, position).draw(drawer);
            position.setRight(2);
        }
    }

}
