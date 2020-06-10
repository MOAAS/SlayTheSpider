package SlayTheSpider.Model.Position;

public interface Position2D {
    int getX();

    void setX(int x);

    int getY();

    void setY(int y);

    Position2D up(int amount);

    Position2D down(int amount);

    Position2D left(int amount);

    Position2D right(int amount);

    Position2D mult(int amountX, int amountY);

    void setUp(int amount);

    void setDown(int amount);

    void setLeft(int amount);

    void setRight(int amount);

    @Override
    boolean equals(Object o);

    @Override
    String toString();

    double distance(Position2D p);

    Position2D copy();
}
