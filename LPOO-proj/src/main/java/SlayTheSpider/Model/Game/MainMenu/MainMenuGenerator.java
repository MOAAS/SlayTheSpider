package SlayTheSpider.Model.Game.MainMenu;

import SlayTheSpider.Model.Game.MainMenu.MainMenuCommands.ChangeGraphicsCommand;
import SlayTheSpider.Model.Game.MainMenu.MainMenuCommands.MainMenuObserver;
import SlayTheSpider.Model.Game.MainMenu.MainMenuCommands.NewGameCommand;
import SlayTheSpider.Model.Game.MainMenu.MainMenuCommands.QuitGameCommand;
import SlayTheSpider.Model.Menus.Menu;
import SlayTheSpider.Model.Menus.MenuButton;

public class MainMenuGenerator {
    public Menu makeMenu(MainMenuObserver observer) {
        Menu menu = new Menu("Main Menu");

        MenuButton newGameItem = new MenuButton("New Game");
        MenuButton quitGameItem = new MenuButton("Quit Game");
        MenuButton changeGraphicsItem = new MenuButton("Change Graphics");

        newGameItem.addCommand(new NewGameCommand(observer));
        quitGameItem.addCommand(new QuitGameCommand(observer));
        changeGraphicsItem.addCommand(new ChangeGraphicsCommand(observer));

        menu.addButton(newGameItem);
        menu.addButton(changeGraphicsItem);
        menu.addButton(quitGameItem);

        return menu;
    }
}
