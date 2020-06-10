package SlayTheSpider.View.Drawers.SwingDrawers;

import SlayTheSpider.Model.Game.Card;
import SlayTheSpider.Model.Position.Position;

import java.awt.*;

public class SwingCardDrawer implements SwingDrawer {
    public static final int RECTANGLE_HEIGTH = 40;
    public static final int RECTANGLE_WIDTH = 120;

    private static final int CIRCLE_RADIUS = 16;

    private final Card card;
    private final Position position;
    private final boolean isSelected;

    public SwingCardDrawer(Card card, Card selectedCard, Position position) {
        this.card = card;
        this.position = position;
        this.isSelected = card == selectedCard;
    }

    @Override
    public void draw(Graphics g) {
        this.drawFrame(g);
        this.drawCost(g);
        this.drawName(g);
    }

    private void drawName(Graphics g) {
        if (isSelected)
            g.setColor(Color.WHITE);
        else g.setColor(Color.BLACK);
        g.drawString(this.card.getName(), position.getX() + 27, position.getY() + 25);
    }

    private void drawCost(Graphics g) {
        Color circleColor = isSelected ? new Color(0, 226, 144) : new Color(0, 188, 115);
        Color textColor = isSelected ? Color.WHITE : Color.BLACK;
        g.setColor(circleColor);
        g.fillOval(position.getX() + 2, position.getY() + 2, CIRCLE_RADIUS, CIRCLE_RADIUS);
        g.setColor(textColor);
        g.drawString("" + this.card.getCost(), position.getX() + 7, position.getY() + 14);
    }

    private void drawFrame(Graphics g) {
        g.setColor(Color.decode("#" + card.getColor()).darker());
        g.fillRect(position.getX() - 3, position.getY() - 3, RECTANGLE_WIDTH + 6, RECTANGLE_HEIGTH + 6);
        g.setColor(Color.decode("#" + card.getColor()));
        g.fillRect(position.getX(), position.getY(), RECTANGLE_WIDTH, RECTANGLE_HEIGTH);
    }
}
