package SlayTheSpider.Model.Menus;

import java.util.List;

public interface IMenuButton {
    String getDescription();
    void click();
    void addCommand(MenuCommand command);
    List<MenuCommand> getCommands();
}
