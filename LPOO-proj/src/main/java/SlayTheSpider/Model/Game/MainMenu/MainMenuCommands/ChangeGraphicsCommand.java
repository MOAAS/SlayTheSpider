package SlayTheSpider.Model.Game.MainMenu.MainMenuCommands;

public class ChangeGraphicsCommand extends MainMenuCommand {
    public ChangeGraphicsCommand(MainMenuObserver observer) {
        super(observer);
    }

    @Override
    public void execute() {
        this.observer.notifiedChangeGraphics();
    }
}
