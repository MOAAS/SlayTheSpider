package SlayTheSpider.View.TextWindow;

import SlayTheSpider.Model.Position.Position;
import SlayTheSpider.Model.Position.Position2D;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;

public class TestGraphics {
    @Test
    public void TestLanternaConst() {
        TextGraphics gMock = Mockito.mock(TextGraphics.class);

        LanternaGraphics g = new LanternaGraphics(gMock);

        Mockito.verify(gMock, times(1)).setBackgroundColor(any());
        Mockito.verify(gMock, times(1)).setForegroundColor(any());
    }

    @Test
    public void TestLanternaDraws() {
        TextGraphics gMock = Mockito.mock(TextGraphics.class);

        LanternaGraphics g = new LanternaGraphics(gMock);

        g.setTextColor("123456");
        g.setBackgroundColor("aaaaaa");

        Mockito.verify(gMock, times(1)).setForegroundColor(TextColor.Factory.fromString("#123456"));
        Mockito.verify(gMock, times(1)).setBackgroundColor(TextColor.Factory.fromString("#aaaaaa"));

        g.drawRect(new Position(8, 0), 2, 3);
        Mockito.verify(gMock, times(1)).fillRectangle(new TerminalPosition(8, 0), new TerminalSize(2, 3), ' ');

        String s = "HELLO";
        g.drawString(s, new Position(3, 2));
        Mockito.verify(gMock, times(1)).enableModifiers(SGR.BOLD);
        Mockito.verify(gMock, times(1)).putString(3, 2, s);
    }

    @Test (expected = NullPointerException.class)
    public void TestLanternaImage() {
        TextGraphics gMock = Mockito.mock(TextGraphics.class);

        LanternaGraphics g = new LanternaGraphics(gMock);

        g.drawImage(null, 0, 9);
    }

    @Test
    public void TestLanternaStrings() {
        TextGraphics gMock = Mockito.mock(TextGraphics.class);

        LanternaGraphics g = new LanternaGraphics(gMock);

        List<String> strings = new ArrayList<>();
        strings.add("HEY");
        strings.add("HOO");
        g.drawStringList(strings, new Position(2, 3), "ffffff");

        Mockito.verify(gMock, times(2)).setBackgroundColor(any());
        Mockito.verify(gMock, times(2)).setForegroundColor(any());
        Mockito.verify(gMock, times(2)).enableModifiers(SGR.BOLD);
    }

    @Test
    public void TestSwingSprites() {
        Graphics gMock = Mockito.mock(Graphics.class);

        SwingGraphics g = new SwingGraphics(gMock);

        String s = "HELLO";
        g.drawString(s, new Position(30, 2));
        Mockito.verify(gMock, times(1)).drawString(s, 30, 12);

        Image image = Mockito.mock(Image.class);
        g.drawImage(image, 3, 40);
        Mockito.verify(gMock, times(1)).drawImage(image, 3, 40, null);

        List<String> strings = new ArrayList<>();
        strings.add("HEY");
        strings.add("HOO");
        g.drawStringList(strings, new Position(2, 3), "ffffff");
        Mockito.verify(gMock, times(1)).setColor(Color.decode("#ffffff"));
        Mockito.verify(gMock, times(1)).drawString(strings.get(0), 2, 13);
        Mockito.verify(gMock, times(1)).drawString(strings.get(1), 2, 23);
    }

    @Test
    public void TestDrawCentered() {
        Graphics gMock = Mockito.mock(Graphics.class);
        FontMetrics metricsMock = Mockito.mock(FontMetrics.class);

        SwingGraphics g = new SwingGraphics(gMock);


        Mockito.when(gMock.getFontMetrics()).thenReturn(metricsMock);
        Mockito.when(metricsMock.stringWidth(any())).thenReturn(5);

        String s  = "HELLO";
        g.drawStringCentered(s, new Position(2,20));
        Mockito.verify(gMock, times(1)).drawString(s, 0, 20);
    }

    @Test
    public void TestWriteInBox() {
        Graphics gMock = Mockito.mock(Graphics.class);
        FontMetrics metricsMock = Mockito.mock(FontMetrics.class);
        Mockito.when(gMock.getFontMetrics()).thenReturn(metricsMock);
        Mockito.when(metricsMock.stringWidth(any())).thenReturn(90);
        Mockito.when(metricsMock.getHeight()).thenReturn(80);

        SwingGraphics g = new SwingGraphics(gMock);

        List<String> strings = new ArrayList<>();
        strings.add("HEY");
        strings.add("HOO");

        Position2D pos = Mockito.mock(Position2D.class);
        Mockito.when(pos.copy()).thenReturn(pos);
        Mockito.when(pos.getX()).thenReturn(20);
        Mockito.when(pos.getY()).thenReturn(2);

        SwingGraphics gSpy = Mockito.spy(g);

        gSpy.writeInBox(strings, pos, 2, 3);

        Mockito.verify(pos, times(1)).copy();
        Mockito.verify(pos, times(4)).setX(2);
        Mockito.verify(pos, times(4)).setDown(80);
        Mockito.verify(gSpy, times(1)).writeStringInBox(eq(strings.get(0)), any(), eq(2), eq(3));
        Mockito.verify(gSpy, times(1)).writeStringInBox(eq(strings.get(1)), any(), eq(2), eq(3));
    }

    @Test
    public void TestStringInBox() {
        Graphics gMock = Mockito.mock(Graphics.class);
        FontMetrics metricsMock = Mockito.mock(FontMetrics.class);
        Mockito.when(gMock.getFontMetrics()).thenReturn(metricsMock);
        Mockito.when(metricsMock.stringWidth(any())).thenReturn(7).thenReturn(8).thenReturn(11);
        Mockito.when(metricsMock.getHeight()).thenReturn(10);

        SwingGraphics g = new SwingGraphics(gMock);

        String sMock = "HELLO HALLE hille";

        g.writeStringInBox(sMock, new Position(2, 8), 4, 10);

        Mockito.verify(gMock, times(1)).drawString(" - HELLO HALLE ", 2, 8);
        Mockito.verify(gMock, times(1)).drawString("hille ", 4, 18);
    }
}
