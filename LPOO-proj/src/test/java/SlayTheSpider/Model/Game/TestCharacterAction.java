package SlayTheSpider.Model.Game;

import SlayTheSpider.Model.Game.StatusEffect.CharacterActions.*;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

public class TestCharacterAction {
    @Test
    public void TestCharacterAction() {
        CharacterActionChanger changerMock1 = Mockito.mock(CharacterActionChanger.class);
        CharacterActionChanger changerMock2 = Mockito.mock(CharacterActionChanger.class);
        CharacterActionChanger changerMock3 = Mockito.mock(CharacterActionChanger.class);

        List<CharacterActionChanger> changerList = new ArrayList<>();
        changerList.add(changerMock1);
        changerList.add(changerMock2);
        changerList.add(changerMock3);


        IncomingAttack attackMock = Mockito.mock(IncomingAttack.class);

        CharacterAction action = new CharacterAction() {
            @Override
            public void applyChanger(CharacterActionChanger changer) {
                changer.incomingAttack(attackMock);
            }
        };

        action.applyEffects(changerList);

        Mockito.verify(changerMock1, times(1)).incomingAttack(attackMock);
        Mockito.verify(changerMock1, times(1)).incomingAttack(any());

        Mockito.verify(changerMock2, times(1)).incomingAttack(attackMock);
        Mockito.verify(changerMock2, times(1)).incomingAttack(any());

        Mockito.verify(changerMock3, times(1)).incomingAttack(attackMock);
        Mockito.verify(changerMock3, times(1)).incomingAttack(any());
    }

    @Test
    public void TestIncAttack() {
        IncomingAttack attack = new IncomingAttack(10);

        CharacterActionChanger changerMock = Mockito.mock(CharacterActionChanger.class);
        attack.applyChanger(changerMock);
        Mockito.verify(changerMock, times(1)).incomingAttack(attack);

        assertEquals(attack.getDamage(), 10);

        attack.setDamage(100);

        assertEquals(attack.getDamage(), 100);

        attack.halveDamage();
        attack.halveDamage();

        assertEquals(attack.getDamage(), 25);

        attack.nullifyDamage();

        assertEquals(attack.getDamage(), 0);
    }

    @Test
    public void TestOutAttack() {
        OutgoingAttack attack = new OutgoingAttack(8);
        CharacterActionChanger changerMock = Mockito.mock(CharacterActionChanger.class);
        attack.applyChanger(changerMock);
        Mockito.verify(changerMock, times(1)).outgoingAttack(attack);

        assertEquals(attack.getDamage(), 8);

        attack.setDamage(9);
        assertEquals(attack.getDamage(), 9);

        attack.halveDamage();
        attack.halveDamage();
        assertEquals(attack.getDamage(), 2);

        attack.doubleDamage();
        assertEquals(attack.getDamage(), 4);

        attack.nullifyDamage();
        assertEquals(attack.getDamage(), 0);
    }

    @Test
    public void TestIncHeal() {
        IncomingHeal heal = new IncomingHeal(6);
        CharacterActionChanger changerMock = Mockito.mock(CharacterActionChanger.class);
        heal.applyChanger(changerMock);
        Mockito.verify(changerMock, times(1)).incomingHeal(heal);

        assertEquals(heal.getHeal(), 6);

        heal.setHeal(88);
        assertEquals(heal.getHeal(), 88);

        heal.halveHeal();
        assertEquals(heal.getHeal(), 44);

        heal.nullifyHeal();
        assertEquals(heal.getHeal(), 0);
    }
}
