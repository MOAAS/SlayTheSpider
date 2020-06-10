package SlayTheSpider.Model.Game.StatusEffect.CharacterActions;

public interface CharacterActionChanger {
    void incomingAttack(IncomingAttack incomingAttack);
    void incomingHeal(IncomingHeal incomingHeal);
    void outgoingAttack(OutgoingAttack outgoingAttack);
}
