package SlayTheSpider.Model.Game.FileStorage.ElementParser.CardEffectBuilders;

import SlayTheSpider.Model.Game.CardEffect.CardEffect;
import SlayTheSpider.Model.Game.CardEffect.TurnCardsToDamageEffect;
import org.w3c.dom.Element;

public class XMLTurnCardsToDamageEffectBuilder implements XMLCardEffectBuilder {
    @Override
    public CardEffect create(Element element) {
        String perCard = element.getAttribute("perCard");
        return new TurnCardsToDamageEffect(Integer.parseInt(perCard));
    }
}
