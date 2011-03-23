package api.highrise;

import org.w3c.dom.Element;

public class Category extends HighriseEntity{

	private int		id;
	private String	name;
	private int		projectId;
	private int		itemCount;
	private boolean	type;
	
	static public final boolean POST 	= true;
	static public final boolean ATTACH	= false;
	
	/***
	 * Get Category By ID
	 * @param auth			BCAuth Object
	 * @param categoryId	ID of desired Category
	 */
	public Category(HRAuth auth, int categoryId) {
		super(auth);
		
		Element catElement = super.get("/categories/"+categoryId+".xml");
		
		this.id 		= ElementValue.getIntValue(catElement, "id");
		this.name 		= ElementValue.getTextValue(catElement, "name");
		this.projectId 	= ElementValue.getIntValue(catElement, "project-id");
		this.itemCount 	= ElementValue.getIntValue(catElement, "element-count");
		String tempType = ElementValue.getTextValue(catElement, "type");
		
		if ( tempType == "post") {
			this.type = Category.POST;
		}
		else if ( tempType == "attachment" ) {
			this.type = Category.ATTACH;
		}
	}
	
	/***
	 * Create new category
	 * 
	 * @param auth			BCAuth Object
	 * @param projectId	 	Project ID you want category to be attached to
	 * @param categoryType	Category Type [ Category.POST || Category.ATTACH ]
	 * @param categoryName	Name of Category
	 */
	public Category(HRAuth auth, int projectId, boolean categoryType, String categoryName) {
		super(auth);
		
		String typeString = type ? "post" : "attachment";
		String request;
		
		request  = "<category>";
		request += "<type>"+typeString+"</type>";
		request += "<name>"+categoryName+"</name>";
		request += "</category>";
		
		int createdId = super.post("/projects/"+projectId+"/categories.xml", request);
		
		Element catElement = super.get("/projects/"+createdId+".xml");
		
		this.id 		= ElementValue.getIntValue(catElement, "id");
		this.name 		= ElementValue.getTextValue(catElement, "name");
		this.projectId 	= ElementValue.getIntValue(catElement, "project-id");
		this.itemCount 	= ElementValue.getIntValue(catElement, "element-count");
		String tempType = ElementValue.getTextValue(catElement, "type");
		
		if ( tempType == "post") {
			this.type = Category.POST;
		}
		else if ( tempType == "attachment" ) {
			this.type = Category.ATTACH;
		}
	}
	
	/***
	 * Build Project from XML Element
	 * 
	 * (Internal Use Only)
	 * 
	 * @param auth				BCAuth Object
	 * @param projectElement	XML Element representation of Project
	 */
	Category(HRAuth auth, Element catElement) {
		super(auth);
		
		this.id 		= ElementValue.getIntValue(catElement, "id");
		this.name 		= ElementValue.getTextValue(catElement, "name");
		this.projectId 	= ElementValue.getIntValue(catElement, "project-id");
		this.itemCount 	= ElementValue.getIntValue(catElement, "element-count");
		String tempType = ElementValue.getTextValue(catElement, "type");
		
		if ( tempType == "post") {
			this.type = Category.POST;
		}
		else if ( tempType == "attachment" ) {
			this.type = Category.ATTACH;
		}
		
	}
	
	/***
	 * 
	 * Update the Category in its current state (only Name editable)
	 *  
	 * @return	Boolean depending on success of operation
	 */
	public boolean save() {
			
		String request;
		
		request  = "<category>";
		request += "<name>"+this.name+"</name>";
		request += "</category>";
			
		return super.put("/categories/"+this.id+".xml", request);
			
		
	}
	
	/***
	 * 
	 * Delete this Category from the BaseCamp System
	 * 
	 * @return Boolean depending on operation success
	 */
	public boolean trash() {
		 
		return super.delete("/categories/"+this.id+".xml");
		
	}

	//--- Getters
	
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
	 * @return the projectId
	 */
	public int getProjectId() {
		return projectId;
	}

	/**
	 * @return the itemCount
	 */
	public int getItemCount() {
		return itemCount;
	}

	/**
	 * @return the type
	 */
	public boolean isType() {
		return type;
	}
	
	//--- Setters

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	

	
	
	
	
}
