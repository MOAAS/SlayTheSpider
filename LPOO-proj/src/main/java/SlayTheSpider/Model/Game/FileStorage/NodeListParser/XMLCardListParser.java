package SlayTheSpider.Model.Game.FileStorage.NodeListParser;

import SlayTheSpider.Model.Game.CardEffect.CardEffect;
import SlayTheSpider.Model.Game.Card;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.List;

public class XMLCardListParser extends XMLNodeListParser<Card>{
    private final List<Card> cardList;

    public XMLCardListParser(List<Card> cardList) {
        this.cardList = cardList;
    }

    @Override
    public Card parseElement(Element element) {
        String id = element.getAttribute("id");

        for (Card card : cardList) {
            if (!card.getName().equals(id))
                continue;
            NodeList cardEffectNodes = element.getElementsByTagName("CardEffect");
            List<CardEffect> cardEffects = new XMLCardEffectListParser(cardList).parseItems(cardEffectNodes);
            for (CardEffect effect : cardEffects) {
                card.addEffect(effect);
            }
            return card;
        }

        return null;
    }
}
