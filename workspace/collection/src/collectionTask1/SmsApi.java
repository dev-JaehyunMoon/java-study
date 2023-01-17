package collectionTask1;

import java.util.HashMap;

import org.json.simple.JSONObject;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

public class SmsApi {
	public void sendSMS(String varifyKey, String userPhoneNumber) {
		String api_key = "NCSA4UXTATWDSAWG";
	       String api_secret = "HFL7EYWWG24QVDJOUYZG3TM5VBCDS2PG";
	       Message coolsms = new Message(api_key, api_secret);

	       // 4 params(to, from, type, text) are mandatory. must be filled
	       HashMap<String, String> params = new HashMap<String, String>();
	       params.put("to", "01034442331");
	       params.put("from", userPhoneNumber);
	       params.put("type", "SMS");
	       params.put("text", "varifyKey : " + varifyKey);
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
