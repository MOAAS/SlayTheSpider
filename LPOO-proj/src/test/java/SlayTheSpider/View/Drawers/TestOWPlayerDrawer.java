package SlayTheSpider.View.Drawers;

import SlayTheSpider.View.Drawers.LanternaDrawers.LanternaOverworldPlayerDrawer;
import SlayTheSpider.View.Drawers.SwingDrawers.SwingOverworldPlayerDrawer;
import SlayTheSpider.Model.Game.OverworldPlayer;
import SlayTheSpider.Model.Position.Position;
import SlayTheSpider.Model.Sprites.PlayerOWSpriteSheet;
import SlayTheSpider.Model.Sprites.Sprite;
import SlayTheSpider.View.TextWindow.LanternaGraphics;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.awt.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

public class TestOWPlayerDrawer {
    private OverworldPlayer player = mock(OverworldPlayer.class);
    private PlayerOWSpriteSheet spriteSheet = mock(PlayerOWSpriteSheet.class);
    private Sprite spriteFront = mock(Sprite.class);
    private Sprite spriteBack = mock(Sprite.class);
    private Sprite spriteLeft = mock(Sprite.class);
    private Sprite spriteRight = mock(Sprite.class);
    private Sprite playerSprite = mock(Sprite.class);

    @Before
    public void setup() {
        player = mock(OverworldPlayer.class);
        playerSprite = mock(Sprite.class);
        spriteSheet = mock(PlayerOWSpriteSheet.class);
        spriteFront = mock(Sprite.class);
        spriteBack = mock(Sprite.class);
        spriteLeft = mock(Sprite.class);
        spriteRight = mock(Sprite.class);

        Mockito.when(spriteSheet.getFront()).thenReturn(spriteFront);
        Mockito.when(spriteSheet.getBack()).thenReturn(spriteBack);
        Mockito.when(spriteSheet.getLeft()).thenReturn(spriteLeft);
        Mockito.when(spriteSheet.getRight()).thenReturn(spriteRight);

        Mockito.when(spriteFront.getHeight()).thenReturn(25);
        Mockito.when(spriteBack.getHeight()).thenReturn(25);
        Mockito.when(spriteLeft.getHeight()).thenReturn(25);
        Mockito.when(spriteRight.getHeight()).thenReturn(25);

        Mockito.when(spriteFront.getWidth()).thenReturn(15);
        Mockito.when(spriteBack.getWidth()).thenReturn(15);
        Mockito.when(spriteLeft.getWidth()).thenReturn(15);
        Mockito.when(spriteRight.getWidth()).thenReturn(15);

        Mockito.when(player.getPosition()).thenReturn(new Position(2,3));
    }

    @Test
    public void TestDrawSwing() {
        Graphics graphics = Mockito.mock(Graphics.class);
        SwingOverworldPlayerDrawer drawer = new SwingOverworldPlayerDrawer(player, spriteSheet);

        Mockito.when(player.getDirection()).thenReturn(OverworldPlayer.Direction.UP);
        drawer.draw(graphics);
        Mockito.verify(spriteBack, times(1)).draw(any(), eq(new Position(17, 18)));

        Mockito.when(player.getDirection()).thenReturn(OverworldPlayer.Direction.DOWN);
        drawer.draw(graphics);
        Mockito.verify(spriteFront, times(1)).draw(any(), eq(new Position(17, 18)));

        Mockito.when(player.getDirection()).thenReturn(OverworldPlayer.Direction.LEFT);
        drawer.draw(graphics);
        Mockito.verify(spriteLeft, times(1)).draw(any(), eq(new Position(17, 18)));

        Mockito.when(player.getDirection()).thenReturn(OverworldPlayer.Direction.RIGHT);
        drawer.draw(graphics);
        Mockito.verify(spriteRight, times(1)).draw(any(), eq(new Position(17, 18)));
    }

    @Test
    public void TestDrawLanterna() {
        LanternaGraphics graphics = Mockito.mock(LanternaGraphics.class);
        LanternaOverworldPlayerDrawer drawer = new LanternaOverworldPlayerDrawer(player, playerSprite);


        drawer.draw(graphics);
        Mockito.verify(playerSprite, times(1)).draw(any(), eq(new Position(2, 3)));


    }

}
