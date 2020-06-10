package SlayTheSpider.Model.Menus;

import java.util.ArrayList;
import java.util.List;

public class MenuButton implements IMenuButton {
    private String description;
    private List<MenuCommand> commands = new ArrayList<>();

    public MenuButton(String description) {
        this.description = description;
    }

    public void click() {
        for (MenuCommand command : commands) {
            command.execute();
        }
    }

    public void addCommand(MenuCommand command) {
        commands.add(command);
    }

    public List<MenuCommand> getCommands() {
        return commands;
    }

    public String getDescription() {
        return description;
    }
}
