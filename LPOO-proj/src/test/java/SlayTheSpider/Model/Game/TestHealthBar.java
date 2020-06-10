package SlayTheSpider.Model.Game;

import SlayTheSpider.Model.Game.HealthBar.EnemyHealthBar;
import SlayTheSpider.Model.Game.HealthBar.HealthBar;
import SlayTheSpider.Model.Game.HealthBar.PlayerHealthBar;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestHealthBar {

    @Test
    public void testHealthBarFuncs() {
        HealthBar healthBar = new EnemyHealthBar(20);

        assertEquals(healthBar.getRemainingHealthPerc(), 1, 0.001);

        healthBar.reduceHealth(10);
        assertEquals(healthBar.getHealth(), 10);
        assertEquals(healthBar.getMaxHealth(), 20);
        assertEquals(healthBar.getRemainingHealthPerc(), 0.5, 0.001);

        healthBar.reduceHealth(15);
        assertEquals(healthBar.getHealth(), 0);
        assertEquals(healthBar.getMaxHealth(), 20);
        assertEquals(healthBar.getRemainingHealthPerc(), 0, 0.001);

        healthBar.increaseHealth(10);
        assertEquals(healthBar.getHealth(), 10);
        assertEquals(healthBar.getMaxHealth(), 20);
        assertEquals(healthBar.getRemainingHealthPerc(), 0.5, 0.001);

        healthBar.increaseHealth(15);
        assertEquals(healthBar.getHealth(), 20);
        assertEquals(healthBar.getMaxHealth(), 20);
        assertEquals(healthBar.getRemainingHealthPerc(), 1, 0.001);

        healthBar.reduceHealth(200);
        healthBar.refillHealth();
        assertEquals(healthBar.getHealth(), 20);
        assertEquals(healthBar.getMaxHealth(), 20);
        assertEquals(healthBar.getRemainingHealthPerc(), 1, 0.001);

        healthBar.increaseMaxHealth(80);
        assertEquals(healthBar.getHealth(), 20);
        assertEquals(healthBar.getMaxHealth(), 100);
        assertEquals(healthBar.getRemainingHealthPerc(), 0.2, 0.001);

    }

    @Test
    public void TestHit() {
        HealthBar healthBar = new EnemyHealthBar(20);
        healthBar.takeHit(10);

        assertEquals(healthBar.getHealth(), 10);
        assertEquals(healthBar.getMaxHealth(), 20);

        healthBar.takeHit(15);
        assertEquals(healthBar.getHealth(), 0);
        assertEquals(healthBar.getMaxHealth(), 20);
    }

    @Test
    public void TestMana() {
        PlayerHealthBar healthBar = new PlayerHealthBar(20);

        int initialMana = healthBar.getCurrentMana();

        assertEquals(healthBar.getCurrentMana(), healthBar.getMaxMana());
        healthBar.reduceMana(2000);
        assertEquals(healthBar.getCurrentMana(), initialMana - 2000);
        healthBar.refillMana();
        assertEquals(healthBar.getCurrentMana(), initialMana);

        healthBar.restoreMana(2);
        assertEquals(healthBar.getCurrentMana(), initialMana);

        healthBar.reduceMana(2);
        assertEquals(healthBar.getCurrentMana(), initialMana - 2);

        healthBar.restoreMana(1);
        assertEquals(healthBar.getCurrentMana(), initialMana - 1);

        healthBar.increaseMaxMana(2);
        assertEquals(healthBar.getCurrentMana(), initialMana - 1);

        healthBar.refillMana();
        assertEquals(healthBar.getCurrentMana(), initialMana + 2);
        assertEquals(healthBar.getCurrentMana(), healthBar.getMaxMana());

        healthBar.increaseMaxMana(1000);
        healthBar.refillMana();
        assertEquals(healthBar.getCurrentMana(), healthBar.getManaCap());

        int manaCap = healthBar.getManaCap();
        healthBar.increaseManaCap(4);
        assertEquals(healthBar.getManaCap(), manaCap + 4);
    }

    @Test
    public void TestShield() {
        PlayerHealthBar healthBar = new PlayerHealthBar(20);

        assertEquals(healthBar.getShield(), 0);

        healthBar.addShield(10);
        assertEquals(healthBar.getShield(), 10);

        healthBar.takeHit(15);
        assertEquals(healthBar.getMaxHealth(), 20);
        assertEquals(healthBar.getHealth(), 15);
        assertEquals(healthBar.getShield(), 0);

        healthBar.addShield(10);
        healthBar.takeHit(5);
        assertEquals(healthBar.getMaxHealth(), 20);
        assertEquals(healthBar.getHealth(), 15);
        assertEquals(healthBar.getShield(), 5);

        healthBar.addShield(12);
        assertEquals(healthBar.getShield(), 17);

        healthBar.takeHit(2000);
        assertEquals(healthBar.getShield(), 0);
        assertEquals(healthBar.getHealth(), 0);
        assertEquals(healthBar.getMaxHealth(), 20);

        healthBar.addShield(12);
        healthBar.clearShield();
        assertEquals(healthBar.getShield(), 0);
    }

    @Test
    public void TestReset() {
        PlayerHealthBar healthBar = new PlayerHealthBar(20);

        assertEquals(healthBar.getCurrentMana(), 2);
        assertEquals(healthBar.getMaxMana(), 2);
        assertEquals(healthBar.getShield(), 0);

        healthBar.reduceMana(1);
        healthBar.addShield(91);
        healthBar.resetTurn();

        assertEquals(healthBar.getCurrentMana(), 3);
        assertEquals(healthBar.getMaxMana(), 3);
        assertEquals(healthBar.getShield(), 91);

        healthBar.resetFight();

        assertEquals(healthBar.getCurrentMana(), 2);
        assertEquals(healthBar.getMaxMana(), 2);
        assertEquals(healthBar.getShield(), 0);
    }

    @Test
    public void TestLastHit() {
        EnemyHealthBar enemyHealthBar = new EnemyHealthBar(10);
        PlayerHealthBar playerHealthBar = new PlayerHealthBar(10);

        enemyHealthBar.takeHit(10);
        playerHealthBar.takeHit(10);
        assertEquals(enemyHealthBar.getLastHit(), 10);
        assertEquals(playerHealthBar.getLastHit(), 10);

        enemyHealthBar.increaseHealth(10);
        playerHealthBar.increaseHealth(10);

        enemyHealthBar.takeHit(15);
        playerHealthBar.takeHit(15);
        assertEquals(enemyHealthBar.getLastHit(), 10);
        assertEquals(playerHealthBar.getLastHit(), 10);


        playerHealthBar.increaseHealth(15);
        playerHealthBar.addShield(5);
        playerHealthBar.takeHit(10);
        assertEquals(playerHealthBar.getLastHit(), 10);
    }
}