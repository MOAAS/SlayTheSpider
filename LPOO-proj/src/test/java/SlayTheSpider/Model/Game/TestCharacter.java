package SlayTheSpider.Model.Game;

import SlayTheSpider.Model.Game.HealthBar.HealthBar;
import SlayTheSpider.Model.Game.StatusEffect.*;
import SlayTheSpider.Model.Sprites.Sprite;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TestCharacter {
    private Character character;
    private Sprite sprite = Mockito.mock(Sprite.class);
    private HealthBar healthBar = Mockito.mock(HealthBar.class);

    @Before
    public void setup() {
        character = new Character(sprite, 200) {
            @Override
            public HealthBar createHealthBar(int maxHealth) {
                return healthBar;
            }

            @Override
            public void endTurn() {
            }

            @Override
            protected void notifyHeal(int heal) {}

            @Override
            protected void notifyDamageTaken(int damage) { }

            @Override
            protected void notifyDamageDealt(int damage) { }

            @Override
            public HealthBar getHealthBar() {
                return healthBar;
            }
        };

        character.statusEffects = Mockito.mock(StatusEffectList.class);
        character.healthBar = healthBar;
    }

    @Test
    public void TestGets() {
        StatusEffectList statusEffectList = Mockito.mock(StatusEffectList.class);
        character.statusEffects = statusEffectList;

        assertEquals(character.getStatusEffects(), statusEffectList);
        assertEquals(character.getHealthBar(), healthBar);
        assertEquals(character.getSprite(), sprite);
        assertEquals(character.createHealthBar(100), healthBar);
    }

    @Test
    public void TestAttack() {
        Player playerMock1 = Mockito.mock(Player.class);
        Player playerMock2 = Mockito.mock(Player.class);
        Player playerMock3 = Mockito.mock(Player.class);

        Enemy enemyMock1 = Mockito.mock(Enemy.class);
        Enemy enemyMock2 = Mockito.mock(Enemy.class);
        Enemy enemyMock3 = Mockito.mock(Enemy.class);

        character.attack(playerMock1, 20);
        character.attack(enemyMock1, 10);

        Mockito.verify(playerMock1, times(1)).takeHit(20);
        Mockito.verify(enemyMock1, times(1)).takeHit(10);
        Mockito.verify(playerMock1, times(1)).takeHit(anyInt());
        Mockito.verify(enemyMock1, times(1)).takeHit(anyInt());

        character.attack(playerMock2, 20);
        character.attack(enemyMock2, 10);

        character.statusEffects = new StatusEffectList();
        character.statusEffects.add(new StatusEffectWeak(), 3);

        character.attack(playerMock3, 20);
        character.attack(enemyMock3, 10);

        Mockito.verify(playerMock3, times(1)).takeHit(10);
        Mockito.verify(enemyMock3, times(1)).takeHit(5);
        Mockito.verify(playerMock3, times(1)).takeHit(anyInt());
        Mockito.verify(enemyMock3, times(1)).takeHit(anyInt());
    }

    @Test
    public void TestOutgoingAtt() {
        Enemy enemyMock1 = Mockito.mock(Enemy.class);
        Player playerMock1 = Mockito.mock(Player.class);

        character.statusEffects = new StatusEffectList();
        character.statusEffects.add(new StatusEffectWeak(), 3);

        character.attack(playerMock1, 20);
        character.attack(enemyMock1, 10);

        Mockito.verify(playerMock1, times(1)).takeHit(10);
        Mockito.verify(enemyMock1, times(1)).takeHit(5);
        Mockito.verify(playerMock1, times(1)).takeHit(anyInt());
        Mockito.verify(enemyMock1, times(1)).takeHit(anyInt());
    }

    @Test
    public void TestIncomingAtt() {
        character.statusEffects = new StatusEffectList();
        character.statusEffects.add(new StatusEffectArmored(), 3);

        character.takeHit(10);


        Mockito.verify(healthBar, times(1)).takeHit(5);
        Mockito.verify(healthBar, times(1)).takeHit(anyInt());
    }

    @Test
    public void TestIncomingHeal() {
        character.heal(90);

        Mockito.verify(healthBar, times(1)).increaseHealth(90);
        Mockito.verify(healthBar, times(1)).increaseHealth(anyInt());

        character.statusEffects = new StatusEffectList();
        character.statusEffects.add(new StatusEffectSick(), 3);

        character.heal(90);

        Mockito.verify(healthBar, times(1)).increaseHealth(0);
        Mockito.verify(healthBar, times(2)).increaseHealth(anyInt());

    }

    @Test
    public void TestHitHeal() {
        character.takeHit(20);

        Mockito.verify(healthBar, times(1)).takeHit(20);
        Mockito.verify(healthBar, times(0)).takeHit(0);

        character.heal(200);

        Mockito.verify(healthBar, times(1)).increaseHealth(200);
        Mockito.verify(healthBar, times(0)).increaseHealth(0);
    }

    @Test
    public void TestDead() {
        Mockito.when(healthBar.getHealth()).thenReturn(10);

        assertFalse(character.isDead());

        Mockito.when(healthBar.getHealth()).thenReturn(0);

        assertTrue(character.isDead());
    }

    @Test
    public void TestApply() {
        StatusEffect effectMock1 = Mockito.mock(StatusEffect.class);
        StatusEffect effectMock2 = Mockito.mock(StatusEffect.class);

        character.applyStatus(effectMock1, 1);
        character.applyStatus(effectMock2, 3);

        Mockito.verify(character.statusEffects, times(1)).add(effectMock1, 1);
        Mockito.verify(character.statusEffects, times(1)).add(effectMock2, 3);
        Mockito.verify(character.statusEffects, times(2)).add(any(), anyInt());
    }

    @Test
    public void TestNotifies() {
        Enemy enemyMock1 = Mockito.mock(Enemy.class);

        Character testChar = new Character(Mockito.mock(Sprite.class), 10) {
            @Override
            protected HealthBar createHealthBar(int maxHealth) {
                return Mockito.mock(HealthBar.class);
            }

            @Override
            public void endTurn() {}

            @Override
            protected void notifyHeal(int heal) {

            }

            @Override
            protected void notifyDamageTaken(int damage) {

            }

            @Override
            protected void notifyDamageDealt(int damage) {

            }
        };

        Character charSpy = Mockito.spy(testChar);

        charSpy.attack(enemyMock1, 10);

        Mockito.verify(charSpy, times(1)).notifyDamageDealt(10);
        Mockito.verify(charSpy, times(0)).notifyDamageTaken(anyInt());
        Mockito.verify(charSpy, times(0)).notifyHeal(anyInt());

        charSpy.takeHit(9);

        Mockito.verify(charSpy, times(1)).notifyDamageTaken(9);

        charSpy.heal(3);

        Mockito.verify(charSpy, times(1)).notifyHeal(3);

    }
}
