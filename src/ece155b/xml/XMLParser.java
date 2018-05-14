package ece155b.xml;

import ece155b.data.Distributor;
import ece155b.data.SellSupply;
import ece155b.data.NeedSupply;
import ece155b.data.Supply;
import ece155b.test.DemoXML;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
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
import org.xml.sax.*;
import org.xml.sax.helpers.*;

public class XMLParser  {
	public static List<Distributor> XMLDomParser(File file){
		List<Distributor> lists =new ArrayList<Distributor>();
		try {
			DocumentBuilderFactory dbf =DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			Node distributor  =doc.getElementsByTagName("Distributor").item(0);
			NodeList comList =distributor.getChildNodes();
			for(int i=0;i<comList.getLength();i++) {
				System.out.println(comList.getLength());
				Node comNode = comList.item(i);
				if(comNode.getNodeType()==Node.ELEMENT_NODE) {
					Element comElement=(Element)comNode;
					Distributor com = new Distributor();
					Element nameElement=(Element)comElement.getElementsByTagName("CompanyName").item(0);
					com.setName(nameElement.getTextContent());
					Element addressElement=(Element)comElement.getElementsByTagName("CompanyAddress").item(0);
					com.setAddress(addressElement.getTextContent());
					Element contactElement=(Element)comElement.getElementsByTagName("CompanyContact").item(0);
					com.setContact(contactElement.getTextContent());
					
					NeedSupply sup = new NeedSupply();
//					Element NeeditemID=(Element)comElement.getElementsByTagName("NeeditemID").item(0);
//					sup.setNeedItemID(NeeditemID.getTextContent());
//					Element NeeditemName=(Element)comElement.getElementsByTagName("NeeditemName").item(0);
//					sup.setNeedItemID(NeeditemName.getTextContent());
//					Element NeeditemPrice=(Element)comElement.getElementsByTagName("NeeditemPrice").item(0);
//					sup.setNeedItemPrice(Double.parseDouble(NeeditemPrice.getTextContent()));
//					Element NeeditemAmount=(Element)comElement.getElementsByTagName("NeeditemAmount").item(0);
//					sup.setAmountNeeded(Integer.parseInt(NeeditemAmount.getTextContent()));
					
					
					
					lists.add(com);
				}
			}
		}catch (Exception e) {  
            e.printStackTrace();  
        }  
        return lists;    //回傳lists物件
	}
	public static void writeXml(List<Distributor> dis) throws IOException {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc =db.newDocument();
			if (doc != null) {  
				Element distributor =doc.createElement("Distributor");
				for(int i=0;i<dis.size();i++) {
					Element companyInfo =doc.createElement("CompanyInfo"); 
					Element companyName =doc.createElement("CompanyName");
					companyName.appendChild(doc.createTextNode(dis.get(i).getName()));
					companyInfo.appendChild(companyName);
					Element companyAddress =doc.createElement("CompanyAddress");
					companyAddress.appendChild(doc.createTextNode(dis.get(i).getAddress()));
					companyInfo.appendChild(companyAddress);
					Element companyContact =doc.createElement("CompanyContact");
					companyContact.appendChild(doc.createTextNode(dis.get(i).getContact()));
					companyInfo.appendChild(companyContact);
					distributor.appendChild(companyInfo);
				}			
				doc.appendChild(distributor);
			}
			//轉換成xml文檔
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer=tf.newTransformer();
			DOMSource source =new DOMSource(doc);
			
			//文件寫入
			PrintWriter printWriter =new PrintWriter(new FileOutputStream("hello.xml"));
			StreamResult result =new StreamResult(printWriter);
			//執行寫入
			transformer.transform(source, result);
			System.out.println("成功!");
			printWriter.close();
		}catch (ParserConfigurationException e) {  
			e.printStackTrace();  
		} catch (TransformerConfigurationException e) {  
			e.printStackTrace();  
		} catch (FileNotFoundException e) {  
			e.printStackTrace();  
		} catch (TransformerException e) {  
			e.printStackTrace();  
		}  
		
}
	
}

