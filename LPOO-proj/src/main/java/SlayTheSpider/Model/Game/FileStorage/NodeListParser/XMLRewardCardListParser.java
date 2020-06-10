package SlayTheSpider.Model.Game.FileStorage.NodeListParser;

import SlayTheSpider.Model.Game.Card;
import org.w3c.dom.Element;

import java.util.List;

public class XMLRewardCardListParser extends XMLNodeListParser<Card> {
    private List<Card> cardList;

    public XMLRewardCardListParser(List<Card> cardList) {
        this.cardList = cardList;
    }

    @Override
    public Card parseElement(Element element) {
        String name = element.getTextContent();
        return new CardSearcher(cardList).search(name);
    }
}
