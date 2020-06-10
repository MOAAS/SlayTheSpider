package SlayTheSpider.Model.Game.CardEffect.Conditions;

import SlayTheSpider.Model.Game.Character;
import SlayTheSpider.Model.Game.HealthBar.HealthBar;
import SlayTheSpider.Model.Game.Player;

public class TargetHPGreaterThan extends CardEffectTargetCondition {
    private final double expected;

    public TargetHPGreaterThan(double expected) {
        this.expected = expected;
    }

    @Override
    public boolean satisfies(Player user, Character target) {
        HealthBar targetHealthBar = user.getTarget().getHealthBar();
        return (double)targetHealthBar.getHealth() / targetHealthBar.getMaxHealth() > expected;
    }

    @Override
    public String getDescription() {
        return "Target HP is greater than " + expected * 100 + "%";
    }
}
