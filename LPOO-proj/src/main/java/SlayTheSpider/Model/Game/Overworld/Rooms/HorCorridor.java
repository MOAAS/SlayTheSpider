package SlayTheSpider.Model.Game.Overworld.Rooms;

import SlayTheSpider.Model.Position.MapArea;
import SlayTheSpider.Model.Position.Position;

import java.util.List;

public class HorCorridor extends Corridor {

    public HorCorridor(int x1, int x2, int y, List<Room> rooms){
        this.mapArea =  new MapArea(new Position(Math.min(x1, x2), y), Math.abs(x2 - x1) + 1, 1);
        this.rooms = rooms;
        this.makeTiles();
    }
}
