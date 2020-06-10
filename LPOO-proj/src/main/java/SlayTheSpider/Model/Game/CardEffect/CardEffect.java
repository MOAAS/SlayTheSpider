package SlayTheSpider.Model.Game.CardEffect;

import SlayTheSpider.Model.Game.CardEffect.Conditions.CardEffectConditionGroup;
import SlayTheSpider.Model.Game.Player;


public interface CardEffect {
    void use(Player owner);

    String getDescription();

    boolean needsTargetSelection();

    void setConditions(CardEffectConditionGroup conditionList);

    CardEffectConditionGroup getConditions();
}
