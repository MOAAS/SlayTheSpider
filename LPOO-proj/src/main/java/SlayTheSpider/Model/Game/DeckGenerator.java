package SlayTheSpider.Model.Game;

import SlayTheSpider.Model.Game.FileStorage.GameStorage;

import java.util.List;

public class DeckGenerator {
    final int numRandomCards;
    final List<String> cardNames;

    public DeckGenerator(List<String> cardNames, int numRandomCards) {
        this.cardNames = cardNames;
        this.numRandomCards = numRandomCards;
    }

    public Deck generate(GameStorage storage) {
        Deck deck = new Deck();

        for (String name : cardNames) {
            deck.addCard(storage.getCard(name));
        }

        for (int i = 0; i < numRandomCards; i++) {
            deck.addCard(storage.getRandomCard());
        }

        return deck;
    }
}
