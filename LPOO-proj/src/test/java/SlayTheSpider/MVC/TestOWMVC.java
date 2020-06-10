package SlayTheSpider.MVC;

import SlayTheSpider.Controller.FightController;
import SlayTheSpider.Controller.OverworldController;
import SlayTheSpider.Factory.GameEvent;
import SlayTheSpider.Model.Game.*;
import SlayTheSpider.Model.Game.FileStorage.GameStorage;
import SlayTheSpider.Model.Game.FileStorage.SpriteStorage;
import SlayTheSpider.Model.Game.HealthBar.PlayerHealthBar;
import SlayTheSpider.Model.Game.Overworld.Floor;
import SlayTheSpider.Model.Game.Overworld.FloorGenerator;
import SlayTheSpider.Model.Game.Overworld.FloorStructure;
import SlayTheSpider.Model.Game.Overworld.FloorStructureList;
import SlayTheSpider.Model.Game.Overworld.OverWorldElements.OverworldObstacle;
import SlayTheSpider.Model.Game.Overworld.OverWorldElements.Tile;
import SlayTheSpider.Model.Game.Overworld.OverWorldElements.Visibility.ElementVisibility;
import SlayTheSpider.Model.Game.Overworld.Rooms.BossRoom;
import SlayTheSpider.Model.Game.Overworld.Rooms.SpawnRoom;
import SlayTheSpider.Model.Position.MapArea;
import SlayTheSpider.Model.Position.Position;
import SlayTheSpider.Model.Sprites.Sprite;
import SlayTheSpider.Controller.ControllerObserver;
import SlayTheSpider.Model.MainMenuModel;
import SlayTheSpider.Model.OverworldModel;
import SlayTheSpider.View.OverworldViewLanterna;
import SlayTheSpider.View.OverworldViewSwing;
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

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

public class TestOWMVC {
    private FloorGenerator generatorMock = Mockito.mock(FloorGenerator.class);
    private GameStorage storageMock = Mockito.mock(GameStorage.class);
    private SpriteStorage spriteStorageMock = Mockito.mock(SpriteStorage.class);
    private OverworldModel modelMock = Mockito.mock(OverworldModel.class);
    private Player playerMock = Mockito.mock(Player.class);
    private OverworldPlayer owPlayerMock = Mockito.mock(OverworldPlayer.class);
    private List<Enemy> enemyList = new ArrayList<>();
    private Sprite spriteMock = Mockito.mock(Sprite.class);
    private MainMenuModel menuMock = Mockito.mock(MainMenuModel.class);
    private Floor floorMock = Mockito.mock(Floor.class);
    private FloorStructureList structListMock = Mockito.mock(FloorStructureList.class);


