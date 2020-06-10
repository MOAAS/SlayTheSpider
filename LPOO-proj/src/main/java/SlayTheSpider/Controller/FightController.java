package SlayTheSpider.Controller;

import SlayTheSpider.Controller.FightStates.GameStateChooseCard;
import SlayTheSpider.Controller.FightStates.GameStateFighting;
import SlayTheSpider.Controller.GameOver.GameOverLoss;
import SlayTheSpider.Controller.GameOver.GameOverWin;
import SlayTheSpider.Factory.GameEvent;
import SlayTheSpider.Model.FightModel;
import SlayTheSpider.Model.OverworldModel;
import SlayTheSpider.Model.RewardModel;
import SlayTheSpider.View.View;
import SlayTheSpider.View.ViewFactory;
import SlayTheSpider.Model.Game.Fight;
public class FightController implements Controller {
    private final FightModel model;
    private final View view;
    private final ViewFactory factory;
    private GameStateFighting state;
    private ControllerObserver observer;

    public FightController(FightModel model, ViewFactory factory, ControllerObserver observer) {
        this.model = model;
        this.factory = factory;
        this.view = factory.makeFightView(model);
        this.observer = observer;
        this.state = new GameStateChooseCard();
    }

    @Override
    public void processEvent(GameEvent event) {
        switch (event) {
            case ArrowUp: this.state.onArrowUp(model); break;
            case ArrowLeft: this.state.onArrowLeft(model); break;
            case ArrowDown: this.state.onArrowDown(model); break;
            case ArrowRight: this.state.onArrowRight(model); break;
            case Enter: this.state = this.state.onEnter(model); break;
            case Esc: this.state = this.state.onEsc(model); break;
        }
    }

    @Override
    public void update() {
        model.getFight().clearDeadCharacters();
        model.getPlayer().getTargets().clearDeadCharacters();

        if (this.wonGame())
            this.observer.setController(new GameOverController(model.getOverworldModel(), factory, observer, new GameOverWin()));
        else if (this.wonFight())
            this.observer.setController(new RewardController(new RewardModel(model.getOverworldModel(), model.getFight().getRewards()), factory, observer));
        else if (this.lostFight())
            this.observer.setController(new GameOverController(model.getOverworldModel(), factory, observer, new GameOverLoss()));
    }

    @Override
    public void drawView() {
        this.view.setTitle(this.state.getTitle());
        this.view.draw();
    }

    private boolean wonFight() {
        return this.model.getFight().isEmpty();
    }

    private boolean lostFight() {
        return this.model.getPlayer().isDead();
    }

    private boolean wonGame() {
        OverworldModel owModel = this.model.getOverworldModel();
        Fight fight = this.model.getFight();
        return fight.isEmpty() && owModel.getFloor().isBossDead() && owModel.getFloorNum() >= 3;
    }
}
