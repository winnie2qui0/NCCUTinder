package application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class ActivityInformation extends Activity{
	
	private String Location;
	private String time;
	private ArrayList<Profile> profiles;
	private Profile host;
	private int PostID;
	private int hostID;
	
	
	
	public ActivityInformation(String title,String location,  LocalDate date, String time , int num, String description, Profile host) {
		
		super( date , title , description , num) ;
		this.time = time;
		this.Location =location;
		this.host =host;
	
		
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




		public int getPostID() {
			return PostID;
		}




		public void setPostID(int postID) {
			PostID = postID;
		}




		public int getHostID() {
			return hostID;
		}




		public void setHostID(int hostID) {
			this.hostID = hostID;
		}




		


		


}
