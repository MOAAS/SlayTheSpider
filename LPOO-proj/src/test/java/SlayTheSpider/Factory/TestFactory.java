package SlayTheSpider.Factory;

import SlayTheSpider.Model.Game.FileStorage.SpriteStorage;
import SlayTheSpider.Model.Sprites.Sprite;
import SlayTheSpider.Model.FightModel;
import SlayTheSpider.Model.MainMenuModel;
import SlayTheSpider.Model.OverworldModel;
import SlayTheSpider.Model.RewardModel;
import SlayTheSpider.View.TextWindow.LanternaGraphics;
import SlayTheSpider.View.TextWindow.LanternaWindow;
import SlayTheSpider.View.TextWindow.SwingGamePanel;
import SlayTheSpider.View.TextWindow.SwingWindow;
import SlayTheSpider.View.*;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.junit.Test;
import org.mockito.Mockito;

import javax.swing.*;

import java.awt.event.WindowAdapter;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;

public class TestFactory {
    @Test
    public void TestSwingFactory() {
        SwingWindow windowMock = Mockito.mock(SwingWindow.class);
        JFrame frame = Mockito.mock(JFrame.class);
        Mockito.when(windowMock.getFrame()).thenReturn(frame);

        GameEventProcessor controller = Mockito.mock(GameEventProcessor.class);

        SwingFactory factory = new SwingFactory(windowMock);
        factory.setupControls(controller);

        Mockito.verify(frame, times(1)).addWindowListener(any(WindowAdapter.class));
        assertSame(factory.getWindow(), windowMock);

        factory.beginDraw();
        factory.endDraw();

        Mockito.verify(frame, times(1)).revalidate();
        Mockito.verify(frame, times(1)).repaint();

        factory.resetWindow();
        Mockito.verify(windowMock, times(1)).close();
        assertNotSame(factory.getWindow(), windowMock);
    }

    @Test
    public void TestSwingViewMaking() {
        SwingWindow windowMock = Mockito.mock(SwingWindow.class);
        SwingGamePanel panelMock = Mockito.mock(SwingGamePanel.class);
        SwingFactory factory = new SwingFactory(windowMock);
        SpriteStorage storageMock = Mockito.mock(SpriteStorage.class);

        Mockito.when(windowMock.getPanel()).thenReturn(panelMock);

        MainMenuModel model1 = Mockito.mock(MainMenuModel.class);
        OverworldModel model2 = Mockito.mock(OverworldModel.class);
        FightModel model3 = Mockito.mock(FightModel.class);
        RewardModel model4 = Mockito.mock(RewardModel.class);

        Mockito.when(model2.getSpriteStorage()).thenReturn(storageMock);
        Mockito.when(model3.getSpriteStorage()).thenReturn(storageMock);
        Mockito.when(storageMock.getSprite(anyString())).thenReturn(Mockito.mock(Sprite.class));

        assertNotNull(factory.createSpriteStorage());
        assertTrue(factory.makeMenuView(model1) instanceof MainMenuViewSwing);
        assertTrue(factory.makeOWView(model2) instanceof OverworldViewSwing);
        assertTrue(factory.makeFightView(model3) instanceof FightViewSwing);
        assertTrue(factory.makeRewardView(model4) instanceof RewardViewSwing);
    }

    @Test
    public void TestLanternaFactory() {
        LanternaGraphics gMock = Mockito.mock(LanternaGraphics.class);
        LanternaWindow windowMock = Mockito.mock(LanternaWindow.class);
        Mockito.when(windowMock.readInput()).thenReturn(new KeyStroke(KeyType.EOF));
        Mockito.when(windowMock.getTextDrawer()).thenReturn(gMock);
        Mockito.when(windowMock.getWidth()).thenReturn(8);
        Mockito.when(windowMock.getHeight()).thenReturn(9);

        GameEventProcessor controller = Mockito.mock(GameEventProcessor.class);

        LanternaFactory factory = new LanternaFactory(windowMock);
        factory.setupControls(controller);
        assertSame(factory.getWindow(), windowMock);

        factory.beginDraw();
        factory.endDraw();

        Mockito.verify(windowMock, times(1)).refresh();
        Mockito.verify(gMock, times(1)).drawRect(any(), eq(8), eq(9));

        factory.resetWindow();
        Mockito.verify(windowMock, times(1)).close();
        assertNotSame(factory.getWindow(), windowMock);
    }

    @Test
    public void TestLanternaViewMaking() {
        LanternaWindow windowMock = Mockito.mock(LanternaWindow.class);
        LanternaFactory factory = new LanternaFactory(windowMock);

        MainMenuModel model1 = Mockito.mock(MainMenuModel.class);
        OverworldModel model2 = Mockito.mock(OverworldModel.class);
        FightModel model3 = Mockito.mock(FightModel.class);
        RewardModel model4 = Mockito.mock(RewardModel.class);

        Mockito.when(model2.getSpriteStorage()).thenReturn(Mockito.mock(SpriteStorage.class));
        Mockito.when(model3.getSpriteStorage()).thenReturn(Mockito.mock(SpriteStorage.class));

        assertNotNull(factory.createSpriteStorage());
        assertTrue(factory.makeMenuView(model1) instanceof MainMenuViewLanterna);
        assertTrue(factory.makeOWView(model2) instanceof OverworldViewLanterna);
        assertTrue(factory.makeFightView(model3) instanceof FightViewLanterna);
        assertTrue(factory.makeRewardView(model4) instanceof RewardViewLanterna);
    }

    @Test
    public void TestInputReader() {
        LanternaWindow window = Mockito.mock(LanternaWindow.class);
        GameEventProcessor controller = Mockito.mock(GameEventProcessor.class);
        LanternaInputReader reader = new LanternaInputReader(window, controller);

        Mockito.when(window.readInput()).thenReturn(new KeyStroke(KeyType.Backspace)).thenReturn(new KeyStroke(KeyType.ArrowUp));
        assertSame(reader.getEvent(), GameEvent.ArrowUp);

        Mockito.when(window.readInput()).thenReturn(new KeyStroke(KeyType.ArrowDown));
        assertSame(reader.getEvent(), GameEvent.ArrowDown);

        Mockito.when(window.readInput()).thenReturn(new KeyStroke(KeyType.ArrowLeft));
        assertSame(reader.getEvent(), GameEvent.ArrowLeft);

        Mockito.when(window.readInput()).thenReturn(new KeyStroke(KeyType.ArrowRight));
        assertSame(reader.getEvent(), GameEvent.ArrowRight);

        Mockito.when(window.readInput()).thenReturn(new KeyStroke(KeyType.Escape));
        assertSame(reader.getEvent(), GameEvent.Esc);

        Mockito.when(window.readInput()).thenReturn(new KeyStroke(KeyType.Enter));
        assertSame(reader.getEvent(), GameEvent.Enter);

        Mockito.when(window.readInput()).thenReturn(new KeyStroke(KeyType.EOF));
        assertSame(reader.getEvent(), GameEvent.Close);

        Mockito.when(window.readInput()).thenReturn(new KeyStroke(KeyType.Enter)).thenReturn(new KeyStroke(KeyType.EOF));

        reader.run();

        Mockito.verify(controller, times(1)).processEvent(GameEvent.Enter);
        Mockito.verify(controller, times(1)).processEvent(GameEvent.Close);
    }

}
