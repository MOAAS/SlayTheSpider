package SlayTheSpider.Model.Game.MainMenu.MainMenuCommands;

public class NewGameCommand extends MainMenuCommand {

    public NewGameCommand(MainMenuObserver observer) {
        super(observer);
    }

    @Override
    public void execute() {
        this.observer.notifiedNewGame();
    }
}
