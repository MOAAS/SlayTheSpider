package SlayTheSpider.Model.Game.FileStorage.ElementParser.CardEffectBuilders;

import SlayTheSpider.Model.Game.CardEffect.CardEffect;
import SlayTheSpider.Model.Game.CardEffect.SelfShielfClearEffect;
import org.w3c.dom.Element;

public class XMLSelfShieldClearBuilder implements XMLCardEffectBuilder {
    @Override
    public CardEffect create(Element element) {
        return new SelfShielfClearEffect();
    }
}
