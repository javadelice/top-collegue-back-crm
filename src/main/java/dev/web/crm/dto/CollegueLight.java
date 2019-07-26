package dev.web.crm.dto;

public class CollegueLight {

	private String firstName;
    private String lastName;
    private String photoUrl;
    
    
    
    
	public CollegueLight() {
		super();
	}


	public CollegueLight(String firstName, String lastName, String photoUrl) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.photoUrl = photoUrl;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getPhotoUrl() {
		return photoUrl;
	}


	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	
	
	
	
    
    
    
}
