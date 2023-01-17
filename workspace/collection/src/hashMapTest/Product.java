package hashMapTest;

public class Product {
	private int number;
	private String name;
	private int price;
	private int stockCount;
	
	public Product() {;}

	
	public Product(int number, String name, int price, int stockCount) {
		super();
		this.number = number;
		this.name = name;
		this.price = price;
		this.stockCount = stockCount;
	}


	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
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

	public int getStockCount() {
		return stockCount;
	}

	public void setStockCount(int stockCount) {
		this.stockCount = stockCount;
	}

	@Override
	public String toString() {
		return "Product [number=" + number + ", name=" + name + ", price=" + price + ", stockCount=" + stockCount + "]";
	}
	

}
