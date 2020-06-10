package SlayTheSpider.Model.Game.CardEffect.Conditions;

import SlayTheSpider.Model.Game.Character;
import SlayTheSpider.Model.Game.Player;

public abstract class CardEffectUserCondition implements CardEffectCondition{
    @Override
    public boolean verify(Player user, Character target) {
        return this.satisfies(user);
    }

    protected abstract boolean satisfies(Player user);
}
