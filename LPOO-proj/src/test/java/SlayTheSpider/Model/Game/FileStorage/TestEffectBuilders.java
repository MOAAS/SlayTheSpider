package SlayTheSpider.Model.Game.FileStorage;

import SlayTheSpider.Model.Game.Card;
import SlayTheSpider.Model.Game.CardEffect.*;
import SlayTheSpider.Model.Game.FileStorage.ElementParser.CardEffectBuilders.*;
import SlayTheSpider.Model.Game.StatusEffect.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;

public class TestEffectBuilders {
    Element elementMock = Mockito.mock(Element.class);
    List<Card> cardList = new ArrayList<>();
    XMLCardEffectBuilder builder;

    @Before
    public void Setup() {
        elementMock = Mockito.mock(Element.class);
        cardList = new ArrayList<>();
        cardList.add(Mockito.mock(Card.class));
        cardList.add(Mockito.mock(Card.class));

        Mockito.when(elementMock.getAttribute(Mockito.anyString())).thenReturn("91");
    }

    @Test
    public void TestAddCards() {
        builder = new XMLAddCardsEffectBuilder(cardList);

        assertTrue(builder.create(elementMock) instanceof AddCardsEffect);

        Mockito.verify(elementMock, times(1)).getAttribute("amount");
    }

    @Test
    public void TestAoEDmg() {
        builder = new XMLAoEDamageEffectBuilder();

        assertTrue(builder.create(elementMock) instanceof AoEDamageEffect);

        Mockito.verify(elementMock, times(1)).getAttribute("amount");
    }

    @Test
    public void TestAoELifeDrain() {
        builder = new XMLAoELifeDrainEffectBuilder();

        assertTrue(builder.create(elementMock) instanceof AoELifeDrainEffect);

        Mockito.verify(elementMock, times(1)).getAttribute("amount");
        Mockito.verify(elementMock, times(1)).getAttribute("drainPerDmg");
    }

    @Test
    public void TestAoEShieldToDmg() {
        builder = new XMLAoEShieldToDamageEffectBuilder();

        assertTrue(builder.create(elementMock) instanceof AoEShieldToDamageEffect);

        Mockito.verify(elementMock, times(1)).getAttribute("perShieldPoint");
    }


    @Test
    public void TestDMG() {
        builder = new XMLDamageEffectBuilder();

        assertTrue(builder.create(elementMock) instanceof DamageEffect);

        Mockito.verify(elementMock, times(1)).getAttribute("amount");
    }


    @Test
    public void TestDiscard() {
        builder = new XMLDiscardCardEffectBuilder();

        assertTrue(builder.create(elementMock) instanceof SelfDiscardCardEffect);

        Mockito.verify(elementMock, times(1)).getAttribute("amount");
    }


    @Test
    public void TestDrawCard() {
        builder = new XMLDrawCardEffectBuilder();

        assertTrue(builder.create(elementMock) instanceof SelfDrawCardEffect);

        Mockito.verify(elementMock, times(1)).getAttribute("amount");
    }


    @Test
    public void TestFightHealToDmg() {
        builder = new XMLFightHealToDamageEffectBuilder();

        assertTrue(builder.create(elementMock) instanceof FightHealToDamageEffect);

        Mockito.verify(elementMock, times(1)).getAttribute("perHealthPoint");
    }

    @Test
    public void TestManaRefill() {
        builder = new XMLManaRefillEffectBuilder();

        assertTrue(builder.create(elementMock) instanceof ManaRefillEffect);

        Mockito.verifyZeroInteractions(elementMock);
    }

    @Test
    public void TestManaRestore() {
        builder = new XMLManaRestoreEffectBuilder();

        assertTrue(builder.create(elementMock) instanceof ManaRestoreEffect);

        Mockito.verify(elementMock, times(1)).getAttribute("amount");
    }

    @Test
    public void TestNull() {
        builder = new XMLNullBuilder();

        assertTrue(builder.create(elementMock) instanceof NullCardEffect);

        Mockito.verifyZeroInteractions(elementMock);
    }

    @Test
    public void TestSelfDmg() {
        builder = new XMLSelfDamageEffectBuilder();

        assertTrue(builder.create(elementMock) instanceof SelfDamageEffect);

        Mockito.verify(elementMock, times(1)).getAttribute("amount");
    }

    @Test
    public void TestSelfHeal() {
        builder = new XMLSelfHealEffectBuilder();

        assertTrue(builder.create(elementMock) instanceof SelfHealEffect);

        Mockito.verify(elementMock, times(1)).getAttribute("amount");
    }

