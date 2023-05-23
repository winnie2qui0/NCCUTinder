package application;
import java.util.*;

public class User {
	private ArrayList<String> username; 
	private ArrayList<String> password;
	private ArrayList<Profile> profiles;
	private boolean valid ;
	private Profile profile;
	
	
	public User() {
		username = new ArrayList<String>(); 
		password = new ArrayList<String>();
		profiles = new ArrayList<Profile>();
		valid = false;
	}
	
	public void add(String name, String pw) throws PasswordError, UserError{
		if (name.length() == 0) throw new UserError("Username can't be empty");
		if (pw.length() < 8) throw new PasswordError("Password should be at least 8 letter");
		
		valid = true;
		username.add(name); 
		password.add(pw); 
		profile = new Profile(name,pw);
		
		//建立profile db 加入新的profile
		
		
		
		return;
	}
	public boolean getValid() {
		return valid;
	}
	
	public Profile getProfile(String name) {
		Profile u = null;
		for(Profile p: profiles) {
			if(p.getUsername().equals(name)){
				u = p ;
			}
		}
		return u ;
		
		
	}
	
	
	public void checkUserExist(String name) throws UserError { 
		if(username.contains(name)) return;
		throw new UserError("Can't find the user");
		
	}
	
	public void checkPassword(String name, String PW) throws PasswordError { 
		int id = username.indexOf(name);
		if(password.get(id).equals(PW)) return;
		throw new PasswordError("Password is wrong");
	} 
	
	
}
class UserError extends Exception { 
	
	public UserError(String Error){
		super(Error);
	}
}
class PasswordError extends Exception {
	
	public PasswordError(String Error){ 
		super(Error);
	} 
}