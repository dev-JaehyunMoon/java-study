package lambdaTest;

public class LambdaTest {
	public static void main(String[] args) {
//		LambdaInter lambdaInter = (a) -> a % 10 == 0;
//		boolean result = lambdaInter.checkMultipleOf10(11);
//		System.out.println(result);
		LambdaInter lambdaInter = (number) -> {
			System.out.println("10의 배수 검사");
			return number % 10 == 0;
		};
		boolean result = lambdaInter.checkMultipleOf10(100);
		System.out.println(result);
	}

}
