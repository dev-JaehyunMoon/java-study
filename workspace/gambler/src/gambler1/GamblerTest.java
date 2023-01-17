package gambler1;

import java.util.Random;
import java.util.Scanner;

class Dobak{
	static int total;
	boolean check;
	int income;
	String name;
	int restmoney;
	int batting;
	{
		batting = 1;
	}
	public Dobak() {}
	
	public Dobak(int income, String name, int restmoney) {
		
		this.batting = batting;
		this.restmoney = restmoney;
				
		this.income = income;
		this.name = name;
	}
	
	void batting() {
		if(batting == 1? true : false);
	}
	void batting2() {
		if(batting == 1? false : true);
	}
	
	
	
}

public class GamblerTest {
//	private static String restmoney;

	public static void main(String[] args) {
		Dobak ¹®ÀçÇö = new Dobak();
		Random random = new Random();
		String message = "1. È¦ \n2. Â¦ ";
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		
		System.out.println(message);
		choice = sc.nextInt();
		
		
		
			
		
		int[] rating = new int[10];
		int data = 50;
		
		for (int i = 0; i < data / 10; i++)	{
			rating[i] = 1;
			
		
		if(rating[random.nextInt(10)]==1) {
			System.out.println("È¦");
		
				
		}else {
			System.out.println("Â¦");
		}
				
				
				
	}

}
}
