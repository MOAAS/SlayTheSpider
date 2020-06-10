package SlayTheSpider.Model.Game.FileStorage.ElementParser.CardEffectBuilders;

import SlayTheSpider.Model.Game.CardEffect.CardEffect;
import SlayTheSpider.Model.Game.CardEffect.SelfDiscardCardEffect;
import org.w3c.dom.Element;

public class XMLDiscardCardEffectBuilder implements XMLCardEffectBuilder {
    @Override
    public CardEffect create(Element element) {
        String amount = element.getAttribute("amount");
        return new SelfDiscardCardEffect(Integer.parseInt(amount));
    }
}
