package SlayTheSpider.Model.Game.CardEffect;

import SlayTheSpider.Model.Game.Card;
import SlayTheSpider.Model.Game.Player;

public class ShuffleCardPermaEffect extends CardEffectSelf {
    private final Card card;

    public ShuffleCardPermaEffect(Card card) {
        this.card = card;
    }

    @Override
    public void applyEffect(Player player) {
        player.getDeck().getDiscardedCards().add(new Card(card));
        player.getDeck().addCard(card);
    }

    @Override
    public String getDescription() {
        return "Shuffles a " + card.getName() + " into your deck.";
    }
}
