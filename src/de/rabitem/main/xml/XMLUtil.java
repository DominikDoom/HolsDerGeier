package de.rabitem.main.xml;

import de.rabitem.main.Main;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.net.URISyntaxException;

public class XMLUtil {

    private static DocumentBuilderFactory dbf = null;
    private static DocumentBuilder db = null;
    private static Document doc = null;
    private static NodeList nodeList = null;

    public static String readXML(final String tagName, final String element) {

        String output = "";
        try {
            dbf = DocumentBuilderFactory.newInstance();
            db = dbf.newDocumentBuilder();
            doc = db.parse(Main.class.getResourceAsStream("/resources/config.xml"));
            doc.getDocumentElement().normalize();
            nodeList = doc.getElementsByTagName(tagName);

            for (int itr = 0; itr < nodeList.getLength(); itr++) {
                Node node = nodeList.item(itr);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    output = eElement.getElementsByTagName(element).item(0).getTextContent();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return output;
    }

    public static <T> void changeXMLAttribute(final String tagName, final String element, T value) {
        /**
         * Get the param from xml and set value
         */
        nodeList = doc.getElementsByTagName(tagName);
        for (int itr = 0; itr < nodeList.getLength(); itr++) {
            Node node = nodeList.item(itr);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;
                eElement.getElementsByTagName(element).item(0).setTextContent(value.toString());
            }
        }

        /**
         * write it back to the xml
         */
        try {
            final TransformerFactory transformerFactory = TransformerFactory.newInstance();
            final Transformer transformer = transformerFactory.newTransformer();
            final DOMSource source = new DOMSource(doc);
            final StreamResult result = new StreamResult(Main.class.getResourceAsStream("/resources/config.xml").toString());
            transformer.transform(source, result);
        }catch (TransformerException e) {
            System.out.println(e.getMessage());
        }
    }
}
