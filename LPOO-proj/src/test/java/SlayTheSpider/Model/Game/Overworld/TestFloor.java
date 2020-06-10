package SlayTheSpider.Model.Game.Overworld;

import SlayTheSpider.Model.Game.Fight;
import SlayTheSpider.Model.Game.Overworld.OverWorldElements.OverworldBoss;
import SlayTheSpider.Model.Game.Overworld.OverWorldElements.OverworldEnemy;
import SlayTheSpider.Model.Game.Overworld.OverWorldElements.Tile;
import SlayTheSpider.Model.Game.Overworld.Rooms.BossRoom;
import SlayTheSpider.Model.Game.Overworld.Rooms.EnemyRoom;
import SlayTheSpider.Model.Game.OverworldPlayer;
import SlayTheSpider.Model.Position.Position;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.*;
import static org.mockito.Mockito.*;

public class TestFloor {
    private Floor testFloor;
    private OverworldPlayer playerMock = Mockito.mock(OverworldPlayer.class);
    private FloorStructureList floorStructureListMock = Mockito.mock(FloorStructureList.class);
    private Tile tileMock = Mockito.mock(Tile.class);
    private EnemyRoom enemyRoomMock = Mockito.mock(EnemyRoom.class);
    private Position positionMock = Mockito.mock(Position.class);


    @Before
    public void setup() {
        testFloor = new Floor(floorStructureListMock,playerMock);
    }

    @Test
    public void TestIsValidTile(){
        List<EnemyRoom> enemyRoomList = new ArrayList<>();
        enemyRoomList.add(enemyRoomMock);
        List<FloorStructure> floorStructuresList = new ArrayList<>();
        floorStructuresList.add(enemyRoomMock);
        when(floorStructureListMock.getEnemyRooms()).thenReturn(enemyRoomList);
        when(floorStructureListMock.getList()).thenReturn(floorStructuresList);

        testFloor.isValidTile(positionMock);

        when(enemyRoomMock.wallCollision(positionMock)).thenReturn(true);
        when(enemyRoomMock.containsPos(positionMock)).thenReturn(true);

        assertFalse(testFloor.isValidTile(positionMock));

        when(enemyRoomMock.wallCollision(positionMock)).thenReturn(false);

        assertTrue(testFloor.isValidTile(positionMock));

        when(enemyRoomMock.containsPos(positionMock)).thenReturn(false);

        assertFalse(testFloor.isValidTile(positionMock));

    }

    @Test
    public void TestUpdateVisibility(){
        List<FloorStructure> floorStructuresList = new ArrayList<>();
        List<Tile> unwalkableTiles = new ArrayList<>();
        floorStructuresList.add(enemyRoomMock);
        unwalkableTiles.add(tileMock);
        when(floorStructureListMock.getList()).thenReturn(floorStructuresList);
        when(floorStructureListMock.getUnwalkableTiles()).thenReturn(unwalkableTiles);
        testFloor.updateVisibility();
        Mockito.verify(enemyRoomMock,times(1)).updateVisibility(playerMock.getPosition());
        Mockito.verify(tileMock,times(1)).updateVisibility(playerMock.getPosition());
    }

    @Test
    public void TestUpdateRoomStatus(){
        List<EnemyRoom> roomList = new ArrayList<>();
        roomList.add(enemyRoomMock);
        when(floorStructureListMock.getEnemyRooms()).thenReturn(roomList);

        testFloor.updateRoomStatus();
        Mockito.verify(enemyRoomMock,times(1)).updateStatus(playerMock.getPosition());
    }

    @Test
    public void TestCheckFightCollision(){
        OverworldEnemy overworldEnemyMock = Mockito.mock(OverworldEnemy.class);
        OverworldBoss overworldBossMock = Mockito.mock(OverworldBoss.class);
        BossRoom bossRoomMock = Mockito.mock(BossRoom.class);
        List<EnemyRoom> enemyRoomList = new ArrayList<>();
        enemyRoomList.add(enemyRoomMock);

        when(bossRoomMock.doesBossCollide(any())).thenReturn(false);
        when(enemyRoomMock.doesEnemyCollide(any())).thenReturn(false);
        when(floorStructureListMock.getEnemyRooms()).thenReturn(enemyRoomList);
        when(floorStructureListMock.getBossRoom()).thenReturn(bossRoomMock);

        assertNull(testFloor.checkFightCollision());
        assertFalse(testFloor.doesFightCollide());

        when(enemyRoomMock.doesEnemyCollide(any())).thenReturn(true);
        when(enemyRoomMock.checkEnemyCollision(playerMock.getPosition())).thenReturn(overworldEnemyMock);

        assertEquals(testFloor.checkFightCollision(),overworldEnemyMock.getFight());
        assertTrue(testFloor.doesFightCollide());

        when(bossRoomMock.doesBossCollide(any())).thenReturn(true);
        when(bossRoomMock.checkBossCollision(playerMock.getPosition())).thenReturn(overworldBossMock);

        assertEquals(testFloor.checkFightCollision(),overworldBossMock.getBossFight());
        assertTrue(testFloor.doesFightCollide());

    }

    @Test
    public void TestSetFights(){

        List<EnemyRoom> enemyRoomList = new ArrayList<>();
        enemyRoomList.add(enemyRoomMock);

        BossRoom bossRoomMock = Mockito.mock(BossRoom.class);

        List<Fight> possibleFightsTest = new ArrayList<>();
        List<Fight> possibleBossesTest = new ArrayList<>();

        when(floorStructureListMock.getEnemyRooms()).thenReturn(enemyRoomList);
        when(floorStructureListMock.getBossRoom()).thenReturn(bossRoomMock);

        testFloor.setFights(possibleFightsTest, possibleBossesTest);

        Mockito.verify(enemyRoomMock,times(1)).insertEnemies(possibleFightsTest);
        Mockito.verify(bossRoomMock,times(1)).insertBoss(possibleBossesTest);
    }

    @Test
    public void TestIsBossDead(){
        BossRoom bossRoomMock = Mockito.mock(BossRoom.class);
        when(floorStructureListMock.getBossRoom()).thenReturn(bossRoomMock);
        when(bossRoomMock.areBossesDead()).thenReturn(false);
        assertEquals(testFloor.isBossDead(),false);
        when(bossRoomMock.areBossesDead()).thenReturn(true);
        assertEquals(testFloor.isBossDead(),true);
    }

    @Test
    public void TestGetStructures(){
        assertEquals(testFloor.getStructures(),floorStructureListMock);
    }
}
