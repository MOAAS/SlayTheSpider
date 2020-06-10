package SlayTheSpider.Model.Sprites;

import SlayTheSpider.Model.Position.Position;
import SlayTheSpider.Model.Position.Position2D;
import org.junit.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

public class TestSprites {
    @Test
    public void TestImage() {
        Image imageMock = Mockito.mock(Image.class);
        Mockito.when(imageMock.getHeight(any())).thenReturn(10);
        Mockito.when(imageMock.getWidth(any())).thenReturn(8);
        Mockito.when(imageMock.getScaledInstance(anyInt(), anyInt(), anyInt())).thenReturn(imageMock);

        ImageSprite sprite = new ImageSprite("Name", imageMock);
        assertEquals(sprite.getHeight(), 10);
        assertEquals(sprite.getWidth(), 8);

        assertSame(sprite.endarken(), sprite);
        assertEquals(sprite.scale(8, 9).getName(), "8/9ScaledName");

        SpriteGraphics graphics = Mockito.mock(SpriteGraphics.class);
        sprite.draw(graphics,  new Position(1, 2));
        Mockito.verify(graphics, Mockito.times(1)).drawImage(imageMock, 1, 2);
    }

    @Test
    public void TestTextSprite() {
        List<String> strings1 = new ArrayList<>();
        strings1.add("---");
        strings1.add("-+-");
        strings1.add("| |");

        List<String> strings2 = new ArrayList<>();
        strings2.add(" . ");
        strings2.add(".....");

        TextSprite sprite1 = new TextSprite("1", strings1, "ff0000");
        TextSprite sprite2 = new TextSprite("2", strings2, "00ff00");

        // Height / Width
        assertEquals(sprite1.getHeight(), 3);
        assertEquals(sprite2.getHeight(), 2);
        assertEquals(sprite1.getWidth(), 3);
        assertEquals(sprite2.getWidth(), 5);

        // Draw
        SpriteGraphics graphics = Mockito.mock(SpriteGraphics.class);
        Position2D position = new Position(1, 2);
        sprite1.draw(graphics,  position);
        Mockito.verify(graphics, Mockito.times(1)).drawStringList(strings1, position, "ff0000");

        assertSame(sprite1.scale(10, 2), sprite1);
        assertEquals(sprite1.endarken().getColor(), "b20000");
        assertEquals(sprite1.getName(), "1");


        TextSprite copy = new TextSprite(sprite2);
        assertEquals(copy.getColor(), "00ff00");
        assertEquals(copy.getName(), "2");
        assertEquals(copy.getWidth(), 5);
        assertEquals(copy.getHeight(), 2);
        assertNotSame(copy, sprite2);
    }

    @Test
    public void TestNull() {
        Sprite sprite = new NullSprite();

        SpriteGraphics graphics = Mockito.mock(SpriteGraphics.class);
        sprite.draw(graphics, new Position(2, 30));
        Mockito.verifyZeroInteractions(graphics);

        assertEquals(sprite.getName(), "");
        assertEquals(sprite.getHeight(), 0);
        assertEquals(sprite.getWidth(), 0);

        assertSame(sprite.endarken(), sprite);
        assertSame(sprite.scale(10, 20), sprite);
    }
}
