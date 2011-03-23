package api.highrise;

import java.util.Calendar;

import org.w3c.dom.Element;

/***
 * Account Object for use with BaseCamp API
 * 
 * @author jondavidjohn
 * @author judeibe
 *
 */
public class Account extends HighriseEntity {
	
	//--- BaseCamp Account
	private Calendar 	createdAt;
	private int 		id;
	private Calendar 	updatedAt;
	private String 		companyName;
	private int 		accountHolderId;
	private boolean 	isSsl;
	//private boolean 	isTimeTracking;
	//private boolean 	isEmailNotify;
	private int 		storage;
	//private int 		primaryCompanyId;
	private String subscriptionPlan;
	private String theme;
	private int people;
	
	//--- Account Subscription information
//	public Subscription subscription;


	
	/***
	 * Returns info about the current user’s Highrise account.
	 * 
	 * @param auth	General Highrise account information object
	 */
	public Account(HRAuth auth) {
		
		super(auth);
		
		Element accountElement = super.get("/account.xml");
		
		//load up Account 
		this.createdAt 		  = ElementValue.getDateTimeValue(accountElement, "created-at");
		this.id 		  	  = ElementValue.getIntValue(accountElement,  "id");
		this.updatedAt 		  = ElementValue.getDateTimeValue(accountElement, "updated-at");
		this.companyName 	  = ElementValue.getTextValue(accountElement, "name");
		this.accountHolderId  = ElementValue.getIntValue(accountElement,  "owner-id");
		this.isSsl 			  = ElementValue.getBoolValue(accountElement, "ssl-enabled");
		//this.isTimeTracking   = ElementValue.getBoolValue(accountElement, "time-tracking");
		//this.isEmailNotify 	  = ElementValue.getBoolValue(accountElement, "email-notification-enabled");
		this.storage 		  = ElementValue.getIntValue(accountElement,  "storage");
		//this.primaryCompanyId = ElementValue.getIntValue(accountElement,  "primary-company-id");
		this.subscriptionPlan = ElementValue.getTextValue(accountElement, "plan");
		this.theme			  = ElementValue.getTextValue(accountElement, "color_theme");
		this.people		  = ElementValue.getIntValue(accountElement, "people-count");
		
		//get 'subscription' sub element
		//NodeList nl = accountElement.getElementsByTagName("subscription");
		//Element subscriptionElement = (Element)nl.item(0);
				
		//Create new Subscription object from inner element
		//this.subscription = new Subscription(subscriptionElement);
		
	}

	//--- Getters

	/**
	 * @return the createdAt
	 */
	public Calendar getCreatedAt() {
		return createdAt;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the updatedAt
	 */
	public Calendar getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @return the accountHolderId
	 */
	public int getAccountHolderId() {
		return accountHolderId;
	}

	/**
	 * @return the isSsl
	 */
	public boolean isSsl() {
		return isSsl;
	}

	
	/**
	 * @return the isTimeTracking
	 */
	
//	public boolean isTimeTracking() {
//		return isTimeTracking;
//	}


	/**
	 * @return the isEmailNotify
	 */ 
//	public boolean isEmailNotify() {
//		return isEmailNotify;
//	}

	/**
	 * @return the storage
	 */
	public int getStorage() {
		return storage;
	}

	/**
	 * @return the subscriptionPlan
	 */
	public String getSubscriptionPlan() {
		return subscriptionPlan;
	}


	/**
	 * @return the theme
	 */
	public String getTheme() {
		return theme;
	}


	/**
	 * @return the peoples
	 */
	public int getPeople() {
		return people;
	}

	/**
	 * @return the primaryCompanyId
	 */
//	public int getPrimaryCompanyId() {
//		return primaryCompanyId;
//	}

	/**
	 * @return the subscription
	 */
//	public Subscription getSubscription() {
//		return subscription;
//	}




	//--- Subscription Class
/*	public class Subscription {
		
		private boolean isTimeTracking;
		private long    storage;
		private String	name;
		private boolean	isSsl;
		private String	writeboards;
		private int		projects;
		
		*//***
		 * Initializes Inner Subscription object with the Account
		 * 
		 * (Internal Use Only)
		 * 
		 * @param subscription	Subscription element used to build subscription object.
		 *//*
		public Subscription(Element subscription) {
			
			this.isTimeTracking = ElementValue.getBoolValue(subscription, "time-tracking");
			this.storage 		= ElementValue.getLongValue(subscription, "storage");
			this.name 			= ElementValue.getTextValue(subscription, "name");
			this.isSsl 			= ElementValue.getBoolValue(subscription, "ssl");
			this.writeboards 	= ElementValue.getTextValue(subscription, "writeboards");
			this.projects 		= ElementValue.getIntValue(subscription, "projects");
			
		}
		
		//--- Getters

		*//**
		 * @return the isTimeTracking
		 *//*
		public boolean isTimeTracking() {
			return isTimeTracking;
		}

		*//**
		 * @return the storage
		 *//*
		public long getStorage() {
			return storage;
		}

		*//**
		 * @return the name
		 *//*
		public String getName() {
			return name;
		}

		*//**
		 * @return the isSsl
		 *//*
		public boolean isSsl() {
			return isSsl;
		}

		*//**
		 * @return the writeboards
		 *//*
		public String getWriteboards() {
			return writeboards;
		}

		*//**
		 * @return the projects
		 *//*
		public int getProjects() {
			return projects;
		}
		

	}*/
}
