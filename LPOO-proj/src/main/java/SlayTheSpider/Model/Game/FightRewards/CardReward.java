package SlayTheSpider.Model.Game.FightRewards;

import SlayTheSpider.Model.Game.Card;
import SlayTheSpider.Model.Game.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CardReward implements Reward {
    private final Card card;
    private final List<Card> cardList;


    public CardReward(List<Card> cardList) {
        this.cardList = cardList;
        this.card = cardList.get(new Random().nextInt(cardList.size()));
    }

    @Override
    public CardReward reroll() {
        return new CardReward(cardList);
    }

    @Override
    public void applyReward(Player winner) {
        winner.getDeck().addCard(card);
    }

    @Override
    public String getName() {
        return "(" + this.card.getCost() + ") Card: " + card.getName();
    }

    @Override
    public String getDescription() {
        return "Permanently adds a " + card.getName() + " to your deck.";
    }

    @Override
    public List<String> getDetails() {
        return new ArrayList<>(card.getDescription());
    }

    @Override
    public String getColor() {
        return card.getColor();
    }
}
