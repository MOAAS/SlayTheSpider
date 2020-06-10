package SlayTheSpider.Model.Game.CardEffect;

import SlayTheSpider.Model.Game.CardEffect.Conditions.CardEffectConditionGroup;
import SlayTheSpider.Model.Game.Character;
import SlayTheSpider.Model.Game.Player;

public abstract class CardEffectTargeted implements CardEffect {
    protected CardEffectConditionGroup cardEffectConditions;

    @Override
    public void setConditions(CardEffectConditionGroup conditionList) {
        this.cardEffectConditions = conditionList;
    }

    @Override
    public CardEffectConditionGroup getConditions() {
        return cardEffectConditions;
    }
    public abstract void applyEffect(Character target, Player user);
}
