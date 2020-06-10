package SlayTheSpider.Model.Game.FileStorage.NodeListParser;

import SlayTheSpider.Model.DocLoader.StringListLoader;
import SlayTheSpider.Model.Sprites.TextSprite;
import org.w3c.dom.Element;

import java.util.List;

public class XMLTextSpriteListParser extends XMLNodeListParser<TextSprite> {

    @Override
    public TextSprite parseElement(Element element) {
        String name = element.getAttribute("id");
        String color = element.getAttribute("color");
        String path = "SpritesText/" + element.getAttribute("texture") ;

        StringListLoader stringLoader = new StringListLoader(path);
        List<String> stringList = stringLoader.load();

        return new TextSprite(name, stringList, color);
    }
}
