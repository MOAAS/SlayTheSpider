package SlayTheSpider.Model.Game.FileStorage.ElementParser.CardEffectBuilders;

import SlayTheSpider.Model.Game.CardEffect.CardEffect;
import SlayTheSpider.Model.Game.CardEffect.NullCardEffect;
import org.w3c.dom.Element;

public class XMLNullBuilder implements XMLCardEffectBuilder {
    @Override
    public CardEffect create(Element element) {
        return new NullCardEffect();
    }
}
