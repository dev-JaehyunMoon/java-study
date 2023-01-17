package study;

import java.util.Scanner;

class Car{
	String brand;
	String color;
	String password;
	int price;
	boolean engine;
	int wrongCount;
	{
		password = "0000";
	}
	public Car() {;}
	public Car(String brand, String color, int price) {
		this.brand = brand;
		this.color = color;
		this.price = price;
	}
	
	public Car(String brand, String color, String password, int price) {
		this.brand = brand;
		this.color = color;
		this.password = password;
		this.price = price;
	}
	void engineStart() {
		engine = true;
		
	}
	void engineStop() {
		engine = false;
	}
	boolean checkPassword(String password){
		return this.password.equals(password);
		
	}
}

public class Study {
	public static void main(String[] args) {
		String message = "1. 시동켜기\n2. 시동끄기";
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		
		Car car = new Car("BMW", "YELLOW", "1234", 35000);
		boolean flag = false;
		while(true) {
			System.out.println(message);
			choice = sc.nextInt();
		
			
		
			switch(choice) {
			case 1:
				if(!car.engine) {
					System.out.println("비밀번호 : ");
					if(car.checkPassword(sc.next())) {
						car.engineStart();
						System.out.println("시동 켜짐");
						car.wrongCount = 0;
					}
			}else {
			
					car.wrongCount++;
					if(car.wrongCount > 2) {
						System.out.println("경찰 출동");
						flag = true;
						
					}
					System.out.println("비밀번호"+car.wrongCount+"회 오류");
					
				}
				break;
		}
			System.out.println("시동이 이미 켜져이씁니다");
			break;
	//   브랜드, 색상 가격
	////시동이 꺼져있다면, "시동 켜기" 출력
	////이미 시동이 켜져있다면, "시동이 이미 켜져있습니다" 출력
	//
	//
	////시동 끄기
	////엔진 상태를 확인하고
	////시동이 켜져있다면, "시동 끄기" 출력
	////이미 시동이 꺼져있다면, 시동이 이미 꺼져있습니다. 출력
	//
	////시동을 켜기 위해서 비밀번호 4자리를 입력받아야 한다.
	////3번연속 잘못 입력했을 시 "경찰 출동" 출력
	////문자열 비교는 ==이 아닌 equals()로 비교한다
		
	}
	
}
}