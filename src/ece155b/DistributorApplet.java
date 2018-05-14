/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ece155b;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import ece155b.data.Company;
import ece155b.data.Distributor;
import ece155b.data.Supply;
import ece155b.data.SellSupply;
import ece155b.data.NeedSupply;
import ece155b.xml.XMLParser;
import ece155b.test.DemoXML;

import javax.xml.parsers.*;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Administrator
 */
public class DistributorApplet extends JApplet implements ActionListener, ListSelectionListener {

	/*
	 * You will definitely have more functions below, such as reading/writing XML
	 * files, GUI parts, mouse/action event listeners...
	 */
	Distributor distributor;
	String fileurl = System.getProperty("user.dir") + File.separator + "test.xml"; // XML file to read/write to

	public void init() {
		// Container content = getContentPane();
		// content.setBackground(Color.white);
		// content.setLayout(new BorderLayout());
		makeGUI();
	}

	public void destroy() {
		System.out.println("...closing");;
	}

	private JPanel jPanel1, jPanel2, jPanel3, jPanel4, jPanelbtn1, jPanelbtn2;
	private JLabel companyname, contactinfo, address;
	private JTextField companynameField, contactinfoField, addressField;
	private JTable table1, table2;
	private JList jList;
	private JScrollPane leftScrollPane;
	private DefaultTableModel model = null;
	private DefaultTableModel model2 = null;
	private DefaultListModel<String> listModel;
	private JButton soldAdd, soldDelete, needAdd, needDelete, saveBtn, loadBtn, addCompany, deleteCompany;
	private List<Company> companyList = new ArrayList();
	private List<Distributor> distributorList = new ArrayList();
	private List<NeedSupply> needSupplyList = new ArrayList();
	private List<SellSupply> sellSupplyList = new ArrayList();
	private File file = new File("hello.xml");

	public void makeGUI() {

		Container content = getContentPane();
		// content.setBackground(Color.white);
		content.setLayout(new BorderLayout(5, 5));
		content.setSize(800, 700);

		jPanel1 = new JPanel();
		String title1 = "Company information";
		Border border1 = BorderFactory.createTitledBorder(title1);
		jPanel1.setBorder(border1);
		companyname = new JLabel("CompanyName");
		companynameField = new JTextField(40);
		contactinfo = new JLabel("Contact Info");
		contactinfoField = new JTextField(40);
		addCompany = new JButton("Add Company");
		addCompany.addActionListener(this);
		deleteCompany = new JButton("Delete Company");
		deleteCompany.addActionListener(this);
		address = new JLabel("Company address");
		addressField = new JTextField(30);
		distributorList = XMLParser.XMLDomParser(file);
		listModel = new DefaultListModel<>();
		for (Distributor dist : distributorList) {
			listModel.addElement(dist.getName());
		}
		jList = new JList<>(listModel);
		jList.setVisibleRowCount(5);
		jList.addListSelectionListener(this);
		leftScrollPane = new JScrollPane(jList);
		leftScrollPane.setPreferredSize(new Dimension(350, 120));
		Box a = Box.createHorizontalBox();
		a.add(companyname);
		a.add(companynameField);
		Box b = Box.createHorizontalBox();
		b.add(contactinfo);
		b.add(contactinfoField);
		Box c = Box.createHorizontalBox();
		c.add(address);
		c.add(addressField);
		Box d = Box.createHorizontalBox();
		d.add(leftScrollPane);
		Box e = Box.createHorizontalBox();
		e.add(addCompany);
		e.add(deleteCompany);
		Box box = Box.createVerticalBox();
		box.add(a);
		box.add(b);
		box.add(c);
		box.add(d);
		box.add(e);
		jPanel1.add(box);
		content.add(jPanel1, BorderLayout.NORTH);
		jPanel1.setPreferredSize(new Dimension(700, 250));

		jPanel2 = new JPanel();
		jPanel2.setLayout(new BorderLayout());
		String title2 = "Items sold to customers";
		Border border2 = BorderFactory.createTitledBorder(title2);
		jPanel2.setBorder(border2);
		String[][] datas = { { "apple", "50", "300" }, { "apple", "50", "300" } };
		String[] TableTitle = { "Item Type", "Price", "Available" };
		model = new DefaultTableModel(datas, TableTitle);
		table1 = new JTable(model);
		jPanel2.add(new JScrollPane(table1), BorderLayout.CENTER);
		jPanelbtn1 = new JPanel();
		jPanelbtn1.setLayout(new FlowLayout());
		soldAdd = new JButton("Add sold items");
		soldAdd.addActionListener(this);
		jPanelbtn1.add(soldAdd);
		soldDelete = new JButton("Delete selected items");
		soldDelete.addActionListener(this);
		jPanelbtn1.add(soldDelete);
		jPanel2.add(jPanelbtn1, BorderLayout.SOUTH);
		jPanel2.setPreferredSize(new Dimension(350, 300));
		add(jPanel2, BorderLayout.WEST);

		jPanel3 = new JPanel();
		jPanel3.setLayout(new BorderLayout());
		String title3 = "Items needed from providers";
		Border border3 = BorderFactory.createTitledBorder(title3);
		jPanel3.setBorder(border3);
		
//		needSupplyList.add(new NeedSupply("1","1",1,1));
//		
//		for (NeedSupply dist : needSupplyList) {
//			NeedSupply[] needs= {dist};
//			model2.addRow(needs);		
//		jList = new JList<>(listModel);
//		jList.setVisibleRowCount(5);
//		jList.addListSelectionListener(this);
//		leftScrollPane = new JScrollPane(jList);
//		
		String[] TableTitle3 = { "ID", "Item Type", "Price", "Available" };
		Object[] a1 = { "1", "apple", "500", "200" };
		model2 = new DefaultTableModel(TableTitle3, 0);
		table2 = new JTable(model2);
		model2.addRow(a1);
		jPanel3.add(new JScrollPane(table2), BorderLayout.CENTER);
		jPanelbtn2 = new JPanel();
		jPanelbtn2.setLayout(new FlowLayout());
		needAdd = new JButton("Add needed items");
		needAdd.addActionListener(this);
		jPanelbtn2.add(needAdd);
		needDelete = new JButton("Delete selected items");
		needDelete.addActionListener(this);
		jPanelbtn2.add(needDelete);
		jPanel3.add(jPanelbtn2, BorderLayout.SOUTH);
		jPanel3.setPreferredSize(new Dimension(350, 300));
		add(jPanel3, BorderLayout.EAST);
		jPanel4 = new JPanel();
		jPanel4.setLayout(new FlowLayout());
		saveBtn = new JButton("Save Information");
		saveBtn.addActionListener(this);
		loadBtn = new JButton("Load Information");
		loadBtn.addActionListener(this);
		jPanel4.add(saveBtn);
		jPanel4.add(loadBtn);
		add(jPanel4, BorderLayout.SOUTH);

		validate();
		setVisible(true);
	}

