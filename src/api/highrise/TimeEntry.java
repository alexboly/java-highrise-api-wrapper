package api.highrise;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import org.w3c.dom.Element;

/***
 * TimeEntry Object for use with BaseCamp API
 * 
 * @author jondavidjohn
 *
 */
public class TimeEntry extends HighriseEntity{
	
	private int 		id;
	private int 		projectId; 
	private int 		personId;
	private int 		toDoItemId;
	private double 		hours;
	private String 		description;
	private Calendar 	date;
	
	/***
	 * Empty (Unsaved) Time Entry Object
	 * 
	 * NOTE: IS NOT SAVED TO BASECAMP UNTIL CALL TO `.save()`
	 * 
	 * @param auth	BCAuth Object
	 */
	public TimeEntry(HRAuth auth) {
		super(auth);
		
		this.id 		 = 0;
		this.projectId 	 = 0;
		this.personId 	 = 0;
		this.date 		 = null;
		this.hours 		 = 0.0;
		this.description = "";
		this.toDoItemId  = 0;
		
	}
	
	/***
	 * Build time entry from ID
	 * 
	 * @param auth			BCAuth Object
	 * @param timeEntryId	ID of entry
	 */
	public TimeEntry(HRAuth auth, int timeEntryId) {
		
		super(auth);
		
		Element timeEntryElement = super.get("/time_entries/"+timeEntryId+".xml");
		
		this.id 		 = timeEntryId;
		this.projectId 	 = ElementValue.getIntValue(timeEntryElement, "project-id");
		this.personId 	 = ElementValue.getIntValue(timeEntryElement, "person-id");
		this.date 		 = ElementValue.getDateValue(timeEntryElement, "date");
		this.hours 		 = ElementValue.getDoubleValue(timeEntryElement, "hours");
		this.description = ElementValue.getTextValue(timeEntryElement, "description");
		this.toDoItemId  = ElementValue.getIntValue(timeEntryElement, "todo-item-id");
		
	}

	/***
	 * Build Time Entry from XML Element
	 * 
	 * (Internal Use Only)
	 * 
	 * @param auth				BCAuth
	 * @param timeEntryElement	XML Element representation of entry
	 */
	TimeEntry(HRAuth auth, Element timeEntryElement) {
		
		super(auth);
				
		this.id 		 = ElementValue.getIntValue(timeEntryElement, "id");
		this.projectId 	 = ElementValue.getIntValue(timeEntryElement, "project-id");
		this.personId 	 = ElementValue.getIntValue(timeEntryElement, "person-id");
		this.date 		 = ElementValue.getDateValue(timeEntryElement, "date");
		this.hours 		 = ElementValue.getDoubleValue(timeEntryElement, "hours");
		this.description = ElementValue.getTextValue(timeEntryElement, "description");
		this.toDoItemId  = ElementValue.getIntValue(timeEntryElement, "todo-item-id");
		
	}
	
	/***
	 * 
	 * Save the time Entry in its current state
	 * 
	 * Will also create new if entry is un-saved ( id is 0 )
	 * 
	 * @return	Boolean depending on success of operation
	 */
	public boolean save() {
		
		int result;
		
		if (this.id == 0) {  //New Entry
			
			String requestString = this.postString();
			
			
			if (this.toDoItemId != 0){  //Attach to toDo
				
				result = super.post("/todo_items/"+this.toDoItemId+"/time_entries.xml", requestString);
				
				if (result != 0) {
					this.id = result;
					return true;
				}
				else {
					return false;
				}

			}
			else {
				
				result = super.post("/projects/"+this.projectId+"/time_entries.xml", requestString);
				
				if(result != 0) {
					this.id = result;
					return true;
				}
				else {
					return false;
				}
			}
			
		}
		else {                        //Update entry
			
			String requestString = this.postString();
			
			return super.put("/time_entries/"+this.id+".xml", requestString);
			
		}
		
	}
	
	/***
	 * 
	 * Delete this entry from the BaseCamp System
	 * 
	 * @return Boolean depending on operation success
	 */
	public boolean trash() {
		 
		return super.delete("/time_entries/"+this.id+".xml");	
		
	}
	
	private String postString() {
		
		String request;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		sdf.setTimeZone(TimeZone.getTimeZone("Zulu"));
		
		request  = "<time-entry>";
		request += "<person-id>"+this.personId+"</person-id>";
		request += "<date>"+sdf.format(this.date)+"</date>";
		request += "<hours>"+this.hours+"</hours>";
		request += "<description>"+this.description+"</description>";
		if (this.toDoItemId != 0) {
			request += "<todo-item-id>"+this.toDoItemId+"</todo-item-id>";
		}
		request += "</time-entry>";
			

		
		return request;
	}

	//--- Getters
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the projectId
	 */
	public int getProjectId() {
		return projectId;
	}

	/**
	 * @return the personId
	 */
	public int getPersonId() {
		return personId;
	}

	/**
	 * @return the toDoItemId
	 */
	public int getToDoItemId() {
		return toDoItemId;
	}

	/**
	 * @return the hours
	 */
	public double getHours() {
		return hours;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the date
	 */
	public Calendar getDate() {
		return date;
	}
	
	//--- Setters

	/**
	 * @param projectId the projectId to set
	 */
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	/**
	 * @param personId the personId to set
	 */
	public void setPersonId(int personId) {
		this.personId = personId;
	}

	/**
	 * @param toDoItemId the toDoItemId to set
	 */
	public void setToDoItemId(int toDoItemId) {
		this.toDoItemId = toDoItemId;
	}

	/**
	 * @param hours the hours to set
	 */
	public void setHours(double hours) {
		this.hours = hours;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Calendar date) {
		this.date = date;
	}
	


}
