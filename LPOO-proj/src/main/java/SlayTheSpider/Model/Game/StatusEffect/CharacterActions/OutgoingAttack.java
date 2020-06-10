package SlayTheSpider.Model.Game.StatusEffect.CharacterActions;

public class OutgoingAttack extends CharacterAction {

    private int damage;

    public OutgoingAttack(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void doubleDamage() {
        this.damage = this.damage * 2;
    }

    public void halveDamage() {
        this.damage = damage / 2;
    }

    public void nullifyDamage() {
        this.damage = 0;
    }

    @Override
    public void applyChanger(CharacterActionChanger changer) {
        changer.outgoingAttack(this);
    }


}
