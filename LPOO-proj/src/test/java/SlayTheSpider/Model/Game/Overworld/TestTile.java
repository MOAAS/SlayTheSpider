package SlayTheSpider.Model.Game.Overworld;

import SlayTheSpider.Model.Game.Overworld.OverWorldElements.Tile;
import SlayTheSpider.Model.Game.Overworld.OverWorldElements.Visibility.ElementInvisible;
import SlayTheSpider.Model.Game.Overworld.OverWorldElements.Visibility.ElementVisibility;
import SlayTheSpider.Model.Position.MapArea;
import SlayTheSpider.Model.Position.Position;
import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.*;

public class TestTile {
    String color = "222222";
    ElementVisibility visibilityMock = Mockito.mock(ElementVisibility.class);
    MapArea hitboxMock = Mockito.mock(MapArea.class);
    Position positionMock = Mockito.mock(Position.class);

    @Test
    public void TestOverWorldElementGetSet(){
        Position pos = new Position(1,1);
        Position pos2 = new Position(2,1);
        Tile testTile = new Tile(pos2,color);


        assertEquals(testTile.collides(pos),false);
        assertEquals(testTile.collides(pos2),true);

        assertEquals(testTile.getPosition(),pos2);
        assertEquals(testTile.getColor(),color);
        assertTrue(testTile.getVisibilitiy() instanceof ElementInvisible);
        testTile.setVisibilitiy(visibilityMock);
        testTile.updateVisibility(pos);
        Mockito.verify(visibilityMock,times(1)).updateState(testTile, pos);
        assertEquals(testTile.getVisibilitiy(),visibilityMock);
    }
}


