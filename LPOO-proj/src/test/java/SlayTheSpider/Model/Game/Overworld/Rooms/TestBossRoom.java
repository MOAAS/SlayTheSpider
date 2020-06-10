package SlayTheSpider.Model.Game.Overworld.Rooms;

import SlayTheSpider.Model.Sprites.NullSprite;
import SlayTheSpider.Model.Game.Fight;
import SlayTheSpider.Model.Game.FightRewards.RewardList;
import SlayTheSpider.Model.Game.Overworld.OverWorldElements.OverworldBoss;
import SlayTheSpider.Model.Game.Overworld.OverWorldElements.Tile;
import SlayTheSpider.Model.Position.MapAreaRandomizer;
import SlayTheSpider.Model.Position.Position;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.*;
import static org.mockito.Mockito.times;

public class TestBossRoom {

    @Test
    public void TestUpdateVisibility(){
        BossRoom bossRoomTest = new BossRoom(new MapAreaRandomizer(6,6,7,7));

        OverworldBoss overworldBossMock = Mockito.mock(OverworldBoss.class);
        List<OverworldBoss> bossesTest = bossRoomTest.getBosses();
        bossesTest.add(overworldBossMock);
        Tile tileMock = Mockito.mock(Tile.class);
        List<Tile> tilesTest = bossRoomTest.getTiles();
        tilesTest.add(tileMock);

        Position p1 = new Position(1,1);

        bossRoomTest.updateVisibility(p1);

        Mockito.verify(overworldBossMock,times(1)).updateVisibility(p1);
        Mockito.verify(tileMock,times(1)).updateVisibility(p1);

    }

    @Test
    public void TestGetColor(){
        BossRoom bossRoomTest = new BossRoom(new MapAreaRandomizer(6,6,7,7));
        assertEquals(bossRoomTest.getColor(),"890012");
    }

    @Test
    public void TestBosses(){
        BossRoom bossRoomTest = new BossRoom(new MapAreaRandomizer(6,6,7,7));
        Position position1 = new Position(1, 1);

        assertEquals(bossRoomTest.getBosses().size() , 0);
        assertTrue(bossRoomTest.areBossesDead());
        assertEquals(bossRoomTest.getObstacles().size() , 0);
        assertNull(bossRoomTest.checkBossCollision(position1));

        Fight fight = new Fight(new NullSprite(), new RewardList(3));
        List<Fight> fightList = new ArrayList<>();
        fightList.add(fight);

        bossRoomTest.insertBoss(fightList);

        assertEquals(bossRoomTest.getBosses().size() , 1);
        assertEquals(bossRoomTest.getObstacles().size() , 1);
        assertFalse(bossRoomTest.areBossesDead());

        OverworldBoss bossTest = bossRoomTest.getBosses().get(0);
        assertFalse(bossRoomTest.doesBossCollide(position1));
        assertNull(bossRoomTest.checkBossCollision(position1));

        assertTrue(bossRoomTest.doesBossCollide(bossTest.getPosition()));
        assertEquals(bossRoomTest.checkBossCollision(bossTest.getPosition()),bossTest);

        assertTrue(bossRoomTest.areBossesDead());

        fightList.clear();
        fightList.add(new Fight(new NullSprite(), new RewardList(3)));
        fightList.add(new Fight(new NullSprite(), new RewardList(3)));
        fightList.add(new Fight(new NullSprite(), new RewardList(3)));
        bossRoomTest.insertBoss(fightList);
        bossRoomTest.insertBoss(fightList);
        bossRoomTest.insertBoss(fightList);
        bossRoomTest.insertBoss(fightList);
        bossRoomTest.insertBoss(fightList);
        bossRoomTest.insertBoss(fightList);
        assertEquals(bossRoomTest.getBosses().size() , 1);
    }
}
