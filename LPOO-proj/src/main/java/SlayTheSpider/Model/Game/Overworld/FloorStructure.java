package SlayTheSpider.Model.Game.Overworld;

import SlayTheSpider.Model.Game.Overworld.OverWorldElements.OverworldObstacle;
import SlayTheSpider.Model.Game.Overworld.OverWorldElements.Tile;
import SlayTheSpider.Model.Position.MapArea;
import SlayTheSpider.Model.Position.Position2D;

import java.util.ArrayList;
import java.util.List;

public abstract class FloorStructure {
    protected MapArea mapArea;
    protected List<Tile> tiles = new ArrayList<>();

    public void updateVisibility(Position2D p){
        for(Tile tile : tiles){
            tile.updateVisibility(p);
        }
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public MapArea getMapArea() {
        return mapArea;
    }

    public boolean intersects(FloorStructure structure, int precision) {
        return this.mapArea.intersects(structure.mapArea, precision);
    }

    public abstract List<OverworldObstacle> getObstacles();

    protected abstract void makeTiles();

    public boolean containsPos(Position2D position) {
        return this.mapArea.containsPos(position);
    }
}
