package love;

public class Love {
	private String Number;
	private String name;
	private String findAge;
	private String list;
	private int age;
	
	
	public Love() {;}


	public String getNumber() {
		return Number;
	}


	public void setNumber(String number) {
		Number = number;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getFindAge() {
		return findAge;
	}


	public void setFindAge(String findAge) {
		this.findAge = findAge;
	}


	public String getList() {
		return list;
	}


	public void setList(String list) {
		this.list = list;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	@Override
	public String toString() {
		return "Love [Number=" + Number + ", name=" + name + ", findAge=" + findAge + ", list=" + list + ", age=" + age
				+ "]";
	}

	
}