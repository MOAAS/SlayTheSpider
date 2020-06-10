package SlayTheSpider.Model.Position;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestPosition {
    @Test
    public void TestGetSets() {
        Position p1 = new Position(1, 1);
        Position p2 = new Position(-1, -1);

        assertEquals(p1.getX(), 1);
        assertEquals(p1.getY(), 1);
        assertEquals(p2.getX(), -1);
        assertEquals(p2.getY(), -1);

        p1.setX(1);
        p1.setY(2);
        p2.setX(-2);
        p2.setY(-3);

        assertEquals(p1.getX(), 1);
        assertEquals(p1.getY(), 2);
        assertEquals(p2.getX(), -2);
        assertEquals(p2.getY(), -3);
    }

    @Test
    public void TestDirections() {
        Position p = new Position(1, 1);

        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 1);

        p = p.up(12);

        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), -11);

        p = p.down(1);

        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), -10);

        p = p.left(-4);

        assertEquals(p.getX(), 5);
        assertEquals(p.getY(), -10);

        p = p.right(2);

        assertEquals(p.getX(), 7);
        assertEquals(p.getY(), -10);

        p.up(2200).down(3030).left(200).right(-582);

        assertEquals(p.getX(), 7);
        assertEquals(p.getY(), -10);
    }

    @Test
    public void TestEquals() {
        Integer num = 3;

        Position p1 = new Position(1,1);
        Position p2 = new Position(1,1);
        Position p3 = new Position(-1,1);

        assertEquals(p1, p1);
        assertEquals(p1, p2);
        assertNotEquals(p1, p3);
        assertNotEquals(p2, p3);
        assertNotEquals(p1, num);
    }

    @Test
    public void TestDist() {
        Position p1 = new Position(1,1);
        Position p2 = new Position(1,1);
        Position p3 = new Position(-1,1);
        Position p4 = new Position(0,0);

        assertEquals(p1.distance(p1), 0, 0.001);
        assertEquals(p1.distance(p2), 0, 0.001);
        assertEquals(p1.distance(p3), 2, 0.001);
        assertEquals(p1.distance(p4), 1.4142, 0.001);
    }

    @Test
    public void TestSetDirections() {
        Position p = new Position(1, 1);

        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 1);

        p.setUp(12);

        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), -11);

        p.setDown(1);

        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), -10);

        p.setLeft(-4);

        assertEquals(p.getX(), 5);
        assertEquals(p.getY(), -10);

        p.setRight(2);

        assertEquals(p.getX(), 7);
        assertEquals(p.getY(), -10);
    }

    @Test
    public void TestStringConstr() {
        Position p1 = new Position("0,1");
        Position p2 = new Position("90,9087");
        Position p3 = new Position("90kjhl9j087");

        assertEquals(p1, new Position(0, 1));
        assertEquals(p2, new Position(90, 9087));
        assertEquals(p3, new Position(0, 0));
    }

    @Test
    public void TestString() {
        Position p1 = new Position(-1,1);
        Position p2 = new Position(0,0);

        assertEquals(p1.toString(), "(-1,1)");
        assertEquals(p2.toString(), "(0,0)");
    }

    @Test
    public void TestMult() {
        Position p1 = new Position(-1,1);
        Position p2 = new Position(0,0);
        Position p3 = new Position(-3,3);

        assertEquals(p1.mult(3, 3), p3);
        assertEquals(p2.mult(5, 5), p2);
    }

    @Test
    public void TestCopy() {
        Position p1 = new Position(-1,2);
        Position p2 = new Position(7,0);
        Position p3 = new Position(-3,38);

        assertNotSame(p1.copy(), p1);
        assertNotSame(p2.copy(), p1);
        assertNotSame(p3.copy(), p1);

        assertEquals(p1.copy().getX(), -1);
        assertEquals(p1.copy().getY(), 2);
        assertEquals(p2.copy().getX(), 7);
        assertEquals(p2.copy().getY(), 0);
        assertEquals(p3.copy().getX(), -3);
        assertEquals(p3.copy().getY(), 38);
    }
}
