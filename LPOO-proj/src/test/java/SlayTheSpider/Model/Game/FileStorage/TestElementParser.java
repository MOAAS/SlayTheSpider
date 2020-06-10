package SlayTheSpider.Model.Game.FileStorage;

import SlayTheSpider.Model.Sprites.ImageSprite;
import SlayTheSpider.Model.Sprites.Sprite;
import SlayTheSpider.Model.Sprites.TextSprite;
import SlayTheSpider.Model.Game.*;
import SlayTheSpider.Model.Game.CardEffect.Conditions.*;
import SlayTheSpider.Model.Game.CardEffect.NullCardEffect;
import SlayTheSpider.Model.Game.FightRewards.AppleReward;
import SlayTheSpider.Model.Game.FightRewards.CardReward;
import SlayTheSpider.Model.Game.FightRewards.ManaReward;
import SlayTheSpider.Model.Game.FightRewards.NullReward;
import SlayTheSpider.Model.Game.FileStorage.ElementParser.*;
import SlayTheSpider.Model.Game.FileStorage.ElementParser.CardEffectBuilders.*;
import SlayTheSpider.Model.Game.FileStorage.NodeListParser.*;
import SlayTheSpider.Model.Position.Position;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;


public class TestElementParser {
    private Element elementMock = Mockito.mock(Element.class);
    private Element nodeMock = Mockito.mock(Element.class);
    private NodeList nodeListMock = Mockito.mock(NodeList.class);
    private SpriteStorage storage = Mockito.mock(SpriteStorage.class);
    private Sprite spriteMock = Mockito.mock(Sprite.class);
    private List<Card> cardList = new ArrayList<>();

    @Before
    public void Setup() {
        elementMock = Mockito.mock(Element.class);
        nodeListMock = Mockito.mock(NodeList.class);
        nodeMock = Mockito.mock(Element.class);
        storage = Mockito.mock(SpriteStorage.class);
        spriteMock = Mockito.mock(Sprite.class);

        Mockito.when(storage.getSprite("20")).thenReturn(spriteMock);

        cardList.clear();
        cardList.add(Mockito.mock(Card.class));
        cardList.add(Mockito.mock(Card.class));
        Mockito.when(cardList.get(0).getName()).thenReturn("idi");
        Mockito.when(cardList.get(1).getName()).thenReturn("ID");


        Mockito.when(elementMock.getAttribute("id")).thenReturn("ID");
        Mockito.when(elementMock.getAttribute("color")).thenReturn("COLOR");
        Mockito.when(elementMock.getAttribute("maxHP")).thenReturn("90");
        Mockito.when(elementMock.getAttribute("pos")).thenReturn("90,90");
        Mockito.when(elementMock.getAttribute("damage")).thenReturn("900");
        Mockito.when(elementMock.getAttribute("damageScale")).thenReturn("9000");
        Mockito.when(elementMock.getAttribute("expected")).thenReturn("5");
        Mockito.when(elementMock.getTextContent()).thenReturn("TEXT");

        Mockito.when(elementMock.getElementsByTagName(Mockito.anyString())).thenReturn(nodeListMock);

        Mockito.when(nodeListMock.item(0)).thenReturn(nodeMock);
        Mockito.when(nodeListMock.getLength()).thenReturn(1);
        Mockito.when(nodeMock.getTextContent()).thenReturn("20");
    }

    @Test
    public void TestTextSprite() {
        Mockito.when(elementMock.getAttribute("id")).thenReturn("ID");
        Mockito.when(elementMock.getAttribute("texture")).thenReturn("bat.txt");

        XMLTextSpriteListParser elementParser = new XMLTextSpriteListParser();
        TextSprite sprite = elementParser.parseElement(elementMock);

        assertEquals(sprite.getName(), "ID");
        assertEquals(sprite.getColor(), "COLOR");
        assertEquals(sprite.getHeight(), 3);
        assertNotNull(sprite);
    }

    @Test
    public void TestImageSprite() {
        Element elementMockImage = Mockito.mock(Element.class);
        Mockito.when(elementMockImage.getAttribute("id")).thenReturn("ID2");
        Mockito.when(elementMockImage.getAttribute("texture")).thenReturn("badguy.png");

        XMLImageSpriteListParser elementParser = new XMLImageSpriteListParser();
        ImageSprite sprite = elementParser.parseElement(elementMockImage);

        assertNotNull(sprite);
        assertEquals(sprite.getName(), "ID2");
        assertEquals(sprite.getHeight(), 128);
        assertEquals(sprite.getWidth(), 129);
        assertSame(sprite, sprite.endarken());
    }

    @Test
    public void TestFight() {
        XMLFightListParser elementParser = new XMLFightListParser(storage, cardList);
        Fight fight = elementParser.parseElement(elementMock);

        assertSame(fight.getSprite(), spriteMock);
        assertEquals(fight.getEnemies().size(), 0);

        Mockito.verify(elementMock, times(1)).getElementsByTagName("Enemy");
        Mockito.verify(elementMock, times(1)).getElementsByTagName("SpriteName");
    }

