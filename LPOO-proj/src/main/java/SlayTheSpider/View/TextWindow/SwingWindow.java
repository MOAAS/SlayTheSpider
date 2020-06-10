package SlayTheSpider.View.TextWindow;

import SlayTheSpider.View.TextWindow.View.EmptySwingView;

import javax.swing.*;
import java.awt.event.WindowEvent;


public class SwingWindow implements Window {
    private final SwingGamePanel panel;
    private JFrame frame;
    private final int width;
    private final int height;

    public SwingWindow(int width, int height) {
        this.width = width;
        this.height = height;
        frame = new JFrame("Slay The Spider");
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));

        this.panel = new SwingGamePanel(new EmptySwingView());
        frame.getContentPane().add(this.panel);
        frame.pack();
    }

    public void open() {
        frame.setSize(width, height);
        frame.setVisible(true);
    }

    public SwingGamePanel getPanel() {
        return panel;
    }

    public JFrame getFrame() {
        return frame;
    }

    @Override
    public void close() {
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

}
