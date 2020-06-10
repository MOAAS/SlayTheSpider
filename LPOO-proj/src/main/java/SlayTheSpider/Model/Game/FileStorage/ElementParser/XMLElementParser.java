package SlayTheSpider.Model.Game.FileStorage.ElementParser;

import org.w3c.dom.Element;

public interface XMLElementParser<T> {
    T parseElement(Element element);
}
