package SlayTheSpider.View.Drawers.SwingDrawers;

import SlayTheSpider.Model.Game.FightRewards.Reward;
import SlayTheSpider.Model.Game.FightRewards.RewardList;
import SlayTheSpider.Model.Position.Position;
import SlayTheSpider.Model.Position.Position2D;
import SlayTheSpider.View.TextWindow.SwingGraphics;

import java.awt.*;

public class SwingRewardDrawer implements SwingDrawer {
    private static final int TITLE_X = 10;
    private static final int TITLE_Y = 30;
    private static final Font TITLE_FONT = new Font("Tahoma", Font.BOLD, 25);

    private static final int LEFT_X = 30;
    private static final int LEFT_Y = 60;
    private static final Font LEFT_FONT = new Font("Tahoma", Font.BOLD, 20);

    private static final int REWARDS_X = LEFT_X;
    private static final int REWARDS_Y = LEFT_Y + 30;
    private static final Font REWARDS_FONT = new Font("Tahoma", Font.PLAIN, 18);

    private static final int SELECTED_X = LEFT_Y + 750;
    private static final int SELECTED_Y = LEFT_Y;

    private static final int SELECTED_WIDTH = 300;
    private static final int SELECTED_HEIGHT = 300;

    private static final Position2D SELECTED_TITLE = new Position(SELECTED_X + SELECTED_WIDTH / 2, SELECTED_Y + 30);
    private static final Position2D SELECTED_TEXT = new Position(SELECTED_X, SELECTED_Y + 60);
    private final RewardList rewards;

    public SwingRewardDrawer(RewardList rewards) {
        this.rewards = rewards;
    }

    @Override
    public void draw(Graphics g) {
        this.drawTitle(g);
        this.drawLeft(g);
        this.drawRewards(g);
        this.drawSelected(g, rewards.getRewardList().getSelected());
    }

    private void drawSelected(Graphics g, Reward selected) {
        g.setColor(Color.decode("#" + selected.getColor()).darker());
        g.fillRect(SELECTED_X - 5, SELECTED_Y - 5, SELECTED_WIDTH + 10, SELECTED_HEIGHT + 10);
        g.setColor(Color.decode("#" + selected.getColor()));
        g.fillRect(SELECTED_X, SELECTED_Y, SELECTED_WIDTH, SELECTED_HEIGHT);
        g.setColor(Color.BLACK);
        new SwingGraphics(g).drawStringCentered(selected.getName(), SELECTED_TITLE);
        new SwingGraphics(g).writeInBox(selected.getDetails(), SELECTED_TEXT, SELECTED_X, SELECTED_X + SELECTED_WIDTH);
    }

    private void drawTitle(Graphics g) {
        g.setFont(TITLE_FONT);
        g.drawString("Congratulations! Pick your rewards!", TITLE_X, TITLE_Y);
    }

    private void drawLeft(Graphics g) {
        g.setFont(LEFT_FONT);
        g.drawString("Rewards left: " + rewards.getLeft(), LEFT_X, LEFT_Y);
    }


    private void drawRewards(Graphics g) {
        int y = REWARDS_Y;
        g.setFont(REWARDS_FONT);
        for (Reward reward : rewards.getRewardList().getList()) {
            if (reward == rewards.getRewardList().getSelected()) {
                g.setColor(Color.RED);
                g.drawString("--> " + reward.getName() + ": " + reward.getDescription(), SwingRewardDrawer.REWARDS_X, y);
            } else {
                g.setColor(Color.BLACK);
                g.drawString("  " + reward.getName() + ": " + reward.getDescription(), SwingRewardDrawer.REWARDS_X, y);
            }
            y += 25;
        }
    }
}
