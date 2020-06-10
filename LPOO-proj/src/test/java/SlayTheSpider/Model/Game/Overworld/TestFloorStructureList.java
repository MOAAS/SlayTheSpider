package SlayTheSpider.Model.Game.Overworld;

import SlayTheSpider.Model.Game.Overworld.Rooms.*;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class TestFloorStructureList {
    @Test
    public void TestAddEnemyRoom() {
        EnemyRoom enemyRoomMock1 = Mockito.mock(EnemyRoom.class);
        EnemyRoom enemyRoomMock2 = Mockito.mock(EnemyRoom.class);
        EnemyRoom enemyRoomMock3 = Mockito.mock(EnemyRoom.class);

        FloorStructureList structureList = new FloorStructureList(100, 100);

        structureList.addEnemyRoom(enemyRoomMock1);
        structureList.addEnemyRoom(enemyRoomMock2);
        structureList.addEnemyRoom(enemyRoomMock3);

        assertEquals(structureList.getEnemyRooms().size(), 3);
        assertEquals(structureList.getList().size(), 3);
        assertEquals(structureList.getRooms().size(), 3);

        assertNull(structureList.getSpawnRoom());
        assertNull(structureList.getBossRoom());

        assertSame(enemyRoomMock1, structureList.getEnemyRooms().get(0));
        assertSame(enemyRoomMock1, structureList.getList().get(0));
        assertSame(enemyRoomMock1, structureList.getRooms().get(0));

        assertSame(enemyRoomMock2, structureList.getEnemyRooms().get(1));
        assertSame(enemyRoomMock2, structureList.getList().get(1));
        assertSame(enemyRoomMock2, structureList.getRooms().get(1));

        assertSame(enemyRoomMock3, structureList.getEnemyRooms().get(2));
        assertSame(enemyRoomMock3, structureList.getList().get(2));
        assertSame(enemyRoomMock3, structureList.getRooms().get(2));
    }

    @Test
    public void TestSetSpawn() {
        SpawnRoom spawnRoomMock1 = Mockito.mock(SpawnRoom.class);
        SpawnRoom spawnRoomMock2 = Mockito.mock(SpawnRoom.class);

        FloorStructureList structureList = new FloorStructureList(100, 100);

        structureList.setSpawnRoom(spawnRoomMock1);

        assertEquals(structureList.getEnemyRooms().size(), 0);
        assertEquals(structureList.getList().size(), 1);
        assertEquals(structureList.getRooms().size(), 1);

        assertSame(structureList.getSpawnRoom(), spawnRoomMock1);
        assertNull(structureList.getBossRoom());


        structureList.setSpawnRoom(spawnRoomMock2);

        assertEquals(structureList.getEnemyRooms().size(), 0);
        assertEquals(structureList.getList().size(), 1);
        assertEquals(structureList.getRooms().size(), 1);

        assertSame(structureList.getSpawnRoom(), spawnRoomMock2);
        assertNull(structureList.getBossRoom());
    }

    @Test
    public void TestSetBoss() {
        BossRoom bossRoomMock1 = Mockito.mock(BossRoom.class);
        BossRoom bossRoomMock2 = Mockito.mock(BossRoom.class);

        FloorStructureList structureList = new FloorStructureList(100, 100);

        structureList.setBossRoom(bossRoomMock1);

        assertEquals(structureList.getEnemyRooms().size(), 0);
        assertEquals(structureList.getList().size(), 1);
        assertEquals(structureList.getRooms().size(), 1);

        assertNull(structureList.getSpawnRoom());
        assertSame(structureList.getBossRoom(), bossRoomMock1);


        structureList.setBossRoom(bossRoomMock2);

        assertEquals(structureList.getEnemyRooms().size(), 0);
        assertEquals(structureList.getList().size(), 1);
        assertEquals(structureList.getRooms().size(), 1);

        assertNull(structureList.getSpawnRoom());
        assertSame(structureList.getBossRoom(), bossRoomMock2);
    }

    @Test
    public void TestAddCorridor() {
        Corridor corridorMock1 = Mockito.mock(Corridor.class);
        Corridor corridorMock2 = Mockito.mock(Corridor.class);
        Corridor corridorMock3 = Mockito.mock(Corridor.class);

        FloorStructureList structureList = new FloorStructureList(100, 100);

        structureList.addCorridor(corridorMock1);
        structureList.addCorridor(corridorMock2);
        structureList.addCorridor(corridorMock3);

        assertEquals(structureList.getEnemyRooms().size(), 0);
        assertEquals(structureList.getList().size(), 3);
        assertEquals(structureList.getRooms().size(), 0);

        assertNull(structureList.getSpawnRoom());
        assertNull(structureList.getBossRoom());

        assertSame(corridorMock1, structureList.getList().get(0));
        assertSame(corridorMock2, structureList.getList().get(1));
        assertSame(corridorMock3, structureList.getList().get(2));
    }

    @Test
    public void TestShuffle() {
        EnemyRoom enemyRoomMock1 = Mockito.mock(EnemyRoom.class);
        EnemyRoom enemyRoomMock2 = Mockito.mock(EnemyRoom.class);
        EnemyRoom enemyRoomMock3 = Mockito.mock(EnemyRoom.class);

        Corridor corridorMock1 = Mockito.mock(Corridor.class);
        Corridor corridorMock2 = Mockito.mock(Corridor.class);
        Corridor corridorMock3 = Mockito.mock(Corridor.class);

        BossRoom bossRoomMock1 = Mockito.mock(BossRoom.class);

        SpawnRoom spawnRoomMock1 = Mockito.mock(SpawnRoom.class);

        FloorStructureList structureList = new FloorStructureList(100, 100);

        structureList.addCorridor(corridorMock1);
        structureList.addCorridor(corridorMock2);
        structureList.addCorridor(corridorMock3);

        structureList.addEnemyRoom(enemyRoomMock1);
        structureList.addEnemyRoom(enemyRoomMock2);
        structureList.addEnemyRoom(enemyRoomMock3);

        structureList.setBossRoom(bossRoomMock1);
        structureList.setSpawnRoom(spawnRoomMock1);

        assertEquals(structureList.getEnemyRooms().size(), 3);
        assertEquals(structureList.getList().size(), 8);
        assertEquals(structureList.getRooms().size(), 5);

        assertSame(structureList.getSpawnRoom(), spawnRoomMock1);
        assertSame(structureList.getBossRoom(), bossRoomMock1);

        structureList.shuffleRooms();

        assertEquals(structureList.getEnemyRooms().size(), 3);
        assertEquals(structureList.getList().size(), 8);
        assertEquals(structureList.getRooms().size(), 5);

        assertSame(structureList.getSpawnRoom(), spawnRoomMock1);
        assertSame(structureList.getBossRoom(), bossRoomMock1);
    }

    @Test
    public void TestReverse() {
        EnemyRoom enemyRoomMock1 = Mockito.mock(EnemyRoom.class);
        EnemyRoom enemyRoomMock2 = Mockito.mock(EnemyRoom.class);
        EnemyRoom enemyRoomMock3 = Mockito.mock(EnemyRoom.class);

        Corridor corridorMock1 = Mockito.mock(Corridor.class);
        Corridor corridorMock2 = Mockito.mock(Corridor.class);
        Corridor corridorMock3 = Mockito.mock(Corridor.class);

        BossRoom bossRoomMock1 = Mockito.mock(BossRoom.class);

        SpawnRoom spawnRoomMock1 = Mockito.mock(SpawnRoom.class);

        FloorStructureList structureList = new FloorStructureList(100, 100);

        structureList.addCorridor(corridorMock1);
        structureList.addCorridor(corridorMock2);
        structureList.addCorridor(corridorMock3);

        structureList.addEnemyRoom(enemyRoomMock1);
        structureList.addEnemyRoom(enemyRoomMock2);
        structureList.addEnemyRoom(enemyRoomMock3);

        structureList.setBossRoom(bossRoomMock1);
        structureList.setSpawnRoom(spawnRoomMock1);

        structureList.reverse();

        assertSame(structureList.getList().get(0), spawnRoomMock1);
        assertSame(structureList.getList().get(1), bossRoomMock1);
        assertSame(structureList.getList().get(2), enemyRoomMock3);
        assertSame(structureList.getList().get(3), enemyRoomMock2);
        assertSame(structureList.getList().get(4), enemyRoomMock1);
        assertSame(structureList.getList().get(5), corridorMock3);
        assertSame(structureList.getList().get(6), corridorMock2);
        assertSame(structureList.getList().get(7), corridorMock1);
    }

    @Test
    public void TestFit() {
        EnemyRoom enemyRoomMock1 = Mockito.mock(EnemyRoom.class);
        EnemyRoom enemyRoomMock2 = Mockito.mock(EnemyRoom.class);
        EnemyRoom enemyRoomMock3 = Mockito.mock(EnemyRoom.class);

        FloorStructureList structureList = new FloorStructureList(100, 100);

        structureList.addEnemyRoom(enemyRoomMock1);
        structureList.addEnemyRoom(enemyRoomMock2);

        when(enemyRoomMock1.intersects(enemyRoomMock3, 3)).thenReturn(true);
        when(enemyRoomMock2.intersects(enemyRoomMock3, 3)).thenReturn(false);
        assertFalse(structureList.doesRoomFit(enemyRoomMock3, 3));

        when(enemyRoomMock1.intersects(enemyRoomMock3, 3)).thenReturn(false);
        when(enemyRoomMock2.intersects(enemyRoomMock3, 3)).thenReturn(false);
        assertTrue(structureList.doesRoomFit(enemyRoomMock3, 3));

        when(enemyRoomMock1.intersects(enemyRoomMock3, 3)).thenReturn(true);
        when(enemyRoomMock2.intersects(enemyRoomMock3, 3)).thenReturn(true);
        assertFalse(structureList.doesRoomFit(enemyRoomMock3, 3));
    }

    @Test
    public void TestClear() {
        EnemyRoom enemyRoomMock1 = Mockito.mock(EnemyRoom.class);
        EnemyRoom enemyRoomMock2 = Mockito.mock(EnemyRoom.class);
        EnemyRoom enemyRoomMock3 = Mockito.mock(EnemyRoom.class);

        FloorStructureList structureList = new FloorStructureList(100, 100);

        structureList.addEnemyRoom(enemyRoomMock1);
        structureList.addEnemyRoom(enemyRoomMock2);
        structureList.addEnemyRoom(enemyRoomMock3);

        structureList.clear();

        assertEquals(structureList.getEnemyRooms().size(), 0);
        assertEquals(structureList.getRooms().size(), 0);
        assertEquals(structureList.getList().size(), 0);
    }

    @Test
    public void TestUnwalkableTiles() {
        FloorStructureList structureList = new FloorStructureList(100, 100);

        assertEquals(structureList.getUnwalkableTiles().size(), 11664);
    }
}
