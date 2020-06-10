package SlayTheSpider.Model.Game.StatusEffect;

import SlayTheSpider.Model.Game.StatusEffect.CharacterActions.CharacterActionChanger;

public interface StatusEffect extends CharacterActionChanger {
    String getName();
    String getColor();
}

