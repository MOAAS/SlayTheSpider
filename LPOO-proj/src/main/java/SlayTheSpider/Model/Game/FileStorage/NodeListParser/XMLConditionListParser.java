package SlayTheSpider.Model.Game.FileStorage.NodeListParser;

import SlayTheSpider.Model.Game.CardEffect.Conditions.*;
import org.w3c.dom.Element;

public class XMLConditionListParser extends XMLNodeListParser<CardEffectCondition> {

    @Override
    public CardEffectCondition parseElement(Element element) {
        String expected = element.getAttribute("expected");
        String conditionName = element.getTextContent();

        if (conditionName.equals("TargetHPLessThan"))
            return new TargetHPLessThan(Double.parseDouble(expected));
        else if (conditionName.equals("TargetHPGreaterThan"))
            return new TargetHPGreaterThan(Double.parseDouble(expected));
        else if (conditionName.equals("TargetHPEqualTo"))
            return new TargetHPEqualTo(Double.parseDouble(expected));
        else if (conditionName.equals("UserHPLessThan"))
            return new UserHPLessThan(Double.parseDouble(expected));
        else if (conditionName.equals("UserHPGreaterThan"))
            return new UserHPGreaterThan(Double.parseDouble(expected));
        else if (conditionName.equals("UserHPEqualTo"))
            return new UserHPEqualTo(Double.parseDouble(expected));
        else if (conditionName.equals("UserHandLargerThan"))
            return new UserHandGreaterThan(Integer.parseInt(expected));
        else if (conditionName.equals("UserHandSmallerThan"))
            return new UserHandSmallerThan(Integer.parseInt(expected));
        else if (conditionName.equals("NumEnemiesGreaterThan"))
            return new NumEnemiesGreaterThan(Integer.parseInt(expected));
        else if (conditionName.equals("NumEnemiesLessThan"))
            return new NumEnemiesLessThan(Integer.parseInt(expected));
        else if (conditionName.equals("LastTurnDamageLessThan"))
            return new LastTurnDamageLessThan(Integer.parseInt(expected));
        else {
            System.out.println("Unknown condition: " + conditionName);
            return new NullCardCondition();
        }
    }
}
