package SlayTheSpider.Model.Game.CardEffect;

import SlayTheSpider.Model.Game.Player;

public class SelfDamageEffect extends CardEffectSelf {
    private final int damage;

    public SelfDamageEffect(int damage) {
        this.damage = damage;
    }

    @Override
    public void applyEffect(Player user) {
        user.attack(user, damage);
    }

    @Override
    public String getDescription() {
        return "Deals " + damage + " damage to self.";
    }
}
