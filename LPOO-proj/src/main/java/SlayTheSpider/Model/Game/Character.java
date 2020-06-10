package SlayTheSpider.Model.Game;

import SlayTheSpider.Model.Game.HealthBar.HealthBar;
import SlayTheSpider.Model.Game.StatusEffect.*;
import SlayTheSpider.Model.Game.StatusEffect.CharacterActions.IncomingAttack;
import SlayTheSpider.Model.Game.StatusEffect.CharacterActions.IncomingHeal;
import SlayTheSpider.Model.Game.StatusEffect.CharacterActions.OutgoingAttack;
import SlayTheSpider.Model.Sprites.Sprite;

import java.util.ArrayList;

public abstract class Character<BarType extends HealthBar> {
    protected Sprite sprite;
    protected BarType healthBar;
    protected StatusEffectGroup statusEffects;

    public Character(Sprite sprite, int maxHealth){
        this.sprite = sprite;
        this.healthBar = createHealthBar(maxHealth);
        this.statusEffects = new StatusEffectList();
    }

    public Sprite getSprite() {
        return sprite;
    }

    public BarType getHealthBar() {
        return healthBar;
    }

    public void attack(Character character, int damage) {
        OutgoingAttack outgoingAttack = new OutgoingAttack(damage);
        outgoingAttack.applyEffects(new ArrayList<>(statusEffects.getEffects()));
        character.takeHit(outgoingAttack.getDamage());
        this.notifyDamageDealt(outgoingAttack.getDamage());
    }

    public void takeHit(int damage) {
        IncomingAttack incomingAttack = new IncomingAttack(damage);
        incomingAttack.applyEffects(new ArrayList<>(statusEffects.getEffects()));
        this.healthBar.takeHit(incomingAttack.getDamage());
        this.notifyDamageTaken(incomingAttack.getDamage());
    }

    public void heal(int amount) {
        IncomingHeal incomingHeal = new IncomingHeal(amount);
        incomingHeal.applyEffects(new ArrayList<>(statusEffects.getEffects()));
        this.healthBar.increaseHealth(incomingHeal.getHeal());
        this.notifyHeal(incomingHeal.getHeal());
    }


    public boolean isDead() {
        return healthBar.getHealth() <= 0;
    }

    public void applyStatus(StatusEffect type, int duration) {
        this.statusEffects.add(type, duration);
    }

    public StatusEffectGroup getStatusEffects() {
        return statusEffects;
    }

    protected abstract BarType createHealthBar(int maxHealth);

    public abstract void endTurn();
    protected abstract void notifyHeal(int heal);
    protected abstract void notifyDamageTaken(int damage);
    protected abstract void notifyDamageDealt(int damage);
}
