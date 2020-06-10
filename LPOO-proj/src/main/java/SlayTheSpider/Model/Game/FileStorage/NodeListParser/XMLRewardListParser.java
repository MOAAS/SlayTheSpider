package SlayTheSpider.Model.Game.FileStorage.NodeListParser;

import SlayTheSpider.Model.Game.Card;
import SlayTheSpider.Model.Game.FightRewards.*;
import org.w3c.dom.Element;

import java.util.List;

public class XMLRewardListParser extends XMLNodeListParser<Reward> {
    private final List<Card> cardList;

    public XMLRewardListParser(List<Card> cardList) {
        this.cardList = cardList;
    }

    @Override
    public Reward parseElement(Element element) {
        String rewardType = element.getAttribute("type");
        switch (rewardType) {
            case "Apple":
                return new AppleReward(Integer.parseInt(element.getAttribute("min")), Integer.parseInt(element.getAttribute("max")));
            case "ManaPoint":
                return new ManaReward();
            case "AnyCard":
                return new CardReward(cardList);
            case "Card":
                return new CardReward(new XMLRewardCardListParser(cardList).parseItems(element.getElementsByTagName("Card")));
            default:
                System.out.println(rewardType + ": Unknown reward type!");
                break;
        }
        return new NullReward();
    }
}
