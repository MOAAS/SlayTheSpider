package SlayTheSpider.Model.Game.CardEffect;

import SlayTheSpider.Model.Game.HealthBar.PlayerHealthBar;
import SlayTheSpider.Model.Game.Player;

public class ShieldMultiEffect extends CardEffectSelf {
    private final int multiplier;

    public ShieldMultiEffect(int multiplier) {
        this.multiplier = multiplier;
    }

    @Override
    public void applyEffect(Player player) {
        PlayerHealthBar healthBar = player.getHealthBar();
        healthBar.addShield(healthBar.getShield() * (multiplier - 1));
    }

    @Override
    public String getDescription() {
        if (multiplier == 2)
            return "Doubles shield.";
        else if (multiplier == 3)
            return "Triples shield.";
        return "Multiplies shield by " + multiplier + ".";
    }
}
