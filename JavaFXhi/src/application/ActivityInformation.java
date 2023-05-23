package application;

import java.time.LocalDate;
import java.util.ArrayList;

public class ActivityInformation extends Activity{
	private String description;
	private int peoplenumbers;
	private LocalDate date;
	private String Location;
	private String time;
	private ArrayList<Profile> profiles;
	private Profile host;
	private String title;
	private ArrayList<ActivityInformation> Posts =new  ArrayList<ActivityInformation>();
	
	
	public ActivityInformation(String title, String location, LocalDate date, String time , int num, String description, Profile host) {
		
		super( date , title , description , num) ;
		this.time = time;
		this.Location =location;
		this.host =host;
		
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


		public LocalDate getDate() {
			return date;
		}


		public void setDate(LocalDate date) {
			this.date = date;
		}


		public String getLocation() {
			return Location;
		}


		public void setLocation(String location) {
			Location = location;
		}


		public String getTime() {
			return time;
		}


		public void setTime(String time) {
			this.time = time;
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


		public String getTitle() {
			return title;
		}


		public void setTitle(String title) {
			this.title = title;
		}


		public ArrayList<ActivityInformation> getPosts() {
			return Posts;
		}
		
		
		
		
		
		public void addProfile(Profile p) {
			
			profiles.add(p);
			
		}
		
		public void deleteProfile(Profile p) {
				
			profiles.remove(p);
				
		}



}
