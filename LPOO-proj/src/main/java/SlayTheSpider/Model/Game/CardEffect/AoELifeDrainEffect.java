package SlayTheSpider.Model.Game.CardEffect;

import SlayTheSpider.Model.Game.Character;
import SlayTheSpider.Model.Game.Player;

public class AoELifeDrainEffect extends CardEffectAoE {
    private final int damage;
    private final int healPerDmg;

    public AoELifeDrainEffect(int damage, int healPerDmg) {
        this.damage = damage;
        this.healPerDmg = healPerDmg;
    }

    @Override
    public void applyEffect(Character target, Player user) {
       user.attack(target, this.damage);
       user.heal(target.getHealthBar().getLastHit() * this.healPerDmg);
    }

    @Override
    public String getDescription() {
        return "Deals " + damage + " to all enemies. Heals for " + healPerDmg + " for each health point dealt.";
    }
}
