package SlayTheSpider.Model.Game.FileStorage.ElementParser.CardEffectBuilders;

import SlayTheSpider.Model.Game.CardEffect.CardEffect;
import SlayTheSpider.Model.Game.CardEffect.TurnCardsToShieldEffect;
import org.w3c.dom.Element;

public class XMLTurnCardsToShieldEffectBuilder implements XMLCardEffectBuilder {
    @Override
    public CardEffect create(Element element) {
        String perCard = element.getAttribute("perCard");
        return new TurnCardsToShieldEffect(Integer.parseInt(perCard));
    }
}
