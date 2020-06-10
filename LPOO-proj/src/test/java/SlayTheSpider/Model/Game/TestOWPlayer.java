package SlayTheSpider.Model.Game;

import SlayTheSpider.Model.Position.Position;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestOWPlayer {
    @Test
    public void TestConstructor() {
        OverworldPlayer player = new OverworldPlayer();

        assertEquals(player.getPosition(), new Position(0, 0));
        assertEquals(player.getDirection(), OverworldPlayer.Direction.DOWN);
    }

    @Test
    public void TestMove() {
        OverworldPlayer player = new OverworldPlayer();
        player.setPosition(new Position(20, 30));

        player.moveUp();
        assertEquals(player.getPosition(), new Position(20, 29));
        assertEquals(player.getDirection(), OverworldPlayer.Direction.UP);

        player.moveDown();
        player.moveDown();
        assertEquals(player.getPosition(), new Position(20, 31));
        assertEquals(player.getDirection(), OverworldPlayer.Direction.DOWN);

        player.moveLeft();
        assertEquals(player.getPosition(), new Position(19, 31));
        assertEquals(player.getDirection(), OverworldPlayer.Direction.LEFT);

        player.moveRight();
        assertEquals(player.getPosition(), new Position(20, 31));
        assertEquals(player.getDirection(), OverworldPlayer.Direction.RIGHT);


    }
}
