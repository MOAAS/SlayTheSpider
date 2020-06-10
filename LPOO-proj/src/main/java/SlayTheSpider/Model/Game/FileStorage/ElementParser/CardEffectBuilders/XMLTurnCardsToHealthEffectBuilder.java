package SlayTheSpider.Model.Game.FileStorage.ElementParser.CardEffectBuilders;

import SlayTheSpider.Model.Game.CardEffect.CardEffect;
import SlayTheSpider.Model.Game.CardEffect.TurnCardsToHealthEffect;
import org.w3c.dom.Element;

public class XMLTurnCardsToHealthEffectBuilder implements XMLCardEffectBuilder {
    @Override
    public CardEffect create(Element element) {
        String perCard = element.getAttribute("perCard");
        return new TurnCardsToHealthEffect(Integer.parseInt(perCard));
    }
}
