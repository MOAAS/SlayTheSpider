package SlayTheSpider.View.TextWindow.View;

import SlayTheSpider.View.TextWindow.SwingViewI;
import SlayTheSpider.View.TextWindow.SwingWindow;

import java.awt.*;

public abstract class SwingView implements SwingViewI {
    private final static Font TITLE_FONT = new Font("Papyrus", Font.BOLD, 20);
    private final static int TITLE_X = 10;
    private final static int TITLE_Y = 20;

    private final SwingWindow window;
    private String title;

    protected SwingView(SwingWindow window) {
        this.window = window;
        this.window.getPanel().setView(this);
        this.title = "";
    }

    @Override
    public void draw() {
        this.window.getFrame().revalidate();
        this.window.getFrame().repaint();
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    public void drawTitle(Graphics g) {
        g.setFont(TITLE_FONT);
        g.setColor(new Color(14, 139, 201));
        g.drawString(this.title, TITLE_X, TITLE_Y);
    }

    public String getTitle() {
        return title;
    }

    public SwingWindow getWindow() {
        return window;
    }

    public abstract void draw(Graphics g);
}
