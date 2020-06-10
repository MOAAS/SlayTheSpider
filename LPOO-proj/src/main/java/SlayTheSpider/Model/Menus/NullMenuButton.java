package SlayTheSpider.Model.Menus;

import java.util.ArrayList;
import java.util.List;

public class NullMenuButton implements IMenuButton {
    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public void click() {
        return;
    }

    @Override
    public void addCommand(MenuCommand command) { }

    @Override
    public List<MenuCommand> getCommands() {
        return new ArrayList<>();
    }
}
