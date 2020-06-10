package SlayTheSpider.View.Drawers.LanternaDrawers;

import SlayTheSpider.Model.Sprites.Sprite;
import SlayTheSpider.View.TextWindow.LanternaGraphics;
import SlayTheSpider.Model.Game.OverworldPlayer;

public class LanternaOverworldPlayerDrawer implements LanternaDrawer {
    private final OverworldPlayer player;
    private final Sprite playerSprite;

    public LanternaOverworldPlayerDrawer(OverworldPlayer player, Sprite playerSprite) {
        this.player = player;
        this.playerSprite = playerSprite;
    }

    @Override
    public void draw(LanternaGraphics drawer) {
        playerSprite.draw(drawer, player.getPosition());
    }
}
