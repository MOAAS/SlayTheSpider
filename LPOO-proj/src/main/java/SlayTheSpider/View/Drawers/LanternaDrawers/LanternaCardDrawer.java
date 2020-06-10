package SlayTheSpider.View.Drawers.LanternaDrawers;

import SlayTheSpider.Model.Game.Card;
import SlayTheSpider.Model.Position.Position;
import SlayTheSpider.View.TextWindow.LanternaGraphics;

public class LanternaCardDrawer implements LanternaDrawer {
    public static final int RECTANGLE_WIDTH = 20;
    public static final int RECTANGLE_HEIGTH = 3;
    private Card card;
    private Position position;
    private boolean isSelected;

    public LanternaCardDrawer(Card card, Card selectedCard, Position position) {
        this.card = card;
        this.position = position;
        this.isSelected = card == selectedCard;
    }

    public void draw(LanternaGraphics drawer) {
        drawer.setBackgroundColor(card.getColor());
        drawer.drawRect(position, RECTANGLE_WIDTH, RECTANGLE_HEIGTH);

        if (isSelected)
            drawer.setTextColor("ffffff");
        else drawer.setTextColor("000000");

        drawer.drawString("(" + this.card.getCost() + ")  " + this.card.getName(), position.down(1));
    }
}