package SlayTheSpider.View.Drawers.LanternaDrawers;

import SlayTheSpider.View.TextWindow.LanternaGraphics;
import SlayTheSpider.Model.Game.FightRewards.Reward;
import SlayTheSpider.Model.Game.FightRewards.RewardList;
import SlayTheSpider.Model.Position.Position;
import SlayTheSpider.Model.Position.Position2D;

public class LanternaRewardDrawer {
    private static final Position2D TITLE_POS = new Position(0,0);
    private static final Position2D LEFT_POS = TITLE_POS.down(1);
    private static final Position2D REWARDS_POS = TITLE_POS.down(2);
    private final RewardList rewards;

    public LanternaRewardDrawer(RewardList rewards) {
        this.rewards = rewards;
    }

    public void draw(LanternaGraphics drawer) {
        drawer.drawString("Pick your rewards", TITLE_POS);
        drawer.drawString("Rewards left: " + rewards.getLeft(), LEFT_POS);
        drawRewards(drawer);
    }

    private void drawRewards(LanternaGraphics drawer) {
        Position2D position = REWARDS_POS;
        for (Reward reward : rewards.getRewardList().getList()) {
            if (reward == rewards.getRewardList().getSelected()) {
                drawer.setTextColor("ff0000");
                drawer.drawString("--> " + reward.getName() + ": " + reward.getDescription(), position);
            }
            else {
                drawer.setTextColor("ffffff");
                drawer.drawString(" " + reward.getName() + ": " + reward.getDescription(), position);
            }
            position = position.down(1);
        }
    }
}
