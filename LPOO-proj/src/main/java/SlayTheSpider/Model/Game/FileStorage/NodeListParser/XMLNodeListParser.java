package SlayTheSpider.Model.Game.FileStorage.NodeListParser;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public abstract class XMLNodeListParser<T> {
    public List<T> parseItems(NodeList nodeList) {
        List<T> list = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() != Node.ELEMENT_NODE)
                continue;
            T item = this.parseElement((Element)node);
            list.add(item);
        }
        return list;

    }

    public abstract T parseElement(Element element);
}
