package SlayTheSpider.Model.Game.Overworld;

import SlayTheSpider.Model.Game.Overworld.OverWorldElements.OverworldElement;
import SlayTheSpider.Model.Game.Overworld.OverWorldElements.Visibility.ElementInvisible;
import SlayTheSpider.Model.Game.Overworld.OverWorldElements.Visibility.ElementVisibility;
import SlayTheSpider.Model.Game.Overworld.OverWorldElements.Visibility.ElementVisibleFar;
import SlayTheSpider.Model.Game.Overworld.OverWorldElements.Visibility.ElementVisibleNear;
import SlayTheSpider.Model.Position.MapArea;
import SlayTheSpider.Model.Position.Position;
import org.junit.Test;
import org.mockito.Mockito;

import static SlayTheSpider.Model.Game.Overworld.OverWorldElements.Visibility.ElementVisibility.ELEMENT_VIEW_RANGE;
import static org.junit.Assert.assertEquals;

public class TestElementVisibility {
    double VIEW_RANGE = Double.valueOf(ELEMENT_VIEW_RANGE);
    @Test
    public void TestInvisible() {
        OverworldElement elementMock = Mockito.mock(OverworldElement.class);
        MapArea hitboxMock = Mockito.mock(MapArea.class);
        Position positionMock = Mockito.mock(Position.class);
        Mockito.when(elementMock.getHitbox()).thenReturn(hitboxMock);
        Mockito.when(hitboxMock.getCenter()).thenReturn(positionMock);

        ElementVisibility invisible = new ElementInvisible();
        assertEquals(invisible.getType(), ElementVisibility.VisibilityType.Invisible);

        Mockito.when(positionMock.distance(Mockito.any())).thenReturn(VIEW_RANGE + 1);
        invisible.updateState(elementMock, positionMock);
        Mockito.verify(elementMock, Mockito.times(0)).setVisibilitiy(Mockito.any());

        Mockito.when(positionMock.distance(Mockito.any())).thenReturn(VIEW_RANGE - 1);
        invisible.updateState(elementMock,positionMock);
        Mockito.verify(elementMock, Mockito.times(1)).setVisibilitiy(Mockito.any());

        Mockito.when(positionMock.distance(Mockito.any())).thenReturn(VIEW_RANGE);
        invisible.updateState(elementMock, positionMock);
        Mockito.verify(elementMock, Mockito.times(2)).setVisibilitiy(Mockito.any());
    }

    @Test
    public void TestVisibleNear() {
        OverworldElement elementMock = Mockito.mock(OverworldElement.class);
        MapArea hitboxMock = Mockito.mock(MapArea.class);
        Position positionMock = Mockito.mock(Position.class);
        Mockito.when(elementMock.getHitbox()).thenReturn(hitboxMock);
        Mockito.when(hitboxMock.getCenter()).thenReturn(positionMock);

        ElementVisibility visibleNear = new ElementVisibleNear();
        assertEquals(visibleNear.getType(), ElementVisibility.VisibilityType.VisibleNear);

        Mockito.when(positionMock.distance(Mockito.any())).thenReturn(VIEW_RANGE + 1);
        visibleNear.updateState(elementMock, positionMock);
        Mockito.verify(elementMock, Mockito.times(1)).setVisibilitiy(Mockito.any());

        Mockito.when(positionMock.distance(Mockito.any())).thenReturn(VIEW_RANGE - 1);
        visibleNear.updateState(elementMock, positionMock);
        Mockito.verify(elementMock, Mockito.times(1)).setVisibilitiy(Mockito.any());

        Mockito.when(positionMock.distance(Mockito.any())).thenReturn(VIEW_RANGE);
        visibleNear.updateState(elementMock, positionMock);
        Mockito.verify(elementMock, Mockito.times(1)).setVisibilitiy(Mockito.any());
    }

    @Test
    public void TestVisibleFar() {
        OverworldElement elementMock = Mockito.mock(OverworldElement.class);
        MapArea hitboxMock = Mockito.mock(MapArea.class);
        Position positionMock = Mockito.mock(Position.class);
        Mockito.when(elementMock.getHitbox()).thenReturn(hitboxMock);
        Mockito.when(hitboxMock.getCenter()).thenReturn(positionMock);

        ElementVisibility visibleFar = new ElementVisibleFar();
        assertEquals(visibleFar.getType(), ElementVisibility.VisibilityType.VisibleFar);

        Mockito.when(positionMock.distance(Mockito.any())).thenReturn(VIEW_RANGE + 1);
        visibleFar.updateState(elementMock, positionMock);
        Mockito.verify(elementMock, Mockito.times(0)).setVisibilitiy(Mockito.any());

        Mockito.when(positionMock.distance(Mockito.any())).thenReturn(VIEW_RANGE - 1);
        visibleFar.updateState(elementMock, positionMock);
        Mockito.verify(elementMock, Mockito.times(1)).setVisibilitiy(Mockito.any());

        Mockito.when(positionMock.distance(Mockito.any())).thenReturn(VIEW_RANGE);
        visibleFar.updateState(elementMock, positionMock);
        Mockito.verify(elementMock, Mockito.times(2)).setVisibilitiy(Mockito.any());
    }
}
