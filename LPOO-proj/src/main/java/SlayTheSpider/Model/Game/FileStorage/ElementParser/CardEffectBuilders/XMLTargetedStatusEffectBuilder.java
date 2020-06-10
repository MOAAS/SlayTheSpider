package SlayTheSpider.Model.Game.FileStorage.ElementParser.CardEffectBuilders;

import SlayTheSpider.Model.Game.CardEffect.CardEffect;
import SlayTheSpider.Model.Game.CardEffect.NullCardEffect;
import SlayTheSpider.Model.Game.CardEffect.TargetedStatusEffect;
import SlayTheSpider.Model.Game.StatusEffect.StatusEffect;

public class XMLTargetedStatusEffectBuilder extends XMLStatusEffectBuilder {
    @Override
    public CardEffect createCardEffect(StatusEffect effect, int duration) {
        if (effect == null)
            return new NullCardEffect();
        return new TargetedStatusEffect(effect, duration);
    }
}
