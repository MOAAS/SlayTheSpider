package SlayTheSpider.View.Drawers;

import SlayTheSpider.View.Drawers.LanternaDrawers.LanternaDeckDrawer;
import SlayTheSpider.View.Drawers.SwingDrawers.SwingDeckDrawer;
import SlayTheSpider.Model.Game.Card;
import SlayTheSpider.Model.Game.Deck;
import SlayTheSpider.Model.Position.Position;
import SlayTheSpider.View.TextWindow.LanternaGraphics;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

public class TestDeckDrawer {
    private Deck deckMock = Mockito.mock(Deck.class);

    @Before
    public void setup() {
        Card cardMock1 = Mockito.mock(Card.class);
        Card cardMock2 = Mockito.mock(Card.class);
        Card cardMock3 = Mockito.mock(Card.class);
        Card cardMock4 = Mockito.mock(Card.class);
        Card cardMock5 = Mockito.mock(Card.class);
        Card cardMock6 = Mockito.mock(Card.class);
        Card cardMock7 = Mockito.mock(Card.class);

        List<Card> cards = new ArrayList<>();
        cards.add(cardMock1);
        cards.add(cardMock2);
        cards.add(cardMock3);
        cards.add(cardMock4);
        cards.add(cardMock5);
        cards.add(cardMock6);
        cards.add(cardMock7);

        List<Card> available = new ArrayList<>();
        available.add(cardMock1);

        List<Card> discarded = new ArrayList<>();
        discarded.add(cardMock2);
        discarded.add(cardMock3);

        List<Card> hand = new ArrayList<>();
        hand.add(cardMock4);
        hand.add(cardMock5);
        hand.add(cardMock6);
        hand.add(cardMock7);

        Mockito.when(cardMock1.getName()).thenReturn("CARD1");
        Mockito.when(cardMock2.getName()).thenReturn("CARD2");
        Mockito.when(cardMock3.getName()).thenReturn("CARD3");
        Mockito.when(cardMock4.getName()).thenReturn("CARD4");
        Mockito.when(cardMock5.getName()).thenReturn("CARD5");
        Mockito.when(cardMock6.getName()).thenReturn("CARD6");
        Mockito.when(cardMock7.getName()).thenReturn("CARD7");

        Mockito.when(cardMock1.getCost()).thenReturn(1);
        Mockito.when(cardMock2.getCost()).thenReturn(2);
        Mockito.when(cardMock3.getCost()).thenReturn(3);
        Mockito.when(cardMock4.getCost()).thenReturn(4);
        Mockito.when(cardMock5.getCost()).thenReturn(5);
        Mockito.when(cardMock6.getCost()).thenReturn(6);
        Mockito.when(cardMock7.getCost()).thenReturn(7);

        Mockito.when(cardMock1.getColor()).thenReturn("1");
        Mockito.when(cardMock2.getColor()).thenReturn("2");
        Mockito.when(cardMock3.getColor()).thenReturn("3");
        Mockito.when(cardMock4.getColor()).thenReturn("4");
        Mockito.when(cardMock5.getColor()).thenReturn("5");
        Mockito.when(cardMock6.getColor()).thenReturn("6");
        Mockito.when(cardMock7.getColor()).thenReturn("7");

        List<String> desc1 = new ArrayList<>();
        List<String> desc2 = new ArrayList<>();
        List<String> desc3 = new ArrayList<>();
        List<String> desc4 = new ArrayList<>();
        List<String> desc5 = new ArrayList<>();
        List<String> desc6 = new ArrayList<>();
        List<String> desc7 = new ArrayList<>();

        desc1.add("Desc1");
        desc2.add("Desc2");
        desc3.add("Desc3");
        desc4.add("Deals 4 damage to the enemy. If he is not an enemy deals an enemy to enemy while enemy.");
        desc5.add("Desc5");
        desc6.add("Desc6");
        desc7.add("Desc7");

        Mockito.when(cardMock1.getDescription()).thenReturn(desc1);
        Mockito.when(cardMock2.getDescription()).thenReturn(desc2);
        Mockito.when(cardMock3.getDescription()).thenReturn(desc3);
        Mockito.when(cardMock4.getDescription()).thenReturn(desc4);
        Mockito.when(cardMock5.getDescription()).thenReturn(desc5);
        Mockito.when(cardMock6.getDescription()).thenReturn(desc6);
        Mockito.when(cardMock7.getDescription()).thenReturn(desc7);

        deckMock = Mockito.mock(Deck.class);

        Mockito.when(deckMock.getSelectedCard()).thenReturn(cardMock4);
        Mockito.when(deckMock.getHand()).thenReturn(hand);
        Mockito.when(deckMock.getCards()).thenReturn(cards);
        Mockito.when(deckMock.getDiscardedCards()).thenReturn(discarded);
        Mockito.when(deckMock.getAvailableCards()).thenReturn(available);
    }

