package application;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class Database {
	
		
	  
	private int hostId = -1;
	private String server ;
	private String database;
	private String url;
	private String username;
	private String password;
	private Connection conn;
	private PreparedStatement stat;
	
	public Database() {
		this.server = "jdbc:mysql://140.119.19.73:3315/";
		this.database = "110306092"; // change to your own database
		this.url = server + database + "?useSSL=false";
		this.username = "110306092"; // change to your own username
		this.password = "4azcv"; // change to your own password
		
		

	}
	

	//Update Profile
	public void update(Profile profile)throws SQLException {
		String query = "UPDATE Profile SET Image=?, Name=?, Age=?, Instagram=?, Facebook=?, Dep=?, Grade=?, MBTI=?, Movie=?,"
				+ " Music=?, Book=?, Celebrity=?, Drinking=?,Smoking=?, SexualOrientation=?, Purpose=?, Gender =? WHERE UserName=?";
		
		try(Connection conn = DriverManager.getConnection(url, username, password);
	            PreparedStatement stat = conn.prepareStatement(query)) 
		{
			stat.setObject(1, profile.getImage());
			stat.setString(2, profile.getName());
			stat.setInt(3, profile.getAge());
			stat.setString(4, profile.getInstagram());
			stat.setString(5, profile.getFacebook());
			stat.setString(6, profile.getDepartment());
			stat.setInt(7, profile.getGrade());
			stat.setString(8, profile.getMBTI());
			stat.setString(9, profile.getMovies());
			stat.setString(10, profile.getMusic());
			stat.setString(11, profile.getBook());
			stat.setString(12, profile.getCelebrity());
			stat.setInt(13, profile.getDrinkinghabit());
			stat.setInt(14, profile.getSmokinghabit());
			stat.setString(15, profile.getSexualperference());
			stat.setString(16, profile.getPurpose());
			stat.setString(17, profile.getGender());
			stat.setString(18, profile.getUsername());
			
			stat.executeUpdate();
			
		} catch (SQLException e) {
	        e.printStackTrace();
		} finally {
			if (stat != null) {
	    		stat.close();
	    	}
			if(conn != null) {
	        	conn.close();
	        }
	    }
	}
	//Update Password
		public void updatePassword(Profile profile)throws SQLException {
			String query = "UPDATE Profile SET Password= ? WHERE ID=?";
			
			try(Connection conn = DriverManager.getConnection(url, username, password);
		            PreparedStatement stat = conn.prepareStatement(query)) 
			{
				stat.setString(1, profile.getPassword());
				stat.setInt(2, profile.getID());
				stat.executeUpdate();
				
			} catch (SQLException e) {
		        e.printStackTrace();
			} finally {
				if (stat != null) {
		    		stat.close();
		    	}
				if(conn != null) {
		        	conn.close();
		        }
		    }
		}


	//Insert Profile
		 public void insertProfile(Profile profile) {
		        String sql = "INSERT INTO Profile(Image, Name, UserName, Password, Age, Instagram, Facebook, Dep, Grade, MBTI, Movie, Music, Book, Celebrity, Drinking, Smoking, SexualOrientation, Purpose, Gender) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";

		        try (Connection conn = DriverManager.getConnection(url, username, password);
		            PreparedStatement ps = conn.prepareStatement(sql)) 
		        {

		            // Set all the values using your Profile object's getters
		            // Replace profile.getXXX() with your actual methods
		            ps.setString(1, profile.getImage());
		            ps.setString(2, profile.getName());
		            ps.setString(3, profile.getUsername());
		            ps.setString(4, profile.getPassword());
		            ps.setInt(5, profile.getAge());
		            ps.setString(6, profile.getInstagram());
		            ps.setString(7, profile.getFacebook());
		            ps.setString(8, profile.getDepartment());
		            ps.setInt(9, profile.getGrade());
		            ps.setString(10,profile.getMBTI());
		            ps.setString(11, profile.getMovies());
		            ps.setString(12, profile.getMusic());
		            ps.setString(13, profile.getBook());
		            ps.setString(14, profile.getCelebrity());
		            ps.setInt(15, profile.getDrinkinghabit());
		            ps.setInt(16, profile.getSmokinghabit());
		            ps.setString(17, profile.getSexualperference());
		            ps.setString(18, profile.getPurpose());
		            ps.setString(19, profile.getGender());
		            ps.executeUpdate();

		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		 
		 
	public boolean checkUser(String name)throws SQLException  {
		   String sql = "SELECT UserName, is_enrolled FROM Profile WHERE UserName = ?";
		   boolean userexit = false;
	        try (Connection conn = DriverManager.getConnection(url, username, password);
	            PreparedStatement stat = conn.prepareStatement(sql)) 
	        {
	        	stat.setString(1, name);
	        	
	        	ResultSet resultSet = stat.executeQuery();
	            if (resultSet.next()) {
	                int isEnrolled = resultSet.getInt("is_enrolled");

	                if (isEnrolled == 1) {
	                	userexit = true;
	                    System.out.println("User has enrolled. User logged in successfully.");
	                    // Additional login logic or actions can be performed here
	                } else {
	                	userexit = false;
	                    System.out.println("User is not enrolled. Please enroll to proceed.");
	                    // Additional logic for user enrollment can be implemented here
	                }
	            } else {
	                System.out.println("User not found.");
	            }
	        	
	            resultSet.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }finally {
	        	if (stat != null) {
		    		stat.close();
		    	}
				if(conn != null) {
		        	conn.close();
		        	
		        }
		    }
	        return userexit;
	      
	        
		
	}
	
	
	public boolean checkPassword(String name, String pword) throws SQLException {
		   String sql = "SELECT  UserName, Password FROM Profile WHERE UserName = ?";
		   boolean rightpassword = false;
		   
//	       
		   
		   try( Connection conn = DriverManager.getConnection(url, username, password);
	           PreparedStatement ps = conn.prepareStatement(sql)
	        		 
			){
	        	ps.setString(1, name);
	        	
	        	ResultSet resultSet = ps.executeQuery();
	        	
	            if (resultSet.next()) {
	            	String dbpassword = resultSet.getString("Password");
	                String uname = resultSet.getString("UserName");

	                if (uname.equals(name) && dbpassword.equals(pword)) {
	                	rightpassword = true;
	                    System.out.println("Correct password. User logged in successfully.");
	                    // Additional login logic or actions can be performed here
	                } else {
	                	rightpassword = false;
	                    System.out.println("Wrong password. Please type your password right.");
	                    // Additional logic for user enrollment can be implemented here
	                }
	            } else {
	                System.out.println("User not found.");
	            }
	            
	            resultSet.close(); // Close the result set
	            ps.close();
	        	
	        	
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }finally {
	        	if (stat != null) {
		    		stat.close();
		    	}
				if(conn != null) {
		        	conn.close();
		        }
		    }
	        return rightpassword;
	        
		
	}
	
	//Insert ActivityInformation
	public void insert(ActivityInformation activity)throws SQLException {
			
			String query = "INSERT INTO Post (Description, Peoplenumber, Date, Time, ProfileID, Title ,Location) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?)";

			 try (Connection conn = DriverManager.getConnection(url, username, password);
			      PreparedStatement stat = conn.prepareStatement(query))  {
		    	stat.setString(1, activity.getDescription());
		        stat.setInt(2, activity.getPeoplenumbers());
		        stat.setDate(3, activity.getsqlDate());
		        stat.setString(4, activity.getTime());
		        hostId = getUserId(activity.getHost().getUsername());
		        if (hostId != -1) {
		            stat.setInt(5, hostId);
		        } else {
		        	 stat.setInt(5, 0);
		        	throw new SQLException("Host not found in profile table");
		        }
		        stat.setString(6, activity.getTitle());
		        stat.setString(7, activity.getLocation());

		        stat.executeUpdate();
		        
		        
		        
		        		
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		    	if (stat != null) {
		    		stat.close();
		    	}
				if(conn != null) {
		        	conn.close();
		        }
		    }
		}
	
	public int getPostId(String title, int hostID) throws SQLException {
	     // initializing with a non-valid value
	    String query = "SELECT PostID FROM Post WHERE Title = ? AND ProfileID = ? ";
	    int postId=0;
	    try(Connection conn = DriverManager.getConnection(url, username, password);
	        PreparedStatement stat = conn.prepareStatement(query)) 
	    {
	        stat.setString(1, title);
	        stat.setInt(2, hostID);
	        
	        ResultSet rs = stat.executeQuery();
	        if (rs.next()) {
	        	postId = rs.getInt("PostID");
	        }
	        
	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }finally {
	    	if (stat != null) {
	    		stat.close();
	    	}
			if(conn != null) {
	        	conn.close();
	        }
			
         }
	
	    
	    return postId;
	}
	
	
	//gethost id
	public int getUserId(String name) throws SQLException {
	     // initializing with a non-valid value
	    String query = "SELECT ID FROM Profile WHERE UserName = ?";
	    int UserId=0;
	    try(Connection conn = DriverManager.getConnection(url, username, password);
	            PreparedStatement stat = conn.prepareStatement(query)) 
	    {
	        stat.setString(1, name);
	        
	        ResultSet rs = stat.executeQuery();
	        if (rs.next()) {
	        	 UserId = rs.getInt("ID");
	        }
	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }finally {
	    	if (stat != null) {
	    		stat.close();
	    	}
			if(conn != null) {
	        	conn.close();
	        }
	    }
	    
	    return UserId;
	}
	//使用者退出貼文
	public void removeFromWaitingList(int postId, int profileId) throws SQLException {
	    String query = "DELETE FROM WaitingList WHERE postId = ? AND profileId = ?";

	    try (Connection conn = DriverManager.getConnection(url, username, password);
	         PreparedStatement stat = conn.prepareStatement(query))  {

	        stat.setInt(1, postId);
	        stat.setInt(2, profileId);

	        stat.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }finally {
	    	if (stat != null) {
	            
				stat.close();
			
         }if(conn != null) {
	        	conn.close();
	        }
	    }
	}

	//使用者加入貼文
	public void addToWaitingList(int postId, int profileId) throws SQLException {
	    String query = "INSERT INTO WaitingList (postId, profileId) VALUES (?, ?)";

	    try (Connection conn = DriverManager.getConnection(url, username, password);
	         PreparedStatement stat = conn.prepareStatement(query))  {

	        stat.setInt(1, postId);
	        stat.setInt(2, profileId);

	        stat.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }finally {
	    	 if (stat != null) {
	            
					stat.close();
				
	         }if(conn != null) {
		        	conn.close();
		        }
	    }
	}
	
	//從貼文中撈出正在等待的使用者
	public List<Profile> getWaitingList(int postId) throws SQLException {
	    List<Profile> waitingList = new ArrayList<>();

	    String query = "SELECT Profile.* FROM Profile " +
	        "JOIN WaitingList ON Profile.ID = WaitingList.profileId " +
	        "WHERE WaitingList.postId = ?";

	    try (Connection conn = DriverManager.getConnection(url, username, password);
	         PreparedStatement stat = conn.prepareStatement(query))  {

	        stat.setInt(1, postId);

	        ResultSet rs = stat.executeQuery();

	        while(rs.next()) {
	            Profile profile = getProfile(rs.getInt("ID"));// create a profile from the result set
	      
	            waitingList.add(profile);
	        }
	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }finally {
	    	 if (stat != null) {
	             stat.close();
	         }if(conn != null) {
		        	conn.close();
		        }
	    }

	    return waitingList;
	}


	
	//Delete Profile
	//判斷條件：用戶名稱
	public void delete(String userName)throws SQLException{
			String query = "DELETE FROM profile WHERE username = ?";
			
		    try (Connection conn = DriverManager.getConnection(url, username, password);
				PreparedStatement stat = conn.prepareStatement(query))
					
		    	{

		    	stat.setString(1, username);
		        stat.executeUpdate();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		    	 if (stat != null) {
		             stat.close();
		         }if(conn != null) {
			        	conn.close();
			        }
		    }
		}

	//Delete Post
	//判斷條件：POST ID
		public void deletePost(int postid) throws SQLException {
		    String query = "DELETE FROM Post WHERE PostID = ?";

		    try {
		    	Connection conn = DriverManager.getConnection(url, username, password);
		        stat = conn.prepareStatement(query);
		        
		        stat.setInt(1,postid );

		        stat.executeUpdate();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        if (stat != null) {
		            stat.close();
		        }
		        if (conn != null) {
		            conn.close();
		        }
		       
		    }
		}

	
	
	//Get Profile
	public Profile getProfile(int ProfileID) throws SQLException {
		String query = "SELECT * FROM Profile WHERE ID=?";
		
		Profile profile = new Profile(null,null);
		try(Connection conn = DriverManager.getConnection(url, username, password);
	       PreparedStatement stat = conn.prepareStatement(query)) 
		{	
			stat.setInt(1, ProfileID);
		
			
	        ResultSet rs = stat.executeQuery();
	        if (rs.next()) {
	        	profile.setUsername(rs.getString("UserName"));
	        	profile.setPassword(rs.getString( "Password"));
	        	profile.setImage(rs.getString("Image"));
	        	profile.setName( rs.getString("Name"));
	        	profile.setGender(rs.getString("Gender"));
	        	profile.setAge(rs.getInt("Age"));
	        	profile.setInstagram(rs.getString("Instagram"));
	        	profile.setFacebook(rs.getString("Facebook"));
	        	profile.setDepartment(rs.getString("Dep"));
	        	profile.setGrade(rs.getInt("Grade"));
	        	profile.setMBTI(rs.getString("MBTI"));
	        	profile.setMovies(rs.getString("Movie"));
	        	profile.setMusic(rs.getString("Music"));
	        	profile.setBook(rs.getString("Book"));
	        	profile.setCelebrity(rs.getString("Celebrity"));
	        	profile.setDrinkinghabit(rs.getInt("Drinking"));
	        	profile.setSmokinghabit(rs.getInt("Smoking"));
	        	profile.setSexualperference(rs.getString("SexualOrientation"));
	        	profile.setPurpose(rs.getString("Purpose"));
	        	
	        }
			
			
	        rs.close();
		
		} catch (SQLException e) {
	        e.printStackTrace();
		} finally {
			 if (stat != null) {
		            stat.close();
		        }
			 if(conn != null) {
		        	conn.close();
		        }
	    }
		
		return profile;
}
		
		
		
		
		
	
	
	
	//Show Profile
		public void show(Profile profile, int userid)throws SQLException {
			String query = "SELECT* FROM Profile WHERE ID=?";
			
			try(Connection conn =  DriverManager.getConnection(url, username, password);
		            PreparedStatement stat = conn.prepareStatement(query)) 
			{
				stat.setInt(1, userid);
				
		

		        ResultSet rs = stat.executeQuery();
		        if (rs.next()) {
		        	profile.setImage(rs.getString("Image"));
		        	profile.setName( rs.getString("Name"));
		        	profile.setAge(rs.getInt("Age"));
		        	profile.setInstagram(rs.getString("Instagram"));
		        	profile.setFacebook(rs.getString("Facebook"));
		        	profile.setDepartment(rs.getString("Dep"));
		        	profile.setGender(rs.getString("Gender"));
		        	profile.setGrade(rs.getInt("Grade"));
		        	profile.setMBTI(rs.getString("MBTI"));
		        	profile.setMovies(rs.getString("Movie"));
		        	profile.setMusic(rs.getString("Music"));
		        	profile.setBook(rs.getString("Book"));
		        	profile.setCelebrity(rs.getString("Celebrity"));
		        	profile.setDrinkinghabit(rs.getInt("Drinking"));
		        	profile.setSmokinghabit(rs.getInt("Smoking"));
		        	profile.setSexualperference(rs.getString("SexualOrientation"));
		        	profile.setPurpose(rs.getString("Purpose"));
		        	
		        }
		        rs.close();
				
			} catch (SQLException e) {
		        e.printStackTrace();
			} finally {
				 if (stat != null) {
			            stat.close();
			        }
				 if(conn != null) {
			        	conn.close();
			        }
		    }
		}
	

	//Select ActivityInformation
	public List<ActivityInformation> GetActivity() throws SQLException {
	    List<ActivityInformation> activities = new ArrayList<>();
	    String query = "SELECT * FROM Post ORDER BY date DESC";

	    try(Connection conn =  DriverManager.getConnection(url, username, password);
        PreparedStatement stat = conn.prepareStatement(query))  {
	        
	        ResultSet resultSet = stat.executeQuery();

	        while (resultSet.next()) {
	        	ActivityInformation activity = new ActivityInformation(null, null, null, null, 0, null, null);
	            activity.setDescription(resultSet.getString("Description"));
	            activity.setPeoplenumbers(resultSet.getInt("Peoplenumber"));
	            activity.setLocation(resultSet.getString("Location"));
	            if(resultSet.getDate("Date")!=null) {
	            	activity.setLocalDate(resultSet.getDate("Date"));
	            }
	            activity.setTime(resultSet.getString("Time"));
	            activity.setHostID(resultSet.getInt("ProfileID"));
	            activity.setTitle(resultSet.getString("title"));

	            activities.add(activity);
	        }
	        resultSet.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        if (stat != null) {
	            stat.close();
	        }
	        if(conn != null) {
	        	conn.close();
	        }
	    }
	    return activities;
	}

	public String getServer() {
	    return server;
	}
	public String getDatabase() {
	    return database;
	}
	public String getUrl() {
	    return url;
	}
	public String getUsername() {
	    return username;
	}
	public String getPassword() {
	    return password;
	}

}