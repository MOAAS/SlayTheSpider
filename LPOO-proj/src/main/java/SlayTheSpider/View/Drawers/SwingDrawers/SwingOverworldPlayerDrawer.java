package SlayTheSpider.View.Drawers.SwingDrawers;

import SlayTheSpider.Model.Sprites.PlayerOWSpriteSheet;
import SlayTheSpider.Model.Sprites.Sprite;
import SlayTheSpider.View.TextWindow.SwingGraphics;
import SlayTheSpider.Model.Game.OverworldPlayer;
import SlayTheSpider.Model.Position.Position2D;

import java.awt.*;

public class SwingOverworldPlayerDrawer implements SwingDrawer {
    private final OverworldPlayer player;
    private final PlayerOWSpriteSheet spriteSheet;

    public SwingOverworldPlayerDrawer(OverworldPlayer player, PlayerOWSpriteSheet spriteSheet) {
        this.player = player;
        this.spriteSheet = spriteSheet;
    }

    @Override
    public void draw(Graphics g) {
        switch (player.getDirection()) {
            case UP: this.drawSprite(spriteSheet.getBack(), g); break;
            case DOWN: this.drawSprite(spriteSheet.getFront(), g); break;
            case LEFT: this.drawSprite(spriteSheet.getLeft(), g); break;
            case RIGHT: this.drawSprite(spriteSheet.getRight(), g); break;
        }
    }

    private void drawSprite(Sprite sprite, Graphics g) {
        Position2D pos = player.getPosition().mult(10, 10).up(sprite.getHeight() / 2).left(sprite.getWidth() / 5);
        sprite.draw(new SwingGraphics(g), pos);
    }
}
