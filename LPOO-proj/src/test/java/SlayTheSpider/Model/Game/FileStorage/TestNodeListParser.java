package SlayTheSpider.Model.Game.FileStorage;

import SlayTheSpider.Model.Game.FileStorage.NodeListParser.*;
import org.junit.Test;
import org.mockito.Mockito;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestNodeListParser {

    @Test
    public void TestNodeList() {
        Element nodeMock1 = Mockito.mock(Element.class);
        Element nodeMock2 = Mockito.mock(Element.class);
        Element nodeMock3 = Mockito.mock(Element.class);

        Mockito.when(nodeMock1.getNodeType()).thenReturn(Node.ELEMENT_NODE);
        Mockito.when(nodeMock2.getNodeType()).thenReturn(Node.ELEMENT_NODE);
        Mockito.when(nodeMock3.getNodeType()).thenReturn(Node.ATTRIBUTE_NODE);

        NodeList listStub = new NodeList() {
            @Override
            public Node item(int index) {
                if (index == 0)
                    return nodeMock1;
                else if (index == 1)
                    return nodeMock2;
                else if (index == 2)
                    return nodeMock3;
                return null;
            }

            @Override
            public int getLength() {
                return 3;
            }
        };

        XMLNodeListParser nodeListParser = new XMLNodeListParser() {
            @Override
            public Object parseElement(Element element) {
                return null;
            }
        };

        XMLNodeListParser spy = Mockito.spy(nodeListParser);


        List a = spy.parseItems(listStub);

        assertEquals(a.size(), 2);

        Mockito.verify(spy, Mockito.times(1)).parseElement(nodeMock1);
        Mockito.verify(spy, Mockito.times(1)).parseElement(nodeMock2);
        Mockito.verify(spy, Mockito.times(0)).parseElement(nodeMock3);
    }
}
