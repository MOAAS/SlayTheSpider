package SlayTheSpider.Model.Game.Overworld.Rooms;

import SlayTheSpider.Model.Game.Overworld.FloorStructure;
import SlayTheSpider.Model.Game.Overworld.OverWorldElements.OverworldObstacle;
import SlayTheSpider.Model.Game.Overworld.OverWorldElements.Tile;
import SlayTheSpider.Model.Position.Position2D;

import java.util.ArrayList;
import java.util.List;

public abstract class Corridor extends FloorStructure {
    private final String TILE_COLOR = "222222";

    protected List<Room> rooms;

    protected void makeTiles() {
        this.tiles = new ArrayList<>();
        List<Position2D> positions = mapArea.getPositionList();
        for (Position2D position : positions) {
            if (hasRoomAtPos(position))
                continue;
            tiles.add(new Tile(position, TILE_COLOR));
        }
    }

    private boolean hasRoomAtPos(Position2D position) {
        for (Room room : rooms) {
            if (room.containsPos(position))
                return true;
        }
        return false;
    }
    @Override
    public List<OverworldObstacle> getObstacles() {
        return new ArrayList<>();
    }
}
