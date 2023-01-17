package apiTest;

import java.util.HashMap;

import org.json.simple.JSONObject;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

public class MessageTest {
	public static void main(String[] args) {
		String api_key = "NCS6TNTARQSHL8EF";
	       String api_secret = "XXC8CG3ZTAL4Y1X2AWUQ5KCVM5FABTYP";
	       Message coolsms = new Message(api_key, api_secret);

	       // 4 params(to, from, type, text) are mandatory. must be filled
	       HashMap<String, String> params = new HashMap<String, String>();
	       params.put("to", "01041998127");
	       params.put("from", "01083971216");
	       params.put("type", "SMS");
	       params.put("text", "³­ Áý¿¡°¡¼­ Àú³á¸Ô°í ÇØºÁ¾ß°ÚÀ½");
	       params.put("app_version", "test app 1.2"); // application name and version

	       try {
	         JSONObject obj = (JSONObject) coolsms.send(params);
	         System.out.println(obj.toString());
	       } catch (CoolsmsException e) {
	         System.out.println(e.getMessage());
	         System.out.println(e.getCode());
	       }
	}

}
