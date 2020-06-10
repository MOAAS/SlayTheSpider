package SlayTheSpider.Model.Game.CardEffect;

import SlayTheSpider.Model.Game.Player;

public class SelfDrawCardEffect extends CardEffectSelf {
    private final int amount;

    public SelfDrawCardEffect(int amount) {
        this.amount = amount;
    }
    @Override
    public void applyEffect(Player player) {
        player.getDeck().drawCards(amount);
    }

    @Override
    public String getDescription() {
        if (amount == 1)
            return "Draws 1 card.";
        return "Draws " + amount + " cards.";
    }
}
