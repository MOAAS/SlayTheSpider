package SlayTheSpider.Model.Game.Overworld.Rooms;

import SlayTheSpider.Model.Game.Overworld.OverWorldElements.OverworldObstacle;
import SlayTheSpider.Model.Position.Position;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class TestCorridor {

    @Test
    public void TestMakeTiles(){
        EnemyRoom roomMock = Mockito.mock(EnemyRoom.class);
        List<Room> rooms = new ArrayList<>();
        rooms.add(roomMock);
        HorCorridor horCorridorTest = new HorCorridor(1,2,2,rooms);

        when(roomMock.containsPos(any(Position.class))).thenReturn(true);
        Mockito.verify(roomMock,times(2)).containsPos(any(Position.class));

        assertEquals(horCorridorTest.getTiles().size(),2);

        HorCorridor horCorridorTest2 = new HorCorridor(1,2,2,rooms);
        when(roomMock.containsPos(any(Position.class))).thenReturn(false);

        assertEquals(horCorridorTest2.getTiles().size(),0);

    }

    @Test
    public void TestGetObstacles(){
        EnemyRoom roomMock = Mockito.mock(EnemyRoom.class);
        List<Room> rooms = new ArrayList<>();
        rooms.add(roomMock);
        VerCorridor verCorridorTest = new VerCorridor(1,2,2,rooms);
        when(roomMock.containsPos(any(Position.class))).thenReturn(true);

        List<OverworldObstacle> overworldObstaclesList = new ArrayList<>();
        assertEquals(verCorridorTest.getObstacles(), overworldObstaclesList);

        assertEquals(verCorridorTest.getTiles().size(),2);

        VerCorridor verCorridorTest2 = new VerCorridor(1,2,2,rooms);
        when(roomMock.containsPos(any(Position.class))).thenReturn(false);

        assertEquals(verCorridorTest2.getTiles().size(),0);

    }

}
