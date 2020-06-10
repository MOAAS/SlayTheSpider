package SlayTheSpider.Model.Game.FileStorage.ElementParser;

import SlayTheSpider.Model.Game.FileStorage.NodeListParser.XMLStringListParser;
import SlayTheSpider.Model.Game.DeckGenerator;
import org.w3c.dom.Element;

import java.util.List;

public class XMLDeckElementParser implements XMLElementParser<DeckGenerator> {
    @Override
    public DeckGenerator parseElement(Element element) {
        List<String> cardNames = new XMLStringListParser().parseItems(element.getElementsByTagName("Card"));
        String numRandomCards = element.getElementsByTagName("NumRandomCards").item(0).getTextContent();

        return new DeckGenerator(cardNames, Integer.parseInt(numRandomCards));
    }
}
