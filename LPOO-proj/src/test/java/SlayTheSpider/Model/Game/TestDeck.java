package SlayTheSpider.Model.Game;

import SlayTheSpider.Model.ListCycler.ArrayListCycler;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;

public class TestDeck {
    @Test
    public void testAdd() {
        Card CardMock1 = Mockito.mock(Card.class);
        Card CardMock2 = Mockito.mock(Card.class);
        Card CardMock3 = Mockito.mock(Card.class);

        Deck deck = new Deck();

        deck.addCard(CardMock1);

        assertEquals(deck.getCards().size(), 1);

        deck.addCard(CardMock2);
        deck.addCard(CardMock3);

        assertEquals(deck.getCards().size(), 3);
    }

    @Test
    public void testEmpty() {
        Deck deck = new Deck();
        assertEquals(deck.getCards().size(), 0);
        assertEquals(deck.getAvailableCards().size(), 0);
        assertEquals(deck.getDiscardedCards().size(), 0);
        assertEquals(deck.getHand().size(), 0);
        assertTrue(deck.emptyHand());

        deck.startFight(10);
        deck.drawCards(20);

        assertEquals(deck.getCards().size(), 0);
        assertEquals(deck.getAvailableCards().size(), 0);
        assertEquals(deck.getDiscardedCards().size(), 0);
        assertEquals(deck.getHand().size(), 0);
        assertTrue(deck.emptyHand());
    }

    @Test
    public void testStart() {
        Card CardMock1 = Mockito.mock(Card.class);
        Card CardMock2 = Mockito.mock(Card.class);
        Card CardMock3 = Mockito.mock(Card.class);

        Deck deck = new Deck();
        deck.cards.add(CardMock1);
        deck.cards.add(CardMock2);
        deck.cards.add(CardMock3);

        deck.startFight(4);

        assertEquals(deck.getCards().size(), 3);
        assertEquals(deck.getAvailableCards().size(), 0);
        assertEquals(deck.getDiscardedCards().size(), 0);
        assertEquals(deck.getHand().size(), 3);

        deck.drawCards(2);

        assertEquals(deck.getCards().size(), 3);
        assertEquals(deck.getAvailableCards().size(), 0);
        assertEquals(deck.getDiscardedCards().size(), 0);
        assertEquals(deck.getHand().size(), 3);

        deck.discardCard(deck.getHand().get(0));
        deck.discardCard(deck.getHand().get(1));

        assertEquals(deck.getCards().size(), 3);
        assertEquals(deck.getAvailableCards().size(), 0);
        assertEquals(deck.getDiscardedCards().size(), 2);
        assertEquals(deck.getHand().size(), 1);

        deck.drawCard();

        assertEquals(deck.getCards().size(), 3);
        assertEquals(deck.getAvailableCards().size(), 1);
        assertEquals(deck.getDiscardedCards().size(), 0);
        assertEquals(deck.getHand().size(), 2);

        deck.startFight(3);

        assertEquals(deck.getCards().size(), 3);
        assertEquals(deck.getAvailableCards().size(), 0);
        assertEquals(deck.getDiscardedCards().size(), 0);
        assertEquals(deck.getHand().size(), 3);

        deck.startFight(2);

        assertEquals(deck.getCards().size(), 3);
        assertEquals(deck.getAvailableCards().size(), 1);
        assertEquals(deck.getDiscardedCards().size(), 0);
        assertEquals(deck.getHand().size(), 2);

        deck.startFight(1);

        assertEquals(deck.getCards().size(), 3);
        assertEquals(deck.getAvailableCards().size(), 2);
        assertEquals(deck.getDiscardedCards().size(), 0);
        assertEquals(deck.getHand().size(), 1);

        deck.startFight(0);

        assertEquals(deck.getCards().size(), 3);
        assertEquals(deck.getAvailableCards().size(), 3);
        assertEquals(deck.getDiscardedCards().size(), 0);
        assertEquals(deck.getHand().size(), 0);

        deck.drawCards(5);

        assertEquals(deck.getCards().size(), 3);
        assertEquals(deck.getAvailableCards().size(), 0);
        assertEquals(deck.getDiscardedCards().size(), 0);
        assertEquals(deck.getHand().size(), 3);

        deck.discardCard(deck.getHand().get(0));
        deck.startFight(0);

        assertEquals(deck.getCards().size(), 3);
        assertEquals(deck.getAvailableCards().size(), 3);
        assertEquals(deck.getDiscardedCards().size(), 0);
        assertEquals(deck.getHand().size(), 0);

        deck.drawCards(3);
        deck.discardRandomCard();
        assertEquals(deck.getHand().size(), 2);

        deck.discardRandomCards(3);
        assertEquals(deck.getHand().size(), 0);
    }

    @Test
    public void testSelection() {
        Card CardMock1 = Mockito.mock(Card.class);
        Card CardMock2 = Mockito.mock(Card.class);
        Card CardMock3 = Mockito.mock(Card.class);


        Deck deck = new Deck();
        deck.cards.add(CardMock1);
        deck.cards.add(CardMock2);
        deck.cards.add(CardMock3);

        deck.startFight(3);

        Card selectedCard = deck.getSelectedCard();

        deck.discardSelectedCard();


        assertNotEquals(deck.getSelectedCard(), selectedCard);

        deck.drawCard();

        assertEquals(deck.getHand().size(), 3);

        for (int i = 0; i < 10; i++) {
            selectedCard = deck.getSelectedCard();
            deck.selectCardLeft();
            assertNotEquals(selectedCard, deck.getSelectedCard());
            assertTrue(deck.getHand().contains(deck.getSelectedCard()));
        }

        for (int i = 0; i < 10; i++) {
            selectedCard = deck.getSelectedCard();
            deck.selectCardRight();
            assertNotEquals(selectedCard, deck.getSelectedCard());
            assertTrue(deck.getHand().contains(deck.getSelectedCard()));
        }

        deck.hand = Mockito.mock(ArrayListCycler.class);

        deck.startFight(3);

        Mockito.verify(deck.hand, times(0)).selectFirst();

        deck.discardCard(Mockito.mock(Card.class));

        deck.selectFirstCard();

        Mockito.verify(deck.hand, times(1)).selectFirst();
    }

    @Test
    public void TestFullHand() {
        Card CardMock = Mockito.mock(Card.class);

        Deck deck = new Deck();

        deck.addCard(CardMock);
        deck.addCard(CardMock);
        deck.addCard(CardMock);
        deck.addCard(CardMock);
        deck.addCard(CardMock);
        deck.addCard(CardMock);
        deck.addCard(CardMock);
        deck.addCard(CardMock);
        deck.addCard(CardMock);
        deck.addCard(CardMock);
        deck.addCard(CardMock);
        deck.addCard(CardMock);
        deck.addCard(CardMock);
        deck.addCard(CardMock);
        deck.addCard(CardMock);
        deck.addCard(CardMock);
        deck.addCard(CardMock);
        deck.addCard(CardMock);

        deck.startFight(15);

        assertEquals(deck.getHand().size(), 12);
    }

    @Test
    public void TestDiscardRandCard() {
        Card CardMock = Mockito.mock(Card.class);

        Deck deck = new Deck();

        deck.addCard(CardMock);
        deck.addCard(CardMock);
        deck.addCard(CardMock);
        deck.addCard(CardMock);
        deck.addCard(CardMock);

        deck.startFight(5);

        assertEquals(deck.getHand().size(), 5);

        deck.discardRandomCards(3);

        assertEquals(deck.getHand().size(), 2);


    }
}
