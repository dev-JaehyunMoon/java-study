package arrayListTask3;

import java.util.ArrayList;

public class Restaurant {
	ArrayList<Food> foods = new ArrayList<Food>();
//	- 음식 추가
	
	public void add(Food food) {
		foods.add(food);
	}
//	   - 음식 이름으로 음식 종류 조회
	public String findByName(String name) {
		for (Food food : foods) {
			if(food.getName().equals(name)) {
				return food.getKind();
			}
		}
		return null;
	}
	
	
//	   - 사용자가 원하는 종류의 음식 전체 조회
	public ArrayList<Food> findAllKind(String kind){
		ArrayList<Food> results = new ArrayList<Food>();
		for (Food food : foods) {
			if(food.getKind().equals(kind)) {
			}
		}
		return results;
	
		
	}
//	   - 음식 종류 수정 후 가격 10% 상승
//	   - 사용자가 원하는 종류의 음식 개수 조회
	
}