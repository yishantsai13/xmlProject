package ece155b.test;


import ece155b.data.Distributor;
import ece155b.data.Supply;
import ece155b.data.SellSupply;
import ece155b.data.NeedSupply;
import ece155b.xml.XMLParser;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.*;

public class DemoXML {
    
    public static void main(String [] args) {
//        new DemoXML(args);
    	new DemoXML(System.getProperty("user.dir") + File.separator + "test.xml");
    }
    public DemoXML(String  args) {
    	String fileurl = "hello.xml";
    	File file=new File(fileurl);
    	Distributor dist = new Distributor();
    	//顯示xml檔裡面data
    	List<Distributor> dis=readXmlFile(file); //dis=file經過parser出的物件
		for(Distributor distributor:dis){  //foreach
			System.out.println(distributor.toString());
		}
    }


    
    public void toXmlFile(Distributor dist, String url)
    {
    	try
	    {
	    	
	    }
	    catch (Exception ex)
	    {
	    	System.out.println ("Exception:"+ex);
	    }
    }

    //用xmlparser class 讀xml檔
    public List<Distributor> readXmlFile(File file){
    	List<Distributor> distr =new ArrayList<Distributor>();
    	try {
    		distr =XMLParser.XMLDomParser(file);
    	}catch (Exception ex)
	    {
	    	ex.printStackTrace();
	    }    
    	return distr;
    }

    /**/
}