package SlayTheSpider.Factory;

import SlayTheSpider.View.TextWindow.LanternaWindow;
import com.googlecode.lanterna.input.KeyStroke;

public class LanternaInputReader implements Runnable {
    private final GameEventProcessor controller;
    private final LanternaWindow window;

    public LanternaInputReader(LanternaWindow window, GameEventProcessor controller) {
        this.controller = controller;
        this.window = window;
    }

    public GameEvent getEvent() {
        while (true) {
            KeyStroke key = window.readInput();

            switch (key.getKeyType()) {
                case ArrowUp: return GameEvent.ArrowUp;
                case ArrowDown: return GameEvent.ArrowDown;
                case ArrowLeft: return GameEvent.ArrowLeft;
                case ArrowRight: return GameEvent.ArrowRight;
                case Escape: return GameEvent.Esc;
                case Enter: return GameEvent.Enter;
                case EOF: return GameEvent.Close;
            }
        }
    }

    @Override
    public void run() {
        while(true) {
            GameEvent event = this.getEvent();
            synchronized (controller) {
                this.controller.processEvent(event);
            }
            if (event == GameEvent.Close)
                break;
        }
    }
};
