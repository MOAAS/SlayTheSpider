package SlayTheSpider.Model.Game.CardEffect.Conditions;

import SlayTheSpider.Model.Game.Player;

public class UserHPGreaterThan extends CardEffectUserCondition {
    private final double expected;

    public UserHPGreaterThan(double expected) {
        this.expected = expected;
    }

    @Override
    public boolean satisfies(Player user) {
        return (double)user.getHealthBar().getHealth() / user.getHealthBar().getMaxHealth() > expected;
    }

    @Override
    public String getDescription() {
        return "User HP is greater than " + expected * 100 + "%";
    }
}
