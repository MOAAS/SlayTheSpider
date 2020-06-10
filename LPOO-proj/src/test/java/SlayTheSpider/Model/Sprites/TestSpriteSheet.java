package SlayTheSpider.Model.Sprites;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

public class TestSpriteSheet {
    @Test
    public void TestOWsheet() {
        Sprite sprite1 = Mockito.mock(Sprite.class);
        Sprite sprite2 = Mockito.mock(Sprite.class);
        Sprite sprite3 = Mockito.mock(Sprite.class);
        Sprite sprite4 = Mockito.mock(Sprite.class);
        PlayerOWSpriteSheet sheet = new PlayerOWSpriteSheet(sprite1, sprite2, sprite3, sprite4);

        assertSame(sheet.getFront(), sprite1);
        assertSame(sheet.getBack(), sprite2);
        assertSame(sheet.getLeft(), sprite3);
        assertSame(sheet.getRight(), sprite4);
    }

    @Test
    public void TestPlayerSheet() {
        Sprite sprite1 = Mockito.mock(Sprite.class);
        Sprite sprite2 = Mockito.mock(Sprite.class);
        Sprite sprite3 = Mockito.mock(Sprite.class);
        PlayerSpriteSheet sheet = new PlayerSpriteSheet(sprite1, sprite2, sprite3);

        assertSame(sheet.getPlayer(), sprite1);
        assertSame(sheet.getManaFull(), sprite2);
        assertSame(sheet.getManaEmpty(), sprite3);
    }
}
