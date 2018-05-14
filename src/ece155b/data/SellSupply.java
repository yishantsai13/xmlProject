package ece155b.data;

public class SellSupply
{
	public String name;
	public double price;
	public int amountAvailable;

	public SellSupply() {
		
	}
	public SellSupply(String name,double price,int amount) {
		this.name=name;
		this.price=price;
		this.amountAvailable=amount;
	}

	public String geSellItemName() {
		return name;
	}
	public double getSellItemPrice() {
		return price;
	}
	public int getAmountNeeded(){
		return amountAvailable;
	}
	
	public void setSellItemName(String name) {
		this.name=name;
	}
	public void setSellItemPrice(double price) {
		this.price=price;
	}
	public void setAmountNeeded(int amount){
		this.amountAvailable=amount;
	}
	
	public String toXML()
	{
		String returnstr="";
		return returnstr;
	}

	public String toString()
	{
		String returnstr="";
		return returnstr+"\n";
	}
}