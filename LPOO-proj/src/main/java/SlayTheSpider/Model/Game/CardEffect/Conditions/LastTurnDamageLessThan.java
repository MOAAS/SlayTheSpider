package SlayTheSpider.Model.Game.CardEffect.Conditions;

import SlayTheSpider.Model.Game.Player;

public class LastTurnDamageLessThan extends CardEffectUserCondition  {
    private final int expected;

    public LastTurnDamageLessThan(int expected) {
        this.expected = expected;
    }

    @Override
    public boolean satisfies(Player user) {
        return user.getPlayerStats().getDamageDealt().lastTurn() < expected;
    }

    @Override
    public String getDescription() {
        return "Last turn user did less than " + expected + " damage";
    }
}