    @Test
    public void TestString() {
        XMLStringListParser elementParser = new XMLStringListParser();
        assertEquals(elementParser.parseElement(elementMock), "TEXT");
    }

    @Test
    public void TestPlayer() {
        XMLPlayerElementParser elementParser = new XMLPlayerElementParser(storage);

        Player player = elementParser.parseElement(elementMock);

        assertSame(player.getSprite(), spriteMock);
        assertSame(player.getHealthBar().getMaxHealth(), 20);
    }

    @Test
    public void TestEnemy() {
        XMLEnemyListParser elementParser = new XMLEnemyListParser(storage);
        Mockito.when(elementMock.getTextContent()).thenReturn("20");

        Enemy enemy = elementParser.parseElement(elementMock);

        assertEquals(enemy.getHealthBar().getMaxHealth(), 90);
        assertEquals(enemy.getDamage(), 900);
        assertEquals(enemy.getDamageScale(), 9000, 0.01);
        assertEquals(enemy.getPosition(), new Position(90, 90));
    }

    @Test
    public void TestDeck() {
        XMLDeckElementParser elementParser = new XMLDeckElementParser();

        DeckGenerator deckGenerator = elementParser.parseElement(elementMock);

        assertNotNull(deckGenerator);

        Mockito.verify(elementMock, times(1)).getElementsByTagName("Card");
        Mockito.verify(elementMock, times(1)).getElementsByTagName("NumRandomCards");
    }

    @Test
    public void TestCardID() {
        XMLCardIDListParser elementParser = new XMLCardIDListParser();

        Card card = elementParser.parseElement(elementMock);

        assertEquals(card.getName(), "ID");
        assertEquals(card.getCost(), 20);
        assertEquals(card.getColor(), "20");
        assertEquals(card.getDescription().get(0), "20");
        assertEquals(card.getDescription().size(), 1);
        assertEquals(card.getCost(), 20);
    }

    @Test
    public void TestCard() {
        XMLCardListParser elementParser = new XMLCardListParser(cardList);

        Card card = elementParser.parseElement(elementMock);

        assertEquals(card.getEffects().size(), 0);
        assertEquals(card.getName(), "ID");

        assertSame(card, cardList.get(1));

        Mockito.verify(elementMock, times(1)).getElementsByTagName("CardEffect");


        elementParser = new XMLCardListParser(new ArrayList<>());
        assertNull(elementParser.parseElement(elementMock));
    }

    @Test
    public void TestReward() {
        Mockito.when(elementMock.getAttribute("min")).thenReturn("2");
        Mockito.when(elementMock.getAttribute("max")).thenReturn("2");
        Mockito.when(nodeMock.getTextContent()).thenReturn("ID");
        Mockito.when(nodeMock.getNodeType()).thenReturn(Node.ELEMENT_NODE);

        Mockito.when(elementMock.getAttribute("type")).thenReturn("Apple");
        assertTrue(new XMLRewardListParser(cardList).parseElement(elementMock) instanceof AppleReward);

        Mockito.when(elementMock.getAttribute("type")).thenReturn("ManaPoint");
        assertTrue(new XMLRewardListParser(cardList).parseElement(elementMock) instanceof ManaReward);

        Mockito.when(elementMock.getAttribute("type")).thenReturn("Appleoo");
        assertTrue(new XMLRewardListParser(cardList).parseElement(elementMock) instanceof NullReward);

        Mockito.when(elementMock.getAttribute("type")).thenReturn("AnyCard");
        assertTrue(new XMLRewardListParser(cardList).parseElement(elementMock) instanceof CardReward);

        Mockito.when(elementMock.getAttribute("type")).thenReturn("Card");
        assertTrue(new XMLRewardListParser(cardList).parseElement(elementMock) instanceof CardReward);
    }

    @Test
    public void TestCardReward() {
        XMLRewardCardListParser elementParser = new XMLRewardCardListParser(cardList);

        Mockito.when(elementMock.getTextContent()).thenReturn("ID");
        Mockito.when(cardList.get(0).getName()).thenReturn("idi");
        Mockito.when(cardList.get(1).getName()).thenReturn("ID");
        assertNotNull(elementParser.parseElement(elementMock));
    }

    @Test (expected = NullPointerException.class)
    public void TestCardNotFound() {
        XMLRewardCardListParser elementParser = new XMLRewardCardListParser(cardList);
        Mockito.when(elementMock.getTextContent()).thenReturn("ID");
        Mockito.when(cardList.get(0).getName()).thenReturn("idi");
        Mockito.when(cardList.get(1).getName()).thenReturn("idi");

        elementParser.parseElement(elementMock);
    }


