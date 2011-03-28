package api.highrise;

import java.util.Calendar;

import org.w3c.dom.Element;

public class Me extends HighriseEntity {

	private int id;
	private String name;
	private String emailAddress;
	private Calendar createdAt;
	private Calendar updatedAt;
	private String apiToken;
	private String dropboxEmail;
	
	public Me(HRAuth auth) {
		super(auth);
		Element userElement = super.get("/me.xml");
		
		this.id				= ElementValue.getIntValue(userElement, "id");
		this.name			= ElementValue.getTextValue(userElement, "name");
		this.emailAddress	= ElementValue.getTextValue(userElement, "email-address");
		this.createdAt		= ElementValue.getDateTimeValue(userElement, "created-at");
		this.updatedAt		= ElementValue.getDateTimeValue(userElement, "updated-at");
		this.apiToken		= ElementValue.getTextValue(userElement, "token");
		this.dropboxEmail	= ElementValue.getTextValue(userElement, "dropbox");

	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * @return the createdAt
	 */
	public Calendar getCreatedAt() {
		return createdAt;
	}

	/**
	 * @return the updatedAt
	 */
	public Calendar getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * @return the apiToken
	 */
	public String getApiToken() {
		return apiToken;
	}

	/**
	 * @return the dropboxEmail
	 */
	public String getDropboxEmail() {
		return dropboxEmail;
	}

}