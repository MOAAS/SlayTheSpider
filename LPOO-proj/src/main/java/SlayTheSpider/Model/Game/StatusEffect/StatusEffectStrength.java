package SlayTheSpider.Model.Game.StatusEffect;

import SlayTheSpider.Model.Game.StatusEffect.CharacterActions.IncomingAttack;
import SlayTheSpider.Model.Game.StatusEffect.CharacterActions.IncomingHeal;
import SlayTheSpider.Model.Game.StatusEffect.CharacterActions.OutgoingAttack;

public class StatusEffectStrength implements StatusEffect {
    @Override
    public String getName() {
        return "Strength";
    }

    @Override
    public String getColor() {
        return "ffffff";
    }

    @Override
    public void outgoingAttack(OutgoingAttack outgoingAttack) {
        outgoingAttack.doubleDamage();
    }

    @Override
    public void incomingAttack(IncomingAttack incomingAttack) { }

    @Override
    public void incomingHeal(IncomingHeal incomingHeal) { }
}
