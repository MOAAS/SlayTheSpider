package SlayTheSpider.MVC;

import SlayTheSpider.Factory.GameEvent;
import SlayTheSpider.Model.Menus.Menu;
import SlayTheSpider.Controller.MainMenuController;
import SlayTheSpider.Model.MainMenuModel;
import SlayTheSpider.View.MainMenuViewLanterna;
import SlayTheSpider.View.MainMenuViewSwing;
import SlayTheSpider.View.View;
import SlayTheSpider.View.ViewFactory;
import SlayTheSpider.View.TextWindow.LanternaGraphics;
import SlayTheSpider.View.TextWindow.LanternaWindow;
import SlayTheSpider.View.TextWindow.SwingGamePanel;
import SlayTheSpider.View.TextWindow.SwingWindow;
import org.junit.Test;
import org.mockito.Mockito;

import java.awt.*;

import static org.junit.Assert.assertSame;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;

public class TestMenuMVC {

    @Test
    public void TestModel() {
        Menu menuMock = Mockito.mock(Menu.class);

        MainMenuModel model = new MainMenuModel(menuMock);

        assertSame(model.getMainMenu(), menuMock);
    }

    @Test
    public void TestViewL() {
        LanternaWindow windowMock = Mockito.mock(LanternaWindow.class);
        LanternaGraphics graphics = Mockito.mock(LanternaGraphics.class);
        Menu menuMock = Mockito.mock(Menu.class);
        MainMenuModel modelMock = Mockito.mock(MainMenuModel.class);
        Mockito.when(modelMock.getMainMenu()).thenReturn(menuMock);
        Mockito.when(windowMock.getTextDrawer()).thenReturn(graphics);

        new MainMenuViewLanterna(modelMock, windowMock).draw();
        Mockito.verify(graphics, times(1)).drawString(any(), any());


    }

    @Test
    public void TestViewS() {
        SwingWindow windowMock = Mockito.mock(SwingWindow.class);
        Mockito.when(windowMock.getPanel()).thenReturn(Mockito.mock(SwingGamePanel.class));

        Graphics graphics = Mockito.mock(Graphics.class);
        Menu menuMock = Mockito.mock(Menu.class);
        MainMenuModel modelMock = Mockito.mock(MainMenuModel.class);
        Mockito.when(modelMock.getMainMenu()).thenReturn(menuMock);

        new MainMenuViewSwing(modelMock, windowMock).draw(graphics);
        Mockito.verify(graphics, times(1)).drawString(anyString(), anyInt(), anyInt());
    }

    @Test
    public void TestController() {
        Menu menuMock = Mockito.mock(Menu.class);
        MainMenuModel modelMock = Mockito.mock(MainMenuModel.class);
        ViewFactory factory = Mockito.mock(ViewFactory.class);
        View viewMock = Mockito.mock(View.class);

        Mockito.when(factory.makeMenuView(any())).thenReturn(viewMock);
        Mockito.when(modelMock.getMainMenu()).thenReturn(menuMock);

        MainMenuController controller = new MainMenuController(modelMock, factory);

        controller.update();
        controller.drawView();

        Mockito.verify(viewMock, times(1)).draw();

        controller.processEvent(GameEvent.ArrowRight);
        Mockito.verify(menuMock, times(1)).goRight();

        controller.processEvent(GameEvent.ArrowUp);
        Mockito.verify(menuMock, times(1)).goLeft();

        controller.processEvent(GameEvent.ArrowLeft);
        Mockito.verify(menuMock, times(2)).goLeft();

        controller.processEvent(GameEvent.ArrowDown);
        Mockito.verify(menuMock, times(2)).goRight();

        controller.processEvent(GameEvent.Enter);
        Mockito.verify(menuMock, times(1)).click();

        controller.processEvent(GameEvent.Esc);
    }

}
