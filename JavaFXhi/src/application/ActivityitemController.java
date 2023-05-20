package application;


import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ActivityitemController {
	
	@FXML
	private Label title;
	@FXML
	private Label description;
	@FXML
	private Label peoplenumbers;
	@FXML
	private Label date;
	
	
	
	public void setData(Activity activity) {
		
		title.setText(activity.getTitle());
		description.setText(activity.getDescription());
		peoplenumbers.setText(Integer.toString(activity.getPeoplenumbers()));
		date.setText(activity.getDate());
		
		
		
	}
	
	
	
	
	

}
