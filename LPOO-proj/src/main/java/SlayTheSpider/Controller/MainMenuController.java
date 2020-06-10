package SlayTheSpider.Controller;

import SlayTheSpider.Factory.GameEvent;
import SlayTheSpider.Model.MainMenuModel;
import SlayTheSpider.View.ViewFactory;
import SlayTheSpider.View.View;

public class MainMenuController implements Controller {
    private final MainMenuModel model;
    private final View view;

    public MainMenuController(MainMenuModel model, ViewFactory factory) {
        this.model = model;
        this.view = factory.makeMenuView(model);
    }

    @Override
    public void processEvent(GameEvent event) {
        switch (event) {
            case ArrowUp:
            case ArrowLeft:
                this.model.getMainMenu().goLeft();
                break;
            case ArrowDown:
            case ArrowRight:
                this.model.getMainMenu().goRight();
                break;
            case Enter:
                this.model.getMainMenu().click();
                break;
            default: break;
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void drawView() {
        this.view.draw();
    }
}
