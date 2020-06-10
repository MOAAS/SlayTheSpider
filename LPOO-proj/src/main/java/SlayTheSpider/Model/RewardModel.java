package SlayTheSpider.Model;

import SlayTheSpider.Model.Game.FightRewards.RewardList;
import SlayTheSpider.Model.Game.Player;

public class RewardModel implements Model {
    private final OverworldModel overworldModel;
    private final RewardList rewards;

    public RewardModel(OverworldModel overworldModel, RewardList rewards) {
        this.overworldModel = overworldModel;
        this.rewards = rewards;
    }

    public RewardList getRewards() {
        return rewards;
    }

    public OverworldModel getOverworldModel() {
        return overworldModel;
    }

    public Player getPlayer() {
        return this.overworldModel.getPlayer();
    }
}
