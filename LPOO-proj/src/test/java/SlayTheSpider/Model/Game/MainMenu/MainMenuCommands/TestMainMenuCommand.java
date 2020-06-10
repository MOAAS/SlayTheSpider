package SlayTheSpider.Model.Game.MainMenu.MainMenuCommands;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;

public class TestMainMenuCommand {
    @Test
    public void TestNewGame() {
        MainMenuObserver observerMock = Mockito.mock(MainMenuObserver.class);

        MainMenuCommand newGameCommand = new NewGameCommand(observerMock);

        newGameCommand.execute();

        Mockito.verify(observerMock, times(1)).notifiedNewGame();

        assertEquals(newGameCommand.observer, observerMock);

    }

    @Test
    public void TestQuitGame() {
        MainMenuObserver observerMock = Mockito.mock(MainMenuObserver.class);

        MainMenuCommand quitGameCommand = new QuitGameCommand(observerMock);

        quitGameCommand.execute();

        Mockito.verify(observerMock, times(1)).notifiedQuitGame();

        assertEquals(quitGameCommand.observer, observerMock);
    }

    @Test
    public void TestChangeGraphics() {
        MainMenuObserver observerMock = Mockito.mock(MainMenuObserver.class);

        MainMenuCommand changeGraphicsCMD = new ChangeGraphicsCommand(observerMock);

        changeGraphicsCMD.execute();

        Mockito.verify(observerMock, times(1)).notifiedChangeGraphics();

        assertEquals(changeGraphicsCMD.observer, observerMock);
    }
}
