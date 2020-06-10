package SlayTheSpider.View.Drawers.LanternaDrawers;

import SlayTheSpider.Model.Menus.IMenuButton;
import SlayTheSpider.Model.Position.Position;
import SlayTheSpider.View.TextWindow.LanternaGraphics;

public class LanternaMenuButtonDrawer implements LanternaDrawer {
    private IMenuButton menuItem;
    private IMenuButton selected;
    private Position position;

    public LanternaMenuButtonDrawer(IMenuButton menuItem, IMenuButton selected, Position position) {
        this.menuItem = menuItem;
        this.selected = selected;
        this.position = position;
    }

    @Override
    public void draw(LanternaGraphics drawer) {
        if (menuItem == selected) {
            drawer.setTextColor("ff0000");
            drawer.drawString("--> " + menuItem.getDescription(), position);
        }
        else {
            drawer.setTextColor("ffffff");
            drawer.drawString( " - " + menuItem.getDescription(), position);
        }
    }

}
