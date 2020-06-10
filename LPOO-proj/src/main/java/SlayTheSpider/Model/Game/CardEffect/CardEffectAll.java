package SlayTheSpider.Model.Game.CardEffect;

import SlayTheSpider.Model.Game.Character;
import SlayTheSpider.Model.Game.Player;

import java.util.ArrayList;
import java.util.List;


public abstract class CardEffectAll extends CardEffectTargeted {
    @Override
    public void use(Player owner) {
        for (Character character : this.getValidTargets(owner)) {
            applyEffect(character, owner);
        }
    }

    @Override
    public boolean needsTargetSelection() {
        return false;
    }

    private List<Character> getValidTargets(Player owner) {
        List<Character> targets = new ArrayList<>();
        for (Character character : owner.getTargets().getCharacters()) {
            if (cardEffectConditions.satisfies(owner, character))
                targets.add(character);
        }
        return targets;
    }
}
