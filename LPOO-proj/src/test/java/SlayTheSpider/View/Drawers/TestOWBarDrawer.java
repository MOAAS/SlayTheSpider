package SlayTheSpider.View.Drawers;

import SlayTheSpider.View.Drawers.LanternaDrawers.LanternaOverworldHealthBarDrawer;
import SlayTheSpider.View.Drawers.SwingDrawers.SwingOverworldHealthBarDrawer;
import SlayTheSpider.Model.Game.HealthBar.PlayerHealthBar;
import SlayTheSpider.Model.Position.Position;
import SlayTheSpider.View.TextWindow.LanternaGraphics;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.awt.*;

import static org.mockito.Mockito.times;

public class TestOWBarDrawer {
    private PlayerHealthBar healthBar = Mockito.mock(PlayerHealthBar.class);

    @Before
    public void setup() {
        healthBar = Mockito.mock(PlayerHealthBar.class);

        Mockito.when(healthBar.getMaxHealth()).thenReturn(10);
        Mockito.when(healthBar.getHealth()).thenReturn(4);
        Mockito.when(healthBar.getRemainingHealthPerc()).thenReturn(0.4);
    }

    @Test
    public void TestDrawSwing() {
        Graphics graphics = Mockito.mock(Graphics.class);
        SwingOverworldHealthBarDrawer drawer = new SwingOverworldHealthBarDrawer(healthBar, 20);

        drawer.draw(graphics);

        Mockito.verify(graphics, times(1)).drawString("4/10", 174, -68);
        Mockito.verify(graphics, times(1)).fillRect(18, -82, 154, 19);

    }

    @Test
    public void TestDrawLanterna() {
        LanternaGraphics graphics = Mockito.mock(LanternaGraphics.class);
        LanternaOverworldHealthBarDrawer drawer = new LanternaOverworldHealthBarDrawer(healthBar, 20);

        drawer.draw(graphics);
        Mockito.verify(graphics, times(1)).drawRect(new Position(0,19), 6, 1);
        Mockito.verify(graphics, times(1)).drawString("4/10", new Position(15, 19));


    }
}
