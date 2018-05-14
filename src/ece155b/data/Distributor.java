package ece155b.data;

import ece155b.data.SellSupply;
import ece155b.data.NeedSupply;
import ece155b.data.Supply;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Vector;

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

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Distributor extends Company
{
	

	public Distributor()
	{
		
	}
	public Distributor(String name, String address, String contact)
	{
		this.name = name;
        this.address = address;
        this.contact = contact;
	}

	public static void addCompany(String name, String address, String contact) throws SAXException, IOException {
			try {
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
				Document doc =db.parse("hello.xml");
				Element root = doc.getDocumentElement();
				if (doc != null) {  
					Element companyInfo =doc.createElement("CompanyInfo"); 
					Element companyName =doc.createElement("CompanyName");
					companyName.appendChild(doc.createTextNode(name));
					companyInfo.appendChild(companyName);
					Element companyAddress =doc.createElement("CompanyAddress");
					companyAddress.appendChild(doc.createTextNode(address));
					companyInfo.appendChild(companyAddress);
					Element companyContact =doc.createElement("CompanyContact");
					companyContact.appendChild(doc.createTextNode(contact));
					companyInfo.appendChild(companyContact);
					root.appendChild(companyInfo);
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
	
	public static void DeleteCompany(String name) throws SAXException, IOException{
		try{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc =db.parse("hello.xml");
			Node distributor  =doc.getElementsByTagName("Distributor").item(0);
			NodeList nodes = distributor.getChildNodes();
			for (int i = 0; i < nodes.getLength(); i++) {
			      Element company = (Element)nodes.item(i);
			      Element companyname = (Element)doc.getElementsByTagName("CompanyName").item(0);
			      String pName = companyname.getTextContent();
			      if (pName.equals(name)) {
			    	  Element target=(Element) companyname.getParentNode();
			    	  target.getParentNode().removeChild(target);
			      }
			    }
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
		}catch (TransformerException e) {  
			e.printStackTrace();  
		} catch (ParserConfigurationException e) {  
			e.printStackTrace();  
		} catch (FileNotFoundException e) {  
			e.printStackTrace();  
		}catch (IOException e) {  
			e.printStackTrace();  
		}catch (SAXException e) {  
			e.printStackTrace();  
		}catch (DOMException e) {
			e.printStackTrace();
		}
	}
	public void addSellItem(SellSupply ss)
	{
		
	}
	
	
	public static void addNeedItem(NeedSupply ns){
		try {
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc =db.parse("hello.xml");
			Element root = doc.getDocumentElement();
			if (doc != null) {  
				Element Needitems =doc.createElement("Needitems"); 
				Element NeeditemID =doc.createElement("NeeditemID");
				NeeditemID.appendChild(doc.createTextNode(ns.ID));
				Needitems.appendChild(NeeditemID);
				Element NeeditemName =doc.createElement("NeeditemName");
				NeeditemName.appendChild(doc.createTextNode(ns.name));
				Needitems.appendChild(NeeditemName);
				Element NeedItemPrice =doc.createElement("NeeditemPrice");
				NeedItemPrice.appendChild(doc.createTextNode(Double.toString(ns.price)));
				Needitems.appendChild(NeedItemPrice);
				Element SoldItemAmount =doc.createElement("NeeditemAmount");
				SoldItemAmount.appendChild(doc.createTextNode(Integer.toString(ns.amountNeeded)));
				Needitems.appendChild(SoldItemAmount);
				root.appendChild(Needitems);
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
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	public String toXML()
	{
		String returnstr="";
		returnstr += "<Distributor>";
			returnstr += "<CompanyInfo>";
				returnstr += "<CompanyName>"+name+"</CompanyName>";
				returnstr += "<CompanyAddress>"+address+"</CompanyAddress>";
				returnstr += "<CompanyContact>"+contact+"</CompanyContact>";
			returnstr += "</CompanyInfo>";


		returnstr += "</Distributor>";
		return returnstr;
	}

	public String toString()
	{
		String returnstr="";
			returnstr += "| CompanyInfo\n";
				returnstr += "|| CompanyName: "+name+"\n";
				returnstr += "|| CompanyAddress: "+address+"\n";
				returnstr += "|| CompanyContact: "+contact+"\n";
			returnstr += "\n";


		return returnstr;
	}
}
