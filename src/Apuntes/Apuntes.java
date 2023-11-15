/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Apuntes;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author node
 */
public class Apuntes {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerConfigurationException, TransformerException {

        //De XML a DOM
        
        DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
        DocumentBuilder creador = fabrica.newDocumentBuilder();
        Document doc = creador.parse("ficheros/ejemplo2.xml");
        
        Element libreria = doc.getDocumentElement();
        Element e = doc.createElement("libro");
        Element l = doc.createElement("El_senor_de_los_anillos");
        l.setTextContent("hola señor");
        e.appendChild(l);
        libreria.appendChild(e);
        
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer trans = transformerFactory.newTransformer();
        DOMSource recurso = new DOMSource(doc);
        File  salida = new File("ficheros/ejemplo2.xml");
        StreamResult resultado = new StreamResult(salida);
        trans.transform(recurso, resultado);
        
//        NodeList hijos = doc.getDocumentElement().getChildNodes();
//        
//        Node n = hijos.item(1);
//        
//        Element e = (Element) n;
//        
//        System.out.println(e.getTagName());
//        
//        //De DOM a XML
//        
//        File fichero = new File("ficheros/ejemplo.xml");
//        Transformer transformador = TransformerFactory.newInstance().newTransformer();
//        transformador.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
//        StreamResult resultado = new StreamResult(fichero);
////        Document doc2 = creador.parse("ficheros/ejemplo.xml");
//        DOMSource conector = new DOMSource(doc);
//        
//        transformador.transform(conector, resultado);
//        
//        
//        Document doc2 = creador.parse("ficheros/ejemplo2.xml");
//        Element elemento = doc2.createElement("bock");
////        doc.appendChild(elemento);
//        
//        doc2.appendChild(elemento);
        
        

    }
}
