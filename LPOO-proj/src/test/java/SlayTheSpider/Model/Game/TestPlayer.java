package SlayTheSpider.Model.Game;

import SlayTheSpider.Model.Game.HealthBar.PlayerHealthBar;
import SlayTheSpider.Model.Game.Stats.PlayerStats;
import SlayTheSpider.Model.Game.StatusEffect.StatusEffectList;
import SlayTheSpider.Model.Position.Position;
import SlayTheSpider.Model.Sprites.Sprite;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;

public class TestPlayer {
    private Sprite spriteMock = Mockito.mock(Sprite.class);
    private Deck deckMock = Mockito.mock(Deck.class);
    private Card cardMock = Mockito.mock(Card.class);
    private Fight fightMock = Mockito.mock(Fight.class);
    private Character targetMock = Mockito.mock(Character.class);
    private StatusEffectList effectListMock = Mockito.mock(StatusEffectList.class);
    private PlayerStats playerStatMock = Mockito.mock(PlayerStats.class);
    private TargetList targetListMock = Mockito.mock(TargetList.class);

    @Before
    public void setup() {
        spriteMock = Mockito.mock(Sprite.class);
        deckMock = Mockito.mock(Deck.class);
        cardMock = Mockito.mock(Card.class);
        fightMock = Mockito.mock(Fight.class);
        targetMock = Mockito.mock(Character.class);
        effectListMock = Mockito.mock(StatusEffectList.class);
        playerStatMock = Mockito.mock(PlayerStats.class);
        targetListMock = Mockito.mock(TargetList.class);

        Mockito.when(deckMock.getSelectedCard()).thenReturn(cardMock);

        Mockito.when(targetListMock.getCharacters()).thenReturn(new ArrayList<Character>());
        Mockito.when(targetListMock.getTarget()).thenReturn(targetMock);
    }

    @Test
    public void TestConstructors() {
        Player player = new Player(spriteMock, 100);

        assertEquals(player.getSprite(), spriteMock);
        assertNotNull(player.createHealthBar(100));
        assertNotNull(player.getHealthBar());
        assertNotNull(player.getOWPlayer());
    }

    @Test
    public void TestStartFight() {
        Player player = new Player(spriteMock, 100);

        PlayerHealthBar barMock = Mockito.mock(PlayerHealthBar.class);
        player.healthBar = barMock;
        player.targets = targetListMock;
        player.playerStats = playerStatMock;

        player.setDeck(deckMock);

        player.beginFight(fightMock);

        Mockito.verify(deckMock, times(1)).startFight(5);
        Mockito.verify(deckMock, times(1)).startFight(anyInt());
        Mockito.verify(barMock, times(1)).resetFight();
        Mockito.verify(playerStatMock, times(1)).resetFight();

        assertEquals(player.getStatusEffects().size(), 0);
        assertEquals(player.getTargets().getCharacters().size(), 1);

        player.statusEffects = effectListMock;
        player.beginFight(fightMock);
        Mockito.verify(effectListMock, times(1)).clear();
    }

    @Test
    public void TestTargets() {
        Player player = new Player(spriteMock, 100);

        player.targets = targetListMock;

        assertEquals(player.getTargets(), targetListMock);
        assertEquals(player.getTarget(), targetMock);
    }

    @Test
    public void TestUseCard() {
        Player player = new Player(spriteMock, 100);

        PlayerHealthBar barMock = Mockito.mock(PlayerHealthBar.class);
        player.healthBar = barMock;
        player.playerStats = playerStatMock;

        player.setDeck(deckMock);
        player.beginFight(fightMock);


        Mockito.when(cardMock.getCost()).thenReturn(8);

        player.useSelectedCard();
        Mockito.verify(playerStatMock, times(1)).logCardUse();

        Mockito.verify(barMock, times(1)).reduceMana(8);
        Mockito.verify(barMock, times(1)).reduceMana(anyInt());

        Mockito.verify(cardMock, times(1)).use(player);
        Mockito.verify(cardMock, times(1)).use(any());

        Mockito.verify(deckMock, times(1)).discardSelectedCard();

        Mockito.when(cardMock.getCost()).thenReturn(1);
        player.useSelectedCard();

        Mockito.verify(barMock, times(1)).reduceMana(1);
        Mockito.verify(barMock, times(2)).reduceMana(anyInt());

        Mockito.verify(cardMock, times(2)).use(player);
        Mockito.verify(cardMock, times(2)).use(any());

        Mockito.verify(deckMock, times(2)).discardSelectedCard();

        Mockito.when(cardMock.getCost()).thenReturn(8);
        Mockito.when(barMock.getCurrentMana()).thenReturn(10);

        assertTrue(player.canAffordCard());

        Mockito.when(cardMock.getCost()).thenReturn(6);
        Mockito.when(barMock.getCurrentMana()).thenReturn(6);

        assertTrue(player.canAffordCard());

        Mockito.when(cardMock.getCost()).thenReturn(8);
        Mockito.when(barMock.getCurrentMana()).thenReturn(7);

        assertFalse(player.canAffordCard());
    }

