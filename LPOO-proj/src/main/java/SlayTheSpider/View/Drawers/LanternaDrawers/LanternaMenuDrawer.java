package SlayTheSpider.View.Drawers.LanternaDrawers;

import SlayTheSpider.Model.Menus.IMenuButton;
import SlayTheSpider.Model.Menus.Menu;
import SlayTheSpider.Model.Position.Position;
import SlayTheSpider.View.TextWindow.LanternaGraphics;

public class LanternaMenuDrawer  {
    private final Menu menu;

    public LanternaMenuDrawer(Menu menu) {
        this.menu = menu;
    }

    public void draw(LanternaGraphics drawer) {
        Position position = new Position(0,0);
        drawer.drawString(menu.getTitle(), position);
        position.setDown(1);
        for (IMenuButton menuItem : menu.getMenuButtons()) {
            new LanternaMenuButtonDrawer(menuItem, menu.getSelected(), position).draw(drawer);
            position.setDown(1);
        }
    }
}
