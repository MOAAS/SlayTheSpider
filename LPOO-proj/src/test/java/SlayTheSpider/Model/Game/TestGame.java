package SlayTheSpider.Model.Game;

import SlayTheSpider.Model.Sprites.Sprite;
import SlayTheSpider.Model.Game.FileStorage.GameStorage;
import SlayTheSpider.Model.Game.Overworld.FloorGenerator;
import SlayTheSpider.Model.Game.Overworld.FloorStructureList;
import SlayTheSpider.Model.Game.Overworld.Rooms.BossRoom;
import SlayTheSpider.Model.Game.Overworld.Rooms.SpawnRoom;
import SlayTheSpider.Model.Position.MapArea;
import SlayTheSpider.Model.Position.Position2D;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class TestGame {
    private Sprite spriteMock = Mockito.mock(Sprite.class);
    private Deck deckMock = Mockito.mock(Deck.class);
    private FloorStructureList floorMock = Mockito.mock(FloorStructureList.class);
    private List<Fight> fightList = new ArrayList<>();
    private BossRoom broomMock = Mockito.mock(BossRoom.class);
    private SpawnRoom sroomMock = Mockito.mock(SpawnRoom.class);
    private Player playerMock = Mockito.mock(Player.class);
    private OverworldPlayer owPlayerMock = Mockito.mock(OverworldPlayer.class);
    private MapArea mapAreaMock = Mockito.mock(MapArea.class);
    private Position2D posMock = Mockito.mock(Position2D.class);
    private GameStorage gameStorageMock = Mockito.mock(GameStorage.class);
    private FloorGenerator floorGeneratorMock = Mockito.mock(FloorGenerator.class);
   // private GameState gameStateMock = Mockito.mock(GameState.class);
    private Fight fightMock = Mockito.mock(Fight.class);
/*
    @Before
    public void Setup() {
        gameStorageMock = Mockito.mock(GameStorage.class);
        floorGeneratorMock = Mockito.mock(FloorGenerator.class);
        spriteMock = Mockito.mock(Sprite.class);
        deckMock = Mockito.mock(Deck.class);
        floorMock = Mockito.mock(FloorStructureList.class);
        broomMock = Mockito.mock(BossRoom.class);
        sroomMock = Mockito.mock(SpawnRoom.class);
        playerMock = Mockito.mock(Player.class);
        mapAreaMock = Mockito.mock(MapArea.class);
        posMock = Mockito.mock(Position2D.class);
        gameStateMock = Mockito.mock(GameState.class);
        fightMock = Mockito.mock(Fight.class);
        owPlayerMock = Mockito.mock(OverworldPlayer.class);

        Mockito.when(floorGeneratorMock.generate(anyInt())).thenReturn(floorMock);
        Mockito.when(gameStorageMock.getInitialDeck()).thenReturn(deckMock);
        Mockito.when(gameStorageMock.getBosses()).thenReturn(fightList);
        Mockito.when(gameStorageMock.getFights()).thenReturn(fightList);
        Mockito.when(gameStorageMock.getSprite(anyString())).thenReturn(spriteMock);
        Mockito.when(floorMock.getEnemyRooms()).thenReturn(new ArrayList<>());
        Mockito.when(floorMock.getList()).thenReturn(new ArrayList<>());
        Mockito.when(floorMock.getBossRoom()).thenReturn(broomMock);
        Mockito.when(floorMock.getSpawnRoom()).thenReturn(sroomMock);
        Mockito.when(sroomMock.getMapArea()).thenReturn(mapAreaMock);
        Mockito.when(mapAreaMock.getRandomPos()).thenReturn(posMock);
        Mockito.when(gameStorageMock.getPlayer()).thenReturn(playerMock);
        Mockito.when(playerMock.getOWPlayer()).thenReturn(owPlayerMock);

        fightList.add(Mockito.mock(Fight.class));
        fightList.add(Mockito.mock(Fight.class));
    }

    @Test
    public void TestCreate() {
        Game game = new Game(gameStorageMock, floorGeneratorMock);

        Game gameSpy = Mockito.spy(game);
        gameSpy.newGame();

        assertSame(gameSpy.getPlayer(), playerMock);
        Mockito.verify(playerMock, times(1)).setDeck(deckMock);
        Mockito.verify(gameSpy, times(1)).makeFloor();
    }

    @Test
    public void TestMake() {
        Game game = new Game(gameStorageMock, floorGeneratorMock);
        assertEquals(game.getFloorNum(), 0);

        game.newGame();

        assertEquals(game.getFloorNum(), 1);
        Mockito.verify(floorGeneratorMock, times(1)).setWallSprite(spriteMock);
        assertSame(game.getFloor().getStructures(), floorMock);
        Mockito.verify(gameStorageMock, times(1)).getFights();
        Mockito.verify(gameStorageMock, times(1)).getBosses();
        Mockito.verify(playerMock, times(1)).setMapPosition(posMock);
    }

 */
}
