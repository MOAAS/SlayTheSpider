package SlayTheSpider.Model.Game.Overworld.Rooms;

import SlayTheSpider.Model.Game.Overworld.FloorStructure;
import SlayTheSpider.Model.Game.Overworld.OverWorldElements.OverworldObstacle;
import SlayTheSpider.Model.Game.Overworld.OverWorldElements.Tile;
import SlayTheSpider.Model.Position.MapAreaRandomizer;
import SlayTheSpider.Model.Position.Position;

import java.util.List;

public abstract class Room extends FloorStructure {
    public Room(MapAreaRandomizer randomizer) {
        this.mapArea = randomizer.randomize();
        this.makeTiles();
    }

    @Override
    protected void makeTiles() {
        this.tiles.clear();
        for (int i = mapArea.getX1(); i <= mapArea.getX2(); i++) {
            for (int j = mapArea.getY1(); j <= mapArea.getY2(); j++) {
                Tile t = new Tile(new Position(i, j), this.getColor());
                tiles.add(t);
            }
        }
    }

    public abstract String getColor();
    public abstract List<OverworldObstacle> getObstacles();
}
