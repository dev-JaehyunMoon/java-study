package study;

public class ArrayStudy {
	public static void main(String[] args) {
		
//		int[] arData = new int[5];
//		
//		for (int i =0; i < arData.length; i++) {
//			arData[i] = 10-i *2;
//			System.out.println(arData[i]);
//			
//		}
		int[] arData = new int[100];
		for (int i = 0; i < arData.length; i++) {
			arData[i]=i+1;
			if(arData[i] % 2 == 1) {
				System.out.println(arData[i]);
			}
		}
	}

}
