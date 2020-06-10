package SlayTheSpider.Model.Game.FileStorage.ElementParser.CardEffectBuilders;

import SlayTheSpider.Model.Game.CardEffect.CardEffect;
import SlayTheSpider.Model.Game.CardEffect.FightHealToDamageEffect;
import org.w3c.dom.Element;

public class XMLFightHealToDamageEffectBuilder implements XMLCardEffectBuilder {
    @Override
    public CardEffect create(Element element) {
        String perHealthPoint = element.getAttribute("perHealthPoint");
        return new FightHealToDamageEffect(Integer.parseInt(perHealthPoint));
    }
}
