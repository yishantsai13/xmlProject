package ece155b.data;

public class Supply
{
	public String ID;
	public String name;
	public double price;
	
	public Supply(){
		
	}
	public Supply(String ID,String name,double price) {
		this.ID=ID;
		this.name=name;
		this.price=price;
	}
	
	public String getSupplyID() {
		return ID;
	}
	public String getSupplyName() {
		return name;
	}
	public double getSupplyPrice() {
		return price;
	}


	public String toXML()
	{
		String returnstr="";
		returnstr += "<Supply>";
			returnstr += "<SupplyID>"+ID+"</SupplyID>";
		returnstr += "</Supply>";
		return returnstr;
	}

	public String toString()
	{
		String returnstr="";
		returnstr += "||| Supply\n";
			returnstr += "|||| SupplyID:"+ID+"\n";
		return returnstr;
	}
}