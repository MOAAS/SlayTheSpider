package SlayTheSpider.Model.Game.DocLoader;

import SlayTheSpider.Model.DocLoader.StringListLoader;
import SlayTheSpider.Model.DocLoader.XMLDocumentLoader;
import org.junit.Test;
import org.w3c.dom.Document;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestDocLoader {
    @Test
    public void TestString() {
        StringListLoader loader  = new StringListLoader("SpritesText/monster.txt");
        List<String> list = loader.load();

        assertEquals(list.size(), 5);
    }

    @Test
    public void TestXML() {
        XMLDocumentLoader loader = new XMLDocumentLoader("xml/Player.xml");
        Document document = loader.load();

        assertNotNull(document);
    }

    @Test (expected = IllegalArgumentException.class)
    public void TestNotFound() {
        XMLDocumentLoader loader1 = new XMLDocumentLoader("wrongFolder/wrongFilePath.xml");
        StringListLoader loader2 = new StringListLoader("wrongFolder/wrongFilePath.xml");

        loader1.load();
        loader2.load();
    }
}
