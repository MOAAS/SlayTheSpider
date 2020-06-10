package SlayTheSpider.Model.Game.FightRewards;

import SlayTheSpider.Model.Game.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AppleReward implements Reward {
    private final int HPincrease;
    private final int min;
    private final int max;

    public AppleReward(int min, int max) {
        this.min = min;
        this.max = max;
        this.HPincrease = new Random().nextInt(max - min + 1) + min;
    }

    @Override
    public AppleReward reroll() {
        return new AppleReward(min, max);
    }

    @Override
    public void applyReward(Player winner) {
        winner.getHealthBar().increaseMaxHealth(HPincrease);
        winner.heal(HPincrease);
    }

    @Override
    public String getName() {
        return "Healthy apple";
    }

    @Override
    public String getDescription() {
        return "Permanently increases MaxHP by " + HPincrease;
    }

    @Override
    public List<String> getDetails() {
        List<String> details = new ArrayList<>();
        details.add("Permanently increases Maximum Health by " + HPincrease);
        return details;
    }

    @Override
    public String getColor() {
        return "ff0000";
    }
}
