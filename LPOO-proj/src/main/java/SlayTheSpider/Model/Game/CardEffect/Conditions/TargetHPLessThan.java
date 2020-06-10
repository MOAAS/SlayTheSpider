package SlayTheSpider.Model.Game.CardEffect.Conditions;

import SlayTheSpider.Model.Game.Character;
import SlayTheSpider.Model.Game.HealthBar.HealthBar;
import SlayTheSpider.Model.Game.Player;

public class TargetHPLessThan extends CardEffectTargetCondition  {
    private final double expected;

    public TargetHPLessThan(double expected) {
        this.expected = expected;
    }

    @Override
    public boolean satisfies(Player user, Character target) {
        HealthBar targetHealthBar = user.getTarget().getHealthBar();
        return (double)targetHealthBar.getHealth() / targetHealthBar.getMaxHealth() < expected;
    }


    @Override
    public String getDescription() {
        return "Target HP is less than " + expected * 100 + "%";
    }
}