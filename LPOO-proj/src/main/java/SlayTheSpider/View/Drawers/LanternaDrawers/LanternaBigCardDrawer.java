package SlayTheSpider.View.Drawers.LanternaDrawers;

import SlayTheSpider.View.TextWindow.LanternaGraphics;
import SlayTheSpider.Model.Game.Card;
import SlayTheSpider.Model.Position.Position;

import java.util.List;

public class LanternaBigCardDrawer implements LanternaDrawer {
    public static final Position SELECTED_CARD_POS = new Position(2, 20);
    public static final int RECTANGLE_WIDTH = 30;
    public static final int RECTANGLE_HEIGTH = 15;

    private Card card;

    public LanternaBigCardDrawer(Card card) {
        this.card = card;
    }

    @Override
    public void draw(LanternaGraphics drawer) {
        drawer.setBackgroundColor(card.getColor());
        drawer.drawRect(SELECTED_CARD_POS, RECTANGLE_WIDTH, RECTANGLE_HEIGTH);
        drawer.setTextColor("ffffff");
        drawer.drawString("(" + this.card.getCost() + ")  " + this.card.getName(), SELECTED_CARD_POS);
        this.drawDescription(drawer);
    }

    private void drawDescription(LanternaGraphics drawer) {
        List<String> description = this.card.getDescription();
        Position position = SELECTED_CARD_POS.down(2);
        for (String s : description) {
            drawer.drawString(" - ", position);
            position.setRight(3);
            String[] words = s.split(" ");

            for (String word : words) {
                if (position.getX() + word.length() > SELECTED_CARD_POS.getX() + RECTANGLE_WIDTH)
                    this.lineFeed(position);

                drawer.drawString(word, position);
                position.setRight(word.length() + 1);
            }
            this.lineFeed(position);
        }
    }

    private void lineFeed(Position position) {
        position.setX(SELECTED_CARD_POS.getX());
        position.setDown(1);
    }
}
