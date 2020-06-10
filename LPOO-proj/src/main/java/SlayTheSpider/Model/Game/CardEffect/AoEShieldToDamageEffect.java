package SlayTheSpider.Model.Game.CardEffect;

import SlayTheSpider.Model.Game.Character;
import SlayTheSpider.Model.Game.Player;

public class AoEShieldToDamageEffect extends CardEffectAoE {
    private final int scale;

    public AoEShieldToDamageEffect(int scale) {
        this.scale = scale;
    }

    @Override
    public void applyEffect(Character target, Player user) {
        user.attack(target, user.getHealthBar().getShield() * this.scale);
    }

    @Override
    public String getDescription() {
        return "Deals " + scale + " damage per shield point as AoE damage.";
    }
}
