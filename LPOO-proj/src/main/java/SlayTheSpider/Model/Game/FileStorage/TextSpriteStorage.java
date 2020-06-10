package SlayTheSpider.Model.Game.FileStorage;

import SlayTheSpider.Model.Sprites.Sprite;
import SlayTheSpider.Model.Sprites.TextSprite;
import SlayTheSpider.Model.DocLoader.XMLDocumentLoader;
import SlayTheSpider.Model.Game.FileStorage.NodeListParser.XMLTextSpriteListParser;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class TextSpriteStorage extends SpriteStorage {
    private final List<TextSprite> sprites;

    public TextSpriteStorage() {
        Document spriteDoc = new XMLDocumentLoader("xml/TextSprites.xml").load();
        NodeList spriteNodeList = spriteDoc.getElementsByTagName("Sprite");
        this.sprites = new XMLTextSpriteListParser().parseItems(spriteNodeList);
    }

    @Override
    public List<Sprite> getSprites() {
        return new ArrayList<>(sprites);
    }
}
