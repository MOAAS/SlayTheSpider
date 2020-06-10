package SlayTheSpider.Model.Game.FightRewards;

import SlayTheSpider.Model.Game.Player;

import java.util.ArrayList;
import java.util.List;

public class NullReward implements Reward {
    @Override
    public Reward reroll() {
        return this;
    }

    @Override
    public void applyReward(Player winner) {
    }

    @Override
    public String getName() {
        return "Null";
    }

    @Override
    public String getDescription() {
        return "Error: Null reward.";
    }

    @Override
    public List<String> getDetails() {
        return new ArrayList<>();
    }

    @Override
    public String getColor() {
        return "cccccc";
    }
}
