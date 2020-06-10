package SlayTheSpider.Model.Game.HealthBar;

public class PlayerHealthBar extends HealthBar {
    private int shield;
    private int mana;
    private int maxMana;
    private int manaCap;
    public static final int SHIELD_CAP = 99;

    public PlayerHealthBar(int maxHealth) {
        super(maxHealth);
        this.shield = 0;
        this.mana = 2;
        this.maxMana = 2;
        this.manaCap = 5;
    }

    public void resetFight() {
        this.shield = 0;
        this.mana = 2;
        this.maxMana = 2;
    }

    public void resetTurn() {
        this.increaseMaxMana(1);
        this.refillMana();
    }

    public void takeHit(int damage) {
        this.lastHit = Math.min(this.shield + this.health, damage);
        this.shield -= damage;
        if (this.shield < 0) {
            this.reduceHealth(Math.abs(this.shield));
            this.shield = 0;
        }
    }

    public int getCurrentMana() {
        return mana;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public int getManaCap() {
        return manaCap;
    }

    public void reduceMana(int cost) {
        this.restoreMana(-cost);
    }

    public void restoreMana(int amount) {
        this.mana = Math.min(this.mana + amount, this.maxMana);
    }

    public void refillMana() {
        this.mana = this.maxMana;
    }

    public void increaseMaxMana(int amount) {
        this.maxMana = Math.min(this.manaCap, this.maxMana + amount);
    }

    public void increaseManaCap(int amount) {
        this.manaCap += amount;
    }


    public void addShield(int amount) {
        this.shield = Math.min(this.SHIELD_CAP, this.shield + amount);
    }

    public int getShield() {
        return shield;
    }

    public void clearShield() {
        this.shield = 0;
    }
}
