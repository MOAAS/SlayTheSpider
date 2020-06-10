package SlayTheSpider.Model.Game.Overworld.Rooms;

import SlayTheSpider.Model.Game.Overworld.OverWorldElements.OverworldObstacle;
import SlayTheSpider.Model.Position.MapAreaRandomizer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class TestSpawnRoom {

    @Test
    public void TestObstacle(){
        SpawnRoom spawnRoomTest = new SpawnRoom(new MapAreaRandomizer(1,1,2,2));
        List<OverworldObstacle> obstaclesTest = new ArrayList<>();
        assertEquals(spawnRoomTest.getObstacles(),obstaclesTest);
    }

    @Test
    public void TestColor(){
        SpawnRoom spawnRoomTest = new SpawnRoom(new MapAreaRandomizer(1,1,2,2));
        assertEquals(spawnRoomTest.getColor(),"7ba9f5");
    }
}
