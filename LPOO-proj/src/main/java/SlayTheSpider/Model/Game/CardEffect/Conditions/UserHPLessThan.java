package SlayTheSpider.Model.Game.CardEffect.Conditions;

import SlayTheSpider.Model.Game.Player;

public class UserHPLessThan extends CardEffectUserCondition {
    private final double expected;

    public UserHPLessThan(double expected) {
        this.expected = expected;
    }

    @Override
    public boolean satisfies(Player user) {
        return (double)user.getHealthBar().getHealth() / user.getHealthBar().getMaxHealth() < expected;
    }

    @Override
    public String getDescription() {
        return "User HP is less than " + expected * 100 + "%";
    }
}
