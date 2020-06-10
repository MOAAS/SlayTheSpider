package SlayTheSpider.Model.Game.StatusEffect.CharacterActions;

public class IncomingHeal extends CharacterAction {
    private int heal;

    public IncomingHeal(int heal) {
        this.heal = heal;
    }

    @Override
    public void applyChanger(CharacterActionChanger changer) {
        changer.incomingHeal(this);
    }

    public int getHeal() {
        return heal;
    }

    public void setHeal(int heal) {
        this.heal = heal;
    }

    public void halveHeal() {
        this.heal = heal / 2;
    }

    public void nullifyHeal() {
        this.heal = 0;
    }

}
