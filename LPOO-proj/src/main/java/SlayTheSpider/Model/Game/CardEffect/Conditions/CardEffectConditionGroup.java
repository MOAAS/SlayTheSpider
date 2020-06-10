package SlayTheSpider.Model.Game.CardEffect.Conditions;

import SlayTheSpider.Model.Game.Character;
import SlayTheSpider.Model.Game.Player;

import java.util.List;

public interface CardEffectConditionGroup {
    boolean satisfies(Player user, Character target);

    List<CardEffectCondition> getList();
}
