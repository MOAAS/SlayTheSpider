package SlayTheSpider.Model.Game.HealthBar;

public class EnemyHealthBar extends HealthBar {
    public EnemyHealthBar(int maxHealth) {
        super(maxHealth);
    }

    @Override
    public void takeHit(int damage) {
        this.lastHit = Math.min(this.health, damage);
        this.reduceHealth(damage);
    }
}
