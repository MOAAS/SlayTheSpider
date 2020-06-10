package SlayTheSpider.Model.Game.Overworld.Rooms;

import SlayTheSpider.Model.Game.Overworld.OverWorldElements.OverworldObstacle;
import SlayTheSpider.Model.Position.MapAreaRandomizer;

import java.util.ArrayList;
import java.util.List;

public class SpawnRoom extends Room {

    public SpawnRoom(MapAreaRandomizer randomizer){
        super(randomizer);
    }

    @Override
    public List<OverworldObstacle> getObstacles() {
        return new ArrayList<>();
    }

    @Override
    public String getColor() {
        return "7ba9f5";
    }
}
