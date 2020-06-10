package SlayTheSpider.Model.Game.FileStorage.ElementParser.CardEffectBuilders;

import SlayTheSpider.Model.Game.CardEffect.CardEffect;
import SlayTheSpider.Model.Game.CardEffect.SelfDrawCardEffect;
import org.w3c.dom.Element;

public class XMLDrawCardEffectBuilder implements XMLCardEffectBuilder {
    @Override
    public CardEffect create(Element element) {
        String amount = element.getAttribute("amount");
        return new SelfDrawCardEffect(Integer.parseInt(amount));
    }
}
