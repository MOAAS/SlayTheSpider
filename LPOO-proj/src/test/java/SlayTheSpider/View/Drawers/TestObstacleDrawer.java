package SlayTheSpider.View.Drawers;

import SlayTheSpider.View.Drawers.LanternaDrawers.LanternaObstacleDrawer;
import SlayTheSpider.View.Drawers.SwingDrawers.SwingObstacleDrawer;
import SlayTheSpider.Model.Game.Overworld.OverWorldElements.OverworldObstacle;
import SlayTheSpider.Model.Game.Overworld.OverWorldElements.Visibility.ElementVisibility;
import SlayTheSpider.Model.Position.Position;
import SlayTheSpider.Model.Sprites.Sprite;
import SlayTheSpider.View.TextWindow.LanternaGraphics;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.awt.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

public class TestObstacleDrawer {
    private OverworldObstacle obstacle = Mockito.mock(OverworldObstacle.class);
    private ElementVisibility visibility = Mockito.mock(ElementVisibility.class);
    private Sprite sprite = Mockito.mock(Sprite.class);
    private Sprite endarkndSprite = Mockito.mock(Sprite.class);

    @Before
    public void setup() {
        obstacle = Mockito.mock(OverworldObstacle.class);
        visibility = Mockito.mock(ElementVisibility.class);

        Mockito.when(sprite.endarken()).thenReturn(endarkndSprite);
        Mockito.when(obstacle.getVisibilitiy()).thenReturn(visibility);
        Mockito.when(obstacle.getSprite()).thenReturn(sprite);
        Mockito.when(obstacle.getPosition()).thenReturn(new Position(2, 4));
    }

    @Test
    public void TestDrawSwing() {
        Graphics graphics = Mockito.mock(Graphics.class);
        SwingObstacleDrawer drawer = new SwingObstacleDrawer(obstacle);

        Mockito.when(visibility.getType()).thenReturn(ElementVisibility.VisibilityType.Invisible);
        drawer.draw(graphics);
        Mockito.verify(sprite, never()).draw(any(), eq(new Position(20, 40)));

        Mockito.when(visibility.getType()).thenReturn(ElementVisibility.VisibilityType.VisibleFar);
        drawer.draw(graphics);
        Mockito.verify(sprite, times(1)).draw(any(), eq(new Position(20, 40)));

        Mockito.when(visibility.getType()).thenReturn(ElementVisibility.VisibilityType.VisibleNear);
        drawer.draw(graphics);
        Mockito.verify(sprite, times(2)).draw(any(), eq(new Position(20, 40)));
    }

    @Test
    public void TestDrawLanterna() {
        LanternaGraphics graphics = Mockito.mock(LanternaGraphics.class);
        LanternaObstacleDrawer drawer = new LanternaObstacleDrawer(obstacle);

        Mockito.when(visibility.getType()).thenReturn(ElementVisibility.VisibilityType.Invisible);
        drawer.draw(graphics);
        Mockito.verify(sprite, never()).draw(any(), eq(new Position(20, 40)));

        Mockito.when(visibility.getType()).thenReturn(ElementVisibility.VisibilityType.VisibleFar);
        drawer.draw(graphics);
        Mockito.verify(endarkndSprite, times(1)).draw(any(), eq(new Position(2, 4)));

        Mockito.when(visibility.getType()).thenReturn(ElementVisibility.VisibilityType.VisibleNear);
        drawer.draw(graphics);
        Mockito.verify(sprite, times(1)).draw(any(), eq(new Position(2, 4)));
    }

}
