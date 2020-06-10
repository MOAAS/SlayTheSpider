package SlayTheSpider.Model.Game.CardEffect.Conditions;

import SlayTheSpider.Model.Game.Character;
import SlayTheSpider.Model.Game.Player;

public class NumEnemiesGreaterThan extends CardEffectTargetCondition {
    private final int expected;

    public NumEnemiesGreaterThan(int expected) {
        this.expected = expected;
    }

    @Override
    public boolean satisfies(Player user, Character target) {
        return user.getTargets().getEnemies().size() > expected;
    }


    @Override
    public String getDescription() {
        return "Number of enemies is greater than " + expected;
    }

}
