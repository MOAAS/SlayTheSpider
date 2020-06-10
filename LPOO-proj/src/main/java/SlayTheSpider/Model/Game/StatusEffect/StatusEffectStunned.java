package SlayTheSpider.Model.Game.StatusEffect;

import SlayTheSpider.Model.Game.StatusEffect.CharacterActions.IncomingAttack;
import SlayTheSpider.Model.Game.StatusEffect.CharacterActions.IncomingHeal;
import SlayTheSpider.Model.Game.StatusEffect.CharacterActions.OutgoingAttack;

public class StatusEffectStunned implements StatusEffect {
    @Override
    public String getName() {
        return "Stunned";
    }

    @Override
    public String getColor() {
        return "bcdf12";
    }

    @Override
    public void outgoingAttack(OutgoingAttack outgoingAttack) {
        outgoingAttack.nullifyDamage();
    }

    @Override
    public void incomingAttack(IncomingAttack incomingAttack) { }

    @Override
    public void incomingHeal(IncomingHeal incomingHeal) { }
}
