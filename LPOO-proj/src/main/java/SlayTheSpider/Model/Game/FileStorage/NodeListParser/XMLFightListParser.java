package SlayTheSpider.Model.Game.FileStorage.NodeListParser;

import SlayTheSpider.Model.Game.FileStorage.SpriteStorage;
import SlayTheSpider.Model.Game.Card;
import SlayTheSpider.Model.Game.Enemy;
import SlayTheSpider.Model.Game.FightRewards.Reward;
import SlayTheSpider.Model.Game.FightRewards.RewardList;
import SlayTheSpider.Model.Sprites.Sprite;
import SlayTheSpider.Model.Game.Fight;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.List;

public class XMLFightListParser extends XMLNodeListParser<Fight> {
    private final SpriteStorage storage;
    private final List<Card> cardList;

    public XMLFightListParser(SpriteStorage storage, List<Card> cardList) {
        this.storage = storage;
        this.cardList = cardList;
    }

    @Override
    public Fight parseElement(Element element) {
        NodeList enemies = element.getElementsByTagName("Enemy");
        NodeList rewards = element.getElementsByTagName("Reward");
        List<Enemy> enemyList = new XMLEnemyListParser(storage).parseItems(enemies);
        List<Reward> rewardList = new XMLRewardListParser(cardList).parseItems(rewards);

        String numRewards = element.getElementsByTagName("NumRewards").item(0).getTextContent();
        String fightSpriteName = element.getElementsByTagName("SpriteName").item(0).getTextContent();
        Sprite fightSprite = storage.getSprite(fightSpriteName);

        RewardList actualRewardList = new RewardList(Integer.parseInt(numRewards));
        for (Reward reward : rewardList)
            actualRewardList.addReward(reward);

        Fight fight = new Fight(fightSprite, actualRewardList);
        for (Enemy enemy : enemyList)
            fight.addEnemy(enemy);

        return fight;
    }
}
