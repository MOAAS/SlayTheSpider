package SlayTheSpider.Model.Game.FightRewards;

import SlayTheSpider.Model.Game.Player;
import SlayTheSpider.Model.ListCycler.ArrayListCycler;
import SlayTheSpider.Model.ListCycler.ListCycler;

import java.util.ArrayList;

public class RewardList {
    private int availablePrizes;
    private ListCycler<Reward> rewardList = new ArrayListCycler<>(new ArrayList<>());

    public RewardList(int numRewards) {
        this.availablePrizes = numRewards;
    }

    public void addReward(Reward reward) {
        rewardList.add(reward);
    }

    public void selectLeft() {
        rewardList.selectLeft();
    }

    public void selectRight() {
        rewardList.selectRight();
    }

    public void consumeReward(Player winner) {
        if (rewardList.size() == 0 || availablePrizes <= 0)
            return;
        availablePrizes--;
        rewardList.getSelected().applyReward(winner);
        rewardList.remove(rewardList.getSelected());
    }

    public ListCycler<Reward> getRewardList() {
        return rewardList;
    }

    public boolean done() {
        return availablePrizes <= 0;
    }

    public int getLeft() {
        return availablePrizes;
    }

    public RewardList copy() {
        RewardList copy = new RewardList(availablePrizes);
        for (Reward reward : rewardList.getList())
            copy.addReward(reward.reroll());
        return copy;
    }

    public void skip() {
        this.availablePrizes = 0;
    }
}
