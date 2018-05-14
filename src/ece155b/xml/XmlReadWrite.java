package ece155b.xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import ece155b.data.Distributor;

public class XmlReadWrite {
	File file=new File("hello.xml");

    public Distributor distributor;
	public void writeXml() {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc =db.newDocument();
			
			//建立根節點
			Element companyInfo =doc.createElement("CompanyInfo");
			companyInfo.setAttribute("attr", "nothing");
			doc.appendChild(companyInfo);
			
			Element companyName =doc.createElement("companyName");
			companyName.setTextContent(distributor.name); 
			companyInfo.appendChild(companyName);
			
			Element companyAddress =doc.createElement("companyAddress");
			companyAddress.setTextContent(distributor.address); 
			companyInfo.appendChild(companyAddress);
			
			Element companyContact =doc.createElement("companyContact");
			companyContact.setTextContent(distributor.contact); 
			companyInfo.appendChild(companyContact);
			
			
			//轉換成xml文檔
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer=tf.newTransformer();
			DOMSource source =new DOMSource(doc);
			
			//文件寫入
			PrintWriter printWriter =new PrintWriter(new FileOutputStream(file));
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
