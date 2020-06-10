package SlayTheSpider.MVC;

import SlayTheSpider.Controller.GameOver.GameOverLoss;
import SlayTheSpider.Controller.GameOver.GameOverType;
import SlayTheSpider.Controller.GameOver.GameOverWin;
import SlayTheSpider.Controller.GameOverController;
import SlayTheSpider.Controller.MainMenuController;
import SlayTheSpider.Factory.GameEvent;
import SlayTheSpider.Controller.ControllerObserver;
import SlayTheSpider.Model.OverworldModel;
import SlayTheSpider.View.View;
import SlayTheSpider.View.ViewFactory;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

public class TestOver {
    @Test
    public void TestController() {
        OverworldModel modelMock = Mockito.mock(OverworldModel.class);
        GameOverType typeMock = Mockito.mock(GameOverType.class);
        ViewFactory factory = Mockito.mock(ViewFactory.class);
        View viewMock = Mockito.mock(View.class);
        ControllerObserver observerMock = Mockito.mock(ControllerObserver.class);
        Mockito.when(factory.makeOWView(any())).thenReturn(viewMock);

        GameOverController controller = new GameOverController(modelMock, factory, observerMock, typeMock);
        controller.processEvent(GameEvent.Enter);
        Mockito.verify(observerMock, Mockito.times(1)).setController(any(MainMenuController.class));

        controller.drawView();
        Mockito.verify(viewMock, times(1)).setTitle(any());
        Mockito.verify(viewMock, times(1)).draw();

        controller.update();
    }

    @Test
    public void TestTypes() {
        GameOverType typeWin = new GameOverWin();
        assertTrue(typeWin.victory());
        assertEquals(typeWin.getTitle(), "You defeated the final boss! Press any key to leave.");


        GameOverType typeLoss = new GameOverLoss();
        assertFalse(typeLoss.victory());
        assertEquals(typeLoss.getTitle(), "You lost! Press any key to leave.");
    }
}
