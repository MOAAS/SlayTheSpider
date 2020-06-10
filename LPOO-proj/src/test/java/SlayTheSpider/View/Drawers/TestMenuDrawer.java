package SlayTheSpider.View.Drawers;

import SlayTheSpider.View.Drawers.LanternaDrawers.LanternaMenuDrawer;
import SlayTheSpider.View.Drawers.SwingDrawers.SwingMenuDrawer;
import SlayTheSpider.Model.Menus.IMenuButton;
import SlayTheSpider.Model.Menus.Menu;
import SlayTheSpider.Model.Position.Position;
import SlayTheSpider.View.TextWindow.LanternaGraphics;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;

public class TestMenuDrawer {
    private Menu menu = Mockito.mock(Menu.class);
    @Before
    public void setup() {
        menu = Mockito.mock(Menu.class);

        List<IMenuButton> buttons = new ArrayList<>();
        IMenuButton button1 = Mockito.mock(IMenuButton.class);
        IMenuButton button2 = Mockito.mock(IMenuButton.class);
        IMenuButton button3 = Mockito.mock(IMenuButton.class);
        Mockito.when(button1.getDescription()).thenReturn("DESC1");
        Mockito.when(button2.getDescription()).thenReturn("DESC2");
        Mockito.when(button3.getDescription()).thenReturn("DESC3");
        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);

        Mockito.when(menu.getTitle()).thenReturn("TITLE");
        Mockito.when(menu.getMenuButtons()).thenReturn(buttons);
        Mockito.when(menu.getSelected()).thenReturn(button2);
    }

    @Test
    public void TestDrawSwing() {
        FontMetrics metrics = Mockito.mock(FontMetrics.class);
        Graphics graphics = Mockito.mock(Graphics.class);
        Mockito.when(graphics.getFontMetrics()).thenReturn(metrics);
        Mockito.when(metrics.stringWidth(anyString())).thenReturn(12);
        SwingMenuDrawer drawer = new SwingMenuDrawer(menu, 80);

        drawer.draw(graphics);
        Mockito.verify(graphics, times(4)).drawString(anyString(), anyInt(), anyInt());
        Mockito.verify(graphics, times(9)).setColor(any());
        Mockito.verify(graphics, times(1)).setColor(new Color(255, 128, 0));
        Mockito.verify(graphics, times(4)).setFont(any());
        Mockito.verify(graphics, times(1)).drawString("SLAY THE SPIDER", 130, 150);
        Mockito.verify(graphics, times(1)).drawString("DESC1", 34, 300);
        Mockito.verify(graphics, times(1)).drawString("DESC2", 34, 480);
        Mockito.verify(graphics, times(1)).fillRect(-340, 195, 760, 160);
        Mockito.verify(graphics, times(1)).fillRect(-335, 200, 750, 150);
        Mockito.verify(graphics, times(1)).fillRect(-340, 375, 760, 160);
        Mockito.verify(graphics, times(1)).fillRect(-335, 380, 750, 150);

    }

    @Test
    public void TestDrawLanterna() {
        LanternaGraphics graphics = Mockito.mock(LanternaGraphics.class);
        LanternaMenuDrawer drawer = new LanternaMenuDrawer(menu);

        drawer.draw(graphics);
        Mockito.verify(graphics, times(4)).drawString(anyString(), any());
        Mockito.verify(graphics, times(3)).setTextColor(anyString());
        Mockito.verify(graphics, times(1)).drawString("TITLE", new Position(0, 4));
        Mockito.verify(graphics, times(1)).drawString(" - DESC1", new Position(0, 4));
        Mockito.verify(graphics, times(1)).drawString("--> DESC2", new Position(0, 4));
    }
}
