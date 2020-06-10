package SlayTheSpider.Model.Menus;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;

public class TestMenu {
    @Test
    public void TestButton() {
        MenuButton button1 = new MenuButton("Desc1");
        MenuButton button2 = new MenuButton("Doosc");

        assertEquals(button1.getDescription(), "Desc1");
        assertEquals(button2.getDescription(), "Doosc");

        MenuCommand commandMock1 = Mockito.mock(MenuCommand.class);
        MenuCommand commandMock2 = Mockito.mock(MenuCommand.class);

        button1.addCommand(commandMock1);
        button1.addCommand(commandMock2);

        button1.click();

        Mockito.verify(commandMock1, times(1)).execute();
        Mockito.verify(commandMock2, times(1)).execute();

        button2.click();

        Mockito.verify(commandMock1, times(1)).execute();
        Mockito.verify(commandMock2, times(1)).execute();

        button2.addCommand(commandMock2);

        button2.click();

        Mockito.verify(commandMock1, times(1)).execute();
        Mockito.verify(commandMock2, times(2)).execute();
    }

    @Test
    public void TestNullButton() {
        IMenuButton menuButton = new NullMenuButton();
        menuButton.click();
        menuButton.addCommand(Mockito.mock(MenuCommand.class));

        assertEquals(menuButton.getDescription(), "");
        assertEquals(menuButton.getCommands().size(), 0);
    }

    @Test
    public void TestMenuTitle() {
        Menu menu1 = new Menu("Title1");
        Menu menu2 = new Menu("toitle");

        assertEquals(menu1.getTitle(), "Title1");
        assertEquals(menu2.getTitle(), "toitle");
    }

    @Test
    public void TestAdd() {
        Menu menu1 = new Menu("Title1");
        Menu menu2 = new Menu("toitle");

        IMenuButton buttonMock1 = Mockito.mock(IMenuButton.class);
        IMenuButton buttonMock2 = Mockito.mock(IMenuButton.class);

        menu1.addButton(buttonMock1);
        menu1.addButton(buttonMock2);

        assertEquals(menu1.getMenuButtons().get(0), buttonMock1);
        assertEquals(menu1.getMenuButtons().get(1), buttonMock2);
        assertEquals(menu1.getMenuButtons().size(), 2);

        assertEquals(menu2.getMenuButtons().size(), 0);
    }

    @Test
    public void TestLeftRightClick() {
        Menu menu1 = new Menu("Title1");

        IMenuButton buttonMock1 = Mockito.mock(IMenuButton.class);
        IMenuButton buttonMock2 = Mockito.mock(IMenuButton.class);
        IMenuButton buttonMock3 = Mockito.mock(IMenuButton.class);

        menu1.addButton(buttonMock1);
        menu1.addButton(buttonMock2);
        menu1.addButton(buttonMock3);

        assertEquals(menu1.getSelected(), buttonMock1);

        menu1.goLeft();

        assertEquals(menu1.getSelected(), buttonMock3);

        menu1.goLeft();
        menu1.goLeft();

        assertEquals(menu1.getSelected(), buttonMock1);

        menu1.goRight();

        assertEquals(menu1.getSelected(), buttonMock2);

        menu1.goRight();

        assertEquals(menu1.getSelected(), buttonMock3);

        menu1.click();

        Mockito.verify(buttonMock1, times(0)).click();
        Mockito.verify(buttonMock2, times(0)).click();
        Mockito.verify(buttonMock3, times(1)).click();

        menu1.goRight();
        menu1.click();

        Mockito.verify(buttonMock1, times(1)).click();
        Mockito.verify(buttonMock2, times(0)).click();
        Mockito.verify(buttonMock3, times(1)).click();
    }
}
