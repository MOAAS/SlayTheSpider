package SlayTheSpider.Model.Game;

import SlayTheSpider.Model.Game.CardEffect.CardEffect;
import SlayTheSpider.Model.Game.CardEffect.Conditions.CardEffectConditionList;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class TestCard {

    @Test
    public void testUse() {
        CardEffect CoolEffect1 = Mockito.mock(CardEffect.class);
        CardEffect CoolEffect2 = Mockito.mock(CardEffect.class);
        CardEffect CoolEffect3 = Mockito.mock(CardEffect.class);
        CardEffect CoolEffect4 = Mockito.mock(CardEffect.class);
        Player playerMock = Mockito.mock(Player.class);

        Card card = new Card("Test card", 5, "0");
        card.addEffect(CoolEffect1);
        card.addEffect(CoolEffect2);
        card.addEffect(CoolEffect3);
        card.addEffect(CoolEffect4);
        card.use(playerMock);
        Mockito.verify(CoolEffect1, times(1)).use(playerMock);
        Mockito.verify(CoolEffect2, times(1)).use(playerMock);
        Mockito.verify(CoolEffect3, times(1)).use(playerMock);
        Mockito.verify(CoolEffect4, times(1)).use(playerMock);
    }

    @Test
    public void testCardColor() {
        CardEffect ShieldEffect = Mockito.mock(CardEffect.class);
        when(ShieldEffect.needsTargetSelection()).thenReturn(false);

        Card ShieldCard = new Card("Test card", 5, "ffffff");
        ShieldCard.addEffect(ShieldEffect);
        assertEquals(ShieldCard.needsTargetSelection(), false);
        assertEquals(ShieldCard.getColor(), "ffffff");
    }


    @Test
    public void testDoubleCard() {
        CardEffect CoolEffect1 = Mockito.mock(CardEffect.class);
        CardEffect CoolEffect2 = Mockito.mock(CardEffect.class);
        when(CoolEffect1.getDescription()).thenReturn("WOOP, DEALS COOL DAMAGE");
        when(CoolEffect2.getDescription()).thenReturn("AND THIS RESTORES SUM HEALTH");
        when(CoolEffect1.getConditions()).thenReturn(Mockito.mock(CardEffectConditionList.class));
        when(CoolEffect2.getConditions()).thenReturn(Mockito.mock(CardEffectConditionList.class));

        when(CoolEffect1.needsTargetSelection()).thenReturn(true);
        when(CoolEffect2.needsTargetSelection()).thenReturn(false);

        Card DoubleCard = new Card("Test card",  5, "0");
        DoubleCard.addEffect(CoolEffect1);
        DoubleCard.addEffect(CoolEffect2);
        DoubleCard.setColor("00ff00");

        assertEquals(DoubleCard.getDescription().get(0), "WOOP, DEALS COOL DAMAGE");
        assertEquals(DoubleCard.getDescription().get(1), "AND THIS RESTORES SUM HEALTH");
        assertEquals(DoubleCard.getColor(), "00ff00");
        assertEquals(DoubleCard.getName(), "Test card");
        assertEquals(DoubleCard.getCost(), 5);
        assertEquals(DoubleCard.needsTargetSelection(), true);
        assertEquals(DoubleCard.getEffects().size(), 2);

        assertEquals(DoubleCard.getEffects().get(0), CoolEffect1);
        assertEquals(DoubleCard.getEffects().get(1), CoolEffect2);
    }

    /*
    @Test
    public void testEquals() {
        CardEffect CoolEffect1 = Mockito.mock(CardEffect.class);

        Card card = new Card("Test card", CoolEffect1, 5, "0");

        Card clone = new Card(card);

        assertNotEquals(card, clone);
        assertEquals(card, card);
        assertEquals(clone, clone);
        assert(card != clone);

        Card card2 = new Card("Test c4rd", CoolEffect1, 5, "0");
        assertNotEquals(card, card2);

        assertNotEquals(card, 1);
        assertNotEquals(card, new ArrayList<>());
        assertNotEquals(card2, "lmao");

    }
    */


}
