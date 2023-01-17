package hashMapTest;

public class DiscountProduct {
	String name;
	int price;
	
	public DiscountProduct() {;}

	public DiscountProduct(String name, int price) {
		super();
		this.name = name;
		this.price = price;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "DiscountProduct [name=" + name + ", price=" + price + "]";
	}
	

}