    @Test
    public void TestCardEffect() {
        XMLCardEffectListParser elementParser = new XMLCardEffectListParser(cardList);

        assertTrue(elementParser.getBuilder("Damage") instanceof XMLDamageEffectBuilder);
        assertTrue(elementParser.getBuilder("SelfDamage") instanceof XMLSelfDamageEffectBuilder);
        assertTrue(elementParser.getBuilder("AoEDamage") instanceof XMLAoEDamageEffectBuilder);
        assertTrue(elementParser.getBuilder("SelfHeal") instanceof XMLSelfHealEffectBuilder);
        assertTrue(elementParser.getBuilder("SelfShield") instanceof XMLSelfShieldEffectBuilder);
        assertTrue(elementParser.getBuilder("ShieldClear") instanceof XMLSelfShieldClearBuilder);
        assertTrue(elementParser.getBuilder("StatusEffect") instanceof XMLTargetedStatusEffectBuilder);
        assertTrue(elementParser.getBuilder("MultiplyShield") instanceof XMLShieldMultiEffectBuilder);
        assertTrue(elementParser.getBuilder("SelfStatusEffect") instanceof XMLSelfStatusEffectBuilder);
        assertTrue(elementParser.getBuilder("ManaRestore") instanceof XMLManaRestoreEffectBuilder);
        assertTrue(elementParser.getBuilder("ManaRefill") instanceof XMLManaRefillEffectBuilder);
        assertTrue(elementParser.getBuilder("DrawCard") instanceof XMLDrawCardEffectBuilder);
        assertTrue(elementParser.getBuilder("DiscardCard") instanceof XMLDiscardCardEffectBuilder);
        assertTrue(elementParser.getBuilder("AoELifeDrain") instanceof XMLAoELifeDrainEffectBuilder);
        assertTrue(elementParser.getBuilder("FightHealToDamage") instanceof XMLFightHealToDamageEffectBuilder);
        assertTrue(elementParser.getBuilder("AoEShieldToDamage") instanceof XMLAoEShieldToDamageEffectBuilder);
        assertTrue(elementParser.getBuilder("TurnCardsToHealthEffect") instanceof XMLTurnCardsToHealthEffectBuilder);
        assertTrue(elementParser.getBuilder("TurnCardsToShieldEffect") instanceof XMLTurnCardsToShieldEffectBuilder);
        assertTrue(elementParser.getBuilder("TurnCardsToDamage") instanceof XMLTurnCardsToDamageEffectBuilder);
        assertTrue(elementParser.getBuilder("AddCards") instanceof XMLAddCardsEffectBuilder);
        assertTrue(elementParser.getBuilder("ShuffleCardPerma") instanceof XMLShuffleCardPermaEffectBuilder);
        assertTrue(elementParser.getBuilder("ShuffleCardFight") instanceof XMLShuffleCardFightEffectBuilder);
        assertTrue(elementParser.getBuilder("WrongName1") instanceof XMLNullBuilder);

        Mockito.when(elementMock.getTextContent()).thenReturn("WrongName2");

        assertTrue(elementParser.parseElement(elementMock) instanceof NullCardEffect);
        assertEquals(elementParser.parseElement(elementMock).getConditions().getList().size(), 0);
    }

    @Test
    public void TestCondition() {
        XMLConditionListParser parser = new XMLConditionListParser();

        Mockito.when(elementMock.getTextContent()).thenReturn("TargetHPLessThan");
        assertTrue(parser.parseElement(elementMock) instanceof TargetHPLessThan);

        Mockito.when(elementMock.getTextContent()).thenReturn("TargetHPGreaterThan");
        assertTrue(parser.parseElement(elementMock) instanceof TargetHPGreaterThan);

        Mockito.when(elementMock.getTextContent()).thenReturn("TargetHPEqualTo");
        assertTrue(parser.parseElement(elementMock) instanceof TargetHPEqualTo);

        Mockito.when(elementMock.getTextContent()).thenReturn("UserHPLessThan");
        assertTrue(parser.parseElement(elementMock) instanceof UserHPLessThan);

        Mockito.when(elementMock.getTextContent()).thenReturn("UserHPGreaterThan");
        assertTrue(parser.parseElement(elementMock) instanceof UserHPGreaterThan);

        Mockito.when(elementMock.getTextContent()).thenReturn("UserHPEqualTo");
        assertTrue(parser.parseElement(elementMock) instanceof UserHPEqualTo);

        Mockito.when(elementMock.getTextContent()).thenReturn("UserHandLargerThan");
        assertTrue(parser.parseElement(elementMock) instanceof UserHandGreaterThan);

        Mockito.when(elementMock.getTextContent()).thenReturn("UserHandSmallerThan");
        assertTrue(parser.parseElement(elementMock) instanceof UserHandSmallerThan);

        Mockito.when(elementMock.getTextContent()).thenReturn("NumEnemiesGreaterThan");
        assertTrue(parser.parseElement(elementMock) instanceof NumEnemiesGreaterThan);

        Mockito.when(elementMock.getTextContent()).thenReturn("NumEnemiesLessThan");
        assertTrue(parser.parseElement(elementMock) instanceof NumEnemiesLessThan);

        Mockito.when(elementMock.getTextContent()).thenReturn("LastTurnDamageLessThan");
        assertTrue(parser.parseElement(elementMock) instanceof LastTurnDamageLessThan);

        Mockito.when(elementMock.getTextContent()).thenReturn("WrongName");
        assertTrue(parser.parseElement(elementMock) instanceof NullCardCondition);
    }
}
