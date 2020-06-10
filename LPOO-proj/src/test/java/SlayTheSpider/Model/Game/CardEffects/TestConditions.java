package SlayTheSpider.Model.Game.CardEffects;

import SlayTheSpider.Model.Game.*;
import SlayTheSpider.Model.Game.CardEffect.Conditions.*;
import SlayTheSpider.Model.Game.Character;
import SlayTheSpider.Model.Game.HealthBar.EnemyHealthBar;
import SlayTheSpider.Model.Game.HealthBar.PlayerHealthBar;
import SlayTheSpider.Model.Game.Stats.PlayerStatEntry;
import SlayTheSpider.Model.Game.Stats.PlayerStats;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestConditions {
    Player playerMock = Mockito.mock(Player.class);
    Enemy enemyMock = Mockito.mock(Enemy.class);
    EnemyHealthBar enemyHealthBarMock = Mockito.mock(EnemyHealthBar.class);
    PlayerHealthBar playerHealthBarMock = Mockito.mock(PlayerHealthBar.class);
    Deck deckMock = Mockito.mock(Deck.class);
    List<Card> handMock = Mockito.mock(ArrayList.class);
    List<Enemy> enemies = Mockito.mock(ArrayList.class);
    TargetList targetListMock = Mockito.mock(TargetList.class);

    @Before
    public void Setup() {
        playerMock = Mockito.mock(Player.class);
        enemyMock = Mockito.mock(Enemy.class);
        enemyHealthBarMock = Mockito.mock(EnemyHealthBar.class);
        playerHealthBarMock = Mockito.mock(PlayerHealthBar.class);
        deckMock = Mockito.mock(Deck.class);
        handMock = Mockito.mock(ArrayList.class);
        enemies = Mockito.mock(ArrayList.class);

        Mockito.when(playerMock.getHealthBar()).thenReturn(playerHealthBarMock);
        Mockito.when(enemyMock.getHealthBar()).thenReturn(enemyHealthBarMock);

        Mockito.when(playerMock.getTarget()).thenReturn(enemyMock);
        Mockito.when(playerMock.getTargets()).thenReturn(targetListMock);
        Mockito.when(playerMock.getDeck()).thenReturn(deckMock);

        Mockito.when(deckMock.getHand()).thenReturn(handMock);

        Mockito.when(targetListMock.getTarget()).thenReturn(enemyMock);
        Mockito.when(targetListMock.getEnemies()).thenReturn(enemies);
    }

    @Test
    public void TestAbstracts() {
        CardEffectTargetCondition targetCondition = new CardEffectTargetCondition() {
            @Override
            protected boolean satisfies(Player user, Character target) { return true; }
            @Override
            public String getDescription() { return "Hi1"; }
        };

        CardEffectUserCondition userCondition = new CardEffectUserCondition() {
            @Override
            protected boolean satisfies(Player user) { return false; }
            @Override
            public String getDescription() { return "Hi2"; }
        };

        assertEquals(targetCondition.getDescription(), "Hi1");
        assertEquals(userCondition.getDescription(), "Hi2");
        assertTrue(targetCondition.verify(Mockito.mock(Player.class), Mockito.mock(Character.class)));
        assertFalse(userCondition.verify(Mockito.mock(Player.class), Mockito.mock(Character.class)));
    }

    @Test
    public void TestNull() {
        NullCardCondition cardCondition = new NullCardCondition();

        assertTrue(cardCondition.verify(playerMock, enemyMock));
        assertEquals(cardCondition.getDescription(), "Error: Null Condition.");
    }

    @Test
    public void TestNumEnemies() {
        NumEnemiesGreaterThan greaterThan = new NumEnemiesGreaterThan(8);
        NumEnemiesLessThan lessThan = new NumEnemiesLessThan(2);

        Mockito.when(enemies.size()).thenReturn(3);
        assertFalse(greaterThan.satisfies(playerMock, enemyMock));
        assertFalse(lessThan.satisfies(playerMock, enemyMock));

        Mockito.when(enemies.size()).thenReturn(8);
        assertFalse(greaterThan.satisfies(playerMock, enemyMock));
        assertFalse(lessThan.satisfies(playerMock, enemyMock));

        Mockito.when(enemies.size()).thenReturn(1);
        assertFalse(greaterThan.satisfies(playerMock, enemyMock));
        assertTrue(lessThan.satisfies(playerMock, enemyMock));

        assertEquals(greaterThan.getDescription(), "Number of enemies is greater than 8");
        assertEquals(lessThan.getDescription(), "Number of enemies is less than 2");
    }

    @Test
    public void TestTargetHP() {
        TargetHPGreaterThan greaterThan = new TargetHPGreaterThan(0.75);
        TargetHPEqualTo equalTo = new TargetHPEqualTo(0.5);
        TargetHPLessThan lessThan = new TargetHPLessThan(0.25);

        Mockito.when(enemyHealthBarMock.getHealth()).thenReturn(2);
        Mockito.when(enemyHealthBarMock.getMaxHealth()).thenReturn(4);
        assertFalse(greaterThan.satisfies(playerMock, enemyMock));
        assertTrue(equalTo.satisfies(playerMock, enemyMock));
        assertFalse(lessThan.satisfies(playerMock, enemyMock));

        Mockito.when(enemyHealthBarMock.getHealth()).thenReturn(1);
        Mockito.when(enemyHealthBarMock.getMaxHealth()).thenReturn(4);
        assertFalse(greaterThan.satisfies(playerMock, enemyMock));
        assertFalse(equalTo.satisfies(playerMock, enemyMock));
        assertFalse(lessThan.satisfies(playerMock, enemyMock));

        Mockito.when(enemyHealthBarMock.getHealth()).thenReturn(3);
        Mockito.when(enemyHealthBarMock.getMaxHealth()).thenReturn(4);
        assertFalse(greaterThan.satisfies(playerMock, enemyMock));
        assertFalse(equalTo.satisfies(playerMock, enemyMock));
        assertFalse(lessThan.satisfies(playerMock, enemyMock));

        Mockito.when(enemyHealthBarMock.getHealth()).thenReturn(1);
        Mockito.when(enemyHealthBarMock.getMaxHealth()).thenReturn(5);
        assertFalse(greaterThan.satisfies(playerMock, enemyMock));
        assertFalse(equalTo.satisfies(playerMock, enemyMock));
        assertTrue(lessThan.satisfies(playerMock, enemyMock));



        assertEquals(greaterThan.getDescription(),  "Target HP is greater than 75.0%");
        assertEquals(equalTo.getDescription(), "Target HP is equal to 50.0%");
        assertEquals(lessThan.getDescription(), "Target HP is less than 25.0%");
    }

    @Test
    public void TestUserHP() {
        UserHPGreaterThan greaterThan = new UserHPGreaterThan(0.75);
        UserHPEqualTo equalTo = new UserHPEqualTo(0.5);
        UserHPLessThan lessThan = new UserHPLessThan(0.25);

        Mockito.when(playerHealthBarMock.getHealth()).thenReturn(2);
        Mockito.when(playerHealthBarMock.getMaxHealth()).thenReturn(4);
        assertFalse(greaterThan.satisfies(playerMock));
        assertTrue(equalTo.satisfies(playerMock));
        assertFalse(lessThan.satisfies(playerMock));

        Mockito.when(playerHealthBarMock.getHealth()).thenReturn(1);
        Mockito.when(playerHealthBarMock.getMaxHealth()).thenReturn(4);
        assertFalse(greaterThan.satisfies(playerMock));
        assertFalse(equalTo.satisfies(playerMock));
        assertFalse(lessThan.satisfies(playerMock));

        Mockito.when(playerHealthBarMock.getHealth()).thenReturn(3);
        Mockito.when(playerHealthBarMock.getMaxHealth()).thenReturn(4);
        assertFalse(greaterThan.satisfies(playerMock));
        assertFalse(equalTo.satisfies(playerMock));
        assertFalse(lessThan.satisfies(playerMock));

        Mockito.when(playerHealthBarMock.getHealth()).thenReturn(1);
        Mockito.when(playerHealthBarMock.getMaxHealth()).thenReturn(5);
        assertFalse(greaterThan.satisfies(playerMock));
        assertFalse(equalTo.satisfies(playerMock));
        assertTrue(lessThan.satisfies(playerMock));

        assertEquals(greaterThan.getDescription(),  "User HP is greater than 75.0%");
        assertEquals(equalTo.getDescription(), "User HP is equal to 50.0%");
        assertEquals(lessThan.getDescription(), "User HP is less than 25.0%");
    }

    @Test
    public void TestHand() {
        UserHandGreaterThan greaterThan = new UserHandGreaterThan(8);
        UserHandSmallerThan lessThan = new UserHandSmallerThan(2);

        Mockito.when(handMock.size()).thenReturn(0);
        assertFalse(greaterThan.satisfies(playerMock));
        assertTrue(lessThan.satisfies(playerMock));

        Mockito.when(handMock.size()).thenReturn(8);
        assertFalse(greaterThan.satisfies(playerMock));
        assertFalse(lessThan.satisfies(playerMock));

        Mockito.when(handMock.size()).thenReturn(2);
        assertFalse(greaterThan.satisfies(playerMock));
        assertFalse(lessThan.satisfies(playerMock));

        assertEquals(greaterThan.getDescription(), "User hand size is larger than 8");
        assertEquals(lessThan.getDescription(), "User hand size is smaller than 2");
    }

    @Test
    public void TestLastTurnDamage() {
        PlayerStats playerStatsMock = Mockito.mock(PlayerStats.class);
        PlayerStatEntry  playerStatEntryMock = Mockito.mock(PlayerStatEntry.class);
        Mockito.when(playerMock.getPlayerStats()).thenReturn(playerStatsMock);
        Mockito.when(playerStatsMock.getDamageDealt()).thenReturn(playerStatEntryMock);

        LastTurnDamageLessThan lessThan= new LastTurnDamageLessThan(2);

        Mockito.when(playerStatEntryMock.lastTurn()).thenReturn(0);
        assertTrue(lessThan.satisfies(playerMock));

        Mockito.when(playerStatEntryMock.lastTurn()).thenReturn(5);
        assertFalse(lessThan.satisfies(playerMock));

        Mockito.when(playerStatEntryMock.lastTurn()).thenReturn(2);
        assertFalse(lessThan.satisfies(playerMock));

        assertEquals(lessThan.getDescription(), "Last turn user did less than 2 damage");
    }
}
