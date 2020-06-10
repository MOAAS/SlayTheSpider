package SlayTheSpider.Model.Game.FileStorage.ElementParser.CardEffectBuilders;

import SlayTheSpider.Model.Game.CardEffect.CardEffect;
import SlayTheSpider.Model.Game.StatusEffect.*;
import org.w3c.dom.Element;

public abstract class XMLStatusEffectBuilder implements XMLCardEffectBuilder {
    @Override
    public CardEffect create(Element element) {
        String effectName = element.getAttribute("effect");
        String duration = element.getAttribute("duration");

        StatusEffect effect = this.getEffect(effectName);

        return this.createCardEffect(effect, Integer.parseInt(duration));
    }

    public abstract CardEffect createCardEffect(StatusEffect effect, int duration);

    public StatusEffect getEffect(String effectName) {
        if (effectName.equals("Weak"))
            return new StatusEffectWeak();
        else if (effectName.equals("Stun"))
            return new StatusEffectStunned();
        else if (effectName.equals("Strength"))
            return new StatusEffectStrength();
        else if (effectName.equals("Armored"))
            return new StatusEffectArmored();
        else if (effectName.equals("Sick"))
            return new StatusEffectSick();
        else {
            System.out.println("Invalid StatusEffect: " + effectName + " not found.");
            return null;
        }
    }
}
