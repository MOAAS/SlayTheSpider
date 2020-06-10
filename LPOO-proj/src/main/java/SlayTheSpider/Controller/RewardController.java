package SlayTheSpider.Controller;

import SlayTheSpider.Factory.GameEvent;
import SlayTheSpider.Model.RewardModel;
import SlayTheSpider.View.ViewFactory;
import SlayTheSpider.View.View;

public class RewardController implements Controller {

    private final RewardModel model;
    private final ViewFactory factory;
    private final View view;
    private final ControllerObserver observer;

    public RewardController(RewardModel model, ViewFactory factory, ControllerObserver observer) {
        this.model = model;
        this.factory = factory;
        this.view = factory.makeRewardView(model);
        this.observer = observer;
    }

    @Override
    public void processEvent(GameEvent event) {
        switch (event) {
            case ArrowUp:
            case ArrowLeft:
                this.model.getRewards().selectLeft(); break;
            case ArrowDown:
            case ArrowRight:
                this.model.getRewards().selectRight(); break;
            case Enter: this.model.getRewards().consumeReward(model.getPlayer()); break;
            case Esc: this.model.getRewards().skip(); break;
        }
    }

    @Override
    public void update() {
        if (!this.model.getRewards().done())
            return;
        if (model.getOverworldModel().getFloor().isBossDead())
            this.advanceFloor();
        this.observer.setController(new OverworldController(model.getOverworldModel(), factory, observer));
    }

    private void advanceFloor() {
        model.getPlayer().getHealthBar().refillHealth();
        model.getOverworldModel().makeFloor();
    }

    @Override
    public void drawView() {
        this.view.setTitle("PICK YOUR REWARDS");
        this.view.draw();
    }
}
