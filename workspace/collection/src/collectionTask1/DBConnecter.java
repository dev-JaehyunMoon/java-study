package collectionTask1;

import java.util.ArrayList;

public class DBConnecter {
	ArrayList<User> userDB = new ArrayList<User>();
	
	public ArrayList<User> getConnection(){
		return this.userDB;
	}
}
