package SlayTheSpider.Model.Game.FileStorage.NodeListParser;

import SlayTheSpider.Model.Game.Card;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class XMLCardIDListParser extends XMLNodeListParser<Card> {

    @Override
    public Card parseElement(Element element) {
        String id = element.getAttribute("id");
        String cost = element.getElementsByTagName("Cost").item(0).getTextContent();
        String color = element.getElementsByTagName("Color").item(0).getTextContent();

        Card card = new Card(id, Integer.parseInt(cost), color);

        NodeList description = element.getElementsByTagName("Description");

        if (description.getLength() > 0)
            card.setDescription(description.item(0).getTextContent());

        return card;
    }
}
