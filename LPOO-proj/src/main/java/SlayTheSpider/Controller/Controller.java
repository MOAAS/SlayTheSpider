package SlayTheSpider.Controller;

import SlayTheSpider.Factory.GameEvent;

public interface Controller {
    void processEvent(GameEvent event);

    void update();

    void drawView();
}
