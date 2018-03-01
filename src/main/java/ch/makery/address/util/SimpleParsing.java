package ch.makery.address.util;

import javax.xml.parsers.DocumentBuilder;
 import javax.xml.parsers.DocumentBuilderFactory;
 import javax.xml.xpath.XPath;
 import javax.xml.xpath.XPathConstants;
 import javax.xml.xpath.XPathExpression;
 import javax.xml.xpath.XPathFactory;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.parser.Parser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Optional;

public class SimpleParsing {
     public static void main(String[] args) throws Exception {

         File fXmlFile = new File("D:/Install/fx/1/AddressApp-Part-7/poms.xml");

         try {


             DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
             DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
             Document doc = dBuilder.parse(fXmlFile);
             doc.getDocumentElement().normalize();
             NodeList nList = doc.getElementsByTagName("modules");


             File root = new File("c:\\");

             String[] extensions = { "xml", "java", "dat" };
             boolean recursive = true;

             Collection files = FileUtils.listFiles(root, extensions, recursive);

             for (Iterator iterator = files.iterator(); iterator.hasNext();) {
                 File file = (File) iterator.next();
                 System.out.println("File = " + file.getAbsolutePath());
             }


             for (int temp = 0; temp < nList.getLength(); temp++) {

                 Node nNode = nList.item(temp);

                 System.out.println("\nCurrent Element :" + nNode.getNodeName());

                 if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                     Element eElement = (Element) nNode;

                     System.out.println(eElement.getTextContent());

                 }
             }
         } catch (Exception e) {
             e.printStackTrace();
         }
     }
     }
//         }

