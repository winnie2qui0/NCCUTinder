package application;
import java.sql.SQLException;
import java.util.*;

public class User {
	private ArrayList<String> username; 
	private ArrayList<String> password;
	private ArrayList<Profile> profiles;
	private Database db = new Database();
	private boolean valid ;
	
	
	public User() {
		username = new ArrayList<String>(); 
		password = new ArrayList<String>();
		profiles = new ArrayList<Profile>();
		valid = false;
	}
	
	public void add(String name, String pw) throws PasswordError, UserError, SQLException{
		if (name.length() == 0) throw new UserError("Username can't be empty");
		else if (pw.length() < 8) throw new PasswordError("Password should be at least 8 letter");
		else if (db.checkPassword(name, pw) ) {
			throw new UserError("the user already exists");
		}
		else {
			valid = true;
			username.add(name); 
			password.add(pw); 
		}
		

		
		return;
	}
	public boolean getValid() {
		return valid;
	}
	
	public void addProfile(Profile p) {
		profiles.add(p);
	}
		
	
	
	
	public void checkUserExist(String name) throws UserError, SQLException { 
		if(db.checkUser(name)) return;
		throw new UserError("Can't find the user");
		
	}
	
	public void checkPassword(String name, String PW) throws PasswordError, SQLException { 
		
		if(db.checkPassword(name, PW)) return ;
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