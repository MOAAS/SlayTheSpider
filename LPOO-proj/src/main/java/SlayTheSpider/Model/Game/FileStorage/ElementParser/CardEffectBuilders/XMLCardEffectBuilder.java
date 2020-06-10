package SlayTheSpider.Model.Game.FileStorage.ElementParser.CardEffectBuilders;

import SlayTheSpider.Model.Game.CardEffect.CardEffect;
import org.w3c.dom.Element;


public interface XMLCardEffectBuilder {
    CardEffect create(Element element);
}
