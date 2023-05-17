package ml.satgrie.pl.ue.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class PolygoneHandler {
	
	private PolygoneModelCercle polygoneModelCercle;
	
	public List<PolygoneModelCercle> getCerclePolygone(String fileName){
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		 try {

				File file = getFileFromResource(fileName);
			 // optional, but recommended
	          // process XML securely, avoid attacks like XML External Entities (XXE)
	          documentBuilderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

	          // parse XML file
	          DocumentBuilder db = documentBuilderFactory.newDocumentBuilder();

	          Document doc = db.parse(file);

	          // optional, but recommended
	          // http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
	          doc.getDocumentElement().normalize();

	          System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());
	          System.out.println("------");
	          NodeList list = doc.getElementsByTagName("cercle");
	          List<PolygoneModelCercle> polygoneModelCercles  = new ArrayList<PolygoneModelCercle>();
	          for (int temp = 0; temp < list.getLength(); temp++) {
	        	  Node node = list.item(temp);
	        	  if (node.getNodeType() == Node.ELEMENT_NODE) {
	        		  PolygoneModelCercle polygoneModelCercle = new PolygoneModelCercle();
	                  Element element = (Element) node;
	                  // get staff's attribute
	                  polygoneModelCercle.setPays(element.getElementsByTagName("pays").item(0).getTextContent());
	                  polygoneModelCercle.setRegion(element.getElementsByTagName("region").item(0).getTextContent());
	                  polygoneModelCercle.setCercle(element.getElementsByTagName("cercleNom").item(0).getTextContent());
	                  polygoneModelCercle.setPolygoneLatLong(new ArrayList<String>(Arrays.asList(element.getElementsByTagName("polygone").item(0).getTextContent().split(";"))));
	                  polygoneModelCercles.add(polygoneModelCercle);
	        	  
	        	  }
	          }
	          return polygoneModelCercles;
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<PolygoneModelCercle> getRegionPolygone(String fileName){
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		 try {

				File file = getFileFromResource(fileName);
			 // optional, but recommended
	          // process XML securely, avoid attacks like XML External Entities (XXE)
	          documentBuilderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

	          // parse XML file
	          DocumentBuilder db = documentBuilderFactory.newDocumentBuilder();

	          Document doc = db.parse(file);

	          // optional, but recommended
	          // http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
	          doc.getDocumentElement().normalize();

	          System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());
	          System.out.println("------");
	          NodeList list = doc.getElementsByTagName("region");
	          List<PolygoneModelCercle> polygoneModelCercles  = new ArrayList<PolygoneModelCercle>();
	          for (int temp = 0; temp < list.getLength(); temp++) {
	        	  Node node = list.item(temp);
	        	  if (node.getNodeType() == Node.ELEMENT_NODE) {
	        		  PolygoneModelCercle polygoneModelCercle = new PolygoneModelCercle();
	                  Element element = (Element) node;
	                  // get staff's attribute
	                  polygoneModelCercle.setPays(element.getElementsByTagName("pays").item(0).getTextContent());
	                  polygoneModelCercle.setRegion(element.getElementsByTagName("regionNom").item(0).getTextContent());
	                  polygoneModelCercle.setPolygoneLatLong(new ArrayList<String>(Arrays.asList(element.getElementsByTagName("polygone").item(0).getTextContent().split(" "))));
	                  polygoneModelCercles.add(polygoneModelCercle);
	        	  
	        	  }
	          }
	          return polygoneModelCercles;
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<PolygoneModelCercle> getCommunePolygone(String fileName){
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		 try {

				File file = getFileFromResource(fileName);
			 // optional, but recommended
	          // process XML securely, avoid attacks like XML External Entities (XXE)
	          documentBuilderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

	          // parse XML file
	          DocumentBuilder db = documentBuilderFactory.newDocumentBuilder();

	          Document doc = db.parse(file);

	          // optional, but recommended
	          // http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
	          doc.getDocumentElement().normalize();

	          System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());
	          System.out.println("------");
	          NodeList list = doc.getElementsByTagName("COMMUNE");
	          List<PolygoneModelCercle> polygoneModelCercles  = new ArrayList<PolygoneModelCercle>();
	          for (int temp = 0; temp < list.getLength(); temp++) {
	        	  Node node = list.item(temp);
	        	  if (node.getNodeType() == Node.ELEMENT_NODE) {
	        		  PolygoneModelCercle polygoneModelCercle = new PolygoneModelCercle();
	                  Element element = (Element) node;
	                  // get staff's attribute
	                  polygoneModelCercle.setPays(element.getElementsByTagName("PAYS").item(0).getTextContent());
	                  polygoneModelCercle.setRegion(element.getElementsByTagName("REGION").item(0).getTextContent());
	                  polygoneModelCercle.setCercle(element.getElementsByTagName("CERCLE").item(0).getTextContent());
	                  polygoneModelCercle.setCommune(element.getElementsByTagName("COMMUNENOM").item(0).getTextContent());
	                  polygoneModelCercle.setPolygoneLatLong(new ArrayList<String>(Arrays.asList(element.getElementsByTagName("POLYGONE").item(0).getTextContent().split(" "))));
	                  polygoneModelCercles.add(polygoneModelCercle);
	        	  
	        	  }
	          }
	          return polygoneModelCercles;
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	 // get a file from the resources folder
    // works everywhere, IDEA, unit test and JAR file.
    private InputStream getFileFromResourceAsStream(String fileName) {

        // The class loader that loaded the class
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }

    }

    /*
        The resource URL is not working in the JAR
        If we try to access a file that is inside a JAR,
        It throws NoSuchFileException (linux), InvalidPathException (Windows)

        Resource URL Sample: file:java-io.jar!/json/file1.json
     */
    private File getFileFromResource(String fileName) throws URISyntaxException{

        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {

            // failed if files have whitespaces or special characters
            //return new File(resource.getFile());

            return new File(resource.toURI());
        }

    }

    // print input stream
    private static void printInputStream(InputStream is) {

        try (InputStreamReader streamReader =
                    new InputStreamReader(is, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // print a file
    private static void printFile(File file) {

        List<String> lines;
        try {
            lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
            lines.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	/**
	 * @return the polygoneModelCercle
	 */
	public PolygoneModelCercle getPolygoneModelCercle() {
		return polygoneModelCercle;
	}

	/**
	 * @param polygoneModelCercle the polygoneModelCercle to set
	 */
	public void setPolygoneModelCercle(PolygoneModelCercle polygoneModelCercle) {
		this.polygoneModelCercle = polygoneModelCercle;
	}
    
}
