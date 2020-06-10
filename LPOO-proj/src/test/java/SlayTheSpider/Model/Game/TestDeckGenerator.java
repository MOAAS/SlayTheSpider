package SlayTheSpider.Model.Game;

import SlayTheSpider.Model.Game.FileStorage.GameStorage;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class TestDeckGenerator {
    @Test
    public void TestMake() {
        Card card1 = new Card("1", 1, "1");
        Card card2 = new Card("2", 2, "2");
        Card card3 = new Card("3", 3, "3");
        Card card4 = new Card("4", 4, "4");

        List<String> stringList1 = new ArrayList<>();
        stringList1.add("1");
        stringList1.add("2");
        stringList1.add("3");

        List<String> stringList2 = new ArrayList<>();
        stringList2.add("2");
        stringList2.add("3");
        stringList2.add("4");

        GameStorage gameStorageMock = Mockito.mock(GameStorage.class);
        Mockito.when(gameStorageMock.getCard("1")).thenReturn(card1);
        Mockito.when(gameStorageMock.getCard("2")).thenReturn(card2);
        Mockito.when(gameStorageMock.getCard("3")).thenReturn(card3);
        Mockito.when(gameStorageMock.getCard("4")).thenReturn(card4);

        Mockito.when(gameStorageMock.getRandomCard()).thenReturn(card3);

        DeckGenerator deckGenerator1 = new DeckGenerator(stringList1, 12);
        DeckGenerator deckGenerator2 = new DeckGenerator(stringList2, 54);

        Deck deck = deckGenerator1.generate(gameStorageMock);

        assertEquals(deck.getCards().size(), 15);
        assertEquals(deck.getHand().size(), 0);
        assertEquals(deck.getCards().get(0).getName(), card1.getName());
        assertEquals(deck.getCards().get(1).getName(), card2.getName());
        assertEquals(deck.getCards().get(2).getName(), card3.getName());
        assertEquals(deck.getCards().get(3).getName(), card3.getName());
        assertEquals(deck.getCards().get(4).getName(), card3.getName());
        assertEquals(deck.getCards().get(5).getName(), card3.getName());

        deck = deckGenerator2.generate(gameStorageMock);

        assertEquals(deck.getCards().size(), 57);
        assertEquals(deck.getHand().size(), 0);
        assertEquals(deck.getCards().get(0).getName(), card2.getName());
        assertEquals(deck.getCards().get(1).getName(), card3.getName());
        assertEquals(deck.getCards().get(2).getName(), card4.getName());
        assertEquals(deck.getCards().get(3).getName(), card3.getName());
        assertEquals(deck.getCards().get(4).getName(), card3.getName());
        assertEquals(deck.getCards().get(5).getName(), card3.getName());

    }

}
