package SlayTheSpider.Model.ListCycler;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestListCycler {
    ArrayListCycler<Integer> cycler;

    @Before
    public void setup() {
        ArrayList<Integer> testList = new ArrayList<>();
        testList.add(1);
        testList.add(2);
        testList.add(3);
        testList.add(4);

        cycler = new ArrayListCycler<>(testList);
    }

    @Test
    public void testListFunctions() {
        assertEquals(cycler.get(0), new Integer(1));
        assertEquals(cycler.get(3), new Integer(4));

        cycler.add(5);
        cycler.add(6);

        assertEquals(cycler.get(3), new Integer(4));
        assertEquals(cycler.get(5), new Integer(6));

        assertEquals(cycler.getList().size(), 6);

        assertEquals(cycler.contains(5), true);
        assertEquals(cycler.contains(7), false);
        cycler.remove(5);
        assertEquals(cycler.contains(5), false);
        assertEquals(cycler.getList().size(), 5);

        cycler.clear();
        assertEquals(cycler.getList().size(), 0);
    }

    @Test
    public void testLeftRight() {
        assertEquals(cycler.getSelected(), new Integer(1));
        cycler.selectRight();
        assertEquals(cycler.getSelected(), new Integer(2));
        cycler.selectRight();
        assertEquals(cycler.getSelected(), new Integer(3));
        cycler.selectRight();
        assertEquals(cycler.getSelected(), new Integer(4));
        cycler.selectRight();
        assertEquals(cycler.getSelected(), new Integer(1));
        cycler.selectRight();
        assertEquals(cycler.getSelected(), new Integer(2));
        cycler.selectRight();
        assertEquals(cycler.getSelected(), new Integer(3));

        cycler.remove(3);
        assertEquals(cycler.getSelected(), new Integer(4));
        cycler.selectLeft();
        assertEquals(cycler.getSelected(), new Integer(2));
        cycler.selectLeft();
        assertEquals(cycler.getSelected(), new Integer(1));
        cycler.selectLeft();
        assertEquals(cycler.getSelected(), new Integer(4));
    }

    @Test
    public void testEmpty() {
        cycler.clear();
        assertNull(cycler.getSelected());
        cycler.selectLeft();
        assertNull(cycler.getSelected());
        cycler.selectRight();
        assertNull(cycler.getSelected());

        cycler.add(1);
        assertEquals(cycler.getSelected(), new Integer(1));
        cycler.remove(1);
        assertNull(cycler.getSelected());
    }

    @Test
    public void TestBoundaries() {
        cycler.clear();
        cycler.add(1);

        assertEquals(cycler.getSelected(), new Integer(1));
        cycler.remove(1);

        cycler.selectLeft();

        cycler.add(2);
        assertEquals(cycler.getSelected(), new Integer(2));

        cycler.clear();
        cycler.add(1);
        cycler.add(2);
        cycler.add(3);
        assertEquals(cycler.getSelected(), new Integer(1));
        cycler.selectRight();
        cycler.selectRight();
        cycler.selectRight();
        assertEquals(cycler.getSelected(), new Integer(1));
        cycler.remove(3);
        cycler.selectRight();
        assertEquals(cycler.getSelected(), new Integer(2));

        cycler.clear();
        cycler.add(1);
        cycler.add(2);
        cycler.add(3);
        cycler.selectRight();
        cycler.selectRight();
        cycler.selectRight();
        cycler.remove(3);
        assertEquals(cycler.getSelected(), new Integer(1));

        cycler.clear();
        cycler.add(1);
        cycler.add(2);
        cycler.add(3);
        cycler.selectRight();
        cycler.selectRight();
        cycler.selectRight();
        cycler.selectRight();
        assertEquals(cycler.getSelected(), new Integer(2));

        cycler.selectRight();
        cycler.remove(3);
        assertEquals(cycler.getSelected(), new Integer(1));
    }

    @Test
    public void TestNullItem() {
        cycler = new ArrayListCycler<Integer>(4);

        assertEquals(cycler.getSelected(), new Integer(4));

    }

    @Test
    public void TestGetRandom() {
        for (int i = 0; i < 100; i++) {
            int rand = cycler.getRandom();
            assertTrue(rand <= 4);
            assertTrue(rand >= 1);
        }

        cycler = new ArrayListCycler<Integer>(new ArrayList<>());

        for (int i = 0; i < 100; i++) {
            assertNull(cycler.getRandom());
        }

        cycler.add(1);

        for (int i = 0; i < 100; i++) {
            int rand = cycler.getRandom();
            assertTrue(rand == 1);
        }



    }


}
