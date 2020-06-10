package SlayTheSpider.View;

import SlayTheSpider.Factory.GameEventProcessor;
import SlayTheSpider.Model.FightModel;
import SlayTheSpider.Model.MainMenuModel;
import SlayTheSpider.Model.OverworldModel;
import SlayTheSpider.Model.RewardModel;
import SlayTheSpider.View.TextWindow.Window;
import SlayTheSpider.Model.Game.FileStorage.SpriteStorage;

public interface ViewFactory {
    SpriteStorage createSpriteStorage();

    View makeMenuView(MainMenuModel model);
    View makeFightView(FightModel model);
    View makeOWView(OverworldModel model);
    View makeRewardView(RewardModel model);
    
    Window getWindow();

    void setupControls(GameEventProcessor superController);

    void endDraw();

    void beginDraw();

    void resetWindow();
}
