package SlayTheSpider.Model.Game.HealthBar;

public abstract class HealthBar {
    protected int maxHealth;
    protected int health;
    protected int lastHit;

    public HealthBar(int maxHealth) {
        this.health = maxHealth;
        this.maxHealth = maxHealth;
        this.lastHit = 0;
    }

    public abstract void takeHit(int damage);

    public double getRemainingHealthPerc() {
        return this.health / (double)this.maxHealth;
    }

    public void increaseMaxHealth(int amount) {
        this.maxHealth += amount;
    }

    public void reduceHealth(int amount) {
        this.health = Math.max(0, health - amount);
    }

    public void increaseHealth(int amount) {
        this.health = Math.min(maxHealth, health + amount);
    }

    public void refillHealth() {
        this.health = this.maxHealth;
    }


    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getLastHit() {
        return lastHit;
    }
}
