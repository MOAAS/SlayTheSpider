package SlayTheSpider.MVC;

import SlayTheSpider.Controller.FightController;
import SlayTheSpider.Controller.GameOverController;
import SlayTheSpider.Factory.GameEvent;
import SlayTheSpider.Model.Game.*;
import SlayTheSpider.Model.Game.FileStorage.SpriteStorage;
import SlayTheSpider.Model.Game.Overworld.Floor;
import SlayTheSpider.Model.Position.Position;
import SlayTheSpider.Model.Sprites.Sprite;
import SlayTheSpider.Controller.ControllerObserver;
import SlayTheSpider.Controller.RewardController;
import SlayTheSpider.Model.FightModel;
import SlayTheSpider.Model.OverworldModel;
import SlayTheSpider.View.FightViewLanterna;
import SlayTheSpider.View.FightViewSwing;
import SlayTheSpider.View.View;
import SlayTheSpider.View.ViewFactory;
import SlayTheSpider.View.TextWindow.LanternaGraphics;
import SlayTheSpider.View.TextWindow.LanternaWindow;
import SlayTheSpider.View.TextWindow.SwingGamePanel;
import SlayTheSpider.View.TextWindow.SwingWindow;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertSame;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

public class TestFightMVC {
    private SpriteStorage storageMock = Mockito.mock(SpriteStorage.class);
    private Fight fightMock = Mockito.mock(Fight.class);
    private OverworldModel owMock = Mockito.mock(OverworldModel.class);
    private Player playerMock = Mockito.mock(Player.class);
    private List<Enemy> enemyList = new ArrayList<>();
    private Sprite spriteMock1 = Mockito.mock(Sprite.class);
    private Sprite spriteMock2 = Mockito.mock(Sprite.class);
    private Deck deckMock = Mockito.mock(Deck.class);
    private FightModel modelMock = Mockito.mock(FightModel.class);
    private Floor floorMock = Mockito.mock(Floor.class);
    private TargetList targetListMock = mock(TargetList.class);


    @Before
    public void setup() {
        storageMock = Mockito.mock(SpriteStorage.class);
        fightMock = Mockito.mock(Fight.class);
        owMock = Mockito.mock(OverworldModel.class);
        deckMock = Mockito.mock(Deck.class);
        playerMock = Mockito.spy(new Player(spriteMock2, 900));
        floorMock = Mockito.mock(Floor.class);
        targetListMock = mock(TargetList.class);
        enemyList.add(new Enemy(new Position(2, 3), spriteMock1, 10, 20, 30));
        enemyList.add(new Enemy(new Position(2, 3), spriteMock2, 10, 20, 30));

        Mockito.when(owMock.getPlayer()).thenReturn(playerMock);
        Mockito.when(owMock.getFloor()).thenReturn(floorMock);
        Mockito.when(owMock.getFloorNum()).thenReturn(3);
        Mockito.when(owMock.getSpriteStorage()).thenReturn(storageMock);
        Mockito.when(fightMock.getEnemies()).thenReturn(enemyList);
        Mockito.when(deckMock.emptyHand()).thenReturn(true);
        Mockito.when(storageMock.getSprite(any())).thenReturn(spriteMock2);
        Mockito.when(spriteMock1.scale(anyInt(),anyInt())).thenReturn(spriteMock1);
        Mockito.when(spriteMock2.scale(anyInt(),anyInt())).thenReturn(spriteMock2);
        Mockito.when(playerMock.getDeck()).thenReturn(deckMock);
        Mockito.when(playerMock.getTargets()).thenReturn(targetListMock);

        modelMock = Mockito.mock(FightModel.class);
        Mockito.when(modelMock.getSpriteStorage()).thenReturn(storageMock);
        Mockito.when(modelMock.getFight()).thenReturn(fightMock);
        Mockito.when(modelMock.getPlayer()).thenReturn(playerMock);
        Mockito.when(modelMock.getOverworldModel()).thenReturn(owMock);
    }

    @Test
    public void TestModel() {
        FightModel model = new FightModel(owMock, fightMock);

        assertSame(model.getSpriteStorage(), storageMock);
        assertSame(model.getFight(), fightMock);
        assertSame(model.getPlayer(), playerMock);
        assertSame(model.getOverworldModel(), owMock);

        Mockito.verify(playerMock, times(1)).beginFight(fightMock);
    }

