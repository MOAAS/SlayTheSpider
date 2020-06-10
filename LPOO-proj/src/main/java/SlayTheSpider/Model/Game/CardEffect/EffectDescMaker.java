package SlayTheSpider.Model.Game.CardEffect;

import SlayTheSpider.Model.Game.CardEffect.Conditions.CardEffectCondition;
import SlayTheSpider.Model.Game.CardEffect.Conditions.CardEffectConditionGroup;

import java.util.List;

public class EffectDescMaker {
    private final CardEffect effect;

    public EffectDescMaker(CardEffect effect) {
        this.effect = effect;
    }

    public String getDescription() {
        return this.makeConditionDesc(effect.getConditions()) + effect.getDescription();
    }

    public String makeConditionDesc(CardEffectConditionGroup conditionGroup) {
        List<CardEffectCondition> list = conditionGroup.getList();
        if (list.size() == 0)
            return "";
        if (list.size() == 1)
            return "If " + list.get(0).getDescription() + ", ";
        StringBuilder string = new StringBuilder("If ");
        for (int i = 0; i < list.size() - 1; i++) {
            string.append(list.get(i).getDescription()).append(", ");
        }
        return string + "and " + list.get(list.size() - 1).getDescription() + ", ";
    }
}
