package SlayTheSpider.Model.Game.FileStorage;

import SlayTheSpider.Model.Sprites.ImageSprite;
import SlayTheSpider.Model.Sprites.Sprite;
import SlayTheSpider.Model.DocLoader.XMLDocumentLoader;
import SlayTheSpider.Model.Game.FileStorage.NodeListParser.XMLImageSpriteListParser;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class ImageSpriteStorage extends SpriteStorage {
    private final List<ImageSprite> sprites;

    public ImageSpriteStorage() {
        Document spriteDoc = new XMLDocumentLoader("xml/ImageSprites.xml").load();
        NodeList spriteNodeList = spriteDoc.getElementsByTagName("Sprite");
        this.sprites = new XMLImageSpriteListParser().parseItems(spriteNodeList);
    }


    @Override
    public List<Sprite> getSprites() {
        return new ArrayList<>(sprites);
    }
}
