package SlayTheSpider.View.Drawers.SwingDrawers;

import SlayTheSpider.Model.Game.Card;
import SlayTheSpider.Model.Position.Position;
import SlayTheSpider.View.TextWindow.SwingGraphics;

import java.awt.*;
import java.util.List;

public class SwingBigCardDrawer implements SwingDrawer {
    public static final int SELECTED_CARD_X = 20;
    public static final int SELECTED_CARD_Y = 450;

    public static final int SELECTED_CARD_TEXT_X = SELECTED_CARD_X;
    public static final int SELECTED_CARD_TEXT_Y = SELECTED_CARD_Y - 10;

    private static final int COST_CIRCLE_X = SELECTED_CARD_X + 5;
    private static final int COST_CIRCLE_Y = SELECTED_CARD_Y + 5;

    private static final int CARD_COST_X = SELECTED_CARD_X + 12;
    private static final int CARD_COST_Y = SELECTED_CARD_Y + 19;

    private static final int CIRCLE_RADIUS = 20;

    private static final int CARD_NAME_X = SELECTED_CARD_X + 31;
    private static final int CARD_NAME_Y = SELECTED_CARD_Y + 19;

    private static final int CARD_DESC_X = SELECTED_CARD_X + 15;
    private static final int CARD_DESC_Y = SELECTED_CARD_Y + 40;

    private static final int RECTANGLE_WIDTH = 300;
    private static final int RECTANGLE_HEIGHT = 150;

    private final Card card;

    public SwingBigCardDrawer(Card card) {
        this.card = card;
    }

    @Override
    public void draw(Graphics g) {
        this.drawFrame(g);
        this.drawCost(g);
        this.drawName(g);
        this.drawDescription(g);
    }

    private void drawName(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawString(this.card.getName(), CARD_NAME_X, CARD_NAME_Y);
    }

    private void drawCost(Graphics g) {
        g.setColor(new Color(0, 226, 144));
        g.fillOval(COST_CIRCLE_X, COST_CIRCLE_Y, CIRCLE_RADIUS, CIRCLE_RADIUS);
        g.setColor(Color.WHITE);
        g.drawString("" + this.card.getCost(), CARD_COST_X , CARD_COST_Y);
    }

    private void drawFrame(Graphics g) {
        g.setColor(Color.decode("#" + card.getColor()).darker());
        g.fillRect(SELECTED_CARD_X - 3, SELECTED_CARD_Y - 3, RECTANGLE_WIDTH + 6, RECTANGLE_HEIGHT + 6);
        g.setColor(Color.decode("#" + card.getColor()));
        g.fillRect(SELECTED_CARD_X, SELECTED_CARD_Y, RECTANGLE_WIDTH, RECTANGLE_HEIGHT);
    }

    private void drawDescription(Graphics g) {
        List<String> description = this.card.getDescription();
        Position position = new Position(CARD_DESC_X, CARD_DESC_Y);
        new SwingGraphics(g).writeInBox(description, position, CARD_DESC_X, SELECTED_CARD_X + RECTANGLE_WIDTH);
    }
}
