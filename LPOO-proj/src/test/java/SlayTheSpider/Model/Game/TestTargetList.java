package SlayTheSpider.Model.Game;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestTargetList {
    private List<Enemy> enemyList = new ArrayList<>();
    private Player player;

    @Before
    public void Setup() {
        player = Mockito.mock(Player.class);

        enemyList.add(Mockito.mock(Enemy.class));
        enemyList.add(Mockito.mock(Enemy.class));
        enemyList.add(Mockito.mock(Enemy.class));
        enemyList.add(Mockito.mock(Enemy.class));
        enemyList.add(Mockito.mock(Enemy.class));

        Mockito.when(player.isDead()).thenReturn(false);
        Mockito.when(enemyList.get(0).isDead()).thenReturn(false);
        Mockito.when(enemyList.get(1).isDead()).thenReturn(true);
        Mockito.when(enemyList.get(2).isDead()).thenReturn(true);
        Mockito.when(enemyList.get(3).isDead()).thenReturn(true);
        Mockito.when(enemyList.get(4).isDead()).thenReturn(false);
    }

    @Test
    public void TestCons() {
        TargetList list = new TargetList(enemyList, player);

        assertEquals(list.getCharacters().size(), 6);
        assertEquals(list.getEnemies().size(), 5);

        assertSame(list.getEnemies(), enemyList);
        assertSame(list.getPlayer(), player);
        assertSame(list.getTarget(), player);
    }

    @Test
    public void TestOthers() {
        TargetList list = new TargetList(enemyList, player);

        list.selectRight();
        assertSame(list.getTarget(), enemyList.get(0));

        list.selectPlayer();
        assertSame(list.getTarget(), player);

        list.selectLeft();
        assertSame(list.getTarget(), enemyList.get(4));

        list.clearDeadCharacters();

        assertEquals(list.getCharacters().size(), 3);
        assertEquals(list.getEnemies().size(), 2);

        assertNotEquals(list.getCharacters().indexOf(player), -1);

    }

}
