package SlayTheSpider.Model.Game.StatusEffect;

import SlayTheSpider.Model.Game.StatusEffect.CharacterActions.IncomingAttack;
import SlayTheSpider.Model.Game.StatusEffect.CharacterActions.IncomingHeal;
import SlayTheSpider.Model.Game.StatusEffect.CharacterActions.OutgoingAttack;

public class StatusEffectArmored implements StatusEffect {
    @Override
    public String getName() {
        return "Armored";
    }

    @Override
    public String getColor() {
        return "654321";
    }

    @Override
    public void outgoingAttack(OutgoingAttack outgoingAttack) { }

    @Override
    public void incomingAttack(IncomingAttack incomingAttack) {
        incomingAttack.halveDamage();
    }

    @Override
    public void incomingHeal(IncomingHeal incomingHeal) { }
}
