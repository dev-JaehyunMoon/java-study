package arrayListTask3;

public class Food {

//음식 정보를 담을 클래스 선언(Food)
//   - 음식 이름
//   - 음식 가격
//   - 음식 종류(한식, 중식, 일식, 양식)
//
//CRUD관련 기능을 담아놓을 클래스 선언(Restaurant)
//   - 음식 추가
//   - 음식 이름으로 음식 종류 조회
//   - 사용자가 원하는 종류의 음식 전체 조회
//   - 음식 종류 수정 후 가격 10% 상승
//   - 사용자가 원하는 종류의 음식 개수 조회
	private String name;
	private int price;
	private String kind;
	
	public Food() {;}
	
	
	public Food(String name, int price, String kind) {
		super();
		this.name = name;
		this.price = price;
		this.kind = kind;
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

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	@Override
	public String toString() {
		return "Food [name=" + name + ", price=" + price + ", kind=" + kind + "]";
	}
	
	
	
	
}