    @Before
    public void setup() {
        spriteMock = Mockito.mock(Sprite.class);
        generatorMock = Mockito.mock(FloorGenerator.class);
        storageMock = Mockito.mock(GameStorage.class);
        spriteStorageMock = Mockito.mock(SpriteStorage.class);
        modelMock = Mockito.mock(OverworldModel.class);
        menuMock = Mockito.mock(MainMenuModel.class);
        floorMock = Mockito.mock(Floor.class);
        playerMock = Mockito.mock(Player.class);
        owPlayerMock = Mockito.mock(OverworldPlayer.class);
        enemyList.add(new Enemy(new Position(2, 3), spriteMock, 10, 20, 30));
        enemyList.add(new Enemy(new Position(2, 3), spriteMock, 10, 20, 30));

        Fight fightMock = Mockito.mock(Fight.class);
        PlayerHealthBar healthBarMock = Mockito.mock(PlayerHealthBar.class);
        Deck deckMock = Mockito.mock(Deck.class);
        SpawnRoom spawnRoomMock = Mockito.mock(SpawnRoom.class);
        BossRoom bossRoomMock = Mockito.mock(BossRoom.class);

        List<Fight> fights = new ArrayList<>();
        fights.add(Mockito.mock(Fight.class));

        List<Tile> unwalkables = new ArrayList<>();
        List<FloorStructure> structures = new ArrayList<>();
        List<OverworldObstacle> obstacles = new ArrayList<>();
        unwalkables.add(Mockito.mock(Tile.class));
        structures.add(Mockito.mock(FloorStructure.class));
        obstacles.add(Mockito.mock(OverworldObstacle.class));

        ElementVisibility visibilityMock = Mockito.mock(ElementVisibility.class);
        Mockito.when(visibilityMock.getType()).thenReturn(ElementVisibility.VisibilityType.VisibleFar);

        Mockito.when(modelMock.getPlayer()).thenReturn(playerMock);
        Mockito.when(modelMock.getFloor()).thenReturn(floorMock);
        Mockito.when(modelMock.getFloorNum()).thenReturn(5);
        Mockito.when(modelMock.getSpriteStorage()).thenReturn(spriteStorageMock);
        Mockito.when(generatorMock.generate(10)).thenReturn(structListMock);
        Mockito.when(generatorMock.generate(6)).thenReturn(null);
        Mockito.when(generatorMock.generate(8)).thenReturn(null);
        Mockito.when(floorMock.getStructures()).thenReturn(structListMock);
        Mockito.when(structListMock.getUnwalkableTiles()).thenReturn(unwalkables);
        Mockito.when(structListMock.getList()).thenReturn(structures);
        Mockito.when(structListMock.getSpawnRoom()).thenReturn(spawnRoomMock);
        Mockito.when(structListMock.getBossRoom()).thenReturn(bossRoomMock);


        Mockito.when(unwalkables.get(0).getVisibilitiy()).thenReturn(visibilityMock);
        Mockito.when(unwalkables.get(0).getColor()).thenReturn("aaaaaa");
        Mockito.when(unwalkables.get(0).getPosition()).thenReturn(new Position(0, 0));
        Mockito.when(obstacles.get(0).getVisibilitiy()).thenReturn(visibilityMock);
        Mockito.when(obstacles.get(0).getSprite()).thenReturn(spriteMock);
        Mockito.when(obstacles.get(0).getPosition()).thenReturn(new Position(0, 0));
        Mockito.when(structures.get(0).getTiles()).thenReturn(unwalkables);
        Mockito.when(structures.get(0).getObstacles()).thenReturn(obstacles);
        Mockito.when(spawnRoomMock.getMapArea()).thenReturn(new MapArea(new Position(0, 2), 20, 20));

        Mockito.when(fightMock.getEnemies()).thenReturn(enemyList);
        Mockito.when(deckMock.emptyHand()).thenReturn(true);

        Mockito.when(spriteStorageMock.getSprite(any())).thenReturn(spriteMock);
        Mockito.when(storageMock.getSprite(any())).thenReturn(spriteMock);
        Mockito.when(storageMock.getNewPlayer()).thenReturn(playerMock);

        Mockito.when(storageMock.getBosses()).thenReturn(fights);
        Mockito.when(storageMock.getFights()).thenReturn(fights);
        Mockito.when(storageMock.getSpriteStorage()).thenReturn(spriteStorageMock);
        Mockito.when(spriteMock.endarken()).thenReturn(spriteMock);
        Mockito.when(spriteMock.scale(anyInt(),anyInt())).thenReturn(spriteMock);
        Mockito.when(spriteMock.scale(anyInt(),anyInt())).thenReturn(spriteMock);
        Mockito.when(playerMock.getMapPosition()).thenReturn(new Position(0, 0));
        Mockito.when(playerMock.getDeck()).thenReturn(deckMock);
        Mockito.when(playerMock.getOWPlayer()).thenReturn(owPlayerMock);
        Mockito.when(playerMock.getHealthBar()).thenReturn(healthBarMock);
        Mockito.when(owPlayerMock.getPosition()).thenReturn(new Position(0, 0));
        Mockito.when(owPlayerMock.getDirection()).thenReturn(OverworldPlayer.Direction.DOWN);
        Mockito.when(modelMock.getMenuModel()).thenReturn(menuMock);

    }

    @Test
    public void TestModel() {
        OverworldModel model = new OverworldModel(menuMock, storageMock, generatorMock);

        assertSame(model.getSpriteStorage(), spriteStorageMock);
        assertSame(model.getPlayer(), playerMock);
        assertEquals(model.getFloorNum(), 1);
        assertSame(model.getMenuModel(), menuMock);
        assertNotNull(model.getFloor());

        Mockito.verify(generatorMock, times(1)).setWallSprite(spriteMock);
        Mockito.verify(playerMock, times(1)).setMapPosition(any());
        Mockito.verify(structListMock, times(1)).getBossRoom();
        Mockito.verify(structListMock, times(1)).getEnemyRooms();
    }