    @Test
    public void TestDrawLanterna() {
        LanternaGraphics graphics = Mockito.mock(LanternaGraphics.class);
        LanternaDeckDrawer drawer = new LanternaDeckDrawer(deckMock, 20, 35);

        drawer.draw(graphics);
        Mockito.verify(graphics, times(30)).drawString(anyString(), any());
        Mockito.verify(graphics, times(8)).setBackgroundColor(any());
        Mockito.verify(graphics, times(8)).setTextColor(any());
        Mockito.verify(graphics, times(7)).drawRect(any(), anyInt(), anyInt());

        // Hand

        // Draw Pile frames
        Mockito.verify(graphics, times(1)).drawString("1", new Position(13, 32));
        Mockito.verify(graphics, times(1)).drawString("2", new Position( 3, 32));

        Mockito.verify(graphics, times(1)).drawString("Disc.", new Position(1, 31));
        Mockito.verify(graphics, times(1)).drawString("Avail.", new Position(11, 31));

        // Draw card
        Mockito.verify(graphics, times(1)).drawString("(4)  CARD4", new Position(35, 21));
        Mockito.verify(graphics, times(1)).drawString("(5)  CARD5", new Position(35, 25));
        Mockito.verify(graphics, times(1)).drawString("(7)  CARD7", new Position(57, 21));
        Mockito.verify(graphics, times(3)).setTextColor("ffffff");
        Mockito.verify(graphics, times(5)).setTextColor("000000");

        // Draw big card
        Mockito.verify(graphics, times(1)).drawRect(new Position(2, 20), 30, 15);
        Mockito.verify(graphics, times(20)).drawString(anyString(), eq(new Position(2, 26)));

        Mockito.when(deckMock.emptyHand()).thenReturn(true);
        Mockito.verify(graphics, never()).setBackgroundColor("777777");
        drawer.draw(graphics);
        Mockito.verify(graphics, times(1)).setBackgroundColor("777777");
    }

    @Test
    public void TestDrawSwing() {
        Graphics graphics = Mockito.mock(Graphics.class);
        SwingDeckDrawer drawer = new SwingDeckDrawer(deckMock);

        FontMetrics metricsMock = Mockito.mock(FontMetrics.class);
        Mockito.when(graphics.getFontMetrics()).thenReturn(metricsMock);
        Mockito.when(metricsMock.stringWidth(anyString())).thenReturn(10);

        drawer.draw(graphics);
        Mockito.verify(graphics, times(16)).drawString(anyString(), anyInt(), anyInt());
        Mockito.verify(graphics, times(32)).setColor(any());
        Mockito.verify(graphics, times(6)).setColor(Color.WHITE);
        Mockito.verify(graphics, times(10)).setColor(Color.BLACK);
        Mockito.verify(graphics, times(5)).setFont(any());
        Mockito.verify(graphics, times(14)).fillRect(anyInt(), anyInt(), anyInt(), anyInt());
        Mockito.verify(graphics, times(5)).fillOval(anyInt(), anyInt(), anyInt(), anyInt());

        // Draw Pile frames
        Mockito.verify(graphics, times(1)).drawString("1", 50, 670);
        Mockito.verify(graphics, times(1)).drawString("2", 150, 670);

        Mockito.verify(graphics, times(1)).fillRect(20, 625, 80, 80);
        Mockito.verify(graphics, times(1)).fillRect(120, 625, 80, 80);
        Mockito.verify(graphics, times(1)).fillRect(17, 622, 86, 86);
        Mockito.verify(graphics, times(1)).fillRect(117, 622, 86, 86);

        // Draw card
        Mockito.verify(graphics, times(1)).fillOval(352, 452, 16, 16);
        Mockito.verify(graphics, times(1)).drawString("CARD4", 377, 475);
        Mockito.verify(graphics, times(1)).drawString("CARD5", 377, 530);
        Mockito.verify(graphics, times(1)).drawString("CARD7", 517, 475);
        Mockito.verify(graphics, times(1)).drawString("5", 357, 519);
        Mockito.verify(graphics, times(2)).setColor(new Color(0, 226, 144));
        Mockito.verify(graphics, times(3)).setColor(new Color(0, 188, 115));
        Mockito.verify(graphics, times(1)).fillRect(350,450,120,40);
        Mockito.verify(graphics, times(1)).fillRect(350, 505, 120, 40);
        Mockito.verify(graphics, times(1)).fillRect(347,447,126,46);
        Mockito.verify(graphics, times(1)).fillRect(347, 502, 126, 46);

        // Draw big card
        Mockito.verify(graphics, times(1)).fillRect(20, 450, 300, 150);
        Mockito.verify(graphics, times(1)).fillRect(17, 447, 306, 156);
        Mockito.verify(graphics, times(1)).drawString(" - Deals 4 damage to the enemy. If he is not an enemy deals an enemy to enemy while enemy. ", 35, 490);

        Mockito.when(deckMock.emptyHand()).thenReturn(true);
        Mockito.verify(graphics, never()).setColor(Color.decode("#777777"));
        drawer.draw(graphics);
        Mockito.verify(graphics, times(1)).setColor(Color.decode("#777777"));
    }
}
