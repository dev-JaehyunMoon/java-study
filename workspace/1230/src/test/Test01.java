package test;

import java.util.Scanner;

public class Test01 {
	 public static void main(String[] args) {
	      
	      // 사용자에게 키를 입력 받고
	      // 정수라면 정수로 출력
	      // 실수라면 실수로 출력
	      // 예) 183 -> 183 출력
	      // 183.5 -> 183.5
	      // 삼항 연산자를 사용!
	      // 뺼샘으로 풀면 쉬움. 서식문자로 풀면 더 쉬움
	      
	      /*
	       * 입력 받을 변수 선언
	       * scanner 변수 선언
	       * 출럭 메세지 출력
	       * 입력받을 수 입력
	       * 정수 + 정수 = 정수 비교대상 정하기 입력값에 0을 더했을때 같은값이 나오면 참 아니면 거짓
	       * 정수 + 실수 = 실수
	       * 
	       */
		 int a = 0;
		 int b = 0;
	      Scanner sc = new Scanner(System.in);
	      String message = "키를 입력하세요. ";
	      a = sc.nextInt();
	      System.out.println(message);
	      
	   

	}

}
