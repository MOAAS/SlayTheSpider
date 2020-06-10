package SlayTheSpider.Model.Game.Overworld;

import SlayTheSpider.Model.Game.Overworld.OverWorldElements.Tile;
import SlayTheSpider.Model.Game.Overworld.Rooms.*;
import SlayTheSpider.Model.Position.Position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FloorStructureList {
    private final static int BORDER_SIZE = 8;
    private final static String UNWALKABLE_COLOR = "0000ff";

    private List<Tile> unwalkableTiles = new ArrayList<>();
    private List<FloorStructure> structures = new ArrayList<>();
    private List<Room> rooms = new ArrayList<>();
    private List<EnemyRoom> enemyRooms = new ArrayList<>();
    private SpawnRoom spawnRoom;
    private BossRoom bossRoom;

    public FloorStructureList(int floorWidth, int floorHeight) {
        for (int i = 0; i < floorWidth + BORDER_SIZE; i++) {
            for (int j = 0; j < floorHeight + BORDER_SIZE; j++) {
                unwalkableTiles.add(new Tile(new Position(i, j), UNWALKABLE_COLOR));
            }
        }
    }

    public void addEnemyRoom(EnemyRoom newRoom) {
        enemyRooms.add(newRoom);
        rooms.add(newRoom);
        structures.add(newRoom);
    }

    public void setSpawnRoom(SpawnRoom newRoom) {
        structures.remove(spawnRoom);
        structures.add(newRoom);
        rooms.remove(spawnRoom);
        rooms.add(newRoom);
        spawnRoom = newRoom;
    }

    public void setBossRoom(BossRoom newRoom) {
        structures.remove(bossRoom);
        structures.add(newRoom);
        rooms.remove(bossRoom);
        rooms.add(newRoom);
        bossRoom = newRoom;
    }

    public void addCorridor(Corridor corridor) {
        structures.add(corridor);
    }

    public List<FloorStructure> getList() {
        return structures;
    }

    public List<Tile> getUnwalkableTiles() {
        return unwalkableTiles;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public List<EnemyRoom> getEnemyRooms() {
        return enemyRooms;
    }

    public BossRoom getBossRoom() {
        return bossRoom;
    }

    public SpawnRoom getSpawnRoom() {
        return spawnRoom;
    }

    public void shuffleRooms() {
        Collections.shuffle(rooms);
    }

    public boolean doesRoomFit(Room room, int precision) {
        for (Room r : rooms) {
            if (r.intersects(room,precision))
                return false;
        }
        return true;
    }

    public void reverse() {
        Collections.reverse(structures);
    }

    public void clear() {
        structures.clear();
        enemyRooms.clear();
        rooms.clear();
    }
}
