package SlayTheSpider.Model.Game.CardEffects;

import SlayTheSpider.Model.Game.*;
import SlayTheSpider.Model.Game.CardEffect.*;
import SlayTheSpider.Model.Game.CardEffect.Conditions.CardEffectConditionList;
import SlayTheSpider.Model.Game.Character;
import SlayTheSpider.Model.Game.HealthBar.EnemyHealthBar;
import SlayTheSpider.Model.Game.HealthBar.PlayerHealthBar;
import SlayTheSpider.Model.Game.Stats.PlayerStatEntry;
import SlayTheSpider.Model.Game.Stats.PlayerStats;
import SlayTheSpider.Model.Game.StatusEffect.StatusEffect;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TestCardEffect {
    Enemy enemyMock = Mockito.mock(Enemy.class);
    Character characterMock = Mockito.mock(Player.class);
    Player playerMock = Mockito.mock(Player.class);
    Deck deckMock = Mockito.mock(Deck.class);
    EnemyHealthBar healthBarMock = Mockito.mock(EnemyHealthBar.class);
    PlayerHealthBar playerHealthBarMock = Mockito.mock(PlayerHealthBar.class);
    PlayerStats statMock = Mockito.mock(PlayerStats.class);
    TargetList targetListMock = Mockito.mock(TargetList.class);
    List<Character> characters  = new ArrayList<>();
    List<Enemy> enemies  = new ArrayList<>();

    @Before
    public void setup() {
        enemyMock = Mockito.mock(Enemy.class);
        playerMock = Mockito.mock(Player.class);
        characterMock = Mockito.mock(Player.class);
        deckMock = Mockito.mock(Deck.class);
        healthBarMock = Mockito.mock(EnemyHealthBar.class);
        playerHealthBarMock = Mockito.mock(PlayerHealthBar.class);
        statMock = Mockito.mock(PlayerStats.class);
        targetListMock = Mockito.mock(TargetList.class);

        characters.clear();
        characters.add(Mockito.mock(Character.class));
        characters.add(Mockito.mock(Character.class));
        characters.add(Mockito.mock(Character.class));
        characters.add(Mockito.mock(Character.class));
        characters.add(playerMock);

        enemies.clear();
        enemies.add(Mockito.mock(Enemy.class));
        enemies.add(Mockito.mock(Enemy.class));
        enemies.add(Mockito.mock(Enemy.class));
        enemies.add(Mockito.mock(Enemy.class));

        when(playerMock.getDeck()).thenReturn(deckMock);
        when(playerMock.getTargets()).thenReturn(targetListMock);
        when(playerMock.getTarget()).thenReturn(characters.get(2));

        when(targetListMock.getCharacters()).thenReturn(characters);
        when(targetListMock.getEnemies()).thenReturn(enemies);
        when(targetListMock.getTarget()).thenReturn(characters.get(2));

        when(enemyMock.getHealthBar()).thenReturn(healthBarMock);
        when(characterMock.getHealthBar()).thenReturn(healthBarMock);
        when(playerMock.getHealthBar()).thenReturn(playerHealthBarMock);

        when(playerMock.getPlayerStats()).thenReturn(statMock);
    }


    @Test
    public void TestAbstracts() {
        CardEffectAll effectAll = Mockito.spy(CardEffectAll.class);
        CardEffectAoE effectAoE = Mockito.spy(CardEffectAoE.class);
        CardEffectSelf effectSelf = Mockito.spy(CardEffectSelf.class);
        CardEffectSingle effectSingle = Mockito.spy(CardEffectSingle.class);

        assertEquals(effectAll.needsTargetSelection(), false);
        assertEquals(effectAoE.needsTargetSelection(), false);
        assertEquals(effectSelf.needsTargetSelection(), false);
        assertEquals(effectSingle.needsTargetSelection(), true);

        CardEffectConditionList condList = Mockito.mock(CardEffectConditionList.class);
        Mockito.when(condList.satisfies(any(), any())).thenReturn(true);

        effectAll.setConditions(condList);
        effectAoE.setConditions(condList);
        effectSelf.setConditions(condList);
        effectSingle.setConditions(condList);
        assertSame(effectAll.getConditions(), condList);
        assertSame(effectAoE.getConditions(), condList);
        assertSame(effectSelf.getConditions(), condList);
        assertSame(effectSingle.getConditions(), condList);

        effectAll.use(playerMock);
        effectAoE.use(playerMock);
        effectSelf.use(playerMock);
        effectSingle.use(playerMock);

        Mockito.verify(effectAll, times(5)).applyEffect(any(), eq(playerMock));
        Mockito.verify(effectAll, times(1)).applyEffect(playerMock, playerMock);
        Mockito.verify(effectAll, times(1)).applyEffect(characters.get(0), playerMock);
        Mockito.verify(effectAll, times(1)).applyEffect(characters.get(1), playerMock);
        Mockito.verify(effectAll, times(1)).applyEffect(characters.get(2), playerMock);
        Mockito.verify(effectAll, times(1)).applyEffect(characters.get(3), playerMock);

        Mockito.verify(effectAoE, times(4)).applyEffect(any(), eq(playerMock));
        Mockito.verify(effectAoE, times(0)).applyEffect(playerMock, playerMock);
        Mockito.verify(effectAoE, times(1)).applyEffect(enemies.get(0), playerMock);
        Mockito.verify(effectAoE, times(1)).applyEffect(enemies.get(1), playerMock);
        Mockito.verify(effectAoE, times(1)).applyEffect(enemies.get(2), playerMock);
        Mockito.verify(effectAoE, times(1)).applyEffect(enemies.get(3), playerMock);

        Mockito.verify(effectSelf, times(1)).applyEffect(any());
        Mockito.verify(effectSelf, times(1)).applyEffect(playerMock);

        Mockito.verify(effectSingle, times(0)).applyEffect(characters.get(0), playerMock);
        Mockito.verify(effectSingle, times(0)).applyEffect(characters.get(1), playerMock);
        Mockito.verify(effectSingle, times(1)).applyEffect(characters.get(2), playerMock);
        Mockito.verify(effectSingle, times(0)).applyEffect(characters.get(3), playerMock);
        Mockito.verify(effectSingle, times(0)).applyEffect(characters.get(4), playerMock);
        Mockito.verify(effectSingle, times(1)).applyEffect(any(), eq(playerMock));
        Mockito.verify(effectSingle, times(1)).applyEffect(targetListMock.getTarget(), playerMock);
    }

    @Test
    public void TestDamage() {
        CardEffectTargeted damageEffect10 = new DamageEffect(10);
        CardEffectTargeted damageEffect20 = new DamageEffect(20);

        assertEquals(damageEffect10.getDescription(), "Deals 10 damage.");
        assertEquals(damageEffect20.getDescription(), "Deals 20 damage.");

        damageEffect10.applyEffect(characterMock, playerMock);

        Mockito.verify(playerMock, times(1)).attack(any(), anyInt());
        Mockito.verify(playerMock, times(1)).attack(characterMock, 10);
        Mockito.verify(playerMock, times(0)).attack(eq(playerMock), anyInt());
        Mockito.verify(characterMock, times(0)).attack(any(), anyInt());

        damageEffect20.applyEffect(playerMock, playerMock);

        Mockito.verify(playerMock, times(2)).attack(any(), anyInt());
        Mockito.verify(playerMock, times(1)).attack(eq(characterMock), anyInt());
        Mockito.verify(playerMock, times(1)).attack(eq(playerMock), anyInt());
        Mockito.verify(playerMock, times(1)).attack(playerMock, 20);
        Mockito.verify(characterMock, times(0)).attack(any(), anyInt());
    }

    @Test
    public void TestSelfDmg() {
        CardEffectSelf selfDamageEffect5 = new SelfDamageEffect(5);
        CardEffectSelf selfDamageEffect8 = new SelfDamageEffect(8);

        assertEquals(selfDamageEffect5.getDescription(), "Deals 5 damage to self.");
        assertEquals(selfDamageEffect8.getDescription(), "Deals 8 damage to self.");


        selfDamageEffect5.applyEffect(playerMock);

        Mockito.verify(playerMock, times(1)).attack(any(), anyInt());
        Mockito.verify(playerMock, times(1)).attack(eq(playerMock), anyInt());
        Mockito.verify(playerMock, times(1)).attack(playerMock, 5);
        Mockito.verify(playerMock, times(1)).attack(any(), eq(5));

        selfDamageEffect8.applyEffect(playerMock);

        Mockito.verify(playerMock, times(2)).attack(any(), anyInt());
        Mockito.verify(playerMock, times(2)).attack(eq(playerMock), anyInt());
        Mockito.verify(playerMock, times(1)).attack(playerMock, 8);
        Mockito.verify(playerMock, times(1)).attack(any(), eq(8));

    }

    @Test
    public void TestAllEnemiesDMG() {
        CardEffectAoE dmgAllEnemies20 = new AoEDamageEffect(20);
        CardEffectAoE dmgAllEnemies200 = new AoEDamageEffect(200);

        assertEquals(dmgAllEnemies20.getDescription(), "Deals 20 damage to all enemies.");
        assertEquals(dmgAllEnemies200.getDescription(), "Deals 200 damage to all enemies.");

        dmgAllEnemies20.applyEffect(characterMock, playerMock);

        Mockito.verify(playerMock, times(1)).attack(any(), anyInt());
        Mockito.verify(playerMock, times(1)).attack(characterMock, 20);
        Mockito.verify(playerMock, times(0)).attack(eq(playerMock), anyInt());
        Mockito.verify(characterMock, times(0)).attack(any(), anyInt());

        dmgAllEnemies200.applyEffect(playerMock, playerMock);

        Mockito.verify(playerMock, times(2)).attack(any(), anyInt());
        Mockito.verify(playerMock, times(1)).attack(eq(characterMock), anyInt());
        Mockito.verify(playerMock, times(1)).attack(eq(playerMock), anyInt());
        Mockito.verify(playerMock, times(1)).attack(playerMock, 200);
        Mockito.verify(characterMock, times(0)).attack(any(), anyInt());
    }

    @Test
    public void TestManaRefill() {
        CardEffectSelf manaRefill = new ManaRefillEffect();
        assertEquals(manaRefill.getDescription(), "Refills mana.");

        PlayerHealthBar healthBarMock = Mockito.mock(PlayerHealthBar.class);
        when(playerMock.getHealthBar()).thenReturn(healthBarMock);

        manaRefill.applyEffect(playerMock);

        Mockito.verify(healthBarMock, times(1)).refillMana();
    }

    @Test
    public void TestManaRestore() {
        CardEffectSelf manaRestore2 = new ManaRestoreEffect(2);
        CardEffectSelf manaRestore6 = new ManaRestoreEffect(6);
        assertEquals(manaRestore2.getDescription(), "Restores 2 mana.");
        assertEquals(manaRestore6.getDescription(), "Restores 6 mana.");

        PlayerHealthBar healthBarMock = Mockito.mock(PlayerHealthBar.class);
        when(playerMock.getHealthBar()).thenReturn(healthBarMock);

        manaRestore2.applyEffect(playerMock);

        Mockito.verify(healthBarMock, times(1)).restoreMana(anyInt());
        Mockito.verify(healthBarMock, times(1)).restoreMana(2);

        manaRestore6.applyEffect(playerMock);

        Mockito.verify(healthBarMock, times(2)).restoreMana(anyInt());
        Mockito.verify(healthBarMock, times(1)).restoreMana(2);
        Mockito.verify(healthBarMock, times(1)).restoreMana(6);
    }

    @Test
    public void TestSelfHeal() {
        CardEffectSelf healRestore2 = new SelfHealEffect(2);
        CardEffectSelf healRestore6 = new SelfHealEffect(6);
        assertEquals(healRestore2.getDescription(), "Restores 2 health to self.");
        assertEquals(healRestore6.getDescription(), "Restores 6 health to self.");

        healRestore2.applyEffect(playerMock);

        Mockito.verify(playerMock, times(1)).heal(anyInt());
        Mockito.verify(playerMock, times(1)).heal(2);

        healRestore6.applyEffect(playerMock);

        Mockito.verify(playerMock, times(2)).heal(anyInt());
        Mockito.verify(playerMock, times(1)).heal(2);
        Mockito.verify(playerMock, times(1)).heal(6);
    }

    @Test
    public void TestSelfShield() {
        CardEffectSelf shieldRestore5 = new SelfShieldEffect(5);
        CardEffectSelf shieldRestore10 = new SelfShieldEffect(10);
        assertEquals(shieldRestore5.getDescription(), "Restores 5 shield to self.");
        assertEquals(shieldRestore10.getDescription(), "Restores 10 shield to self.");

        shieldRestore5.applyEffect(playerMock);

        Mockito.verify(playerHealthBarMock, times(1)).addShield(anyInt());
        Mockito.verify(playerHealthBarMock, times(1)).addShield(5);

        shieldRestore10.applyEffect(playerMock);

        Mockito.verify(playerHealthBarMock, times(2)).addShield(anyInt());
        Mockito.verify(playerHealthBarMock, times(1)).addShield(10);
    }

    @Test
    public void TestShieldMulti() {
        CardEffectSelf shieldMulti2 = new ShieldMultiEffect(2);
        CardEffectSelf shieldMulti3 = new ShieldMultiEffect(3);
        CardEffectSelf shieldMulti5 = new ShieldMultiEffect(5);
        assertEquals(shieldMulti2.getDescription(), "Doubles shield.");
        assertEquals(shieldMulti3.getDescription(), "Triples shield.");
        assertEquals(shieldMulti5.getDescription(), "Multiplies shield by 5.");

        Mockito.when(playerHealthBarMock.getShield()).thenReturn(10);

        shieldMulti2.applyEffect(playerMock);
        Mockito.verify(playerHealthBarMock, times(1)).addShield(anyInt());
        Mockito.verify(playerHealthBarMock, times(1)).addShield(10);

        shieldMulti3.applyEffect(playerMock);
        Mockito.verify(playerHealthBarMock, times(2)).addShield(anyInt());
        Mockito.verify(playerHealthBarMock, times(1)).addShield(20);

        shieldMulti5.applyEffect(playerMock);
        Mockito.verify(playerHealthBarMock, times(3)).addShield(anyInt());
        Mockito.verify(playerHealthBarMock, times(1)).addShield(40);

    }

    @Test
    public void TestSelfStatus() {
        StatusEffect effectMock1 = Mockito.mock(StatusEffect.class);
        StatusEffect effectMock2 = Mockito.mock(StatusEffect.class);
        when(effectMock1.getName()).thenReturn("STOONED");
        when(effectMock2.getName()).thenReturn("Weeeek");

        CardEffectSelf effectSelf1 = new SelfStatusEffect(effectMock1, 5);
        CardEffectSelf effectSelf2 = new SelfStatusEffect(effectMock2, 1);

        assertEquals(effectSelf1.getDescription(), "Applies STOONED to self for 5 rounds.");
        assertEquals(effectSelf2.getDescription(), "Applies Weeeek to self for 1 round.");

        effectSelf1.applyEffect(playerMock);

        Mockito.verify(playerMock, times(1)).applyStatus(any(), anyInt());
        Mockito.verify(playerMock, times(1)).applyStatus(effectMock1, 5);

        effectSelf2.applyEffect(playerMock);

        Mockito.verify(playerMock, times(2)).applyStatus(any(), anyInt());
        Mockito.verify(playerMock, times(1)).applyStatus(effectMock1, 5);
        Mockito.verify(playerMock, times(1)).applyStatus(effectMock2, 1);
    }

    @Test
    public void TestTargetStatus() {
        StatusEffect effectMock1 = Mockito.mock(StatusEffect.class);
        StatusEffect effectMock2 = Mockito.mock(StatusEffect.class);
        when(effectMock1.getName()).thenReturn("STOONED");
        when(effectMock2.getName()).thenReturn("Weeeek");

        CardEffectTargeted effectTargeted1 = new TargetedStatusEffect(effectMock1, 1);
        CardEffectTargeted effectTargeted2 = new TargetedStatusEffect(effectMock2, 8);

        assertEquals(effectTargeted1.getDescription(), "Applies STOONED for 1 round.");
        assertEquals(effectTargeted2.getDescription(), "Applies Weeeek for 8 rounds.");

        effectTargeted1.applyEffect(characterMock, playerMock);

        Mockito.verify(characterMock, times(1)).applyStatus(any(), anyInt());
        Mockito.verify(characterMock, times(1)).applyStatus(effectMock1, 1);

        effectTargeted2.applyEffect(characterMock, playerMock);

        Mockito.verify(characterMock, times(2)).applyStatus(any(), anyInt());
        Mockito.verify(characterMock, times(1)).applyStatus(effectMock1, 1);
        Mockito.verify(characterMock, times(1)).applyStatus(effectMock2, 8);
    }

    @Test
    public void TestDrawCard() {
        CardEffectSelf drawCardEffect1 = new SelfDrawCardEffect(1);
        CardEffectSelf drawCardEffect6 = new SelfDrawCardEffect(6);

        assertEquals(drawCardEffect1.getDescription(),  "Draws 1 card.");
        assertEquals(drawCardEffect6.getDescription(),  "Draws 6 cards.");

        drawCardEffect1.applyEffect(playerMock);
        Mockito.verify(deckMock, times(1)).drawCards(1);
        Mockito.verify(deckMock, times(1)).drawCards(anyInt());


        drawCardEffect6.applyEffect(playerMock);
        Mockito.verify(deckMock, times(1)).drawCards(6);
        Mockito.verify(deckMock, times(2)).drawCards(anyInt());
    }

    @Test
    public void TestDiscardCard() {
        CardEffectSelf discardCardEffect1 = new SelfDiscardCardEffect(1);
        CardEffectSelf discardCardEffect2 = new SelfDiscardCardEffect(6);

        assertEquals(discardCardEffect1.getDescription(),  "Discard 1 card.");
        assertEquals(discardCardEffect2.getDescription(),  "Discard 6 cards.");

        discardCardEffect1.applyEffect(playerMock);
        Mockito.verify(deckMock, times(1)).discardRandomCards(1);
        Mockito.verify(deckMock, times(1)).discardRandomCards(anyInt());


        discardCardEffect2.applyEffect(playerMock);
        Mockito.verify(deckMock, times(1)).discardRandomCards(6);
        Mockito.verify(deckMock, times(2)).discardRandomCards(anyInt());
    }

    @Test
    public void TestAddCards() {
        List<Card> hand = new ArrayList<>();
        Mockito.when(deckMock.getHand()).thenReturn(hand);

        List<Card> cards = new ArrayList<>();

        cards.add(Mockito.mock(Card.class));
        cards.add(Mockito.mock(Card.class));

        CardEffectSelf addCards1 = new AddCardsEffect(cards, 1);
        CardEffectSelf addCards2 = new AddCardsEffect(cards, 2);

        assertEquals(addCards1.getDescription(), "Adds 1 random card to your hand.");
        assertEquals(addCards2.getDescription(), "Adds 2 random cards to your hand.");

        addCards1.applyEffect(playerMock);
        assertEquals(hand.size(), 1);

        addCards2.applyEffect(playerMock);
        assertEquals(hand.size(), 3);
    }

    @Test
    public void TestShuffleFight() {
        Card cardMock = Mockito.mock(Card.class);
        Mockito.when(cardMock.getName()).thenReturn("COOLIO");

        CardEffectSelf shuffle1 = new ShuffleCardFightEffect(cardMock);
        assertEquals(shuffle1.getDescription(), "Shuffles a COOLIO to your discard pile.");

        shuffle1.applyEffect(playerMock);
        Mockito.verify(playerMock, times(1)).getDeck();
        Mockito.verify(deckMock, times(1)).getDiscardedCards();
    }

    @Test
    public void TestShufflePerma() {
        Card cardMock = Mockito.mock(Card.class);
        Mockito.when(cardMock.getName()).thenReturn("COOLIO");

        CardEffectSelf shuffle1 = new ShuffleCardPermaEffect(cardMock);
        assertEquals(shuffle1.getDescription(), "Shuffles a COOLIO into your deck.");

        shuffle1.applyEffect(playerMock);
        Mockito.verify(playerMock, times(2)).getDeck();
        Mockito.verify(deckMock, times(1)).getDiscardedCards();
        Mockito.verify(deckMock, times(1)).addCard(cardMock);
    }

    @Test
    public void TestAoELifeDrain() {
        CardEffectAoE lifeDrain1 = new AoELifeDrainEffect(1, 1);
        CardEffectAoE lifeDrain2 = new AoELifeDrainEffect(6, 2);

        assertEquals(lifeDrain1.getDescription(),  "Deals 1 to all enemies. Heals for 1 for each health point dealt.");
        assertEquals(lifeDrain2.getDescription(),  "Deals 6 to all enemies. Heals for 2 for each health point dealt.");

        Mockito.when(healthBarMock.getLastHit()).thenReturn(5);

        lifeDrain1.applyEffect(enemyMock, playerMock);
        Mockito.verify(playerMock, times(1)).attack(enemyMock, 1);
        Mockito.verify(playerMock, times(1)).heal(5);

        lifeDrain2.applyEffect(enemyMock, playerMock);
        Mockito.verify(playerMock, times(1)).attack(enemyMock, 6);
        Mockito.verify(playerMock, times(1)).heal(10);
    }

    @Test
    public void TestAoeShieldToDmg() {
        CardEffectAoE shieldToDmg1 = new AoEShieldToDamageEffect(1);
        CardEffectAoE shieldToDmg2 = new AoEShieldToDamageEffect(2);

        assertEquals(shieldToDmg1.getDescription(),  "Deals 1 damage per shield point as AoE damage.");
        assertEquals(shieldToDmg2.getDescription(),  "Deals 2 damage per shield point as AoE damage.");

        Mockito.when(playerHealthBarMock.getShield()).thenReturn(12);

        shieldToDmg1.applyEffect(characterMock, playerMock);
        Mockito.verify(playerMock, times(1)).attack(characterMock, 12);

        shieldToDmg2.applyEffect(characterMock, playerMock);
        Mockito.verify(playerMock, times(1)).attack(characterMock, 24);
    }

    @Test
    public void TestHealToDmg() {
        PlayerStatEntry entryMock = Mockito.mock(PlayerStatEntry.class);
        Mockito.when(statMock.getHealTaken()).thenReturn(entryMock);
        Mockito.when(entryMock.fight()).thenReturn(12);

        CardEffectSelf healToDmg1 = new FightHealToDamageEffect(1);
        CardEffectSelf healToDmg2 = new FightHealToDamageEffect(2);

        assertEquals(healToDmg1.getDescription(),  "Deals 1 damage per health recovered during this fight.");
        assertEquals(healToDmg2.getDescription(),  "Deals 2 damage per health recovered during this fight.");

        healToDmg1.applyEffect(playerMock);
        Mockito.verify(playerMock, times(1)).heal(12);

        healToDmg2.applyEffect(playerMock);
        Mockito.verify(playerMock, times(1)).heal(24);
    }

    @Test
    public void TestNull() {
        NullCardEffect nullEff = new NullCardEffect();

        assertEquals(nullEff.getDescription(), "Error: Null Card Effect.");
        assertFalse(nullEff.needsTargetSelection());

        nullEff.use(null);
        nullEff.setConditions(null);
        assertNotNull(nullEff.getConditions());
    }

    @Test
    public void TestShieldClear() {
        SelfShielfClearEffect effect = new SelfShielfClearEffect();

        assertEquals(effect.getDescription(),  "Clears shield.");

        effect.applyEffect(playerMock);

        Mockito.verify(playerHealthBarMock, times(1)).clearShield();
    }

    @Test
    public void TestCardsToDmg() {
        PlayerStatEntry entryMock = Mockito.mock(PlayerStatEntry.class);
        Mockito.when(statMock.getNumCardsPlayed()).thenReturn(entryMock);
        Mockito.when(entryMock.turn()).thenReturn(6);

        CardEffectTargeted cardsToDmg1 = new TurnCardsToDamageEffect(3);
        CardEffectTargeted cardsToDmg2 = new TurnCardsToDamageEffect(5);

        assertEquals(cardsToDmg1.getDescription(),  "Deals 3 damage for every card played this turn.");
        assertEquals(cardsToDmg2.getDescription(),  "Deals 5 damage for every card played this turn.");

        cardsToDmg1.applyEffect(characterMock, playerMock);
        Mockito.verify(playerMock, times(1)).attack(characterMock,18);

        cardsToDmg2.applyEffect(characterMock, playerMock);
        Mockito.verify(playerMock, times(1)).attack(characterMock, 30);
    }

    @Test
    public void TestCardsToHeal() {
        PlayerStatEntry entryMock = Mockito.mock(PlayerStatEntry.class);
        Mockito.when(statMock.getNumCardsPlayed()).thenReturn(entryMock);
        Mockito.when(entryMock.turn()).thenReturn(6);

        CardEffectSelf cardsToHeal1 = new TurnCardsToHealthEffect(3);
        CardEffectSelf cardsToHeal2 = new TurnCardsToHealthEffect(5);

        assertEquals(cardsToHeal1.getDescription(),  "Heals 3 for every card played this turn.");
        assertEquals(cardsToHeal2.getDescription(),  "Heals 5 for every card played this turn.");

        cardsToHeal1.applyEffect(playerMock);
        Mockito.verify(playerMock, times(1)).heal(18);

        cardsToHeal2.applyEffect(playerMock);
        Mockito.verify(playerMock, times(1)).heal( 30);
    }

    @Test
    public void TestCardsToShield() {
        PlayerStatEntry entryMock = Mockito.mock(PlayerStatEntry.class);
        Mockito.when(statMock.getNumCardsPlayed()).thenReturn(entryMock);
        Mockito.when(entryMock.turn()).thenReturn(6);

        CardEffectSelf cardsToShield1 = new TurnCardsToShieldEffect(3);
        CardEffectSelf cardsToShield2 = new TurnCardsToShieldEffect(5);

        assertEquals(cardsToShield1.getDescription(),  "Shields 3 for every card played this turn.");
        assertEquals(cardsToShield2.getDescription(),  "Shields 5 for every card played this turn.");

        cardsToShield1.applyEffect(playerMock);
        Mockito.verify(playerHealthBarMock, times(1)).addShield(18);

        cardsToShield2.applyEffect(playerMock);
        Mockito.verify(playerHealthBarMock, times(1)).addShield( 30);
    }


}
