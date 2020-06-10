package SlayTheSpider.Model.Game.Overworld.OverWorldElements;

import SlayTheSpider.Model.Position.Position2D;

public class Tile extends OverworldElement {
    protected String color;

    public Tile(Position2D position, String color){
        super(position, 1);
        this.color = color;
    }

    public String getColor(){
        return color;
    }
}
