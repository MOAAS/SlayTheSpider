package SlayTheSpider.View.TextWindow;


import javax.swing.*;
import java.awt.*;

public class SwingGamePanel extends JPanel {

    private SwingViewI view;

    public SwingGamePanel(SwingViewI view) {
        this.view = view;
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.view.draw(g);
    }

    public void setView(SwingViewI view) {
        this.view = view;
    }
}
