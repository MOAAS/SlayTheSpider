package SlayTheSpider.Controller;

import SlayTheSpider.Controller.GameOver.GameOverType;
import SlayTheSpider.Factory.GameEvent;
import SlayTheSpider.Model.OverworldModel;
import SlayTheSpider.View.ViewFactory;
import SlayTheSpider.View.View;

public class GameOverController implements Controller {
    private final View view;
    private final ViewFactory factory;
    private final ControllerObserver observer;
    private final GameOverType type;
    private final OverworldModel model;

    public GameOverController(OverworldModel model, ViewFactory factory, ControllerObserver observer, GameOverType type) {
        this.factory = factory;
        this.model = model;
        this.view = factory.makeOWView(model);
        this.observer = observer;
        this.type = type;
    }

    @Override
    public void processEvent(GameEvent event) {
        if (event == GameEvent.Enter)
            this.observer.setController(new MainMenuController(model.getMenuModel(), factory));
    }

    @Override
    public void update() {

    }

    @Override
    public void drawView() {
        this.view.setTitle(this.type.getTitle());
        this.view.draw();
    }
}
