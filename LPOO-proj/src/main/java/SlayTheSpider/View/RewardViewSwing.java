package SlayTheSpider.View;

import SlayTheSpider.View.Drawers.SwingDrawers.SwingRewardDrawer;
import SlayTheSpider.Model.RewardModel;
import SlayTheSpider.View.TextWindow.SwingWindow;
import SlayTheSpider.View.TextWindow.View.SwingView;

import java.awt.*;

public class RewardViewSwing extends SwingView {
    private final RewardModel model;

    public RewardViewSwing(RewardModel model, SwingWindow window) {
        super(window);
        this.model = model;
    }

    @Override
    public void draw(Graphics g) {
        new SwingRewardDrawer(model.getRewards()).draw(g);
    }
}
