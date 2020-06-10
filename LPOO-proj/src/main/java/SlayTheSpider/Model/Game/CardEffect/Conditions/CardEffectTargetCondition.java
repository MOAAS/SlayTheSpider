package SlayTheSpider.Model.Game.CardEffect.Conditions;

import SlayTheSpider.Model.Game.Character;
import SlayTheSpider.Model.Game.Player;

public abstract class CardEffectTargetCondition implements CardEffectCondition {
    public boolean verify(Player user, Character target) {
        return this.satisfies(user, target);
    }

    protected abstract boolean satisfies(Player user, Character target);
}
