package SlayTheSpider.View.TextWindow;

import SlayTheSpider.View.TextWindow.View.EmptySwingView;
import SlayTheSpider.View.TextWindow.View.LanternaView;
import SlayTheSpider.View.TextWindow.View.SwingView;
import org.junit.Test;
import org.mockito.Mockito;

import javax.swing.*;
import java.awt.*;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;

public class TestViews {
    @Test
    public void TestEmpty() {
        EmptySwingView view = new EmptySwingView();

        Graphics gMock = Mockito.mock(Graphics.class);

        view.draw(gMock);
        view.draw();
        view.setTitle("HELLO");

        Mockito.verifyZeroInteractions(gMock);
    }

    @Test
    public void TestLanterna() {
        LanternaWindow window = Mockito.mock(LanternaWindow.class);
        LanternaGraphics gMock = Mockito.mock(LanternaGraphics.class);

        LanternaView view = new LanternaView(window) {
            public void draw() { }
        };

        assertSame(view.getWindow(), window);

        String title = "HELLO";
        view.setTitle(title);
        view.drawTitle(gMock);

        Mockito.verify(gMock, times(1)).setTextColor("ffffff");
        Mockito.verify(gMock, times(1)).drawString(eq(title), any());
    }

    @Test
    public void TestSwing() {
        SwingWindow window = Mockito.mock(SwingWindow.class);
        JFrame frameMock = Mockito.mock(JFrame.class);
        SwingGamePanel panelMock = Mockito.mock(SwingGamePanel.class);

        Mockito.when(window.getFrame()).thenReturn(frameMock);
        Mockito.when(window.getPanel()).thenReturn(panelMock);
        Graphics gMock = Mockito.mock(Graphics.class);

        SwingView view = new SwingView(window) {
            @Override
            public void draw(Graphics g) { }
        };

        Mockito.verify(panelMock, times(1)).setView(view);

        assertSame(view.getWindow(), window);

        view.drawTitle(gMock);

        assertEquals(view.getTitle(), "");

        String title = "HELLO";
        view.setTitle(title);
        view.drawTitle(gMock);

        Mockito.verify(gMock, times(2)).setFont(any());
        Mockito.verify(gMock, times(2)).setColor(any());
        Mockito.verify(gMock, times(1)).drawString(title, 10, 20);

        view.draw();
        Mockito.verify(frameMock, times(1)).revalidate();
        Mockito.verify(frameMock, times(1)).repaint();
    }
}
