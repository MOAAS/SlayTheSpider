package SlayTheSpider.View.TextWindow;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class LanternaWindow implements Window {
    Screen screen = null;

    private int width;
    private int height;

    public LanternaWindow(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void open() {
        try {
            Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(width, height)).createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);   // we don't need a cursor
            screen.startScreen();             // screens must be started
            screen.doResizeIfNecessary();     // resize screen if necessary
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LanternaGraphics getTextDrawer() {
        return new LanternaGraphics(screen.newTextGraphics());
    }

    public void refresh() {
        try {
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public KeyStroke readInput() {
        try {
            return screen.readInput();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void close() {
        try {
            screen.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
