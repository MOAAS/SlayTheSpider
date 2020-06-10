package SlayTheSpider.Model.Game.StatusEffect;

public class StatusEffectListEntry {
    private final StatusEffect effect;
    private int roundsLeft;

    public StatusEffectListEntry(StatusEffect effect, int duration) {
        this.effect = effect;
        this.roundsLeft = duration;
    }

    public void endRound() {
        roundsLeft--;
    }

    public boolean isWornOff() {
        return roundsLeft <= 0;
    }

    public StatusEffect getEffect() {
        return effect;
    }

    public int getRoundsLeft() {
        return roundsLeft;
    }

    public void increaseDuration(int duration) {
        roundsLeft += duration;
    }
}
