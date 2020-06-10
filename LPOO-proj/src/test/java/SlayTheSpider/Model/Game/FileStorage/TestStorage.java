package SlayTheSpider.Model.Game.FileStorage;

import SlayTheSpider.Model.Sprites.Sprite;
import SlayTheSpider.Model.Game.Card;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;

public class TestStorage {
    private static XMLStorage storage;
    private static SpriteStorage spriteStorageMock;

    private SpriteStorage tsstorage = new TextSpriteStorage();
    private SpriteStorage isstorage = new ImageSpriteStorage();

    @BeforeClass
    static public void  LoadStorage() {
        spriteStorageMock = Mockito.mock(SpriteStorage.class);
        storage = new XMLStorage(spriteStorageMock);
        Mockito.when(spriteStorageMock.getSprite(anyString())).thenReturn(Mockito.mock(Sprite.class));
    }

    @Test
    public void TestSprites() {
        Sprite monster = Mockito.mock(Sprite.class);
        Sprite spider = Mockito.mock(Sprite.class);

        Mockito.when(spriteStorageMock.getSprite("Monster")).thenReturn(monster);
        Mockito.when(spriteStorageMock.getSprite("Spider")).thenReturn(spider);

        assertSame(storage.getSprite("Monster"), monster);
        assertSame(storage.getSprite("Spider"), spider);
    }

    @Test
    public void TestCards() {
        try {
            assertEquals(storage.getCard("Whirlwind").getName(), "Whirlwind");
            assertEquals(storage.getCard("Knife Throw").getName(), "Knife Throw");
            assertEquals(storage.getCard("Bash").getName(), "Bash");
        }
        catch (NullPointerException e) {
            Assert.fail();
        }
    }

    @Test(expected = NullPointerException.class)
    public void TestCardNotFound() {
        storage.getCard("Damageo Card 1");
    }

    @Test
    public void TestFights() {
        assertEquals(storage.getFights().size(), 5);
        assertEquals(storage.getBosses().size(), 2);
    }

    @Test
    public void TestRandomCard() {
        assertNotEquals(storage.getRandomCard(), null);

        Card card = storage.getRandomCard();

        assertEquals(card.getName(), storage.getCard(card.getName()).getName());
    }

    @Test
    public void TestOthers() {
        assertNotNull(storage.getNewPlayer());
        assertNotNull(storage.getNewPlayer().getDeck());
        assertNotEquals(storage.getNewPlayer().getDeck().getCards().size(), 0);
        assertSame(storage.getSpriteStorage(), spriteStorageMock);
    }

    @Test
    public void TestSpriteStorage() {
        assertEquals(tsstorage.getSprites().size(), 13);
        assertEquals(isstorage.getSprites().size(), 19);

        assertNotNull(tsstorage.getSprite("Bat"));
        assertNotNull(isstorage.getSprite("Bat"));
    }

    @Test(expected = NullPointerException.class)
    public void TestSpriteNotFound() {
        tsstorage.getSprite("GOIruhewiv vergytr");
    }

}
