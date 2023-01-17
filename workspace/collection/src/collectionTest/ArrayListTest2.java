package collectionTest;

import java.util.ArrayList;
import java.util.Collections;

public class ArrayListTest2 {
	public static void main(String[] args) {
		
		ArrayList<Integer> datas = new ArrayList<Integer>();
		final int SIZE = 9;
		
		for (int i = 0; i < SIZE; i++) {
			if(i > 4) {
				datas.add((14 - i) * 10);
				System.out.println(datas.size());
				continue;
				
			}
			datas.add((i+1)*10);
			System.out.println(datas.size());
			
		}
		for (int i = 0; i < datas.size(); i++) {
			System.out.println(datas.get(i));
			
		}
		Collections.sort(datas);
	}

}
