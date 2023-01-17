package collectionTask1;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class UserField {
	public ArrayList<User> arUser = new DBConnecter().getConnection();
	private final int ENCRYPTION_KEY = 3;
	
	public User checkId(String userId) {
		User result = null;
		for(User user : arUser) {
			if(user.getUserId().equals(userId)) {
				return user;
			}
		}
		
		return result;
	}
	
	public void join(String userId, String userPw, String userName, String userPhoneNumber) {
		User user = new User();
		
		if(checkId(userId) != null) {
			return;
		}
		
		user.setUserId(userId);
		user.setUserPw(encryptPw(userPw));
		user.setUserName(userName);
		user.setUserPhoneNumber(userPhoneNumber);
		
		arUser.add(user);
	}
	
	public User login(String userId, String userPw) {
		for(User user : arUser) {
			if(user.getUserId().equals(userId) && user.getUserPw().equals(encryptPw(userPw))) {
				return user;
			}
		}
		
		return null;
	}
	
	private String sendVerify(String userId, String userPhoneNumber) {
		User user = checkId(userId);
		String verifyNumber = null;
		
		Random rd = new Random();
		SmsApi api = new SmsApi();
		
//		�쑀�� �븘�씠�뵒媛� �뾾�쑝硫� null 由ы꽩
		if(user == null) {
			return verifyNumber;
		}
		
//		�쑀�� �빖�뱶�룿 踰덊샇媛� �씪移섑븯吏� �븡�쑝硫� null 由ы꽩
		if(!user.getUserPhoneNumber().equals(userPhoneNumber)) {
			return verifyNumber;
		}
		
//		5�옄由ъ쓽 �옖�뜡�븳 �닽�옄 �씤利앸쾲�샇 �깮�꽦
		verifyNumber = "" + rd.nextInt(9) + rd.nextInt(9999);
		api.sendSMS(verifyNumber, userPhoneNumber);
		
		return verifyNumber;
	}
	
	public void changePw(String userId, String newUserPw, String userPhoneNumber) {
		Scanner sc = new Scanner(System.in);
		
		User user = null;
		
		String verifyNumber = sendVerify(userId, userPhoneNumber);
		
		if(verifyNumber == null) {
			return;
		}
		
		System.out.println("�씤利앸쾲�샇 �엯�젰.");
		
		if(!sc.next().equals(verifyNumber)) {
			System.out.println("�씤利앸쾲�샇媛� �떎由낅땲�떎.");
		}
		
		user = checkId(userId);
		user.setUserPw(encryptPw(newUserPw));
		System.out.println("鍮꾨�踰덊샇 蹂�寃� �꽦怨�.");
	}
	
	private String encryptPw(String userPw) {
		String encryptedPw = "";
		for(char c : userPw.toCharArray()) {
			encryptedPw += (char)(c - ENCRYPTION_KEY);
		}
		
		return encryptedPw;
	}
}