    @Test
    public void TestEndTurn() {
        Player player = new Player(spriteMock, 100);
        player.playerStats = playerStatMock;

        PlayerHealthBar barMock = Mockito.mock(PlayerHealthBar.class);
        player.healthBar = barMock;
        player.setDeck(deckMock);
        player.beginFight(fightMock);

        player.endTurn();

        Mockito.verify(deckMock, times(1)).drawCards(1);
        Mockito.verify(deckMock, times(1)).drawCards(anyInt());

        Mockito.verify(barMock, times(1)).resetTurn();
        Mockito.verify(playerStatMock, times(1)).resetTurn();

        StatusEffectList effectList = Mockito.mock(StatusEffectList.class);
        player.statusEffects = effectList;

        player.endTurn();

        Mockito.verify(effectList, times(1)).endTurn();

        player.endTurn();

        Mockito.verify(effectList, times(2)).endTurn();
    }

    @Test
    public void TestMapPos() {
        Player player = new Player(spriteMock, 100);

        Position mapPosition = Mockito.mock(Position.class);

        player.setMapPosition(mapPosition);

        assertEquals(mapPosition, player.getMapPosition());
    }

    @Test
    public void TestStats() {
        Player player = new Player(spriteMock, 100);
        player.playerStats = playerStatMock;

        assertNotNull(player.getPlayerStats());
        assertSame(player.getPlayerStats(), playerStatMock);

    }

    @Test
    public void TestNotify() {
        Player player = new Player(spriteMock, 100);
        player.playerStats = playerStatMock;

        Mockito.verify(playerStatMock, times(0)).logAttack(anyInt());
        Mockito.verify(playerStatMock, times(0)).logHeal(anyInt());
        Mockito.verify(playerStatMock, times(0)).logHit(anyInt());
        Mockito.verify(playerStatMock, times(0)).logCardUse();

        Mockito.verify(playerStatMock, times(0)).logAttack(9);
        Mockito.verify(playerStatMock, times(0)).logHeal(8);
        Mockito.verify(playerStatMock, times(0)).logHit(7);

        player.notifyDamageDealt(9);
        Mockito.verify(playerStatMock, times(1)).logAttack(anyInt());
        Mockito.verify(playerStatMock, times(0)).logHeal(anyInt());
        Mockito.verify(playerStatMock, times(0)).logHit(anyInt());
        Mockito.verify(playerStatMock, times(0)).logCardUse();
        Mockito.verify(playerStatMock, times(1)).logAttack(9);
        Mockito.verify(playerStatMock, times(0)).logHeal(8);
        Mockito.verify(playerStatMock, times(0)).logHit(7);

        player.notifyHeal(8);
        Mockito.verify(playerStatMock, times(1)).logAttack(anyInt());
        Mockito.verify(playerStatMock, times(1)).logHeal(anyInt());
        Mockito.verify(playerStatMock, times(0)).logHit(anyInt());
        Mockito.verify(playerStatMock, times(0)).logCardUse();
        Mockito.verify(playerStatMock, times(1)).logAttack(9);
        Mockito.verify(playerStatMock, times(1)).logHeal(8);
        Mockito.verify(playerStatMock, times(0)).logHit(7);

        player.notifyDamageTaken(7);
        Mockito.verify(playerStatMock, times(1)).logAttack(anyInt());
        Mockito.verify(playerStatMock, times(1)).logHeal(anyInt());
        Mockito.verify(playerStatMock, times(1)).logHit(anyInt());
        Mockito.verify(playerStatMock, times(0)).logCardUse();
        Mockito.verify(playerStatMock, times(1)).logAttack(9);
        Mockito.verify(playerStatMock, times(1)).logHeal(8);
        Mockito.verify(playerStatMock, times(1)).logHit(7);
    }

    @Test
    public void TestClear() {
        Player player = new Player(spriteMock, 100);

        player.healthBar = Mockito.mock(PlayerHealthBar.class);
        player.targets = targetListMock;
        player.playerStats = playerStatMock;

        player.setDeck(deckMock);

        ArrayList<Enemy> list = new ArrayList<>();
        list.add(Mockito.mock(Enemy.class));
        list.add(Mockito.mock(Enemy.class));
        Mockito.when(fightMock.getEnemies()).thenReturn(list);

        player.beginFight(fightMock);

        assertEquals(player.getTargets().getCharacters().size(), 3);
    }
}