    @Test
    public void TestViewL(){
        LanternaWindow windowMock = Mockito.mock(LanternaWindow.class);
        LanternaGraphics graphics = Mockito.mock(LanternaGraphics.class);
        Mockito.when(windowMock.getTextDrawer()).thenReturn(graphics);
        Mockito.when(windowMock.getWidth()).thenReturn(1);
        Mockito.when(windowMock.getHeight()).thenReturn(1);

        OverworldViewLanterna spy = Mockito.spy(new OverworldViewLanterna(modelMock, windowMock));
        spy.draw();

        Mockito.verify(spy, times(1)).drawTitle(graphics);
        Mockito.verify(spriteMock, times(2)).draw(eq(graphics), any());
        Mockito.verify(graphics, times(3)).drawString(anyString(), any());
        Mockito.verify(graphics, times(3)).setTextColor(anyString());
        Mockito.verify(graphics, times(6)).setBackgroundColor(anyString());
        Mockito.verify(graphics, times(4)).drawRect(any(), anyInt(), anyInt());
    }

    @Test
    public void TestViewS(){
        SwingWindow windowMock = Mockito.mock(SwingWindow.class);
        Mockito.when(windowMock.getPanel()).thenReturn(Mockito.mock(SwingGamePanel.class));
        Graphics graphics = Mockito.mock(Graphics.class);
        Mockito.when(windowMock.getWidth()).thenReturn(1);
        Mockito.when(windowMock.getHeight()).thenReturn(1);

        OverworldViewSwing spy = Mockito.spy(new OverworldViewSwing(modelMock, windowMock));
        spy.draw(graphics);

        Mockito.verify(spy, times(1)).drawTitle(graphics);
        Mockito.verify(spriteMock, times(2)).draw(any(), any());
        Mockito.verify(graphics, times(3)).drawString(anyString(), anyInt(), anyInt());
        Mockito.verify(graphics, times(8)).setColor(any());
        Mockito.verify(graphics, times(3)).setFont(any());
        Mockito.verify(graphics, times(5)).fillRect(anyInt(), anyInt(), anyInt(), anyInt());

    }

    @Test
    public void TestController() {
        ViewFactory factory = Mockito.mock(ViewFactory.class);
        View viewMock = Mockito.mock(View.class);
        ControllerObserver observerMock = Mockito.mock(ControllerObserver.class);
        Mockito.when(factory.makeOWView(any())).thenReturn(viewMock);

        OverworldController controller = new OverworldController(modelMock, factory, observerMock);

        controller.drawView();
        Mockito.verify(viewMock, times(1)).setTitle(anyString());
        Mockito.verify(viewMock, times(1)).draw();

        controller.update();
        Mockito.verify(floorMock, times(1)).updateRoomStatus();
        Mockito.verify(floorMock, times(1)).updateVisibility();

        Mockito.when(floorMock.isValidTile(any())).thenReturn(true);

        controller.processEvent(GameEvent.ArrowRight);
        Mockito.verify(owPlayerMock, times(1)).moveRight();

        controller.processEvent(GameEvent.ArrowUp);
        Mockito.verify(owPlayerMock, times(1)).moveUp();

        controller.processEvent(GameEvent.ArrowLeft);
        Mockito.verify(owPlayerMock, times(1)).moveLeft();

        controller.processEvent(GameEvent.ArrowDown);
        Mockito.verify(owPlayerMock, times(1)).moveDown();

        Mockito.when(floorMock.isValidTile(any())).thenReturn(false);
        controller.processEvent(GameEvent.ArrowRight);
        controller.processEvent(GameEvent.ArrowUp);
        controller.processEvent(GameEvent.ArrowLeft);
        controller.processEvent(GameEvent.ArrowDown);
        controller.processEvent(GameEvent.Esc);

        Mockito.verify(owPlayerMock, times(1)).moveUp();
        Mockito.verify(owPlayerMock, times(1)).moveLeft();
        Mockito.verify(owPlayerMock, times(1)).moveRight();
        Mockito.verify(owPlayerMock, times(1)).moveDown();

        Mockito.verify(floorMock, times(9)).updateRoomStatus();
        Mockito.verify(floorMock, times(9)).updateVisibility();

        Mockito.when(floorMock.doesFightCollide()).thenReturn(true);
        Mockito.verify(observerMock, never()).setController(any(FightController.class));
        controller.processEvent(GameEvent.ArrowLeft);
        Mockito.verify(observerMock, times(1)).setController(any(FightController.class));
    }
}
