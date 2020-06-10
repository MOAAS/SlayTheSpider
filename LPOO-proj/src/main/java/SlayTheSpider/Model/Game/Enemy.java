package SlayTheSpider.Model.Game;

import SlayTheSpider.Model.Game.HealthBar.EnemyHealthBar;
import SlayTheSpider.Model.Position.Position;
import SlayTheSpider.Model.Sprites.Sprite;
import SlayTheSpider.Model.Position.Position2D;

public class Enemy extends Character<EnemyHealthBar> {
    private final Position position;
    private final double damageScale;
    private double damage;

    public Enemy(Enemy enemy) {
        super(enemy.sprite, enemy.healthBar.getMaxHealth());
        this.position = enemy.position;
        this.damage = enemy.damage;
        this.damageScale = enemy.damageScale;
    }

    public Enemy(Position position, Sprite sprite, int maxHealth, int damage, double damageScale){ // code smell :(
        super(sprite, maxHealth);
        this.position = position;
        this.damage = damage;
        this.damageScale = damageScale;
    }

    @Override
    protected EnemyHealthBar createHealthBar(int maxHealth) {
        return new EnemyHealthBar(maxHealth);
    }

    @Override
    public void endTurn() {
        this.damage += damageScale;
        this.statusEffects.endTurn();
    }

    public void attack(Character character) {
        super.attack(character, this.getDamage());
    }

    public int getDamage() {
        return (int)Math.round(damage);
    }

    public double getDamageScale() {
        return damageScale;
    }

    public Position2D getPosition() {
        return position;
    }


    @Override
    protected void notifyHeal(int heal) { }

    @Override
    protected void notifyDamageTaken(int damage) { }

    @Override
    protected void notifyDamageDealt(int damage) { }

}
