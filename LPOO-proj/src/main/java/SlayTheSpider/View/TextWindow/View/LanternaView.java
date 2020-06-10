package SlayTheSpider.View.TextWindow.View;

import SlayTheSpider.View.TextWindow.LanternaGraphics;
import SlayTheSpider.View.TextWindow.LanternaWindow;
import SlayTheSpider.Model.Position.Position;
import SlayTheSpider.View.View;

public abstract class LanternaView implements View {
    private final LanternaWindow window;
    private String title;

    protected LanternaView(LanternaWindow window) {
        this.window = window;
        this.title = "";
    }

    public void drawTitle(LanternaGraphics g) {
        g.setTextColor("ffffff");
        g.drawString(this.title, new Position(0, 0));
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    public LanternaWindow getWindow() {
        return window;
    }
}
