package SlayTheSpider.Model.Game.FightRewards;

import SlayTheSpider.Model.Game.Player;

import java.util.ArrayList;
import java.util.List;

public class ManaReward implements Reward {
    @Override
    public ManaReward reroll() {
        return this;
    }

    @Override
    public void applyReward(Player winner) {
        winner.getHealthBar().increaseManaCap(1);
    }

    @Override
    public String getName() {
        return "Mana Point";
    }

    @Override
    public String getDescription() {
        return "Increases mana cap by 1";
    }

    @Override
    public List<String> getDetails() {
        List<String> details = new ArrayList<>();
        details.add("When picked, increases Mana Cap by 1.");
        return details;
    }

    @Override
    public String getColor() {
        return "00D890";
    }
}
