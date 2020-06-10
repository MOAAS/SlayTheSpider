package SlayTheSpider.Model.Game.Overworld.Rooms;

import SlayTheSpider.Model.Game.Overworld.Rooms.RoomStatus.EnemyRoomStatus;
import SlayTheSpider.Model.Game.Overworld.Rooms.RoomStatus.RoomFighting;
import SlayTheSpider.Model.Game.Overworld.Rooms.RoomStatus.RoomNotVisited;
import SlayTheSpider.Model.Game.Overworld.Rooms.RoomStatus.RoomVisited;
import SlayTheSpider.Model.Position.Position;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class TestRoomStatus {
    @Test
    public void TestFighting() {
        EnemyRoom roomMock = Mockito.mock(EnemyRoom.class);
        Position posMock = Mockito.mock(Position.class);

        EnemyRoomStatus statusFighting = new RoomFighting();

        when(roomMock.noEnemies()).thenReturn(false);
        statusFighting.updateRoomStatus(roomMock, posMock);
        verify(roomMock, times(0)).clearWalls();
        verify(roomMock, times(0)).setStatus(any());

        when(roomMock.noEnemies()).thenReturn(true);
        statusFighting.updateRoomStatus(roomMock, posMock);
        verify(roomMock, times(1)).clearWalls();
        verify(roomMock, times(1)).setStatus(any());
    }

    @Test
    public void TestNotVisited() {
        EnemyRoom roomMock = Mockito.mock(EnemyRoom.class);
        Position posMock = Mockito.mock(Position.class);

        EnemyRoomStatus statusNotVisited = new RoomNotVisited();

        when(roomMock.containsPos(posMock)).thenReturn(false);
        statusNotVisited.updateRoomStatus(roomMock, posMock);
        verify(roomMock, times(0)).buildWalls();
        verify(roomMock, times(0)).setStatus(any());

        when(roomMock.containsPos(posMock)).thenReturn(true);
        statusNotVisited.updateRoomStatus(roomMock, posMock);
        verify(roomMock, times(1)).buildWalls();
        verify(roomMock, times(1)).setStatus(any());
    }

    @Test
    public void TestVisited() {
        EnemyRoom roomMock = Mockito.mock(EnemyRoom.class);
        Position posMock = Mockito.mock(Position.class);

        EnemyRoomStatus statusVisited = new RoomVisited();

        statusVisited.updateRoomStatus(roomMock, posMock);
        Mockito.verifyZeroInteractions(roomMock, posMock);
    }
}
