package application;

import java.time.LocalDate;
import java.util.ArrayList;

public class Activity {
	
	private String description;
	private int peoplenumbers;
	private LocalDate date;
	private String title;
	
	public Activity(LocalDate date , String title , String description ,int num) {
		
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
		this.date = date;
	}
	


}
