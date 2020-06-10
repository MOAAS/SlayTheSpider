package SlayTheSpider.Model.Game.FileStorage.ElementParser.CardEffectBuilders;

import SlayTheSpider.Model.Game.CardEffect.AoEShieldToDamageEffect;
import SlayTheSpider.Model.Game.CardEffect.CardEffect;
import org.w3c.dom.Element;

public class XMLAoEShieldToDamageEffectBuilder implements XMLCardEffectBuilder {
    @Override
    public CardEffect create(Element element) {
        String amount = element.getAttribute("perShieldPoint");
        return new AoEShieldToDamageEffect(Integer.parseInt(amount));
    }
}
