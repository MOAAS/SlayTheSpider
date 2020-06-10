package SlayTheSpider.Model.Game.Overworld;

import SlayTheSpider.Model.Sprites.Sprite;
import SlayTheSpider.Model.Game.Fight;
import SlayTheSpider.Model.Game.Overworld.OverWorldElements.OverworldBoss;
import SlayTheSpider.Model.Game.Overworld.OverWorldElements.OverworldEnemy;
import SlayTheSpider.Model.Game.Overworld.OverWorldElements.OverworldWall;
import SlayTheSpider.Model.Position.Position;
import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotEquals;

public class TestOverworldObstacle {


    @Test
    public void TestOverworldEnemy(){
        Position position = new Position(1,1);
        Position position2 = new Position(1,2);
        Fight fightMock = Mockito.mock(Fight.class);
        Sprite spriteMock = Mockito.mock(Sprite.class);
        OverworldEnemy overworldEnemyTest = new OverworldEnemy(position, spriteMock,fightMock);

        assertEquals(overworldEnemyTest.getFight(),fightMock);

        assertEquals(overworldEnemyTest.collides(position),true);
        assertEquals(overworldEnemyTest.collides(position2),false);

        assertTrue(overworldEnemyTest.getSprite().equals(spriteMock));
    }

    @Test
    public void TestOverworldWall(){
        Position position = new Position(1,1);
        Sprite spriteMock = Mockito.mock(Sprite.class);
        OverworldWall overworldWallTest = new OverworldWall(position, spriteMock);

        assertEquals(overworldWallTest.getSprite(),spriteMock);
    }

     @Test
    public void TestOverworldBoss(){
         Position position = new Position(1,1);
         Fight fightMock = Mockito.mock(Fight.class);
         Sprite spriteMock = Mockito.mock(Sprite.class);

         OverworldBoss overworldBossTest = new OverworldBoss(position,spriteMock,fightMock);

         assertEquals(overworldBossTest.getSprite(),spriteMock);

         assertEquals(overworldBossTest.getBossFight(),fightMock);

     }

}