    @Test
    public void TestViewL(){
        LanternaWindow windowMock = Mockito.mock(LanternaWindow.class);
        LanternaGraphics graphics = Mockito.mock(LanternaGraphics.class);
        Mockito.when(windowMock.getTextDrawer()).thenReturn(graphics);
        Mockito.when(windowMock.getWidth()).thenReturn(1);
        Mockito.when(windowMock.getHeight()).thenReturn(1);

        FightViewLanterna spy = Mockito.spy(new FightViewLanterna(modelMock, windowMock));
        spy.draw();

        Mockito.verify(spy, times(1)).drawTitle(graphics);
        Mockito.verify(spriteMock1, times(1)).draw(eq(graphics), any());
        Mockito.verify(spriteMock2, times(2)).draw(eq(graphics), any());

        Mockito.verify(graphics, times(17)).drawString(anyString(), any());
        Mockito.verify(graphics, times(13)).setTextColor(anyString());
        Mockito.verify(graphics, times(13)).setBackgroundColor(anyString());

    }

    @Test
    public void TestViewS(){
        SwingWindow windowMock = Mockito.mock(SwingWindow.class);
        Mockito.when(windowMock.getPanel()).thenReturn(Mockito.mock(SwingGamePanel.class));
        Graphics graphics = Mockito.mock(Graphics.class);
        Mockito.when(windowMock.getWidth()).thenReturn(1);
        Mockito.when(windowMock.getHeight()).thenReturn(1);

        FightViewSwing spy = Mockito.spy(new FightViewSwing(modelMock, windowMock));
        spy.draw(graphics);

        Mockito.verify(spy, times(1)).drawTitle(graphics);
        Mockito.verify(spriteMock1, times(1)).draw(any(), any());
        Mockito.verify(spriteMock2, times(5)).draw(any(), any());

        Mockito.verify(graphics, times(18)).drawString(anyString(), anyInt(), anyInt());
        Mockito.verify(graphics, times(30)).setColor(any());
        Mockito.verify(graphics, times(11)).setFont(any());
    }

    @Test
    public void TestController() {
        ViewFactory factory = Mockito.mock(ViewFactory.class);
        View viewMock = Mockito.mock(View.class);
        ControllerObserver observerMock = Mockito.mock(ControllerObserver.class);
        Mockito.when(factory.makeFightView(any())).thenReturn(viewMock);

        FightController controller = new FightController(modelMock, factory, observerMock);

        controller.drawView();
        Mockito.verify(viewMock, times(1)).setTitle(anyString());
        Mockito.verify(viewMock, times(1)).draw();

        controller.processEvent(GameEvent.ArrowRight);
        Mockito.verify(deckMock, times(1)).selectCardRight();

        controller.processEvent(GameEvent.ArrowUp);
        Mockito.verify(deckMock, times(1)).selectCardLeft();

        controller.processEvent(GameEvent.ArrowLeft);
        Mockito.verify(deckMock, times(2)).selectCardLeft();

        controller.processEvent(GameEvent.ArrowDown);
        Mockito.verify(deckMock, times(2)).selectCardRight();

        controller.processEvent(GameEvent.Enter);
        Mockito.verify(modelMock, times(5)).getPlayer();

        controller.processEvent(GameEvent.Esc);

        Mockito.when(fightMock.isEmpty()).thenReturn(true);
        Mockito.when(floorMock.isBossDead()).thenReturn(true);
        controller.update();
        Mockito.verify(fightMock, times(1)).clearDeadCharacters();
        Mockito.verify(targetListMock, times(1)).clearDeadCharacters();
        Mockito.verify(observerMock, times(1)).setController(any(GameOverController.class));


        Mockito.when(fightMock.isEmpty()).thenReturn(true);
        Mockito.when(floorMock.isBossDead()).thenReturn(false);
        Mockito.when(playerMock.isDead()).thenReturn(false);
        controller.update();
        Mockito.verify(observerMock, times(1)).setController(any(RewardController.class));


        Mockito.when(fightMock.isEmpty()).thenReturn(false);
        Mockito.when(floorMock.isBossDead()).thenReturn(false);
        Mockito.when(playerMock.isDead()).thenReturn(false);
        controller.update();
        Mockito.verify(observerMock, times(1)).setController(any(GameOverController.class));
        Mockito.verify(observerMock, times(1)).setController(any(RewardController.class));

        Mockito.when(fightMock.isEmpty()).thenReturn(false);
        Mockito.when(floorMock.isBossDead()).thenReturn(false);
        Mockito.when(playerMock.isDead()).thenReturn(true);
        controller.update();
        Mockito.verify(observerMock, times(2)).setController(any(GameOverController.class));
    }
}
