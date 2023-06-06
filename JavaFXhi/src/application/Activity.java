package application;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Activity {
	
	private String description;
	private int peoplenumbers;
	private LocalDate date;
	private String title;
	private java.sql.Date sqlDate;
	
	public Activity( LocalDate date , String title , String description ,int num) {
		
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
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		// TODO Auto-generated method stub
		this.date =date;
	}
	
	public void setLocalDate(java.sql.Date sqlDate) {
		this.date = sqlDate.toLocalDate();
		
	}
	public void setsqlDate(LocalDate date) {
		
		if (this.date != null) {
		    sqlDate = java.sql.Date.valueOf(this.date);
		    // Use sqlDate when inserting into the database.
		} else {
		    System.out.println("Date is null");
		}
		
	}
	public java.sql.Date getsqlDate(){
		return sqlDate;
	}

	


}
