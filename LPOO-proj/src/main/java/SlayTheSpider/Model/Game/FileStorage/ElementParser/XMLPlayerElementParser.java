package SlayTheSpider.Model.Game.FileStorage.ElementParser;

import SlayTheSpider.Model.Game.FileStorage.SpriteStorage;
import SlayTheSpider.Model.Sprites.Sprite;
import SlayTheSpider.Model.Game.Player;
import org.w3c.dom.Element;

public class XMLPlayerElementParser implements XMLElementParser<Player> {
    private final SpriteStorage storage;

    public XMLPlayerElementParser(SpriteStorage storage) {
        this.storage = storage;
    }

    @Override
    public Player parseElement(Element element) {
        String maxHP = element.getElementsByTagName("MaxHP").item(0).getTextContent();
        String spriteName = element.getElementsByTagName("Sprite").item(0).getTextContent();

        Sprite playerSprite = storage.getSprite(spriteName);

        return new Player(playerSprite, Integer.parseInt(maxHP));
    }
}
