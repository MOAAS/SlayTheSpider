package SlayTheSpider.Model.Game.FileStorage.NodeListParser;

import SlayTheSpider.Model.Sprites.ImageSprite;
import org.w3c.dom.Element;

import javax.swing.*;
import java.net.URL;

public class XMLImageSpriteListParser extends XMLNodeListParser<ImageSprite> {

    @Override
    public ImageSprite parseElement(Element element) {
        String name = element.getAttribute("id");
        String path = "/SpritesImage/" + element.getAttribute("texture");

        URL url = XMLImageSpriteListParser.class.getResource(path);
        return new ImageSprite(name, new ImageIcon(url).getImage());
    }
}
