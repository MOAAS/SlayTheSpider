package SlayTheSpider.Controller.FightStates;


import SlayTheSpider.Model.FightModel;

public class GameStateEnemyTurn implements GameStateFighting {
    @Override
    public void onArrowUp(FightModel model)  {

    }

    @Override
    public void onArrowDown(FightModel model)  {

    }

    @Override
    public void onArrowLeft(FightModel model) {

    }

    @Override
    public void onArrowRight(FightModel model) {

    }

    @Override
    public GameStateFighting onEnter(FightModel model) {
        model.getFight().endTurn(model.getPlayer());
        return new GameStateChooseCard();
    }

    @Override
    public GameStateFighting onEsc(FightModel model) {
        return new GameStateChooseCard();
    }

    @Override
    public String getTitle() {
        return "Enemy Turn! (Press ENTER to continue)";
    }
}
