package SlayTheSpider.Model.Game.CardEffect;

import SlayTheSpider.Model.Game.Player;

public class ManaRestoreEffect extends CardEffectSelf {
    private final int amount;

    public ManaRestoreEffect(int amount) {
        this.amount = amount;
    }

    @Override
    public void applyEffect(Player player) {
        player.getHealthBar().restoreMana(amount);
    }

    @Override
    public String getDescription() {
        return "Restores " + amount + " mana.";
    }
}
