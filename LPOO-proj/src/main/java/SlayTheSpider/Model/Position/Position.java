package SlayTheSpider.Model.Position;

public class Position implements Position2D {
    private int x , y ;
    public Position (int x , int y ){
        this.x = x;
        this.y = y;
    }

    public Position (String pos){
        int sep = pos.indexOf(",");

        if (sep == -1) {
            this.x = 0;
            this.y = 0;
        }
        else {
            this.x = Integer.parseInt(pos.substring(0, sep));
            this.y = Integer.parseInt(pos.substring(sep + 1));
        }
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public Position up(int amount) {
        return new Position(x, y - amount);
    }

    @Override
    public Position down(int amount) {
        return new Position(x, y + amount);
    }

    @Override
    public Position left(int amount) {
        return new Position(x - amount, y);
    }

    @Override
    public Position right(int amount) {
        return new Position(x + amount, y);
    }

    @Override
    public Position mult(int amountX, int amountY) {
        return new Position(x * amountX, y * amountY);
    }

    @Override
    public void setUp(int amount) {
        y -= amount;
    }

    @Override
    public void setDown(int amount) {
        y += amount;
    }

    @Override
    public void setLeft(int amount) {
        x -= amount;
    }

    @Override
    public void setRight(int amount) {
        x += amount;
    }

    @Override
    public boolean equals(Object o){
        if(o == this)
            return true;
        if(!(o instanceof Position))
            return false;
        Position p = (Position)o;
        return p.x == this.x && p.y == this.y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    @Override
    public double distance(Position2D p){
        return Math.sqrt((p.getY() - this.y) * (p.getY() - this.y) + (p.getX() - this.x) * (p.getX() - this.x));
    }

    @Override
    public Position2D copy() {
        return new Position(this.x, this.y);
    }

}
