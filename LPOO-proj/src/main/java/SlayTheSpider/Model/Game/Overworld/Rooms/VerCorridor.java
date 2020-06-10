package SlayTheSpider.Model.Game.Overworld.Rooms;

import SlayTheSpider.Model.Position.MapArea;
import SlayTheSpider.Model.Position.Position;

import java.util.List;


public class VerCorridor extends Corridor {

    public VerCorridor(int y1, int y2, int x, List<Room> rooms){
        this.mapArea = new MapArea(new Position(x, Math.min(y1, y2)), 1, Math.abs(y2 - y1) + 1);
        this.rooms = rooms;
        this.makeTiles();
    }

}
