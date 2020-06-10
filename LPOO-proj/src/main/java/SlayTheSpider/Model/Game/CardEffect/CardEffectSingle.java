package SlayTheSpider.Model.Game.CardEffect;

import SlayTheSpider.Model.Game.Character;
import SlayTheSpider.Model.Game.Player;

public abstract class CardEffectSingle extends CardEffectTargeted {
    @Override
    public void use(Player owner) {
        Character target = owner.getTarget();
        if (cardEffectConditions.satisfies(owner, target))
            applyEffect(target, owner);
    }

    @Override
    public boolean needsTargetSelection() {
        return true;
    }
}
