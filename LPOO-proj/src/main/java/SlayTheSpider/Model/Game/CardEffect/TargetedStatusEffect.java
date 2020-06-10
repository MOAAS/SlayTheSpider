package SlayTheSpider.Model.Game.CardEffect;

import SlayTheSpider.Model.Game.Character;
import SlayTheSpider.Model.Game.Player;
import SlayTheSpider.Model.Game.StatusEffect.StatusEffect;

public class TargetedStatusEffect extends CardEffectSingle {
    private final StatusEffect type;
    private final int duration;

    public TargetedStatusEffect(StatusEffect type, int duration) {
        this.type = type;
        this.duration = duration;
    }

    @Override
    public void applyEffect(Character target, Player user) {
        target.applyStatus(this.type, this.duration);
    }

    @Override
    public String getDescription() {
        if (duration == 1)
            return "Applies " + type.getName() + " for 1 round.";
        return "Applies " + type.getName() + " for " + duration + " rounds.";
    }


}
