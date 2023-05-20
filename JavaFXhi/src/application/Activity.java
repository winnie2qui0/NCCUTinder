package application;

import java.util.ArrayList;

public class Activity {
	
	private String description;
	private int peoplenumbers;
	private String date;
	private ArrayList<Profile> profiles;
	private Profile host;
	private String title;
	
	public Activity(String date, String title, String description, int num) {
		this.date = date;
		this.title = title;
		this.description = description;
		this.peoplenumbers = num;
	}
	
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPeoplenumbers() {
		return peoplenumbers;
	}
	public void setPeoplenumbers(int peoplenumbers) {
		this.peoplenumbers = peoplenumbers;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public ArrayList<Profile> getProfiles() {
		return profiles;
	}
	public void setProfiles(ArrayList<Profile> profiles) {
		this.profiles = profiles;
	}
	public Profile getHost() {
		return host;
	}
	public void setHost(Profile host) {
		this.host = host;
	}
	
	public void addProfile(Profile p) {
		
		profiles.add(p);
		
	}
	
	public void deleteProfile(Profile p) {
			
		profiles.remove(p);
			
	}



	
	
	
	

}
