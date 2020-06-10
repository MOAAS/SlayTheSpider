package SlayTheSpider.Model.Game.CardEffect;

import SlayTheSpider.Model.Game.Player;
import SlayTheSpider.Model.Game.StatusEffect.StatusEffect;

public class SelfStatusEffect extends CardEffectSelf {
    private final StatusEffect type;
    private final int duration;

    public SelfStatusEffect(StatusEffect type, int duration) {
        this.type = type;
        this.duration = duration;
    }

    @Override
    public void applyEffect(Player player) {
        player.applyStatus(this.type, this.duration);
    }

    @Override
    public String getDescription() {
        if (duration == 1)
            return "Applies " + type.getName() + " to self for 1 round.";
        return "Applies " + type.getName() + " to self for " + duration + " rounds.";
    }
}
