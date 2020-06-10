package SlayTheSpider.View;

import SlayTheSpider.View.Drawers.LanternaDrawers.LanternaRewardDrawer;
import SlayTheSpider.Model.RewardModel;
import SlayTheSpider.View.TextWindow.LanternaWindow;
import SlayTheSpider.View.TextWindow.View.LanternaView;

public class RewardViewLanterna extends LanternaView {
    private final RewardModel model;

    public RewardViewLanterna(RewardModel model, LanternaWindow window) {
        super(window);
        this.model = model;
    }

    @Override
    public void draw() {
        new LanternaRewardDrawer(model.getRewards()).draw(this.getWindow().getTextDrawer());
    }
}
