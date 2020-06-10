package SlayTheSpider.Model;

import SlayTheSpider.Model.Menus.Menu;

public class MainMenuModel implements Model {
    private final Menu mainMenu;

    public MainMenuModel(Menu mainMenu) {
        this.mainMenu = mainMenu;
    }

    public Menu getMainMenu() {
        return mainMenu;
    }
}
