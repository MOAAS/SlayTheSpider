package SlayTheSpider.Model.Game.FileStorage;

import SlayTheSpider.Model.DocLoader.XMLDocumentLoader;
import SlayTheSpider.Model.Game.FileStorage.ElementParser.XMLDeckElementParser;
import SlayTheSpider.Model.Game.FileStorage.ElementParser.XMLPlayerElementParser;
import SlayTheSpider.Model.Game.FileStorage.NodeListParser.CardSearcher;
import SlayTheSpider.Model.Game.FileStorage.NodeListParser.XMLCardIDListParser;
import SlayTheSpider.Model.Game.FileStorage.NodeListParser.XMLCardListParser;
import SlayTheSpider.Model.Game.FileStorage.NodeListParser.XMLFightListParser;
import SlayTheSpider.Model.Sprites.Sprite;
import SlayTheSpider.Model.Game.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.List;
import java.util.Random;

public class XMLStorage implements GameStorage {
    private final List<Card> cards;
    private final List<Fight> fights;
    private final List<Fight> bosses;
    private final Player player;
    private final DeckGenerator deckGenerator;
    private final SpriteStorage spriteStorage;

    public XMLStorage(SpriteStorage spriteStorage) {
        Document cardDoc = new XMLDocumentLoader("xml/Cards.xml").load();
        Document fightDoc = new XMLDocumentLoader("xml/Fights.xml").load();
        Document playerDoc = new XMLDocumentLoader("xml/Player.xml").load();

        NodeList cardNodeList = cardDoc.getElementsByTagName("Card");
        NodeList fightNodeList = fightDoc.getElementsByTagName("Fight");
        NodeList bossNodeList = fightDoc.getElementsByTagName("BossFight");
        Node playerNode = playerDoc.getElementsByTagName("Player").item(0);
        Node deckNode = ((Element)playerNode).getElementsByTagName("Deck").item(0);

        List<Card> cards = new XMLCardIDListParser().parseItems(cardNodeList);

        for (Card card1 : cards) {
            for (Card card2 : cards) {
                if (card1 != card2 && card1.getName().equals(card2.getName())) {
                    System.out.println("Warning: Duplicate card (" + card1.getName() + "). May result in unwanted behavior.");
                }
            }
        }

        this.spriteStorage = spriteStorage;
        this.cards = new XMLCardListParser(cards).parseItems(cardNodeList);
        this.fights = new XMLFightListParser(spriteStorage, cards).parseItems(fightNodeList);
        this.bosses = new XMLFightListParser(spriteStorage, cards).parseItems(bossNodeList);
        this.player = new XMLPlayerElementParser(spriteStorage).parseElement((Element)playerNode);
        this.deckGenerator = new XMLDeckElementParser().parseElement((Element)deckNode);
    }

    @Override
    public Player getNewPlayer() {
        Player newPlayer = new Player(this.player.getSprite(), this.player.getHealthBar().getMaxHealth());
        newPlayer.setDeck(deckGenerator.generate(this));
        return newPlayer;
    }

    @Override
    public Card getCard(String name) {
        return new CardSearcher(cards).search(name);
    }

    @Override
    public Card getRandomCard() {
        return new Card(cards.get(new Random().nextInt(cards.size())));
    }

    @Override
    public Sprite getSprite(String name) {
        return spriteStorage.getSprite(name);
    }

    @Override
    public List<Fight> getFights() {
        return fights;
    }

    @Override
    public List<Fight> getBosses() {
        return bosses;
    }

    @Override
    public SpriteStorage getSpriteStorage() {
        return spriteStorage;
    }
}
