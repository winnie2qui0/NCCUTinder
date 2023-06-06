package application;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Parent;

public class ActivityitemController implements Initializable{
	
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
	
	private Profile profile;
	
	private int Joinperson=0;
    private int currentnumber;
	private Stage stage;
	private Scene scene;
	private Parent root;
	private Database db = new Database();
	 private List<Profile> waiting = new ArrayList<>();
	
	private List<ActivityInformation> Posts =new  ArrayList<ActivityInformation>();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		
	}
	
	public void setData(ActivityInformation activity) throws SQLException {
		
		title.setText(activity.getTitle());
		description.setText(activity.getDescription());
		if(activity.getDate()!=null) {
			date.setText(activity.getDate().toString());
		}
		
		this.acti = activity;
		this.profile= activity.getHost();
		acti.setPostID(db.getPostId(activity.getTitle(), activity.getHostID()));
		Updatewaitingperson() ;
//		Posts.add(acti);
		
//		System.out.print(acti.getTitle());
		
		
//		waiting = db.getWaitingList(activity.getPostID());
//		this.Joinperson = waiting.size();
//		currentnumber = activity.getPeoplenumbers();
//		peoplenumbers.setText(String.format("%d / %d",  Joinperson,currentnumber));
		
		
	}
	public void Updatewaitingperson() throws SQLException {
		waiting = db.getWaitingList(acti.getPostID());
		this.Joinperson = waiting.size();
		currentnumber = acti.getPeoplenumbers();
		peoplenumbers.setText(String.format("%d / %d",  Joinperson,currentnumber));
	
		
	}
	
	public Profile getProfile() {
		return profile;
	}
	public void setProfile(Profile p) {
		this.profile = p;
	}
	
	public ActivityInformation getPost() {
		return acti;
	}
	public void setPost(ActivityInformation i) {
		this.acti = i;
	}
	
	
	@FXML
	public void ShowActivityDetail(ActionEvent event) throws SQLException {
		
			
		try {
			FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("Showinfo.fxml"));
			root = (Parent) fxmlloader.load();	
			ShowinfoController sc = fxmlloader.getController();
			sc.SetDetailData(acti);
			stage = new Stage();
			scene = new Scene(root);
			stage.setTitle("NCCU Tinder");
	        stage.setScene(scene);
	        stage.show();
	       
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
	
	
	public List<ActivityInformation> getPosts() {
		return Posts;
	}
	
	

	
	
	

	
	
	
	

}
