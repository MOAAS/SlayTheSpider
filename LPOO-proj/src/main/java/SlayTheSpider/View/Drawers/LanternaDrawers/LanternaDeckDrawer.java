package SlayTheSpider.View.Drawers.LanternaDrawers;

import SlayTheSpider.Model.Game.Card;
import SlayTheSpider.Model.Game.Deck;
import SlayTheSpider.Model.Position.Position;
import SlayTheSpider.View.TextWindow.LanternaGraphics;
import SlayTheSpider.Model.Position.Position2D;

public class LanternaDeckDrawer implements LanternaDrawer {
    private final int windowWidth;
    private final int windowHeight;
    private final Deck deck;

    private static final int MIN_X = 35;
    private static final int MIN_Y = 20;

    public LanternaDeckDrawer(Deck deck, int windowWidth, int windowHeight) {
        this.deck = deck;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
    }

    public void draw(LanternaGraphics drawer) {
        this.drawHand(drawer);
        this.drawSelected(drawer);
        this.drawAvailable(drawer);
        this.drawDiscarded(drawer);
    }

    private void drawAvailable(LanternaGraphics drawer) {
        Position rectanglePosition = new Position(windowWidth - 10, windowHeight - 4);
        Position textPosition = rectanglePosition.right(1);
        drawer.setBackgroundColor("ffffff");
        drawer.setTextColor("000000");
        drawer.drawRect(rectanglePosition, 8, 3);
        drawer.drawString( "Avail.", textPosition);
        drawer.drawString(Integer.toString(deck.getAvailableCards().size()), textPosition.down(1).right(2));
    }

    private void drawDiscarded(LanternaGraphics drawer) {
        Position rectanglePosition = new Position(windowWidth - 20, windowHeight - 4);
        Position textPosition = rectanglePosition.right(1);
        drawer.setBackgroundColor("aaaaaa");
        drawer.setTextColor("000000");
        drawer.drawRect(rectanglePosition, 8, 3);
        drawer.drawString("Disc.", textPosition);
        drawer.drawString(Integer.toString(deck.getDiscardedCards().size()), textPosition.down(1).right(2));
    }

    private void drawHand(LanternaGraphics drawer) {
        Position position = new Position(MIN_X, MIN_Y);

        for (Card card : deck.getHand()) {
            new LanternaCardDrawer(card, deck.getSelectedCard(), position).draw(drawer);
            this.lineFeed(position);
        }
    }

    private void drawSelected(LanternaGraphics drawer) {
        drawer.setBackgroundColor("0");
        drawer.setTextColor("ffffff");
        drawer.drawString("Selected Card", LanternaBigCardDrawer.SELECTED_CARD_POS.up(2));

        Card card = deck.getSelectedCard();
        if (deck.emptyHand())
            card = new Card("No cards available", 0, "777777");

        new LanternaBigCardDrawer(card).draw(drawer);
    }

    private void lineFeed(Position2D position) {
        position.setDown(LanternaCardDrawer.RECTANGLE_HEIGTH + 1);

        if (position.getY() >= windowHeight - 6) {
            position.setX(position.getX() + LanternaCardDrawer.RECTANGLE_WIDTH + 2);
            position.setY(MIN_Y);
        }
    }
}
