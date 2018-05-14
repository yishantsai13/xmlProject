package ece155b.data;

public class NeedSupply{
	public String ID;
	public String name;
	public double price;
	public int amountNeeded;

	public NeedSupply() {
		
	}
	public NeedSupply(String id,String name,double price,int amount) {
		this.ID=id;
		this.name=name;
		this.price=price;
		this.amountNeeded=amount;
	}
	public String getNeedItemID() {
		return ID;
	}
	
	public String getNeedItemName() {
		return name;
	}
	public double getNeedItemPrice() {
		return price;
	}
	public int getAmountNeeded(){
		return amountNeeded;
	}
	
	public void setNeedItemID(String id) {
		this.ID=id;
	}
	
	public void setNeedItemName(String name) {
		this.name=name;
	}
	public void setNeedItemPrice(double price) {
		this.price=price;
	}
	public void setAmountNeeded(int amount){
		this.amountNeeded=amount;
	}
	public String toXML()
	{
		String returnstr="";
		return returnstr;
	}

	public String toString()
	{
		return name;
	}
}