package SlayTheSpider.Model.Game.FightRewards;

import SlayTheSpider.Model.Game.Player;

import java.util.List;

public interface Reward {
    Reward reroll();
    void applyReward(Player winner);
    String getName();
    String getDescription();

    List<String> getDetails();
    String getColor();
}
