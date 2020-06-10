package SlayTheSpider.Model.Game;

import SlayTheSpider.Model.Position.Position;
import SlayTheSpider.Model.Position.Position2D;

public class OverworldPlayer {
    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    };

    private Direction dir;
    private Position2D mapPosition;

    public OverworldPlayer() {
        this.mapPosition = new Position(0, 0);
        this.dir = Direction.DOWN;
    }

    public void setPosition(Position2D pos) {
        this.mapPosition = pos;
    }

    public Position2D getPosition() {
        return mapPosition;
    }

    public Direction getDirection() {
        return dir;
    }

    public void moveLeft() {
        mapPosition.setLeft(1);
        this.dir = Direction.LEFT;
    }

    public void moveRight() {
        mapPosition.setRight(1);
        this.dir = Direction.RIGHT;
    }

    public void moveUp() {
        mapPosition.setUp(1);
        this.dir = Direction.UP;
    }

    public void moveDown() {
        mapPosition.setDown(1);
        this.dir = Direction.DOWN;
    }


}
