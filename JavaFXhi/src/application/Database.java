package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;

public class Database {
	
	private String server ;
	private String database;
	private String url;
	private String username;
	private String password;
	
	
	public Database() {
	this.server = "jdbc:mysql://140.119.19.73:3315/";
	this.database = "110306092"; // change to your own database
	this.url = server + database + "?useSSL=false";
	this.username = "110306092"; // change to your own username
	this.password = "4azcv"; // change to your own password
	DBConnected();
	
	}
	public void DBConnected() {
	
	try {
		
		Connection conn = DriverManager.getConnection(url, username, password);
		System.out.println("DB Connectd");
		Statement stat = conn.createStatement();
		//adjust query code later
	    String query = "SELECT * FROM twice";
	    ResultSet result = stat.executeQuery(query);
	        
		
	} 
	catch (SQLException e) {
		
		e.printStackTrace();
	}
	}
//	
//	public static String showResultSet(ResultSet result) throws SQLException {
//        ResultSetMetaData metaData = result.getMetaData();
//        int columnCount = metaData.getColumnCount();
//        String output = "";
//        for (int i = 1; i <= columnCount; i++) {
//               output += String.format("%15s", metaData.getColumnLabel(i));
//        }
//        output += "\n";
//        while (result.next()) {
//               for (int i = 1; i <= columnCount; i++) {
//                     output += String.format("%15s", result.getString(i));
//               }
//               output += "\n";
//        }
//        return output;
// }

}
