package SlayTheSpider.Model.Game.CardEffect;

import SlayTheSpider.Model.Game.Character;
import SlayTheSpider.Model.Game.Player;

public class AoEDamageEffect extends CardEffectAoE {

    private final int damage;

    public AoEDamageEffect(int damage) {
        this.damage = damage;
    }

    @Override
    public void applyEffect(Character target, Player user) {
        user.attack(target, damage);
    }

    @Override
    public String getDescription() {
        return "Deals " + damage + " damage to all enemies.";
    }
}
