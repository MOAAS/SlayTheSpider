package SlayTheSpider.Model.Game.FileStorage.ElementParser.CardEffectBuilders;

import SlayTheSpider.Model.Game.CardEffect.CardEffect;
import SlayTheSpider.Model.Game.CardEffect.NullCardEffect;
import SlayTheSpider.Model.Game.CardEffect.SelfStatusEffect;
import SlayTheSpider.Model.Game.StatusEffect.StatusEffect;

public class XMLSelfStatusEffectBuilder extends XMLStatusEffectBuilder {
    @Override
    public CardEffect createCardEffect(StatusEffect effect, int duration) {
        if (effect == null)
            return new NullCardEffect();
        return new SelfStatusEffect(effect, duration);
    }
}
