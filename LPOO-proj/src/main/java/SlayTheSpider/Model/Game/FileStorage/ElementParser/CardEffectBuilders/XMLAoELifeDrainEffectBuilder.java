package SlayTheSpider.Model.Game.FileStorage.ElementParser.CardEffectBuilders;

import SlayTheSpider.Model.Game.CardEffect.AoELifeDrainEffect;
import SlayTheSpider.Model.Game.CardEffect.CardEffect;
import org.w3c.dom.Element;

public class XMLAoELifeDrainEffectBuilder implements XMLCardEffectBuilder {
    @Override
    public CardEffect create(Element element) {
        String amount = element.getAttribute("amount");
        String perDmg = element.getAttribute("drainPerDmg");
        return new AoELifeDrainEffect(Integer.parseInt(amount), Integer.parseInt(perDmg));
    }
}
