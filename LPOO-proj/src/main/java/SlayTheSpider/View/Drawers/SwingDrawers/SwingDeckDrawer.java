package SlayTheSpider.View.Drawers.SwingDrawers;

import SlayTheSpider.Model.Game.Card;
import SlayTheSpider.Model.Game.Deck;
import SlayTheSpider.Model.Position.Position;

import java.awt.*;

public class SwingDeckDrawer implements SwingDrawer {
    private final Deck deck;

    private static final int MIN_X = 350;

    private static final int MIN_Y = SwingBigCardDrawer.SELECTED_CARD_Y;
    private static final int MAX_Y = MIN_Y + 150;

    private static final int PILE_WIDTH = 80;
    private static final int PILE_HEIGHT = 80;

    private static final int AVAILABLE_X = 20;
    private static final int AVAILABLE_Y = 625;

    private static final int DISCARDED_X = AVAILABLE_X + 100;
    private static final int DISCARDED_Y = AVAILABLE_Y;

    public SwingDeckDrawer(Deck deck) {
        this.deck = deck;
    }

    @Override
    public void draw(Graphics g) {
        this.drawHand(g);
        this.drawSelected(g);
        this.drawAvailable(g);
        this.drawDiscarded(g);
    }

    private void drawAvailable(Graphics g) {
        this.drawPileFrame(g, Color.WHITE, new Position(AVAILABLE_X, AVAILABLE_Y));
        g.setColor(Color.BLACK);
        g.setFont(new Font("Tahoma", Font.BOLD, 12));
        g.drawString("Available", AVAILABLE_X + 15, AVAILABLE_Y + 20);
        g.setFont(new Font("Tahoma", Font.BOLD, 15));
        g.drawString(Integer.toString(deck.getAvailableCards().size()), AVAILABLE_X + 30, AVAILABLE_Y + 45);
    }

    private void drawDiscarded(Graphics g) {
        this.drawPileFrame(g, Color.GRAY, new Position(DISCARDED_X, DISCARDED_Y));
        g.setColor(Color.BLACK);
        g.setFont(new Font("Tahoma", Font.BOLD, 12));
        g.drawString("Discarded", DISCARDED_X + 11, DISCARDED_Y + 20);
        g.setFont(new Font("Tahoma", Font.BOLD, 15));
        g.drawString(Integer.toString(deck.getDiscardedCards().size()), DISCARDED_X + 30, DISCARDED_Y + 45);
    }

    private void drawPileFrame(Graphics g, Color color, Position position) {
        g.setColor(Color.BLACK);
        g.fillRect(position.getX() - 3, position.getY() - 3, PILE_WIDTH + 6, PILE_HEIGHT + 6);
        g.setColor(color);
        g.fillRect(position.getX(), position.getY(), PILE_WIDTH, PILE_HEIGHT);
    }

    private void drawHand(Graphics g) {
        Position position = new Position(MIN_X, MIN_Y);
        g.setFont(new Font("Tahoma", Font.PLAIN, 12));
        for (Card card : deck.getHand()) {
            new SwingCardDrawer(card, deck.getSelectedCard(), position).draw(g);
            position.setDown(SwingCardDrawer.RECTANGLE_HEIGTH + 15);

            if (position.getY() >= MAX_Y)

                position = new Position(position.getX() + SwingCardDrawer.RECTANGLE_WIDTH + 20, MIN_Y);
        }
    }

    private void drawSelected(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawString("Selected Card", SwingBigCardDrawer.SELECTED_CARD_TEXT_X, SwingBigCardDrawer.SELECTED_CARD_TEXT_Y);

        Card card = deck.getSelectedCard();
        if (deck.emptyHand())
            card = new Card("No cards available", 0, "777777");

        new SwingBigCardDrawer(card).draw(g);
    }
}
