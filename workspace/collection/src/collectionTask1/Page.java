package collectionTask1;

public class Page {
	public static void main(String[] args) {
		UserField field = new UserField();
		
		field.join("kmg2331", "1234", "媛뺣�쇨뎄", "01034442331");
		field.join("hgd2331", "1234", "�솉湲몃룞", "01034442331");
		
		System.out.println(field.arUser);
		System.out.println(field.login("kmg2331", "1234"));
//		field.changePw("kmg2331", "4321", "01034442331");
		System.out.println(field.login("kmg2331", "1234"));
		System.out.println(field.login("kmg2331", "4321"));
	}
}
