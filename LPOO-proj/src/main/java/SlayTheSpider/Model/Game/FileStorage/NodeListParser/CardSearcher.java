package SlayTheSpider.Model.Game.FileStorage.NodeListParser;

import SlayTheSpider.Model.Game.Card;

import java.util.List;

public class CardSearcher {
    private final List<Card> cardList;

    public CardSearcher(List<Card> cardList) {
        this.cardList = cardList;
    }

    public Card search(String name) {
        for (Card card : cardList) {
            if (card.getName().equals(name))
                return card;
        }
        throw new NullPointerException();
    }
}
