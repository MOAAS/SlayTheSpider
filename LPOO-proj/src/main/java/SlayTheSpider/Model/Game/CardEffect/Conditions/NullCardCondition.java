package SlayTheSpider.Model.Game.CardEffect.Conditions;

import SlayTheSpider.Model.Game.Character;
import SlayTheSpider.Model.Game.Player;

public class NullCardCondition implements CardEffectCondition {
    @Override
    public boolean verify(Player user, Character target) {
        return true;
    }

    @Override
    public String getDescription() {
        return "Error: Null Condition.";
    }
}
