/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TareaRepasoXML;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 *
 * @author node
 */
public class Main {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerConfigurationException {

        File fichero = new File("ficheros/pedidos.dat");
        File ficheroXml = new File("ficheros/pedidos.xml");

        ArrayList<Pedido> pedidos = new ArrayList();
        generarListaAleatoriaDePedidos(pedidos);

        generarPedidosDat(pedidos, fichero);

        leerFicheroPedidosDat(fichero);
        
        crearXML(pedidos,ficheroXml);
    }

    private static void generarListaAleatoriaDePedidos(ArrayList<Pedido> pedidos) {
        ArrayList<Producto> productos;

        Producto pro;
        Pedido ped;

        Random rEntero = new Random();
        
        for (int i = 0; i < 5; i++) {
            Integer idRandom = rEntero.nextInt(100);
            pro = new Producto(idRandom.toString(), "Electrodomestico", rEntero.nextInt(1000));
            productos = new ArrayList();
            productos.add(pro);
            if (rEntero.nextBoolean()) {
                pro = new Producto(rEntero.nextInt(100) + "", "Comida", rEntero.nextInt(1000));
                productos.add(pro);
            }
            ped = new Pedido(i, "Cliente" + i, productos);
            pedidos.add(ped);
        }
    }

    private static void generarPedidosDat(ArrayList<Pedido> pedidos, File fichero) {

        try {
            fichero.delete();
            fichero.createNewFile();
            for (Pedido pedido : pedidos) {
                pedido.escribirEn(fichero);
            }
        } catch (IOException ex) {
            System.err.println("No se pudo generar el fichero");
        }
    }

    private static void leerFicheroPedidosDat(File fichero) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fichero));
            try {
                String lineaSigui=br.readLine();
                while ((lineaSigui)!=null) {
                    System.out.println(lineaSigui);
                    lineaSigui = br.readLine();
                }
                br.close();
            } catch (IOException ex) {
                System.err.println("No se pudo cerrar");
            }
        } catch (FileNotFoundException ex) {
            System.err.println("No se pudo leer");
        }
    }

    private static void crearXML(ArrayList<Pedido> pedidos,File ficheroXml) throws ParserConfigurationException, SAXException, IOException, TransformerConfigurationException {
        if(!ficheroXml.exists()){
            ficheroXml.createNewFile();
        }else{
            ficheroXml.delete();
            ficheroXml.createNewFile();
        }
        DocumentBuilderFactory fabrica = DocumentBuilderFactory.newDefaultInstance();
        DocumentBuilder creador = fabrica.newDocumentBuilder();
        Document doc = creador.newDocument();
        
        DOMImplementation implementacion = creador.getDOMImplementation();
        
        Element raiz = doc.createElement("pedidos");
        doc.appendChild(raiz);
        
        for (Pedido pedido : pedidos) {
            raiz.appendChild(pedido.toElement(doc));
        }
        
        TransformerFactory tFabrica = TransformerFactory.newInstance();
        Transformer trans = tFabrica.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult resultado = new StreamResult(ficheroXml);
        try {
            trans.transform(source, resultado);
        } catch (TransformerException ex) {
            System.err.println("No pudo transformar");
        }
    }
}
