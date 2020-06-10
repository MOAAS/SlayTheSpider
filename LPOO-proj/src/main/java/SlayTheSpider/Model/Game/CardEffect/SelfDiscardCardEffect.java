package SlayTheSpider.Model.Game.CardEffect;

import SlayTheSpider.Model.Game.Player;

public class SelfDiscardCardEffect extends CardEffectSelf {
    private final int amount;

    public SelfDiscardCardEffect(int amount) {
        this.amount = amount;
    }

    @Override
    public void applyEffect(Player player) {
        player.getDeck().discardRandomCards(amount);
    }

    @Override
    public String getDescription() {
        if (amount == 1)
            return "Discard 1 card.";
        return "Discard " + amount + " cards.";
    }
}
