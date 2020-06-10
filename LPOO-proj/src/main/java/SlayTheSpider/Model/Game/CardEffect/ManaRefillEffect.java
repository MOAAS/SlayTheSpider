package SlayTheSpider.Model.Game.CardEffect;


import SlayTheSpider.Model.Game.Player;

public class ManaRefillEffect extends CardEffectSelf {
    @Override
    public void applyEffect(Player player) {
        player.getHealthBar().refillMana();
    }

    @Override
    public String getDescription() {
        return "Refills mana.";
    }
}
