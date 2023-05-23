package application;


import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ActivityitemController {
	
	@FXML
	private Label title;
	@FXML
	private Label description;
	@FXML
	private Label peoplenumbers;
	@FXML
	private Label date;
	@FXML
	private Button Btndetial;
	
	private ActivityInformation acti;
	
	
	
	public void setData(ActivityInformation activity) {
		
		title.setText(activity.getTitle());
		description.setText(activity.getDescription());
		peoplenumbers.setText(Integer.toString(activity.getPeoplenumbers()));
		date.setText(activity.getDate().toString());
		this.acti = activity;
		
		
		
	}
	
	public void ShowActivityDetail(ActionEvent event) {
	
		FXMLLoader fxmlloader = new FXMLLoader();
		fxmlloader.setLocation(getClass().getResource("Showinfo.fxml"));
		
		try {
			VBox Vbox = fxmlloader.load();
			ActivityitemController ac = fxmlloader.getController();
			ac.setData(acti);
			Scene scene = new Scene(Vbox );
			Stage stage = (Stage) Vbox.getScene().getWindow();
	        stage.setScene(scene);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
	
	
	
	
	

}
