package SlayTheSpider.Model.Game.CardEffect.Conditions;

import SlayTheSpider.Model.Game.Character;
import SlayTheSpider.Model.Game.Player;

public interface CardEffectCondition {
    boolean verify(Player user, Character target);
    String getDescription();
}
