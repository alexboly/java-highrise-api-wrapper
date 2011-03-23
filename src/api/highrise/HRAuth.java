package api.highrise;

/***
 * 
 * Object used to Authenticate with Highrise
 * 
 * @author jon
 *
 */
public class HRAuth {

	public String 	username;
	public String 	password;
	public String 	company;
	public boolean 	useSsl;
	
	/***
	 * Init Empty HRAuth Object
	 * 
	 * Note: properties are public, so you can init them one by one if needed
	 */
	public HRAuth() {
		
		this.username = "";
		this.password = "";
		this.company  = "";
		this.useSsl	  = false;
		
	}
	
	/***
	 * Initialize Highrise Authentication Object
	 *
	 * @param un	Highrise Username
	 * @param pw	Highrise Password
	 * @param cp	Highrise Company Name (for url purposes)
	 * @param ssl	if ssl is enabled on your account
	 */
	public HRAuth(String un, String pw, String cp, boolean ssl) {
		
		this.username = un;
		this.password = pw;
		this.company  = cp;
		this.useSsl	  = ssl;
		
	}
	
}