	class SupplyTab extends JTabbedPane implements ActionListener {
		public SupplyTab(SellSupply viewsellsupply, int index) {
		}

		public void actionPerformed(ActionEvent s) {
		}
	}

	public void actionPerformed(ActionEvent e){
		try{
     Distributor distri;
     NeedSupply need = new NeedSupply();
     int i;
    	 switch(e.getActionCommand()){
	     	case "Add Company":
	     		distributorList = XMLParser.XMLDomParser(file);
				Distributor.addCompany(companynameField.getText(),addressField.getText(),contactinfoField.getText());
				companyList.add(new Distributor(companynameField.getText(),addressField.getText(),contactinfoField.getText()));
	     		distributorList.add(new Distributor(companynameField.getText(),addressField.getText(),contactinfoField.getText()));
	     		listModel.addElement(companynameField.getText());
	     		System.out.println(distributorList);
	     		break;
	        case "Delete Company":
	        	distributorList = XMLParser.XMLDomParser(file);
	            i=jList.getSelectedIndex();
	            distri=distributorList.get(i);
	            String namestr =distri.getName();
	            System.out.println(distri.toString());
	            Distributor.DeleteCompany(namestr);
//	            for (Distributor dist : distributorList) {
//	            	System.out.println(dist.toString());
//	            }
	            distributorList.remove(i);
	            listModel.remove(i);
	            break;
	        case "Add sold items":
	            JTextField sname=new JTextField();
	            JTextField sprice=new JTextField();
	            JTextField samount=new JTextField();
	            Object[] message = {"name:",sname,"price",sprice,"amount",samount};
	            int option = JOptionPane.showConfirmDialog(null, message, "Add sold items", JOptionPane.OK_CANCEL_OPTION);
	            if (option == JOptionPane.OK_OPTION) {
	            	Object[] datas = {sname.getText(),Double.parseDouble(sprice.getText()),Integer.parseInt(samount.getText())};
	//            	System.out.println(Double.parseDouble(price.getText()));
	            	model.addRow(datas);
	            	sellSupplyList.add(new SellSupply(sname.getText(),Double.parseDouble(sprice.getText()),Integer.parseInt(samount.getText())));
//	            	Distributor.addNeedItem(new SellSupply(sname.getText(),Double.parseDouble(sprice.getText()),Integer.parseInt(samount.getText())));
	            } else {
	            	System.out.println("");
	            }
	        	break;
	         case "Delete selected sold items":
	            i=table1.getSelectedRow();
	            model.removeRow(i);
	            break;
	         case "Add needed items":
	            JTextField id =new JTextField();
	            JTextField name=new JTextField();
	            JTextField price=new JTextField();
	            JTextField amount=new JTextField();
	            Object[] message1 = {"id:",id,"name:",name,"price",price,"amount",amount};
	            int option1 = JOptionPane.showConfirmDialog(null, message1, "Add needed items", JOptionPane.OK_CANCEL_OPTION);
	            if (option1 == JOptionPane.OK_OPTION) {
	            	Object[] data = {id.getText(),name.getText(),Double.parseDouble(price.getText()),Integer.parseInt(amount.getText())};
	//            	System.out.println(Double.parseDouble(price.getText()));
	            	model2.addRow(data);
	            	needSupplyList.add(new NeedSupply(id.getText(),name.getText(),Double.parseDouble(price.getText()),Integer.parseInt(amount.getText())));
	            	Distributor.addNeedItem(new NeedSupply(id.getText(),name.getText(),Double.parseDouble(price.getText()),Integer.parseInt(amount.getText())));
	            	System.out.println(needSupplyList);
	            } else {
	            	System.out.println("");
	            }
	            break;
	          case "Delete selected needed items":
	            i=table2.getSelectedRow();
	            model2.removeRow(i);
	            break;
	          case "Load Information":
	            break;
	          case "Save Information":
	            XMLParser.writeXml(distributorList);
	            break;
	     }
     }catch (FileNotFoundException e1) {  
			e1.printStackTrace();  
		}catch (IOException e1) {  
			e1.printStackTrace();  
		}catch (SAXException e1) {  
			e1.printStackTrace();  
		}
 }

	public void listupdate() {
		distributorList = XMLParser.XMLDomParser(file);
	}
	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub

	}
}
