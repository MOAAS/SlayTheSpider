package SlayTheSpider.Model.Game.CardEffect;

import SlayTheSpider.Model.Game.Player;

public class FightHealToDamageEffect extends CardEffectSelf {
    private final int dmgPerHeal;

    public FightHealToDamageEffect(int dmgPerHeal) {
        this.dmgPerHeal = dmgPerHeal;
    }

    @Override
    public void applyEffect(Player player) {
        int healTaken = player.getPlayerStats().getHealTaken().fight();
        player.heal(healTaken * dmgPerHeal);
    }

    @Override
    public String getDescription() {
        return "Deals " + dmgPerHeal + " damage per health recovered during this fight.";
    }
}
