package SlayTheSpider.Model.Game.FileStorage.ElementParser.CardEffectBuilders;

import SlayTheSpider.Model.Game.Card;
import SlayTheSpider.Model.Game.CardEffect.AddCardsEffect;
import SlayTheSpider.Model.Game.CardEffect.CardEffect;
import org.w3c.dom.Element;

import java.util.List;

public class XMLAddCardsEffectBuilder implements XMLCardEffectBuilder {
    private final List<Card> cardList;

    public XMLAddCardsEffectBuilder(List<Card> cardList) {
        this.cardList = cardList;
    }

    @Override
    public CardEffect create(Element element) {
        String amount = element.getAttribute("amount");
        return new AddCardsEffect(cardList, Integer.parseInt(amount));
    }
}
