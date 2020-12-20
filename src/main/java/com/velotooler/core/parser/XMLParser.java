package com.velotooler.core.parser;

import com.velotooler.core.exception.ReadingFromFileException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class XMLParser implements Parser {

    private final static String BICYCLE_DATA_XML_PATH = "src/main/resources/bicycleData.xml";
    private final static String LOGIN_XML_PATH = "src/main/resources/LogIn.xml";

    public String parseBicycleData(String tag) {
        return parse(BICYCLE_DATA_XML_PATH, tag);
    }

    public String parseLogin(String tag) {
        return parse(LOGIN_XML_PATH, tag);
    }

    @Override
    public String parse(String path, String tag) {
        String result = "";
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document doc = documentBuilder.parse(path);
            NodeList nodeList = doc.getElementsByTagName(tag);

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                result += node.getTextContent();
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new ReadingFromFileException("Error occurred while reading from the file " + e.getMessage());
        }
        return result;
    }
}
