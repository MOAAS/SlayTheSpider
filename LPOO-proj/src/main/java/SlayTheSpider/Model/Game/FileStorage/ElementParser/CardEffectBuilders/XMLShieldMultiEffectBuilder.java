package SlayTheSpider.Model.Game.FileStorage.ElementParser.CardEffectBuilders;

import SlayTheSpider.Model.Game.CardEffect.CardEffect;
import SlayTheSpider.Model.Game.CardEffect.ShieldMultiEffect;
import org.w3c.dom.Element;

public class XMLShieldMultiEffectBuilder implements XMLCardEffectBuilder {
    @Override
    public CardEffect create(Element element) {
        String multiplier = element.getAttribute("multiplier");
        return new ShieldMultiEffect(Integer.parseInt(multiplier));
    }
}
