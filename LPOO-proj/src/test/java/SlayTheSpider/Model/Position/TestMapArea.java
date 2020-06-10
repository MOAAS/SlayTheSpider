package SlayTheSpider.Model.Position;

import org.junit.Assert;
import org.junit.Test;


import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotEquals;


public class TestMapArea {

    @Test
    public void TestIntersects(){
        Position p1 = new Position(1,1);
        Position p2 = new Position(2,2);
        Position p3 = new Position(3,1);

        MapArea maparea1 = new MapArea(p1,1,1);
        MapArea maparea2 = new MapArea(p2,2,2);
        MapArea maparea3 = new MapArea(p1,5,5);
        MapArea maparea4 = new MapArea(p3,2,2);

        assertEquals(maparea1.intersects(maparea2,0),false);
        assertEquals(maparea1.intersects(maparea2,1),true);
        assertEquals(maparea3.intersects(maparea1,0),true);
        assertEquals(maparea1.intersects(maparea4,0),false);
        assertEquals(maparea1.intersects(maparea4,2),true);

    }

    @Test
    public void TestContainsPos(){
        Position p1 = new Position(1,1);
        Position p2 = new Position(2,2);
        Position p3 = new Position(3,2);

        MapArea maparea1 = new MapArea(p1,1,1);
        MapArea maparea2 = new MapArea(p2,2,2);

        assertEquals(maparea1.containsPos(p1),true);
        assertEquals(maparea1.containsPos(p2),false);
        assertEquals(maparea1.containsPos(p3),false);
        assertEquals(maparea2.containsPos(p1),false);
        assertEquals(maparea2.containsPos(p3),true);

    }

    @Test
    public void TestGets(){
        Position p1 = new Position(1,1);
        Position p2 = new Position(2,2);
        Position p3 = new Position(2,4);
        MapArea maparea1 = new MapArea(p1,3,3);
        MapArea maparea2 = new MapArea(p2,1,5);

        assertEquals(maparea1.getPosition(),p1);
        assertEquals(maparea1.getCenter(),p2);
        assertEquals(maparea1.getX1(),1);
        assertEquals(maparea1.getX2(),3);
        assertEquals(maparea1.getY1(),1);
        assertEquals(maparea1.getY2(),3);

        assertEquals(maparea2.getPosition(),p2);
        assertEquals(maparea2.getCenter(),p3);
        assertEquals(maparea2.getX1(),2);
        assertEquals(maparea2.getX2(),2);
        assertEquals(maparea2.getY1(),2);
        assertEquals(maparea2.getY2(),6);

    }

    @Test
    public void TestShrink(){
        Position p1 = new Position(5,2);
        Position p2 = new Position(7,4);
        Position p3 = new Position(55,52);
        MapArea maparea1 = new MapArea(p1,100,200);
        MapArea maparea2 = new MapArea(p2,96,196);
        MapArea maparea3 = new MapArea(p3,0,100);
        assertEquals(maparea1.shrink(2), maparea2);
        assertEquals(maparea1.shrink(50), maparea3);
        assertEquals(maparea1.shrink(150), maparea3);

        maparea1 = new MapArea(p1,200,100);
        maparea2 = new MapArea(p2,196,96);
        maparea3 = new MapArea(p3,100,0);
        assertEquals(maparea1.shrink(2), maparea2);
        assertEquals(maparea1.shrink(50), maparea3);
        assertEquals(maparea1.shrink(150), maparea3);
    }

    @Test
    public void TestEquals() {
        Position p1 = new Position(1,1);
        Position p2 = new Position(3,3);
        MapArea maparea1 = new MapArea(p1,6,6);
        MapArea maparea2 = new MapArea(p2,2,2);
        MapArea maparea3 = new MapArea(p2,2,2);

        assertEquals(maparea1, maparea1);
        assertEquals(maparea2, maparea3);

        assertNotEquals(maparea1, maparea2);
        assertNotEquals(maparea1, maparea3);
        assertNotEquals(maparea1, "Lol");
        assertNotEquals(maparea1, 23);

    }

    @Test
    public void TestPositions() {
        Position p1 = new Position(2,4);
        MapArea maparea = new MapArea(p1,7,2);
        List<Position2D> positionList = maparea.getPositionList();

        assertEquals(positionList.size(), 14);
        assertEquals(positionList.get(0), new Position(2, 4));
        assertEquals(positionList.get(1), new Position(2, 5));
        assertEquals(positionList.get(2), new Position(3, 4));
        assertEquals(positionList.get(3), new Position(3, 5));
        assertEquals(positionList.get(4), new Position(4, 4));
        assertEquals(positionList.get(5), new Position(4, 5));
        assertEquals(positionList.get(6), new Position(5, 4));
        assertEquals(positionList.get(7), new Position(5, 5));
        assertEquals(positionList.get(8), new Position(6, 4));
        assertEquals(positionList.get(9), new Position(6, 5));
        assertEquals(positionList.get(10), new Position(7, 4));
        assertEquals(positionList.get(11), new Position(7, 5));
        assertEquals(positionList.get(12), new Position(8, 4));
        assertEquals(positionList.get(13), new Position(8, 5));
    }

    @Test
    public void TestCenterRange() {
        MapArea maparea = new MapArea(new Position(5, 5), 1);

        assertEquals(maparea.getPosition(), new Position(5,5));
        assertEquals(maparea.getWidth(), 1);
        assertEquals(maparea.getHeight(), 1);
        assertEquals(maparea.getCenter(), new Position(5, 5));

        maparea = new MapArea(new Position(4, 4), 3);

        assertEquals(maparea.getPosition(), new Position(2,2));
        assertEquals(maparea.getWidth(), 5);
        assertEquals(maparea.getHeight(), 5);
        assertEquals(maparea.getCenter(), new Position(4, 4));

        maparea = new MapArea(new Position(4, 4), 4);

        assertEquals(maparea.getPosition(), new Position(1,1));
        assertEquals(maparea.getWidth(), 7);
        assertEquals(maparea.getHeight(), 7);
        assertEquals(maparea.getCenter(), new Position(4, 4));
    }

    @Test
    public void TestCenters() {
        Position p1 = new Position(0,0);
        MapArea maparea1 = new MapArea(p1,4,4);
        assertEquals(maparea1.getCenter(), new Position(2, 2));

        Position p2 = new Position(2,4);
        MapArea maparea2 = new MapArea(p2,5,5);
        assertEquals(maparea2.getCenter(), new Position(4, 6));
    }

    @Test
    public void TestRandomizer() {
        MapAreaRandomizer randomizer = new MapAreaRandomizer(10, 20, 50, 100);

        MapArea mapArea = randomizer.randomize();

        Assert.assertTrue(mapArea.getWidth() >= 10);
        Assert.assertTrue(mapArea.getHeight() >= 10);

        Assert.assertTrue(mapArea.getWidth() <= 20);
        Assert.assertTrue(mapArea.getHeight() <= 20);

        Assert.assertTrue(mapArea.getPosition().getX() <= 50);
        Assert.assertTrue(mapArea.getPosition().getY() <= 100);

        Assert.assertTrue(mapArea.getPosition().getX() >= 0);
        Assert.assertTrue(mapArea.getPosition().getY() >= 0);
    }
}
