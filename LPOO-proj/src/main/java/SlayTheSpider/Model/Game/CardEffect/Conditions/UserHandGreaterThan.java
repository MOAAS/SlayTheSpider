package SlayTheSpider.Model.Game.CardEffect.Conditions;

import SlayTheSpider.Model.Game.Player;

public class UserHandGreaterThan extends CardEffectUserCondition  {
    private final int expected;

    public UserHandGreaterThan(int expected) {
        this.expected = expected;
    }

    @Override
    public boolean satisfies(Player user) {
        return user.getDeck().getHand().size() > expected;
    }

    @Override
    public String getDescription() {
        return "User hand size is larger than " + expected;
    }
}
