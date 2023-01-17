package exceptionTest;

public class ExceptionSolo {
	public static void main(String[] args) {
		try {
			System.out.println(10/0);
			System.out.println("연산 성공!00");
		}catch(ArithmeticException e) {
			System.out.println("0으로 나눌 수 없습니다.");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
