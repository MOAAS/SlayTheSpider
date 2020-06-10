package SlayTheSpider.Model.Game.StatusEffect;

import SlayTheSpider.Model.Game.StatusEffect.CharacterActions.IncomingAttack;
import SlayTheSpider.Model.Game.StatusEffect.CharacterActions.IncomingHeal;
import SlayTheSpider.Model.Game.StatusEffect.CharacterActions.OutgoingAttack;

public class StatusEffectWeak implements StatusEffect {
    @Override
    public String getName() {
        return "Weak";
    }

    @Override
    public String getColor() {
        return "bbbbbb";
    }

    @Override
    public void outgoingAttack(OutgoingAttack outgoingAttack) {
        outgoingAttack.halveDamage();
    }

    @Override
    public void incomingAttack(IncomingAttack incomingAttack) { }

    @Override
    public void incomingHeal(IncomingHeal incomingHeal) { }
}
