package study;

import java.util.Scanner;

public class Self {
	public static void main(String[] args) {
		
		int firstNumber = 0,  lastNumber = 0;
		String message = "정수 두 개를 입력하세요";
		Scanner sc = new Scanner(System.in);
		
		System.out.println(message);
		firstNumber = sc.nextInt();
		lastNumber = sc.nextInt();
		
		Object result = firstNumber > lastNumber ? "큰 값 : " + firstNumber 
				: firstNumber == lastNumber ? "두 수는 같다."   : "큰 값 :" + lastNumber;
		System.out.println(result);
	}
}
