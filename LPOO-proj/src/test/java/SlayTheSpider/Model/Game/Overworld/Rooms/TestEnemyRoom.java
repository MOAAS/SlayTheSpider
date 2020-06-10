package SlayTheSpider.Model.Game.Overworld.Rooms;

import SlayTheSpider.Model.Sprites.NullSprite;
import SlayTheSpider.Model.Sprites.Sprite;
import SlayTheSpider.Model.Game.Fight;
import SlayTheSpider.Model.Game.FightRewards.RewardList;
import SlayTheSpider.Model.Position.MapArea;
import SlayTheSpider.Model.Game.Overworld.OverWorldElements.OverworldEnemy;
import SlayTheSpider.Model.Game.Overworld.OverWorldElements.OverworldWall;
import SlayTheSpider.Model.Game.Overworld.OverWorldElements.Tile;
import SlayTheSpider.Model.Game.Overworld.Rooms.RoomStatus.EnemyRoomStatus;
import SlayTheSpider.Model.Position.MapAreaRandomizer;
import SlayTheSpider.Model.Position.Position;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.*;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class TestEnemyRoom {

    @Test
    public void TestDungeonStructure() {
        EnemyRoom enemyRoomTest = new EnemyRoom(new MapAreaRandomizer(1, 1, 2, 2), Mockito.mock(Sprite.class));
        Position p1 = new Position(1, 1);
        Position p2 = new Position(1, 2);

        //canHeroMove
        assertEquals(enemyRoomTest.containsPos(p1), true);
        assertEquals(enemyRoomTest.containsPos(p2), false);

        //updateVisibility/getTiles
        List<Tile> tilesTest = enemyRoomTest.getTiles();
        Tile tileMock = Mockito.mock(Tile.class);
        tilesTest.add(tileMock);
        enemyRoomTest.updateVisibility(p1);
        Mockito.verify(tileMock, times(1)).updateVisibility(p1);

    }

    @Test
    public void TestRoom() {
        EnemyRoom enemyRoomTest = new EnemyRoom(new MapAreaRandomizer(1, 1, 2, 2), Mockito.mock(Sprite.class));
        EnemyRoom enemyRoomTest2 = new EnemyRoom(new MapAreaRandomizer(2, 2, 3, 3),Mockito.mock(Sprite.class));

        //intersects
        assertEquals(enemyRoomTest.intersects(enemyRoomTest, 0), true);
        assertEquals(enemyRoomTest.intersects(enemyRoomTest, 1), true);
        //hmmmmm

        //getMapArea
        MapArea mapArea = new MapArea(new Position(1, 1), 1, 1);
        MapArea mapArea2 = new MapArea(new Position(1, 1), 2, 1);
        MapArea mapArea3 = new MapArea(new Position(2, 1), 1, 1);
        assertEquals(mapArea, enemyRoomTest.getMapArea());
        assertNotEquals(mapArea2, enemyRoomTest.getMapArea());
        assertNotEquals(mapArea3, enemyRoomTest.getMapArea());

        //containsPos
        Position p1 = new Position(1, 1);
        Position p2 = new Position(2, 1);
        assertEquals(enemyRoomTest.containsPos(p1), true);
        assertEquals(enemyRoomTest.containsPos(p2), false);
    }

    @Test
    public void TestEnemyRoomEnemy() {
        EnemyRoom enemyRoomTest = new EnemyRoom(new MapAreaRandomizer(2, 2, 3, 3), Mockito.mock(Sprite.class));
        Position position1 = new Position(1, 1);
        Position position2 = new Position(2, 1);

        assertTrue(enemyRoomTest.noEnemies());
        assertFalse(enemyRoomTest.doesEnemyCollide(position1));
        assertFalse(enemyRoomTest.doesEnemyCollide(position2));
        assertNull(enemyRoomTest.checkEnemyCollision(position1));
        assertNull(enemyRoomTest.checkEnemyCollision(position2));

        Fight fightMock = new Fight(new NullSprite(), new RewardList(3));
        List<Fight> fightList = new ArrayList<>();
        fightList.add(fightMock);

        enemyRoomTest.insertEnemies(fightList);

        assertFalse(enemyRoomTest.noEnemies());

        List<OverworldEnemy> enemies = enemyRoomTest.getEnemies();
        List<OverworldEnemy> enemiesCopy = new ArrayList<>(enemies);
        for (OverworldEnemy enemy : enemiesCopy) {
            assertTrue(enemyRoomTest.doesEnemyCollide(enemy.getPosition()));
            assertEquals(enemyRoomTest.checkEnemyCollision(enemy.getPosition()), enemy);
        }
    }

    @Test
    public void TestEnemyRoomWalls() {
        EnemyRoom enemyRoomTest = new EnemyRoom(new MapAreaRandomizer(1, 1, 2, 2), Mockito.mock(Sprite.class));
        List<OverworldWall> wallsTest = new ArrayList<>();

        assertEquals(enemyRoomTest.getWalls(), wallsTest);

        enemyRoomTest.buildWalls();

        wallsTest.add(new OverworldWall(new Position(0, 0), Mockito.mock(Sprite.class)));
        wallsTest.add(new OverworldWall(new Position(0, 2), Mockito.mock(Sprite.class)));
        wallsTest.add(new OverworldWall(new Position(1, 0), Mockito.mock(Sprite.class)));
        wallsTest.add(new OverworldWall(new Position(1, 2), Mockito.mock(Sprite.class)));

        assertNotEquals(enemyRoomTest.getWalls(), wallsTest);

        wallsTest.add(new OverworldWall(new Position(2, 0), Mockito.mock(Sprite.class)));
        wallsTest.add(new OverworldWall(new Position(2, 2), Mockito.mock(Sprite.class)));
        wallsTest.add(new OverworldWall(new Position(0, 1), Mockito.mock(Sprite.class)));
        wallsTest.add(new OverworldWall(new Position(2, 1), Mockito.mock(Sprite.class)));


        assertEquals(enemyRoomTest.getWalls().size(), wallsTest.size());
        assertEquals(enemyRoomTest.getObstacles().size(), wallsTest.size());

        for (int i = 0; i < enemyRoomTest.getWalls().size(); i++) {
            assertEquals(enemyRoomTest.getWalls().get(i).getPosition(), wallsTest.get(i).getPosition());
        }

        Position p1 = new Position(0, 0);
        Position p2 = new Position(1, 1);
        assertTrue(enemyRoomTest.wallCollision(p1));
        assertFalse(enemyRoomTest.wallCollision(p2));

        enemyRoomTest.buildWalls();
        enemyRoomTest.buildWalls();

        assertEquals(enemyRoomTest.getWalls().size(), wallsTest.size());
        assertEquals(enemyRoomTest.getObstacles().size(), wallsTest.size());

        for (int i = 0; i < enemyRoomTest.getWalls().size(); i++) {
            assertEquals(enemyRoomTest.getWalls().get(i).getPosition(), wallsTest.get(i).getPosition());
        }


        enemyRoomTest.clearWalls();
        wallsTest.clear();

        assertEquals(enemyRoomTest.getWalls().size(), wallsTest.size());
        for (int i = 0; i < enemyRoomTest.getWalls().size(); i++) {
            assertEquals(enemyRoomTest.getWalls().get(i).getPosition(), wallsTest.get(i).getPosition());
        }

        assertFalse(enemyRoomTest.wallCollision(p1));
        assertFalse(enemyRoomTest.wallCollision(p2));

    }

    @Test
    public void TestEnemyRoomStatus() {
        EnemyRoom enemyRoomTest = new EnemyRoom(new MapAreaRandomizer(1, 1, 2, 2), Mockito.mock(Sprite.class));
        EnemyRoomStatus statusMock = Mockito.mock(EnemyRoomStatus.class);
        Position p1 = new Position(1, 1);
        enemyRoomTest.setStatus(statusMock);
        enemyRoomTest.updateStatus(p1);

        Mockito.verify(statusMock, times(1)).updateRoomStatus(enemyRoomTest, p1);
    }

    @Test
    public void TestEnemyRoomVisibility() {
        EnemyRoom enemyRoomTest = new EnemyRoom(new MapAreaRandomizer(1, 1, 2, 2), Mockito.mock(Sprite.class));
        OverworldEnemy overworldEnemyMock = Mockito.mock(OverworldEnemy.class);
        OverworldWall overworldWallMock = Mockito.mock(OverworldWall.class);
        Tile tileMock = Mockito.mock(Tile.class);

        List<OverworldEnemy> enemiesTest = enemyRoomTest.getEnemies();
        enemiesTest.add(overworldEnemyMock);
        List<OverworldWall> wallsTest = enemyRoomTest.getWalls();
        wallsTest.add(overworldWallMock);
        List<Tile> tilesTest = enemyRoomTest.getTiles();
        tilesTest.add(tileMock);

        Position p1 = new Position(1, 1);

        enemyRoomTest.updateVisibility(p1);

        Mockito.verify(overworldEnemyMock, times(1)).updateVisibility(p1);
        Mockito.verify(overworldWallMock, times(1)).updateVisibility(p1);
        Mockito.verify(tileMock, times(1)).updateVisibility(p1);
    }

    @Test
    public void TestEnemyRoomGetColor() {
        EnemyRoom enemyRoomTest = new EnemyRoom(new MapAreaRandomizer(1, 1, 2, 2), Mockito.mock(Sprite.class));
        assertEquals(enemyRoomTest.getColor(), "333333");
    }

    @Test
    public void TestFits() {
        EnemyRoom enemyRoomTest = new EnemyRoom(new MapAreaRandomizer(1, 1, 2, 2), Mockito.mock(Sprite.class));

        OverworldEnemy enemyMock1 = Mockito.mock(OverworldEnemy.class);
        OverworldEnemy enemyMock2 = Mockito.mock(OverworldEnemy.class);
        OverworldEnemy enemyMock3 = Mockito.mock(OverworldEnemy.class);
        OverworldEnemy enemyMock4 = Mockito.mock(OverworldEnemy.class);
        Position positionMock1 = Mockito.mock(Position.class);
        Position positionMock2 = Mockito.mock(Position.class);
        Position positionMock3 = Mockito.mock(Position.class);
        Position positionMock4 = Mockito.mock(Position.class);

        List<OverworldEnemy> enemyMockList = new ArrayList<>();
        enemyMockList.add(enemyMock1);
        enemyMockList.add(enemyMock2);
        enemyMockList.add(enemyMock3);
        enemyRoomTest.enemies = enemyMockList;

        when(enemyMock1.getPosition()).thenReturn(positionMock1);
        when(enemyMock2.getPosition()).thenReturn(positionMock2);
        when(enemyMock3.getPosition()).thenReturn(positionMock3);
        when(enemyMock4.getPosition()).thenReturn(positionMock4);

        when(positionMock1.distance(positionMock4)).thenReturn(Double.valueOf(3));
        when(positionMock2.distance(positionMock4)).thenReturn(Double.valueOf(4));
        when(positionMock3.distance(positionMock4)).thenReturn(Double.valueOf(5));
        assertEquals(enemyRoomTest.enemyFits(enemyMock4), false);

        when(positionMock1.distance(positionMock4)).thenReturn(Double.valueOf(2));
        when(positionMock2.distance(positionMock4)).thenReturn(Double.valueOf(4));
        when(positionMock3.distance(positionMock4)).thenReturn(Double.valueOf(1));
        assertEquals(enemyRoomTest.enemyFits(enemyMock4), false);

        when(positionMock1.distance(positionMock4)).thenReturn(Double.valueOf(8));
        when(positionMock2.distance(positionMock4)).thenReturn(Double.valueOf(4));
        when(positionMock3.distance(positionMock4)).thenReturn(Double.valueOf(5));
        assertEquals(enemyRoomTest.enemyFits(enemyMock4), true);


    }

}