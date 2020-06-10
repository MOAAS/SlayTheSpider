package SlayTheSpider.Factory;

import SlayTheSpider.Model.Position.Position;
import SlayTheSpider.Model.FightModel;
import SlayTheSpider.Model.MainMenuModel;
import SlayTheSpider.Model.OverworldModel;
import SlayTheSpider.Model.RewardModel;
import SlayTheSpider.View.*;
import SlayTheSpider.View.TextWindow.LanternaWindow;
import SlayTheSpider.View.TextWindow.Window;
import SlayTheSpider.Model.Game.FileStorage.TextSpriteStorage;

public class LanternaFactory implements ViewFactory {
    private LanternaWindow window;

    public LanternaFactory(LanternaWindow window) {
        this.window = window;
    }

    @Override
    public Window getWindow() {
        return window;
    }

    @Override
    public void setupControls(GameEventProcessor controller) {
        new Thread(new LanternaInputReader(this.window, controller)).start();
    }

    @Override
    public void endDraw() {
        window.refresh();
    }

    @Override
    public void beginDraw() {
        window.getTextDrawer().drawRect(new Position(0, 0), this.window.getWidth(), this.window.getHeight());
    }

    public void resetWindow() {
        this.window.close();
        this.window = new LanternaWindow(window.getWidth(), window.getHeight());
    }

    @Override
    public TextSpriteStorage createSpriteStorage() {
        return new TextSpriteStorage();
    }

    @Override
    public View makeMenuView(MainMenuModel model) {
        return new MainMenuViewLanterna(model, window);
    }

    @Override
    public View makeFightView(FightModel model) {
        return new FightViewLanterna(model, window);
    }

    @Override
    public View makeOWView(OverworldModel model) {
        return new OverworldViewLanterna(model, window);
    }

    @Override
    public View makeRewardView(RewardModel model) {
        return new RewardViewLanterna(model, window);
    }
}
