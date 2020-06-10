package SlayTheSpider.Model.DocLoader;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

import static java.lang.ClassLoader.getSystemClassLoader;

public class XMLDocumentLoader implements FileLoader<Document> {
    private final String pathname;

    public XMLDocumentLoader(String pathname) {
        this.pathname = pathname;
    }

    @Override
    public Document load() {
        InputStream in = getSystemClassLoader().getResourceAsStream(pathname);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        Document document = null;
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(in);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return document;
    }
}
