package SlayTheSpider.Model.Game.Stats;


public class PlayerStats {
    private PlayerStatEntry numCardsPlayed = new PlayerStatEntry();
    private PlayerStatEntry damageDealt = new PlayerStatEntry();
    private PlayerStatEntry damageTaken = new PlayerStatEntry();
    private PlayerStatEntry healTaken = new PlayerStatEntry();

    public void resetTurn() {
        numCardsPlayed.resetTurn();
        damageDealt.resetTurn();
        damageTaken.resetTurn();
        healTaken.resetTurn();
    }

    public void resetFight() {
        numCardsPlayed.resetFight();
        damageDealt.resetFight();
        damageTaken.resetFight();
        healTaken.resetFight();
    }

    public void logAttack(int damageDealt) {
        this.damageDealt.increment(damageDealt);
    }


    public void logHit(int damageTaken) {
        this.damageTaken.increment(damageTaken);

    }

    public void logHeal(int heal) {
        this.healTaken.increment(heal);
    }

    public void logCardUse() {
        this.numCardsPlayed.increment(1);
    }

    public PlayerStatEntry getDamageDealt() {
        return damageDealt;
    }

    public PlayerStatEntry getDamageTaken() {
        return damageTaken;
    }

    public PlayerStatEntry getHealTaken() {
        return healTaken;
    }

    public PlayerStatEntry getNumCardsPlayed() {
        return numCardsPlayed;
    }
}
