package SlayTheSpider.View;


import SlayTheSpider.View.Drawers.SwingDrawers.SwingMenuDrawer;
import SlayTheSpider.Model.MainMenuModel;
import SlayTheSpider.View.TextWindow.SwingWindow;
import SlayTheSpider.View.TextWindow.View.SwingView;

import java.awt.*;

public class MainMenuViewSwing extends SwingView {
    private final MainMenuModel model;

    public MainMenuViewSwing(MainMenuModel model, SwingWindow window) {
        super(window);
        this.model = model;
    }

    @Override
    public void draw(Graphics g) {
        new SwingMenuDrawer(model.getMainMenu(), this.getWindow().getWidth()).draw(g);
    }

}
