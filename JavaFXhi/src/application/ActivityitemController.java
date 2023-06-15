package application;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;
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
	
	@FXML
	private Button addbutton;

	
	private ActivityInformation acti;
	
	private Profile profile;
	private Profile user;
	
	private int Joinperson=0;
    private int currentnumber;
	private Stage stage;
	private Scene scene;
	private Parent root;
	private DatingController dc;
	private Database db = new Database();
	private List<Profile> waiting = new ArrayList<>();
	
	
	private List<ActivityInformation> Posts =new  ArrayList<ActivityInformation>();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("Dating.fxml"));
    	try {
			root = fxmlloader.load();
			dc = fxmlloader.getController();
			user = dc.getProfile();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@FXML
	public void AddinList(ActionEvent event) throws IOException, SQLException, Error {
		try {
				if( acti.getHostID() == profile.getID()) {
			   		throw new Error("你不能加入自己的貼文");
			   		
				   	}
				 
				   	if (Joinperson == currentnumber) {
				   	    throw new Error("加入人數已滿");
				   	}
				   	
				   	if (db.doesProfileExist(profile.getID(),acti.getPostID())) {
				   		throw new Error("你已加入此活動");
				   	}
				db.addToWaitingList(acti.getPostID(), profile.getID());
				Updatewaitingperson() ;
		}
		catch (Error  e) {
			// TODO Auto-generated catch block
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		}

    
	}
	
	public void setData(ActivityInformation activity) throws SQLException {
		
		title.setText("『"+activity.getTitle()+"』");
		description.setText(activity.getDescription());
		if(activity.getDate()!=null) {
			date.setText(activity.getDate().toString());
		}
		
		this.acti = activity;
		this.profile= activity.getHost();
		acti.setPostID(db.getPostId(activity.getTitle(), activity.getHostID()));
		
		Updatewaitingperson() ;
		Posts.add(acti);
		
		System.out.print(acti.getTitle());

		
		
	}
	
	public void Updatewaitingperson() {
	    Task<List<Profile>> waitingTask = db.getWaitingList(acti.getPostID());
	    waitingTask.setOnSucceeded(e -> {
	        waiting = waitingTask.getValue();
	        Joinperson = waiting.size();
	        currentnumber = acti.getPeoplenumbers();
	        if(Joinperson==currentnumber) {
	        	 peoplenumbers.setText("已滿");
	        }else {
	        	peoplenumbers.setText(String.format("%d / %d", Joinperson, currentnumber));
	        }
	        
	    });
	    new Thread(waitingTask).start();
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
