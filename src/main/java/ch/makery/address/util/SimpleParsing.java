package ch.makery.address.util;

import javafx.collections.ObservableList;
import org.apache.commons.io.FileUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class SimpleParsing {
    public static void main(String[] args) throws Exception {

        File fXmlFile = new File("poms.xml");

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            List<String> list = new ArrayList<>();
            NodeList nList = doc.getElementsByTagName("modules");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node node = nList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    list.add(eElement.getTextContent());
                }
            }
            System.out.println(list);
            if (list.contains("bundles")){
                fXmlFile = new File("bundles/"+ "poms.xml");
            }
            doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            list.clear();
            nList = doc.getElementsByTagName("bundles");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node node = nList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    list.add(eElement.getTextContent());
                }
            }
            System.out.println(list);


//             File root = new File("c:\\");
//
//             String[] extensions = { "xml", "java", "dat" };
//             boolean recursive = true;
//
//             Collection files = FileUtils.listFiles(root, extensions, recursive);
//
//             for (Iterator iterator = files.iterator(); iterator.hasNext();) {
//                 File file = (File) iterator.next();
//                 System.out.println("File = " + file.getAbsolutePath());
//             }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

