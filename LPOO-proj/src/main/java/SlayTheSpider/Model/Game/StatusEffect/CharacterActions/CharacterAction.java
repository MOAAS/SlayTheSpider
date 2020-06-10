package SlayTheSpider.Model.Game.StatusEffect.CharacterActions;

import java.util.List;

public abstract class CharacterAction {
    public void applyEffects(List<CharacterActionChanger> characterActionChangers) {
        for (CharacterActionChanger changer : characterActionChangers) {
            this.applyChanger(changer);
        }
    }

    public abstract void applyChanger(CharacterActionChanger changer);
}
