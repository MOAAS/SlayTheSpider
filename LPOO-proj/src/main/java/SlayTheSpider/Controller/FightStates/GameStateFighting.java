package SlayTheSpider.Controller.FightStates;

import SlayTheSpider.Model.FightModel;

public interface GameStateFighting {
    void onArrowUp(FightModel model);
    void onArrowDown(FightModel model);
    void onArrowLeft(FightModel model);
    void onArrowRight(FightModel model);
    GameStateFighting onEnter(FightModel model);
    GameStateFighting onEsc(FightModel model);

    String getTitle();
}
