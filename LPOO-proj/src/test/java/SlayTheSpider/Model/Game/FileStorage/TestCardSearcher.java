package SlayTheSpider.Model.Game.FileStorage;

import SlayTheSpider.Model.Game.Card;
import SlayTheSpider.Model.Game.FileStorage.NodeListParser.CardSearcher;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestCardSearcher {
    @Test
    public void TestSearcher() {
        List<Card> cards = new ArrayList<>();

        cards.add(Mockito.mock(Card.class));
        cards.add(Mockito.mock(Card.class));
        cards.add(Mockito.mock(Card.class));
        cards.add(Mockito.mock(Card.class));

        Mockito.when(cards.get(0).getName()).thenReturn("0");
        Mockito.when(cards.get(1).getName()).thenReturn("1");
        Mockito.when(cards.get(2).getName()).thenReturn("2");
        Mockito.when(cards.get(3).getName()).thenReturn("3");

        CardSearcher searcher = new CardSearcher(cards);
        assertSame(searcher.search("0"), cards.get(0));
        assertSame(searcher.search("3"), cards.get(3));
    }

    @Test (expected = NullPointerException.class)
    public void TestNotFound() {
        List<Card> cards = new ArrayList<>();

        cards.add(Mockito.mock(Card.class));
        cards.add(Mockito.mock(Card.class));

        Mockito.when(cards.get(0).getName()).thenReturn("0");
        Mockito.when(cards.get(1).getName()).thenReturn("1");

        CardSearcher searcher = new CardSearcher(cards);

        assertNull(searcher.search("5"));
    }
}
