package SlayTheSpider.Model.Game.Overworld;

import SlayTheSpider.Model.Sprites.Sprite;
import SlayTheSpider.Model.Game.Overworld.Rooms.*;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class TestFloorGenerator {

    @Test
    public void TestGenerate(){
        FloorGenerator floorGeneratorTest = new FloorGenerator(1500,1500);
        FloorGenerator generatorSpy = Mockito.spy(floorGeneratorTest);

        List<FloorStructure> structures = floorGeneratorTest.generate(15).getList();
        assertEquals(43, structures.size());
        assertTrue(structures.size() > 0);
        for(int i = 0 ; i < 43 - 15 ; i++){
            assertTrue(structures.get(i).getClass().equals(HorCorridor.class) || structures.get(i).getClass().equals(VerCorridor.class) );
        }
        for(int i = 43 - 15; i < structures.size(); i++){
            Class Class = structures.get(i).getClass();
            assertTrue(Class == EnemyRoom.class || Class == BossRoom.class || Class == SpawnRoom.class);
        }

        FloorStructureList listMock = Mockito.mock(FloorStructureList.class);
        when(listMock.doesRoomFit(any(),anyInt())).thenReturn(true);
        when(listMock.getRooms()).thenReturn(new ArrayList<>());
        generatorSpy.generate(15);
        Mockito.verify(generatorSpy, times(1)).setSpawnRoom(any(FloorStructureList.class));
        Mockito.verify(generatorSpy, times(1)).setBossRoom(any(FloorStructureList.class));
        Mockito.verify(generatorSpy, times(1)).setBossRoom(any(FloorStructureList.class));
        Mockito.verify(generatorSpy, times(1)).makeNormalRooms(eq(15), any(FloorStructureList.class));
        Mockito.verify(generatorSpy, times(1)).makeCorridors(any(FloorStructureList.class));
    }

    @Test
    public void TestAddSpawnRoom(){
       FloorGenerator floorGeneratorTest = new FloorGenerator(100,100);
        FloorStructureList floorStructureListMock = Mockito.mock(FloorStructureList.class);
        when(floorStructureListMock.doesRoomFit(any(),anyInt())).thenReturn(false).thenReturn(true);
        floorGeneratorTest.setSpawnRoom(floorStructureListMock);
        Mockito.verify(floorStructureListMock, Mockito.times(1)).setSpawnRoom(any());
        Mockito.verify(floorStructureListMock, Mockito.times(2)).doesRoomFit(any(), anyInt());
    }

    @Test
    public void TestAddBossRoom(){
        FloorGenerator floorGeneratorTest = new FloorGenerator(100,100);
        FloorStructureList floorStructureListMock = Mockito.mock(FloorStructureList.class);
        when(floorStructureListMock.doesRoomFit(any(),anyInt())).thenReturn(false).thenReturn(true);
        floorGeneratorTest.setBossRoom(floorStructureListMock);
        Mockito.verify(floorStructureListMock, Mockito.times(1)).setBossRoom(any());
        Mockito.verify(floorStructureListMock, Mockito.times(2)).doesRoomFit(any(), anyInt());
    }

    @Test
    public void TestWallSprite() {
        FloorGenerator floorGeneratorTest = new FloorGenerator( 100, 100);

        Sprite wallSprite = Mockito.mock(Sprite.class);

        floorGeneratorTest.setWallSprite(wallSprite);
        assertSame(floorGeneratorTest.wallSprite, wallSprite);
    }

    @Test
    public void TestGets() {
        FloorGenerator floorGeneratorTest = new FloorGenerator( 100, 800);

        assertEquals(floorGeneratorTest.getWidth(), 100);
        assertEquals(floorGeneratorTest.getHeight(), 800);
    }
}
