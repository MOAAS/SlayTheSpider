package SlayTheSpider.Model.Game.Overworld.Rooms;

import SlayTheSpider.Model.Game.Overworld.OverWorldElements.OverworldObstacle;
import SlayTheSpider.Model.Position.MapAreaRandomizer;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestRoom {
    @Test
    public void TestRoom() {
        Room room = new Room(new MapAreaRandomizer(10, 10, 100, 100)) {
            public String getColor() { return ""; }
            public List<OverworldObstacle> getObstacles() { return null;}
        };

        assertEquals(room.getTiles().size(), 100);

        room.makeTiles();

        assertEquals(room.getTiles().size(), 100);

    }
}
