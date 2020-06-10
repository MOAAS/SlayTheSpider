package SlayTheSpider.Model.Game.Overworld;

import SlayTheSpider.Model.Sprites.NullSprite;
import SlayTheSpider.Model.Sprites.Sprite;
import SlayTheSpider.Model.Game.Overworld.Rooms.*;
import SlayTheSpider.Model.Position.MapAreaRandomizer;
import SlayTheSpider.Model.Position.Position2D;

import java.util.List;

public class FloorGenerator {
    private static final int MIN_ROOM_SIZE = 6;
    private static final int MAX_ROOM_SIZE = 10;

    private static final int MIN_BOSS_ROOM_SIZE = 10;
    private static final int MAX_BOSS_ROOM_SIZE = 15;

    private static final int MIN_DIST_BETWEEN_ROOMS = 4;

    private final int width;
    private final int height;

    private MapAreaRandomizer roomRandomizer;
    private MapAreaRandomizer bossRoomRandomizer;

    Sprite wallSprite = new NullSprite();

    public FloorGenerator(int floorWidth, int floorHeight){
        this.width = floorWidth;
        this.height = floorHeight;
        this.roomRandomizer = new MapAreaRandomizer(MIN_ROOM_SIZE, MAX_ROOM_SIZE, width, height);
        this.bossRoomRandomizer = new MapAreaRandomizer(MIN_BOSS_ROOM_SIZE, MAX_BOSS_ROOM_SIZE, width, height);
    }

    public FloorStructureList generate(int numRooms) {
        FloorStructureList structures = new FloorStructureList(width, height);
        this.setSpawnRoom(structures);
        this.setBossRoom(structures);
        this.makeNormalRooms(numRooms, structures);
        this.makeCorridors(structures);
        structures.reverse();
        return structures;
    }

    public void setSpawnRoom(FloorStructureList structures) {
        SpawnRoom spawnRoom = new SpawnRoom(this.roomRandomizer);
        while (!structures.doesRoomFit(spawnRoom, MIN_DIST_BETWEEN_ROOMS)) {
            spawnRoom = new SpawnRoom(this.roomRandomizer);
        }
        structures.setSpawnRoom(spawnRoom);
    }

    public void setBossRoom(FloorStructureList structures) {
        BossRoom bossRoom = new BossRoom(this.bossRoomRandomizer);
        while (!structures.doesRoomFit(bossRoom, MIN_DIST_BETWEEN_ROOMS)) {
            bossRoom = new BossRoom(this.bossRoomRandomizer);
        }
        structures.setBossRoom(bossRoom);
    }

    public void setWallSprite(Sprite wallSprite) {
        this.wallSprite = wallSprite;
    }

    public void makeNormalRooms(int numRooms, FloorStructureList structures){
        for(int attempts = 0; structures.getRooms().size() < numRooms && attempts < 200 * numRooms; attempts++){
            EnemyRoom newRoom = new EnemyRoom(this.roomRandomizer, this.wallSprite);
            if (structures.doesRoomFit(newRoom, MIN_DIST_BETWEEN_ROOMS))
                structures.addEnemyRoom(newRoom);
        }
    }

    public void makeCorridors(FloorStructureList structures){
        structures.shuffleRooms();
        List<Room> rooms = structures.getRooms();
        for(int i = 0; i < rooms.size()-1 ; i++) {
            Position2D r1center = rooms.get(i).getMapArea().getCenter();
            Position2D r2center = rooms.get(i+1).getMapArea().getCenter();

            if(Math.random() > 0.5) {
                structures.addCorridor(new VerCorridor(r1center.getY(), r2center.getY(), r1center.getX(), rooms));
                structures.addCorridor(new HorCorridor(r1center.getX(), r2center.getX(), r2center.getY(), rooms));
            }
            else {
                structures.addCorridor(new HorCorridor(r1center.getX(), r2center.getX(), r1center.getY(), rooms));
                structures.addCorridor(new VerCorridor(r1center.getY(), r2center.getY(), r2center.getX(), rooms));
            }
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
