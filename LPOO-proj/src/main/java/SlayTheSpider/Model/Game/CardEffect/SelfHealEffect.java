package SlayTheSpider.Model.Game.CardEffect;

import SlayTheSpider.Model.Game.Player;

public class SelfHealEffect extends CardEffectSelf {
    private final int heal;

    public SelfHealEffect(int heal) {
        this.heal = heal;
    }

    @Override
    public void applyEffect(Player player) {
        player.heal(heal);
    }

    @Override
    public String getDescription() {
        return "Restores " + heal + " health to self.";
    }
}