    @Test
    public void TestSelfShieldClear() {
        builder = new XMLSelfShieldClearBuilder();

        assertTrue(builder.create(elementMock) instanceof SelfShielfClearEffect);

        Mockito.verifyZeroInteractions(elementMock);
    }

    @Test
    public void TestSelfShield() {
        builder = new XMLSelfShieldEffectBuilder();

        assertTrue(builder.create(elementMock) instanceof SelfShieldEffect);

        Mockito.verify(elementMock, times(1)).getAttribute("amount");
    }

    @Test
    public void TestStatus() {

        XMLStatusEffectBuilder stBuilder = new XMLStatusEffectBuilder() {
            @Override
            public CardEffect createCardEffect(StatusEffect effect, int duration) {
                return null;
            }
        };

        XMLStatusEffectBuilder spy = Mockito.spy(stBuilder);
        assertNull(spy.create(elementMock));
        Mockito.verify(elementMock, times(1)).getAttribute("effect");
        Mockito.verify(elementMock, times(1)).getAttribute("duration");
        Mockito.verify(spy, times(1)).getEffect(anyString());

        XMLStatusEffectBuilder selfBuilder = new XMLSelfStatusEffectBuilder();
        assertTrue(selfBuilder.createCardEffect(null, 10) instanceof NullCardEffect);
        assertTrue(selfBuilder.createCardEffect(Mockito.mock(StatusEffect.class), 10) instanceof SelfStatusEffect);

        XMLStatusEffectBuilder targetBuilder = new XMLTargetedStatusEffectBuilder();
        assertTrue(targetBuilder.createCardEffect(null, 10) instanceof NullCardEffect);
        assertTrue(targetBuilder.createCardEffect(Mockito.mock(StatusEffect.class), 10) instanceof TargetedStatusEffect);

        assertTrue(stBuilder.getEffect("Weak") instanceof StatusEffectWeak);
        assertTrue(stBuilder.getEffect("Stun") instanceof StatusEffectStunned);
        assertTrue(stBuilder.getEffect("Strength") instanceof StatusEffectStrength);
        assertTrue(stBuilder.getEffect("Armored") instanceof StatusEffectArmored);
        assertTrue(stBuilder.getEffect("Sick") instanceof StatusEffectSick);
        assertTrue(stBuilder.getEffect("TestEffect") == null);
    }

    @Test
    public void TestShuffleFight() {
        Mockito.when(cardList.get(0).getName()).thenReturn("91");
        Mockito.when(cardList.get(1).getName()).thenReturn("92");

        builder = new XMLShuffleCardFightEffectBuilder(cardList);

        assertTrue(builder.create(elementMock) instanceof ShuffleCardFightEffect);

        Mockito.verify(elementMock, times(1)).getAttribute("cardName");
    }

    @Test (expected = NullPointerException.class)
    public void TestShuffleNotFound() {
        Mockito.when(cardList.get(0).getName()).thenReturn("967");
        Mockito.when(cardList.get(1).getName()).thenReturn("989");

        builder = new XMLShuffleCardFightEffectBuilder(cardList);
        builder.create(elementMock);

        builder = new XMLShuffleCardPermaEffectBuilder(cardList);
        builder.create(elementMock);
    }

    @Test
    public void TestShufflePerma() {
        Mockito.when(cardList.get(0).getName()).thenReturn("91");
        Mockito.when(cardList.get(1).getName()).thenReturn("92");

        builder = new XMLShuffleCardPermaEffectBuilder(cardList);

        assertTrue(builder.create(elementMock) instanceof ShuffleCardPermaEffect);

        Mockito.verify(elementMock, times(1)).getAttribute("cardName");
    }

    @Test
    public void TestCardsToDmg() {
        builder = new XMLTurnCardsToDamageEffectBuilder();

        assertTrue(builder.create(elementMock) instanceof TurnCardsToDamageEffect);

        Mockito.verify(elementMock, times(1)).getAttribute("perCard");
    }

    @Test
    public void TestCardsToShield() {
        builder = new XMLTurnCardsToShieldEffectBuilder();

        assertTrue(builder.create(elementMock) instanceof TurnCardsToShieldEffect);

        Mockito.verify(elementMock, times(1)).getAttribute("perCard");
    }

    @Test
    public void TestCardsToHealth() {
        builder = new XMLTurnCardsToHealthEffectBuilder();

        assertTrue(builder.create(elementMock) instanceof TurnCardsToHealthEffect);

        Mockito.verify(elementMock, times(1)).getAttribute("perCard");
    }

    @Test
    public void TestMultiShield() {
        builder = new XMLShieldMultiEffectBuilder();

        assertTrue(builder.create(elementMock) instanceof ShieldMultiEffect);

        Mockito.verify(elementMock, times(1)).getAttribute("multiplier");

    }

}
