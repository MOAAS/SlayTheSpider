package SlayTheSpider.Model.Game.Overworld.Rooms.RoomStatus;

import SlayTheSpider.Model.Game.Overworld.Rooms.EnemyRoom;
import SlayTheSpider.Model.Position.Position2D;

public class RoomNotVisited implements EnemyRoomStatus {
    @Override
    public void updateRoomStatus(EnemyRoom enemyRoom, Position2D playerPos) {
        if(enemyRoom.containsPos(playerPos)){
            enemyRoom.buildWalls();
            enemyRoom.setStatus(new RoomFighting());
        }
    }
}
