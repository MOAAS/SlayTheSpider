package SlayTheSpider.Model.Game.CardEffect;

import SlayTheSpider.Model.Game.CardEffect.Conditions.CardEffectConditionGroup;
import SlayTheSpider.Model.Game.CardEffect.Conditions.CardEffectConditionList;
import SlayTheSpider.Model.Game.Player;

public class NullCardEffect implements CardEffect {
    @Override
    public void use(Player owner) {
        return;
    }

    @Override
    public String getDescription() {
        return "Error: Null Card Effect.";
    }

    @Override
    public boolean needsTargetSelection() {
        return false;
    }

    @Override
    public void setConditions(CardEffectConditionGroup conditionList) {
        return;
    }

    @Override
    public CardEffectConditionGroup getConditions() {
        return new CardEffectConditionList();
    }
}
