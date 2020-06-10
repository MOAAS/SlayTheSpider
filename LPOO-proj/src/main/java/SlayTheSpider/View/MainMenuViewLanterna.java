package SlayTheSpider.View;

import SlayTheSpider.View.Drawers.LanternaDrawers.LanternaMenuDrawer;
import SlayTheSpider.Model.MainMenuModel;
import SlayTheSpider.View.TextWindow.LanternaWindow;
import SlayTheSpider.View.TextWindow.View.LanternaView;

public class MainMenuViewLanterna extends LanternaView {
    private final MainMenuModel model;

    public MainMenuViewLanterna(MainMenuModel model, LanternaWindow window) {
        super(window);
        this.model = model;
    }

    @Override
    public void draw() {
        new LanternaMenuDrawer(this.model.getMainMenu()).draw(this.getWindow().getTextDrawer());
    }
}
