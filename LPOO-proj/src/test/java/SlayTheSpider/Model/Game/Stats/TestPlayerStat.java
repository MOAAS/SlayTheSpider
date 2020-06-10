package SlayTheSpider.Model.Game.Stats;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestPlayerStat {
    @Test
    public void TestEntry() {
        PlayerStatEntry entry = new PlayerStatEntry();

        assertEquals(entry.game(), 0);
        assertEquals(entry.turn(), 0);
        assertEquals(entry.fight(), 0);

        entry.increment(2);

        assertEquals(entry.game(), 2);
        assertEquals(entry.turn(), 2);
        assertEquals(entry.fight(), 2);

        entry.resetTurn();

        assertEquals(entry.game(), 2);
        assertEquals(entry.turn(), 0);
        assertEquals(entry.fight(), 2);

        entry.increment(1);
        entry.resetFight();

        assertEquals(entry.game(), 3);
        assertEquals(entry.turn(), 0);
        assertEquals(entry.fight(), 0);
    }

    @Test
    public void TestStats() {
        PlayerStats stats = new PlayerStats();


        stats.logAttack(3);
        stats.logHit(2);
        stats.logHeal(1);
        stats.logCardUse();

        assertEquals(stats.getDamageDealt().lastTurn(), 0);
        assertEquals(stats.getDamageDealt().fight(), 3);
        assertEquals(stats.getDamageTaken().fight(), 2);
        assertEquals(stats.getHealTaken().fight(), 1);
        assertEquals(stats.getNumCardsPlayed().fight(), 1);

        stats.resetTurn();

        assertEquals(stats.getDamageDealt().lastTurn(), 3);
        assertEquals(stats.getDamageDealt().fight(), 3);
        assertEquals(stats.getDamageTaken().fight(), 2);
        assertEquals(stats.getHealTaken().fight(), 1);
        assertEquals(stats.getNumCardsPlayed().fight(), 1);

        assertEquals(stats.getDamageDealt().turn(), 0);
        assertEquals(stats.getDamageTaken().turn(), 0);
        assertEquals(stats.getHealTaken().turn(), 0);
        assertEquals(stats.getNumCardsPlayed().turn(), 0);

        stats.resetFight();

        assertEquals(stats.getDamageDealt().lastTurn(), 0);
        assertEquals(stats.getDamageDealt().fight(), 0);
        assertEquals(stats.getDamageTaken().fight(), 0);
        assertEquals(stats.getHealTaken().fight(), 0);
        assertEquals(stats.getNumCardsPlayed().fight(), 0);

    }
}
