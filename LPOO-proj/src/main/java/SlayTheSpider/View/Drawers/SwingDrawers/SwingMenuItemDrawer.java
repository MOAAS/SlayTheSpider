package SlayTheSpider.View.Drawers.SwingDrawers;

import SlayTheSpider.Model.Menus.IMenuButton;
import SlayTheSpider.Model.Position.Position2D;

import java.awt.*;

public class SwingMenuItemDrawer implements SwingDrawer {
    private static final Color SELECTED_TEXT_COLOR = new Color(255, 255, 255);
    private static final Color SELECTED_RECT_COLOR = new Color(255, 128, 0);
    private static final Color SELECTED_BORD_COLOR = new Color(210, 107, 0);

    private static final Color UNSELECTED_TEXT_COLOR = new Color(0, 0, 0);
    private static final Color UNSELECTED_RECT_COLOR = new Color(255, 0, 0);
    private static final Color UNSELECTED_BORD_COLOR = new Color(210, 0, 0);

    private final IMenuButton button;
    private final IMenuButton selected;
    private final int x;
    private final int y;

    private static final int ITEM_FONT_SIZE = 80;
    private static final Font ITEM_FONT = new Font("Tahoma", Font.BOLD, ITEM_FONT_SIZE);

    public static final int RECTANGLE_WIDTH = 750;
    public static final int RECTANGLE_HEIGHT = 150;

    public SwingMenuItemDrawer(IMenuButton menuItem, IMenuButton selected, Position2D pos) {
        this.button = menuItem;
        this.selected = selected;
        this.x = pos.getX();
        this.y = pos.getY();
    }

    @Override
    public void draw(Graphics g) {
        if (button == selected) {
            this.drawButton(g, SELECTED_RECT_COLOR, SELECTED_BORD_COLOR);
            this.drawText(g, SELECTED_TEXT_COLOR);
        }
        else {
            this.drawButton(g, UNSELECTED_RECT_COLOR, UNSELECTED_BORD_COLOR);
            this.drawText(g, UNSELECTED_TEXT_COLOR);
        }
    }

    private void drawText(Graphics g, Color color) {
        g.setFont(ITEM_FONT);
        g.setColor(color);
        int middle = this.x + RECTANGLE_WIDTH / 2;
        int descWidth = g.getFontMetrics().stringWidth(this.button.getDescription());
        g.drawString(this.button.getDescription(), middle - descWidth / 2, y + ITEM_FONT_SIZE + 20);
    }

    private void drawButton(Graphics g, Color color, Color border) {
        g.setColor(border);
        g.fillRect(this.x - 5, this.y - 5, RECTANGLE_WIDTH + 10, RECTANGLE_HEIGHT + 10);
        g.setColor(color);
        g.fillRect(this.x, this.y, RECTANGLE_WIDTH, RECTANGLE_HEIGHT);
    }
}
