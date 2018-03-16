package ch.makery.address.util;

import com.google.common.collect.ImmutableList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SimpleParsing {

    private static final String POM = "pom.xml";
    private static final String BUNDLES = "bundles";
    private static final String MODULES = "modules";

    public static List<String> getListBundles(String path) {
        Document doc = null;
        try {
            if (path.isEmpty()) {
                return ImmutableList.of();
            }
            String filePath = path + "/" + POM;
            File fXmlFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = null;

            dBuilder = dbFactory.newDocumentBuilder();


            doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();
            List<String> list = getListElements(doc);
            if (list.contains(BUNDLES)) {
                fXmlFile = new File(filePath.replace(POM, BUNDLES + "/" + POM));
            }
            doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            list.clear();

        } catch (SAXException | IOException | ParserConfigurationException e) {
            e.printStackTrace();
        }
        return getListElements(doc);
    }


    private static List<String> getListElements(Document doc) {
        List<String> list = new ArrayList<>();
        NodeList nList = doc.getElementsByTagName(MODULES);
        Node node = nList.item(0);
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element eElement = (Element) node;
            String[] split = eElement.getTextContent().split("\n");
            for (String el : split) {
                String trim = el.trim();
                if (!trim.isEmpty()) {
                    list.add(trim);
                }
            }
        }
        return list;
    }
}

