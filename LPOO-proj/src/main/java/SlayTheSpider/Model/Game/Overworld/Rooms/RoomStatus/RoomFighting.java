package SlayTheSpider.Model.Game.Overworld.Rooms.RoomStatus;

import SlayTheSpider.Model.Game.Overworld.Rooms.EnemyRoom;
import SlayTheSpider.Model.Position.Position2D;

public class RoomFighting implements EnemyRoomStatus {
    @Override
    public void updateRoomStatus(EnemyRoom enemyRoom, Position2D playerPos) {
        if(enemyRoom.noEnemies()) {
            enemyRoom.clearWalls();
            enemyRoom.setStatus(new RoomVisited());
        }
    }
}
