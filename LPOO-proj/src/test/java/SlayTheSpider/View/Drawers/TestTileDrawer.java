package SlayTheSpider.View.Drawers;

import SlayTheSpider.View.Drawers.LanternaDrawers.LanternaTileDrawer;
import SlayTheSpider.View.Drawers.SwingDrawers.SwingTileDrawer;
import SlayTheSpider.Model.Game.Overworld.OverWorldElements.Tile;
import SlayTheSpider.Model.Game.Overworld.OverWorldElements.Visibility.ElementVisibility;
import SlayTheSpider.Model.Position.Position;
import SlayTheSpider.View.TextWindow.LanternaGraphics;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.awt.*;

import static org.mockito.Mockito.times;

public class TestTileDrawer {
    private Tile tile = Mockito.mock(Tile.class);
    private ElementVisibility visibility = Mockito.mock(ElementVisibility.class);

    @Before
    public void setup() {
        tile = Mockito.mock(Tile.class);
        visibility = Mockito.mock(ElementVisibility.class);

        Mockito.when(tile.getVisibilitiy()).thenReturn(visibility);
        Mockito.when(tile.getColor()).thenReturn("ffffff");
        Mockito.when(tile.getPosition()).thenReturn(new Position(7, 8));
    }

    @Test
    public void TestDrawSwing() {
        Graphics graphics = Mockito.mock(Graphics.class);
        SwingTileDrawer drawer = new SwingTileDrawer(tile);

        Mockito.when(visibility.getType()).thenReturn(ElementVisibility.VisibilityType.Invisible);
        drawer.draw(graphics);
        Mockito.verifyZeroInteractions(graphics);

        Mockito.when(visibility.getType()).thenReturn(ElementVisibility.VisibilityType.VisibleFar);
        drawer.draw(graphics);
        Mockito.verify(graphics, times(1)).setColor(new Color(178, 178, 178));
        Mockito.verify(graphics, times(1)).fillRect(70, 80, 10, 10);

        Mockito.when(visibility.getType()).thenReturn(ElementVisibility.VisibilityType.VisibleNear);
        drawer.draw(graphics);
        Mockito.verify(graphics, times(1)).setColor(new Color(255, 255, 255));
        Mockito.verify(graphics, times(2)).fillRect(70, 80, 10, 10);

    }

    @Test
    public void TestDrawLanterna() {
        LanternaGraphics graphics = Mockito.mock(LanternaGraphics.class);
        LanternaTileDrawer drawer = new LanternaTileDrawer(tile);

        Mockito.when(visibility.getType()).thenReturn(ElementVisibility.VisibilityType.Invisible);
        drawer.draw(graphics);
        Mockito.verifyZeroInteractions(graphics);

        Mockito.when(visibility.getType()).thenReturn(ElementVisibility.VisibilityType.VisibleFar);
        drawer.draw(graphics);
        Mockito.verify(graphics, times(1)).setBackgroundColor("b2b2b2");
        Mockito.verify(graphics, times(1)).drawRect(new Position(7, 8), 1, 1);

        Mockito.when(visibility.getType()).thenReturn(ElementVisibility.VisibilityType.VisibleNear);
        drawer.draw(graphics);
        Mockito.verify(graphics, times(1)).setBackgroundColor("ffffff");
        Mockito.verify(graphics, times(2)).drawRect(new Position(7, 8), 1, 1);
    }

}
