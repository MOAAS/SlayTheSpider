package SlayTheSpider.Model.Game.CardEffect;

import SlayTheSpider.Model.Game.CardEffect.Conditions.CardEffectConditionGroup;
import SlayTheSpider.Model.Game.Player;

public abstract class CardEffectSelf implements CardEffect {
    protected CardEffectConditionGroup cardEffectConditions;

    @Override
    public void setConditions(CardEffectConditionGroup conditionList) {
        this.cardEffectConditions = conditionList;
    }

    @Override
    public CardEffectConditionGroup getConditions() {
        return cardEffectConditions;
    }

    public void use(Player owner) {
        if (cardEffectConditions.satisfies(owner, owner))
            this.applyEffect(owner);
    }

    public abstract void applyEffect(Player player);

    public boolean needsTargetSelection() {
        return false;
    }
}
