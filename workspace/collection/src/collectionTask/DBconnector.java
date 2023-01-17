package collectionTask;

import java.util.ArrayList;

public class DBconnector {
	   public static ArrayList<User> userDB = new ArrayList<User>();
	   
	   public ArrayList<User> getConnection(){
	      return this.userDB;
	  }
}