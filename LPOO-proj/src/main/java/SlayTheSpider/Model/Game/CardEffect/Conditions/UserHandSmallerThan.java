package SlayTheSpider.Model.Game.CardEffect.Conditions;

import SlayTheSpider.Model.Game.Player;

public class UserHandSmallerThan extends CardEffectUserCondition {
    private final int expected;

    public UserHandSmallerThan(int expected) {
        this.expected = expected;
    }

    @Override
    public boolean satisfies(Player user) {
        return user.getDeck().getHand().size() < expected;
    }

    @Override
    public String getDescription() {
        return "User hand size is smaller than " + expected;
    }
}
