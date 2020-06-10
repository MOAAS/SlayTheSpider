package SlayTheSpider.Model.Game.FileStorage.ElementParser.CardEffectBuilders;

import SlayTheSpider.Model.Game.CardEffect.CardEffect;
import SlayTheSpider.Model.Game.CardEffect.DamageEffect;
import org.w3c.dom.Element;


public class XMLDamageEffectBuilder implements XMLCardEffectBuilder {
    @Override
    public CardEffect create(Element element) {
        String amount = element.getAttribute("amount");
        return new DamageEffect(Integer.parseInt(amount));
    }
}
