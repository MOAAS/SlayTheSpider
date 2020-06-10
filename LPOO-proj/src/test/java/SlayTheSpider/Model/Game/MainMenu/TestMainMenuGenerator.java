package SlayTheSpider.Model.Game.MainMenu;

import SlayTheSpider.Model.Game.MainMenu.MainMenuCommands.MainMenuObserver;
import SlayTheSpider.Model.Menus.Menu;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestMainMenuGenerator {
    @Test
    public void TestGeneral() {
        new MainMenuGenerator();
        Menu menu = new MainMenuGenerator().makeMenu(Mockito.mock(MainMenuObserver.class));

        assertNotNull(menu);
        assertEquals(menu.getMenuButtons().size(), 3);
        assertEquals(menu.getMenuButtons().get(0).getCommands().size(), 1);
        assertEquals(menu.getMenuButtons().get(1).getCommands().size(), 1);
        assertEquals(menu.getMenuButtons().get(2).getCommands().size(), 1);
    }
}
