package SlayTheSpider.Model.Game;

import SlayTheSpider.Model.Game.StatusEffect.*;
import SlayTheSpider.Model.Game.StatusEffect.CharacterActions.IncomingAttack;
import SlayTheSpider.Model.Game.StatusEffect.CharacterActions.IncomingHeal;
import SlayTheSpider.Model.Game.StatusEffect.CharacterActions.OutgoingAttack;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestStatusEffect {

    @Test
    public void TestGetSets() {
        StatusEffect effect = new StatusEffect() {
            @Override
            public void incomingAttack(IncomingAttack incomingAttack) {}

            @Override
            public void incomingHeal(IncomingHeal incomingHeal) {}

            @Override
            public void outgoingAttack(OutgoingAttack outgoingAttack) {}

            @Override
            public String getName() {
                return "CooL Name";
            }

            @Override
            public String getColor() {
                return "CooL Color";
            }
        };

        assertEquals(effect.getName(), "CooL Name");
        assertEquals(effect.getColor(), "CooL Color");
    }

    @Test
    public void TestEntry() {
        StatusEffect effectMock = Mockito.mock(StatusEffect.class);

        StatusEffectListEntry entry = new StatusEffectListEntry(effectMock, 2);

        assertEquals(entry.getEffect(), effectMock);
        assertEquals(entry.getRoundsLeft(), 2);
        assertEquals(entry.isWornOff(), false);

        entry.increaseDuration(3);
        assertEquals(entry.getRoundsLeft(), 5);
        assertEquals(entry.isWornOff(), false);

        entry.endRound();
        entry.endRound();
        entry.endRound();
        entry.endRound();
        assertEquals(entry.getRoundsLeft(), 1);
        assertEquals(entry.isWornOff(), false);

        entry.endRound();
        assertEquals(entry.getRoundsLeft(), 0);
        assertEquals(entry.isWornOff(), true);

        entry.endRound();
        assertEquals(entry.getRoundsLeft(), -1);
        assertEquals(entry.isWornOff(), true);
    }

    @Test
    public void TestList() {
        StatusEffect effectMock1 = Mockito.mock(StatusEffect.class);
        StatusEffect effectMock2 = Mockito.mock(StatusEffect.class);

        Mockito.when(effectMock1.getName()).thenReturn("1");
        Mockito.when(effectMock2.getName()).thenReturn("2");

        StatusEffectList list = new StatusEffectList();
        list.add(effectMock1, 2);
        list.add(effectMock2, 3);
        list.add(effectMock2, 1);

        assertEquals(list.size(), 2);
        assertEquals(list.getList().get(0).getRoundsLeft(), 2);
        assertEquals(list.getList().get(1).getRoundsLeft(), 4);

        list.endTurn();

        assertEquals(list.size(), 2);
        assertEquals(list.getList().get(0).getRoundsLeft(), 1);
        assertEquals(list.getList().get(1).getRoundsLeft(), 3);

        list.endTurn();

        assertEquals(list.size(), 1);
        assertEquals(list.getList().get(0).getRoundsLeft(), 2);
        list.add(effectMock2, 1);

        assertEquals(list.size(), 1);
        assertEquals(list.getList().get(0).getRoundsLeft(), 3);

        list.clear();
        assertEquals(list.size(), 0);
    }

    @Test
    public void TestEffects() {
        StatusEffect stun = new StatusEffectStunned();
        StatusEffect weak = new StatusEffectWeak();
        StatusEffect armored = new StatusEffectArmored();
        StatusEffect sick = new StatusEffectSick();
        StatusEffect strength = new StatusEffectStrength();

        assertEquals(stun.getName(), "Stunned");
        assertEquals(weak.getName(), "Weak");
        assertEquals(armored.getName(), "Armored");
        assertEquals(sick.getName(), "Sick");
        assertEquals(strength.getName(), "Strength");

        assertEquals(stun.getColor(), "bcdf12");
        assertEquals(weak.getColor(), "bbbbbb");
        assertEquals(armored.getColor(), "654321");
        assertEquals(sick.getColor(), "00aa00");
        assertEquals(strength.getColor(), "ffffff");
    }

    @Test
    public void TestStun() {
        StatusEffect stun = new StatusEffectStunned();

        IncomingAttack incomingAttackMock = Mockito.mock(IncomingAttack.class);
        OutgoingAttack outgoingAttackMock = Mockito.mock(OutgoingAttack.class);
        IncomingHeal incomingHealMock = Mockito.mock(IncomingHeal.class);

        stun.incomingAttack(incomingAttackMock);
        stun.outgoingAttack(outgoingAttackMock);
        stun.incomingHeal(incomingHealMock);

        Mockito.verify(outgoingAttackMock, Mockito.times(1)).nullifyDamage();
        Mockito.verifyZeroInteractions(incomingAttackMock);
        Mockito.verifyZeroInteractions(incomingHealMock);
    }

    @Test
    public void TestWeak() {
        StatusEffect weak = new StatusEffectWeak();

        IncomingAttack incomingAttackMock = Mockito.mock(IncomingAttack.class);
        OutgoingAttack outgoingAttackMock = Mockito.mock(OutgoingAttack.class);
        IncomingHeal incomingHealMock = Mockito.mock(IncomingHeal.class);

        weak.incomingAttack(incomingAttackMock);
        weak.outgoingAttack(outgoingAttackMock);
        weak.incomingHeal(incomingHealMock);

        Mockito.verify(outgoingAttackMock, Mockito.times(1)).halveDamage();
        Mockito.verifyZeroInteractions(incomingAttackMock);
        Mockito.verifyZeroInteractions(incomingHealMock);
    }

    @Test
    public void TestStrength() {
        StatusEffect strength = new StatusEffectStrength();

        IncomingAttack incomingAttackMock = Mockito.mock(IncomingAttack.class);
        OutgoingAttack outgoingAttackMock = Mockito.mock(OutgoingAttack.class);
        IncomingHeal incomingHealMock = Mockito.mock(IncomingHeal.class);

        strength.incomingAttack(incomingAttackMock);
        strength.outgoingAttack(outgoingAttackMock);
        strength.incomingHeal(incomingHealMock);

        Mockito.verifyZeroInteractions(incomingAttackMock);
        Mockito.verify(outgoingAttackMock, Mockito.times(1)).doubleDamage();
        Mockito.verifyZeroInteractions(incomingHealMock);
    }


    @Test
    public void TestSick() {
        StatusEffect sick = new StatusEffectSick();

        IncomingAttack incomingAttackMock = Mockito.mock(IncomingAttack.class);
        OutgoingAttack outgoingAttackMock = Mockito.mock(OutgoingAttack.class);
        IncomingHeal incomingHealMock = Mockito.mock(IncomingHeal.class);

        sick.incomingAttack(incomingAttackMock);
        sick.outgoingAttack(outgoingAttackMock);
        sick.incomingHeal(incomingHealMock);

        Mockito.verifyZeroInteractions(incomingAttackMock);
        Mockito.verifyZeroInteractions(outgoingAttackMock);
        Mockito.verify(incomingHealMock, Mockito.times(1)).nullifyHeal();
    }


    @Test
    public void TestArmored() {
        StatusEffect armored = new StatusEffectArmored();

        IncomingAttack incomingAttackMock = Mockito.mock(IncomingAttack.class);
        OutgoingAttack outgoingAttackMock = Mockito.mock(OutgoingAttack.class);
        IncomingHeal incomingHealMock = Mockito.mock(IncomingHeal.class);

        armored.incomingAttack(incomingAttackMock);
        armored.outgoingAttack(outgoingAttackMock);
        armored.incomingHeal(incomingHealMock);

        Mockito.verify(incomingAttackMock, Mockito.times(1)).halveDamage();
        Mockito.verifyZeroInteractions(outgoingAttackMock);
        Mockito.verifyZeroInteractions(incomingHealMock);
    }

}
