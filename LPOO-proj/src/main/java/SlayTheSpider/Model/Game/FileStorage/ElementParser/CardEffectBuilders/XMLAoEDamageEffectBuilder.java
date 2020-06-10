package SlayTheSpider.Model.Game.FileStorage.ElementParser.CardEffectBuilders;

import SlayTheSpider.Model.Game.CardEffect.AoEDamageEffect;
import SlayTheSpider.Model.Game.CardEffect.CardEffect;
import org.w3c.dom.Element;

public class XMLAoEDamageEffectBuilder implements XMLCardEffectBuilder {
    @Override
    public CardEffect create(Element element) {
        String amount = element.getAttribute("amount");
        return new AoEDamageEffect(Integer.parseInt(amount));
    }
}
