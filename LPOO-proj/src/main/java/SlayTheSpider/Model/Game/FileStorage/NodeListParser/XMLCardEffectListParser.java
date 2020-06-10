package SlayTheSpider.Model.Game.FileStorage.NodeListParser;

import SlayTheSpider.Model.Game.CardEffect.CardEffect;
import SlayTheSpider.Model.Game.CardEffect.Conditions.CardEffectConditionList;
import SlayTheSpider.Model.Game.FileStorage.ElementParser.CardEffectBuilders.*;
import SlayTheSpider.Model.Game.Card;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.util.List;

public class XMLCardEffectListParser extends XMLNodeListParser<CardEffect> {
    private final List<Card> cardList;

    public XMLCardEffectListParser(List<Card> cardList) {
        this.cardList = cardList;
    }

    @Override
    public CardEffect parseElement(Element element) {
        Node typeNode = element.getElementsByTagName("Type").item(0);
        String typeName = typeNode.getTextContent();

        CardEffect effect = this.getBuilder(typeName).create((Element)typeNode);
        effect.setConditions(new CardEffectConditionList(new XMLConditionListParser().parseItems(element.getElementsByTagName("Condition"))));

        return effect;
    }

    public XMLCardEffectBuilder getBuilder(String effectName) {
        switch (effectName) {
            case "Damage":
                return new XMLDamageEffectBuilder();
            case "SelfDamage":
                return new XMLSelfDamageEffectBuilder();
            case "AoEDamage":
                return new XMLAoEDamageEffectBuilder();
            case "SelfHeal":
                return new XMLSelfHealEffectBuilder();
            case "SelfShield":
                return new XMLSelfShieldEffectBuilder();
            case "ShieldClear":
                return new XMLSelfShieldClearBuilder();
            case "MultiplyShield":
                return new XMLShieldMultiEffectBuilder();
            case "StatusEffect":
                return new XMLTargetedStatusEffectBuilder();
            case "SelfStatusEffect":
                return new XMLSelfStatusEffectBuilder();
            case "ManaRestore":
                return new XMLManaRestoreEffectBuilder();
            case "ManaRefill":
                return new XMLManaRefillEffectBuilder();
            case "DrawCard":
                return new XMLDrawCardEffectBuilder();
            case "DiscardCard":
                return new XMLDiscardCardEffectBuilder();
            case "AoELifeDrain":
                return new XMLAoELifeDrainEffectBuilder();
            case "FightHealToDamage":
                return new XMLFightHealToDamageEffectBuilder();
            case "AoEShieldToDamage":
                return new XMLAoEShieldToDamageEffectBuilder();
            case "TurnCardsToHealthEffect":
                return new XMLTurnCardsToHealthEffectBuilder();
            case "TurnCardsToShieldEffect":
                return new XMLTurnCardsToShieldEffectBuilder();
            case "TurnCardsToDamage":
                return new XMLTurnCardsToDamageEffectBuilder();
            case "AddCards":
                return new XMLAddCardsEffectBuilder(cardList);
            case "ShuffleCardPerma":
                return new XMLShuffleCardPermaEffectBuilder(cardList);
            case "ShuffleCardFight":
                return new XMLShuffleCardFightEffectBuilder(cardList);
            default:
                System.out.println("Effect name: " + effectName + " not found.");
                return new XMLNullBuilder();
        }
    }
}
