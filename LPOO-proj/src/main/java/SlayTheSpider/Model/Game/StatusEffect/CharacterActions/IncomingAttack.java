package SlayTheSpider.Model.Game.StatusEffect.CharacterActions;

public class IncomingAttack extends CharacterAction {
    private int damage;

    public IncomingAttack(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void halveDamage() {
        this.damage = damage / 2;
    }

    public void nullifyDamage() {
        this.damage = 0;
    }

    @Override
    public void applyChanger(CharacterActionChanger changer) {
        changer.incomingAttack(this);
    }
}
