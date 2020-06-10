package SlayTheSpider.Model.Game.CardEffect.Conditions;

import SlayTheSpider.Model.Game.Character;
import SlayTheSpider.Model.Game.Player;

import java.util.ArrayList;
import java.util.List;

public class CardEffectConditionList implements CardEffectConditionGroup {
    private final List<CardEffectCondition> conditionList;

    public CardEffectConditionList() {
        this.conditionList = new ArrayList<>();
    }

    public CardEffectConditionList(List<CardEffectCondition> conditionList) {
        this.conditionList = conditionList;
    }

    @Override
    public boolean satisfies(Player user, Character target) {
        for (CardEffectCondition condition : conditionList) {
            if (!condition.verify(user, target))
                return false;
        }
        return true;
    }

    @Override
    public List<CardEffectCondition> getList() {
        return conditionList;
    }
}
