package SlayTheSpider.Model.Game.MainMenu.MainMenuCommands;

import SlayTheSpider.Model.Menus.MenuCommand;

public abstract class MainMenuCommand implements MenuCommand {
    protected MainMenuObserver observer;

    public MainMenuCommand(MainMenuObserver observer) {
        this.observer = observer;
    }
}
