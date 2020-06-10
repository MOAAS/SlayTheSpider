package SlayTheSpider.Model.Game.FileStorage.ElementParser.CardEffectBuilders;

import SlayTheSpider.Model.Game.Card;
import SlayTheSpider.Model.Game.CardEffect.CardEffect;
import SlayTheSpider.Model.Game.CardEffect.ShuffleCardPermaEffect;
import SlayTheSpider.Model.Game.FileStorage.NodeListParser.CardSearcher;
import org.w3c.dom.Element;

import java.util.List;

public class XMLShuffleCardPermaEffectBuilder implements XMLCardEffectBuilder {
    private final List<Card> cardList;

    public XMLShuffleCardPermaEffectBuilder(List<Card> cardList) {
        this.cardList = cardList;
    }

    @Override
    public CardEffect create(Element element) {
        String cardName = element.getAttribute("cardName");
        Card card = new CardSearcher(cardList).search(cardName);
        return new ShuffleCardPermaEffect(card);
    }
}
