package SlayTheSpider.Model.Game.CardEffect;

import SlayTheSpider.Model.Game.Card;
import SlayTheSpider.Model.Game.Player;

public class ShuffleCardFightEffect extends CardEffectSelf {
    private final Card card;

    public ShuffleCardFightEffect(Card card) {
        this.card = card;
    }

    @Override
    public void applyEffect(Player player) {
        player.getDeck().getDiscardedCards().add(new Card(card));
    }

    @Override
    public String getDescription() {
        return "Shuffles a " + card.getName() + " to your discard pile.";
    }
}
