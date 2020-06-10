package SlayTheSpider.Model.Game;

import SlayTheSpider.Model.Game.HealthBar.EnemyHealthBar;
import SlayTheSpider.Model.Game.StatusEffect.StatusEffectList;
import SlayTheSpider.Model.Position.Position;
import SlayTheSpider.Model.Sprites.Sprite;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

public class TestEnemy {
    Position positionMock = Mockito.mock(Position.class);
    Sprite spriteMock = Mockito.mock(Sprite.class);

    @Test
    public void TestConstructors() {
        Enemy enemy = new Enemy(positionMock, spriteMock, 100, 5,0);

        assertEquals(enemy.getDamage(), 5);
        assertEquals(enemy.getPosition(), positionMock);
        assertEquals(enemy.getSprite(), spriteMock);
        assertTrue(enemy.createHealthBar(100) instanceof EnemyHealthBar);
        enemy.getHealthBar();

        Enemy enemyCopy = new Enemy(enemy);
        assertEquals(enemyCopy.getDamage(), 5);
        assertEquals(enemyCopy.getPosition(), positionMock);
        assertEquals(enemyCopy.getSprite(), spriteMock);
        assertTrue(enemyCopy.createHealthBar(100) instanceof EnemyHealthBar);
        enemyCopy.getHealthBar();
    }

    @Test
    public void TestAttack() {
        Enemy enemy = new Enemy(positionMock, spriteMock, 100, 5, 0);
        Enemy enemySpy = Mockito.spy(enemy);

        enemySpy.attack(enemy);
        Mockito.verify(enemySpy, Mockito.times(1)).attack(enemy);//, enemySpy.getDamage());
        Mockito.verify(enemySpy, Mockito.times(1)).attack(any());

        assertEquals(enemy.getHealthBar().getHealth(), 95);
    }

    @Test
    public void TestEndTurn() {
        Enemy enemy = new Enemy(positionMock, spriteMock, 100, 5, 0);
        StatusEffectList effectList = Mockito.mock(StatusEffectList.class);
        enemy.statusEffects = effectList;

        enemy.endTurn();

        Mockito.verify(effectList, times(1)).endTurn();

        enemy.endTurn();

        Mockito.verify(effectList, times(2)).endTurn();

    }


    @Test
    public void TestDamageScale() {
        Enemy enemy = new Enemy(positionMock, spriteMock, 100, 5, 0.5);

        enemy.statusEffects = Mockito.mock(StatusEffectList.class);

        assertEquals(enemy.getDamageScale(), 0.5, 0.0001);
        assertEquals(enemy.getDamage(), 5);
        enemy.endTurn();

        assertEquals(enemy.getDamageScale(), 0.5, 0.0001);
        assertEquals(enemy.getDamage(), 6);
    }

    @Test
    public void TestNotify() {
        Enemy enemy = new Enemy(positionMock, spriteMock, 100, 5, 0.5);

        enemy.notifyDamageDealt(9);
        enemy.notifyDamageTaken(9);
        enemy.notifyHeal(9);
    }

}
