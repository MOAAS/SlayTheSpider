package SlayTheSpider.Model.Game.FileStorage.NodeListParser;

import org.w3c.dom.Element;

public class XMLStringListParser extends XMLNodeListParser<String> {

    @Override
    public String parseElement(Element element) {
        return element.getTextContent();
    }
}
