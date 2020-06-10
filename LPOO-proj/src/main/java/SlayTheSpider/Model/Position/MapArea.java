package SlayTheSpider.Model.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MapArea {
    private int width;
    private int height;
    private Position2D position;
    private Position2D center;

    public MapArea(Position2D position, int width, int height) {
        this.position = position;
        this.width = Math.max(width, 0);
        this.height = Math.max(height, 0);
        this.center = position.right(width / 2).down(height / 2);
    }

    public MapArea(Position2D center, int range) {
        this.position = center.up(range - 1).left(range - 1);
        this.width = range * 2 - 1;
        this.height = range * 2 - 1;
        this.center = position.right(width / 2).down(height / 2);
    }

    public boolean intersects(MapArea mapArea, int precision) {
        return ((this.getX1() - precision) <= mapArea.getX2() && (this.getX2() + precision) >= mapArea.getX1() && (this.getY1() - precision) <= mapArea.getY2() && (this.getY2() + precision) >= mapArea.getY1());
    }

    public Position2D getPosition() {
        return position;
    }

    public Position2D getCenter() {
        return center;
    }

    public int getX1() {
        return position.getX();
    }

    public int getX2() {
        return position.getX() + width - 1;
    }

    public int getY1() {
        return position.getY();
    }

    public int getY2() {
        return position.getY() + height - 1;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Position2D getRandomPos() {
        int x = ThreadLocalRandom.current().nextInt(getX1(), getX2() + 1);
        int y = ThreadLocalRandom.current().nextInt(getY1(), getY2() + 1);
        return new Position(x, y);
    }

    public List<Position2D> getPositionList() {
        List<Position2D> positions = new ArrayList<>();
        for (int x = position.getX(); x < this.position.getX() + this.width; x++) {
            for (int y = position.getY(); y < this.position.getY() + this.height; y++) {
                positions.add(new Position(x, y));
            }
        }
        return positions;
    }

    public MapArea shrink(int amount) {
        amount = Math.min(amount, width / 2);
        amount = Math.min(amount, height / 2);
        return new MapArea(position.right(amount).down(amount), this.width - 2 * amount, this.height - 2 * amount);
    }

    public boolean containsPos(Position2D p) {
        return getX1() <= p.getX() &&  getX2() >= p.getX() && getY1() <= p.getY() && getY2() >= p.getY();
    }

    @Override
    public boolean equals(Object o){
        if(o == this)
            return true;
        if(!(o instanceof MapArea))
            return false;
        MapArea mp = (MapArea) o;
        return this.position.equals(mp.position) && this.height==mp.height && this.width == mp.width;
    }
}
