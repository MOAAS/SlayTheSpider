package SlayTheSpider.Factory;

import SlayTheSpider.Model.FightModel;
import SlayTheSpider.Model.MainMenuModel;
import SlayTheSpider.Model.OverworldModel;
import SlayTheSpider.Model.RewardModel;
import SlayTheSpider.View.*;
import SlayTheSpider.View.TextWindow.SwingWindow;
import SlayTheSpider.Model.Game.FileStorage.ImageSpriteStorage;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static java.awt.event.KeyEvent.*;


public class SwingFactory implements ViewFactory {
    private KeyEventDispatcher keyEventDispatcher;
    private SwingWindow window;

    public SwingFactory(SwingWindow window) {
        this.window = window;
    }

    @Override
    public SwingWindow getWindow() {
        return window;
    }


    @Override
    public void setupControls(GameEventProcessor controller) {
        KeyEventDispatcher keyEventDispatcher = e -> {
            if (e.getID() != KEY_PRESSED)
                return false;
            synchronized (controller) {
                switch (e.getKeyCode()) {
                    case VK_UP:
                        controller.processEvent(GameEvent.ArrowUp);
                        break;
                    case VK_DOWN:
                        controller.processEvent(GameEvent.ArrowDown);
                        break;
                    case VK_LEFT:
                        controller.processEvent(GameEvent.ArrowLeft);
                        break;
                    case VK_RIGHT:
                        controller.processEvent(GameEvent.ArrowRight);
                        break;
                    case VK_ESCAPE:
                        controller.processEvent(GameEvent.Esc);
                        break;
                    case VK_ENTER:
                        controller.processEvent(GameEvent.Enter);
                        break;
                }
            }
            return false;
        };

        this.keyEventDispatcher = keyEventDispatcher;

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(keyEventDispatcher);

        this.window.getFrame().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                synchronized (controller) {
                    controller.processEvent(GameEvent.Close);
                }
            }
        });
    }

    public void resetWindow() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(keyEventDispatcher);
        this.window.close();
        this.window = new SwingWindow(window.getWidth(), window.getHeight());
    }

    @Override
    public void endDraw() {
        window.getFrame().revalidate();
        window.getFrame().repaint();
    }

    @Override
    public void beginDraw() {

    }

    @Override
    public ImageSpriteStorage createSpriteStorage() {
        return new ImageSpriteStorage();
    }

    @Override
    public View makeMenuView(MainMenuModel model) {
        return new MainMenuViewSwing(model, window);
    }

    @Override
    public View makeFightView(FightModel model) {
        return new FightViewSwing(model, window);
    }

    @Override
    public View makeOWView(OverworldModel model) {
        return new OverworldViewSwing(model, window);
    }

    @Override
    public View makeRewardView(RewardModel model) {
        return new RewardViewSwing(model, window);
    }
}
