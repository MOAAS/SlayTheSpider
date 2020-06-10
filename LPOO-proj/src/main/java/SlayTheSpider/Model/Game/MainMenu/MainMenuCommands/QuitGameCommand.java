package SlayTheSpider.Model.Game.MainMenu.MainMenuCommands;

public class QuitGameCommand extends MainMenuCommand {

    public QuitGameCommand(MainMenuObserver observer) {
        super(observer);
    }

    @Override
    public void execute() {
        this.observer.notifiedQuitGame();
    }
}
