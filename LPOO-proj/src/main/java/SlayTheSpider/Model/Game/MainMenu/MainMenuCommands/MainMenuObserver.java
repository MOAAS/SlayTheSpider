package SlayTheSpider.Model.Game.MainMenu.MainMenuCommands;

public interface MainMenuObserver {
    void notifiedQuitGame();
    void notifiedNewGame();
    void notifiedChangeGraphics();
}
