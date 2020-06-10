package SlayTheSpider.View.TextWindow;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import org.junit.Test;
import org.mockito.Mockito;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;

public class TestWindows {
    @Test
    public void TestSwing() {
        SwingWindow window = new SwingWindow(1, 2);

        assertSame(window.getFrame().getContentPane().getComponent(0), window.getPanel());
        assertEquals(window.getFrame().getTitle(), "Slay The Spider");
        assertTrue(window.getFrame().getContentPane().getLayout() instanceof BoxLayout);

        assertFalse(window.getFrame().isVisible());
        assertEquals(window.getHeight(), 2);
        assertEquals(window.getWidth(), 1);

        window.open();
        assertTrue(window.getFrame().isVisible());
        window.close();
        assertFalse(window.getFrame().isVisible());
    }

    @Test
    public void TestSwingPanel() {
        SwingViewI viewMock1 = Mockito.mock(SwingViewI.class);
        SwingViewI viewMock2 = Mockito.mock(SwingViewI.class);

        SwingGamePanel gamePanel = new SwingGamePanel(viewMock1);

        Graphics gMock = Mockito.mock(Graphics.class);
        Mockito.when(gMock.create()).thenReturn(gMock);

        gamePanel.paintComponent(gMock);
        Mockito.verify(viewMock1, times(1)).draw(gMock);
        Mockito.verify(viewMock2, times(0)).draw(gMock);
        Mockito.verify(gMock, times(1)).create();

        gamePanel.setView(viewMock2);
        gamePanel.paintComponent(gMock);

        Mockito.verify(viewMock1, times(1)).draw(gMock);
        Mockito.verify(viewMock2, times(1)).draw(gMock);
    }

    @Test
    public void TestLanterna() {
        LanternaWindow window = new LanternaWindow(1, 2);
        assertEquals(window.getHeight(), 2);
        assertEquals(window.getWidth(), 1);

        Screen screen = Mockito.mock(Screen.class);
        window.screen = screen;

        Mockito.when(screen.newTextGraphics()).thenReturn(Mockito.mock(TextGraphics.class));
        assertNotNull(window.getTextDrawer());


        try {
            KeyStroke strokeMock = Mockito.mock(KeyStroke.class);
            Mockito.when(screen.readInput()).thenReturn(strokeMock);
            window.close();
            Mockito.verify(screen, times(1)).close();
            window.refresh();
            Mockito.verify(screen, times(1)).refresh();
            assertSame(window.readInput(), strokeMock);
            Mockito.verify(screen, times(1)).readInput();
        } catch (IOException ignored) { }

        try {
            Mockito.when(screen.readInput()).thenThrow(new IOException());
            doThrow(new IOException()).when(screen).refresh();
            doThrow(new IOException()).when(screen).close();

            window.refresh();
            window.readInput();
            window.close();
        } catch (IOException ignored) {
        }

        window.open();

        assertNotSame(screen, window.screen);

        window.close();
    }
}
