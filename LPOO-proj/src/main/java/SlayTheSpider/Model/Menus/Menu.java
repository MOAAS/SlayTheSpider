package SlayTheSpider.Model.Menus;

import SlayTheSpider.Model.ListCycler.*;

import java.util.List;


public class Menu {
    private ListCycler<IMenuButton> menuButtons = new ArrayListCycler<IMenuButton>(new NullMenuButton());
    private final String title;

    public Menu(String title) {
        this.title = title;
    }

    public void addButton(IMenuButton button) {
        menuButtons.add(button);
    }

    public void goRight() {
        menuButtons.selectRight();
    }

    public void goLeft() {
        menuButtons.selectLeft();
    }

    public List<IMenuButton> getMenuButtons() {
        return menuButtons.getList();
    }

    public IMenuButton getSelected() {
        return menuButtons.getSelected();
    }

    public String getTitle() {
        return title;
    }

    public void click() {
        menuButtons.getSelected().click();
    }
}
