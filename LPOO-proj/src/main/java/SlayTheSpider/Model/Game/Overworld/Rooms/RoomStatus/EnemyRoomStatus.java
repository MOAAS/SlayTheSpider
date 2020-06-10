package SlayTheSpider.Model.Game.Overworld.Rooms.RoomStatus;

import SlayTheSpider.Model.Game.Overworld.Rooms.EnemyRoom;
import SlayTheSpider.Model.Position.Position2D;

public interface
EnemyRoomStatus {
    void updateRoomStatus(EnemyRoom enemyRoom, Position2D playerPos);
}
