package SlayTheSpider.Model.Game;

import SlayTheSpider.Model.ListCycler.ArrayListCycler;
import SlayTheSpider.Model.ListCycler.ListCycler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    List<Card> discardedCards = new ArrayList<>();
    List<Card> availableCards = new ArrayList<>();
    ListCycler<Card> hand = new ArrayListCycler<>(new ArrayList<Card>());
    List<Card> cards = new ArrayList<>();

    private static final int HAND_CAP = 12;

    public Deck() { }

    public void addCard(Card card) {
        cards.add(new Card(card));
    }

    public void startFight(int numCards) {
        availableCards = new ArrayList<>(cards);
        discardedCards.clear();
        hand.clear();
        Collections.shuffle(availableCards);
        this.drawCards(numCards);
    }

    public void drawCard() {
        if (hand.size() >= HAND_CAP)
            return;
        if (availableCards.isEmpty() && discardedCards.isEmpty())
            return;
        if (availableCards.isEmpty()) {
            availableCards = new ArrayList<>(discardedCards);
            Collections.shuffle(availableCards);
            discardedCards.clear();
        }
        hand.add(availableCards.get(availableCards.size() - 1));
        availableCards.remove(availableCards.size() - 1);
    }

    public void discardSelectedCard() {
        discardCard(this.getSelectedCard());
    }

    public void discardCard(Card card) {
        if (hand.remove(card))
            discardedCards.add(new Card(card));
    }

    public void discardRandomCard(){
        this.discardCard(hand.getRandom());
    }

    public void discardRandomCards(int num){
        for (int i = 0; i < num; i++)
            discardRandomCard();
    }

    public void drawCards(int num) {
        for (int i = 0; i < num; i++)
            drawCard();
    }

    public void selectFirstCard() {
        hand.selectFirst();
    }

    public void selectCardRight(){
        hand.selectRight();
    }

    public void selectCardLeft() {
        hand.selectLeft();        
    }

    public Card getSelectedCard() {
        return hand.getSelected();
    }

    public List<Card> getHand() {
        return hand.getList();
    }

    public List<Card> getCards() {
        return cards;
    }

    public List<Card> getDiscardedCards() {
        return discardedCards;
    }

    public List<Card> getAvailableCards() {
        return availableCards;
    }

    public boolean emptyHand() {
        return hand.size() == 0;
    }
}
