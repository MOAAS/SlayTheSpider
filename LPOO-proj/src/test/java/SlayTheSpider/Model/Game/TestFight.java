package SlayTheSpider.Model.Game;

import SlayTheSpider.Model.Sprites.Sprite;
import SlayTheSpider.Model.Game.FightRewards.RewardList;
import SlayTheSpider.Model.Position.Position;
import SlayTheSpider.Model.Sprites.TextSprite;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class TestFight {

    @Test
    public void TestCopy() {
        Sprite spriteMock = Mockito.mock(Sprite.class);
        RewardList rewardListMock = Mockito.mock(RewardList.class);
        RewardList copyMock = Mockito.mock(RewardList.class);
        Mockito.when(rewardListMock.copy()).thenReturn(copyMock);

        Fight fight = new Fight(spriteMock, rewardListMock);

        assertSame(fight.getRewards(), copyMock);

        TextSprite sprite = Mockito.mock(TextSprite.class);
        Enemy enemy1 = new Enemy(new Position(1, 1), sprite, 10, 5, 0);
        Enemy enemy2 = new Enemy(new Position(1, 1), sprite, 10, 5, 0);
        Enemy enemy3 = new Enemy(new Position(1, 1), sprite, 10, 5, 0);

        fight.addEnemy(enemy1);
        fight.addEnemy(enemy2);
        fight.addEnemy(enemy3);

        assertNotSame(fight.getEnemies().get(0), enemy1);
        assertNotSame(fight.getEnemies().get(1), enemy2);
        assertNotSame(fight.getEnemies().get(2), enemy3);

        Fight fight2 = new Fight(fight);
        assertSame(fight.getRewards(), copyMock);
        assertNotSame(fight.getEnemies().get(0), fight2.getEnemies().get(0));
        assertNotSame(fight.getEnemies().get(1), fight2.getEnemies().get(1));
        assertNotSame(fight.getEnemies().get(2), fight2.getEnemies().get(2));

        assertEquals(fight.getEnemies().size(), 3);
        assertEquals(fight2.getEnemies().size(), 3);
    }

    @Test
    public void TestBegin() {
        Enemy enemyMock1 = Mockito.mock(Enemy.class);
        Enemy enemyMock2 = Mockito.mock(Enemy.class);
        Enemy enemyMock3 = Mockito.mock(Enemy.class);

        Sprite spriteMock = Mockito.mock(Sprite.class);
        RewardList rewardListMock = Mockito.mock(RewardList.class);
        Fight fight = new Fight(spriteMock, rewardListMock);

        fight.getEnemies().add(enemyMock1);
        fight.getEnemies().add(enemyMock2);
        fight.getEnemies().add(enemyMock3);

        assertEquals(fight.getEnemies().size(), 3);


        assertEquals(fight.getEnemies().get(0), enemyMock1);
        assertEquals(fight.getEnemies().get(1), enemyMock2);
        assertEquals(fight.getEnemies().get(2), enemyMock3);
    }

    @Test
    public void TestClear() {
        Enemy enemyMock1 = Mockito.mock(Enemy.class);
        Enemy enemyMock2 = Mockito.mock(Enemy.class);
        Enemy enemyMock3 = Mockito.mock(Enemy.class);
        Player playerMock = Mockito.mock(Player.class);
        TargetList targetListMock = Mockito.mock(TargetList.class);

        Sprite spriteMock = Mockito.mock(Sprite.class);
        RewardList rewardListMock = Mockito.mock(RewardList.class);
        Fight fight = new Fight(spriteMock, rewardListMock);

        when(enemyMock1.isDead()).thenReturn(true);
        when(enemyMock2.isDead()).thenReturn(false);
        when(enemyMock3.isDead()).thenReturn(true);

        when(playerMock.getTargets()).thenReturn(targetListMock);

        fight.getEnemies().add(enemyMock1);
        fight.getEnemies().add(enemyMock2);
        fight.getEnemies().add(enemyMock3);

        assertEquals(fight.getEnemies().size(), 3);
        assertFalse(fight.isEmpty());

        fight.clearDeadCharacters();

        assertEquals(fight.getEnemies().size(), 1);
        assertFalse(fight.isEmpty());

        Mockito.verify(enemyMock1, times(1)).isDead();
        Mockito.verify(enemyMock2, times(1)).isDead();
        Mockito.verify(enemyMock3, times(1)).isDead();
    }

    @Test
    public void TestAttack() {
        Enemy enemyMock1 = Mockito.mock(Enemy.class);
        Enemy enemyMock2 = Mockito.mock(Enemy.class);
        Enemy enemyMock3 = Mockito.mock(Enemy.class);
        Player playerMock = Mockito.mock(Player.class);

        Sprite spriteMock = Mockito.mock(Sprite.class);
        RewardList rewardListMock = Mockito.mock(RewardList.class);
        Fight fight = new Fight(spriteMock, rewardListMock);

        fight.getEnemies().add(enemyMock1);
        fight.getEnemies().add(enemyMock2);
        fight.getEnemies().add(enemyMock3);

        fight.endTurn(playerMock);

        Mockito.verify(enemyMock1, times(1)).attack(playerMock);
        Mockito.verify(enemyMock2, times(1)).attack(playerMock);
        Mockito.verify(enemyMock3, times(1)).attack(playerMock);

        Mockito.verify(enemyMock1, times(1)).endTurn();
        Mockito.verify(enemyMock2, times(1)).endTurn();
        Mockito.verify(enemyMock3, times(1)).endTurn();

        Mockito.verify(enemyMock1, times(1)).attack(any());
        Mockito.verify(enemyMock2, times(1)).attack(any());
        Mockito.verify(enemyMock3, times(1)).attack(any());

        Mockito.verify(playerMock, times(1)).endTurn();
    }

    @Test
    public void TestEmpty() {
        Enemy enemyMock1 = Mockito.mock(Enemy.class);
        Enemy enemyMock2 = Mockito.mock(Enemy.class);
        Enemy enemyMock3 = Mockito.mock(Enemy.class);
        Player playerMock = Mockito.mock(Player.class);
        TargetList targetList = Mockito.mock(TargetList.class);

        when(enemyMock1.isDead()).thenReturn(true);
        when(enemyMock2.isDead()).thenReturn(true);
        when(enemyMock3.isDead()).thenReturn(true);
        when(playerMock.getTargets()).thenReturn(targetList);

        Sprite spriteMock = Mockito.mock(Sprite.class);
        RewardList rewardListMock = Mockito.mock(RewardList.class);
        Fight fight = new Fight(spriteMock, rewardListMock);

        fight.getEnemies().add(enemyMock1);
        fight.getEnemies().add(enemyMock2);
        fight.getEnemies().add(enemyMock3);

        assertFalse(fight.isEmpty());

        fight.clearDeadCharacters();

        assertTrue(fight.isEmpty());
    }

    @Test
    public void TestSprite() {
        Sprite spriteMock = Mockito.mock(Sprite.class);
        RewardList rewardListMock = Mockito.mock(RewardList.class);
        Fight fight = new Fight(spriteMock, rewardListMock);

        assertSame(fight.getSprite(), spriteMock);
    }

}
