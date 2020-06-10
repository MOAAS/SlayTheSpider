package SlayTheSpider.Model.Game.FileStorage.ElementParser.CardEffectBuilders;

import SlayTheSpider.Model.Game.CardEffect.CardEffect;
import SlayTheSpider.Model.Game.CardEffect.ManaRestoreEffect;
import org.w3c.dom.Element;

public class XMLManaRestoreEffectBuilder implements XMLCardEffectBuilder {
    @Override
    public CardEffect create(Element element) {
        String amount = element.getAttribute("amount");
        return new ManaRestoreEffect(Integer.parseInt(amount));
    }
}
