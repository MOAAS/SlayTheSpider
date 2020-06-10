package SlayTheSpider.Model.Game.CardEffect;

import SlayTheSpider.Model.Game.Card;
import SlayTheSpider.Model.Game.Player;

import java.util.List;
import java.util.Random;

public class AddCardsEffect extends CardEffectSelf {
    private final List<Card> cardList;
    private final int amount;

    public AddCardsEffect(List<Card> cardList, int amount) {
        this.amount = amount;
        this.cardList = cardList;
    }

    @Override
    public void applyEffect(Player player) {
        for (int i = 0; i < amount; i++) {
            Card randCard = cardList.get(new Random().nextInt(cardList.size()));

            player.getDeck().getHand().add(new Card(randCard));
        }
    }

    @Override
    public String getDescription() {
        if (amount == 1)
            return "Adds 1 random card to your hand.";
        return "Adds " + amount + " random cards to your hand.";
    }
}
