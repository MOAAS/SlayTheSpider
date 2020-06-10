package SlayTheSpider.View.Drawers.SwingDrawers;

import SlayTheSpider.Model.Menus.IMenuButton;
import SlayTheSpider.Model.Menus.Menu;
import SlayTheSpider.Model.Position.Position;
import SlayTheSpider.Model.Position.Position2D;

import java.awt.*;

import static SlayTheSpider.View.Drawers.SwingDrawers.SwingMenuItemDrawer.RECTANGLE_HEIGHT;
import static SlayTheSpider.View.Drawers.SwingDrawers.SwingMenuItemDrawer.RECTANGLE_WIDTH;

public class SwingMenuDrawer implements SwingDrawer {
    private final Menu menu;

    private static final int TITLE_FONT_SIZE = 100;

    private static final int TITLE_POS_X = 130;
    private static final int TITLE_POS_Y = 150;

    private static final int ITEM_POS_Y = TITLE_POS_Y + 50;

    private static final Font TITLE_FONT = new Font("Comic Sans MS", Font.BOLD, TITLE_FONT_SIZE);

    private final int windowWidth;

    public SwingMenuDrawer(Menu menu, int windowWidth) {
        this.menu = menu;
        this.windowWidth = windowWidth;
    }

    @Override
    public void draw(Graphics g) {
        this.drawTitle(g);

        Position2D currPos = new Position((windowWidth - RECTANGLE_WIDTH)/ 2 , ITEM_POS_Y);
        for (IMenuButton menuItem : menu.getMenuButtons()) {
            new SwingMenuItemDrawer(menuItem, menu.getSelected(), currPos).draw(g);
            currPos.setDown(RECTANGLE_HEIGHT + 30);
        }
    }

    private void drawTitle(Graphics g) {
        g.setFont(TITLE_FONT);
        g.drawString("SLAY THE SPIDER", TITLE_POS_X, TITLE_POS_Y);
    }
}
