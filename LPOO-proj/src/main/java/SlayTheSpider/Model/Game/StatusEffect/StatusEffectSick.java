package SlayTheSpider.Model.Game.StatusEffect;

import SlayTheSpider.Model.Game.StatusEffect.CharacterActions.IncomingAttack;
import SlayTheSpider.Model.Game.StatusEffect.CharacterActions.IncomingHeal;
import SlayTheSpider.Model.Game.StatusEffect.CharacterActions.OutgoingAttack;

public class StatusEffectSick implements StatusEffect {
    @Override
    public String getName() {
        return "Sick";
    }

    @Override
    public String getColor() {
        return "00aa00";
    }

    @Override
    public void outgoingAttack(OutgoingAttack outgoingAttack) { }

    @Override
    public void incomingAttack(IncomingAttack incomingAttack) { }

    @Override
    public void incomingHeal(IncomingHeal incomingHeal) {
        incomingHeal.nullifyHeal();
    }
}
