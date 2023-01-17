package hashMapTest;

import java.util.HashMap;

public class HashMapTest {
	public static void main(String[] args) {
//		아이디,비밀번호 ,이름, 나이
		HashMap<String, Object> userMap = new HashMap<String, Object>();
		
		
		userMap.put("id","hsd1234");
		userMap.put("password","1234");
		userMap.put("name","한동석");
		userMap.put("name","강민구");
		userMap.put("age","20");
		
		System.out.println(userMap);
		System.out.println(userMap.get("name"));
			
		}
	}


