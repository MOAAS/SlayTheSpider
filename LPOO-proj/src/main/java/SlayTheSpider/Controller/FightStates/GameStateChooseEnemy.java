package SlayTheSpider.Controller.FightStates;

import SlayTheSpider.Model.Game.TargetList;
import SlayTheSpider.Model.FightModel;

public class GameStateChooseEnemy implements GameStateFighting {
    @Override
    public void onArrowUp(FightModel model) {
        TargetList targetList = model.getPlayer().getTargets();
        targetList.selectLeft();
    }

    @Override
    public void onArrowDown(FightModel model) {
        TargetList targetList = model.getPlayer().getTargets();
        targetList.selectRight();
    }

    @Override
    public void onArrowLeft(FightModel model) {
        TargetList targetList = model.getPlayer().getTargets();
        targetList.selectLeft();
    }

    @Override
    public void onArrowRight(FightModel model) {
        TargetList targetList = model.getPlayer().getTargets();
        targetList.selectRight();
    }

    @Override
    public GameStateFighting onEnter(FightModel model) {
        model.getPlayer().useSelectedCard();
        model.getPlayer().getTargets().selectPlayer();
        return new GameStateChooseCard();
    }

    @Override
    public GameStateFighting onEsc(FightModel model) {
        model.getPlayer().getTargets().selectPlayer();
        return new GameStateChooseCard();
    }

    @Override
    public String getTitle() {
        return "Choose enemy! (Arrow Keys + Enter)";
    }
}
