package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;



public class ShowinfoController implements Initializable{
	@FXML
	private Stage stage;
	
	@FXML
    private Button	BtnDelete ;
	
	@FXML
    private Button BtnQuit;
	
	
	@FXML
    private Button BtnCancel;

    @FXML
    private Button BtnAdd;

    @FXML
    private Label ShowDateLabel;

    @FXML
    private Label ShowDescriptionLabel;

    @FXML
    private Label ShowIntegerLabel;

    @FXML
    private Label ShowLocationLabel;

    @FXML
    private Label ShowTimeLabel;

    @FXML
    private Label ShowTitleLabel;
    
    @FXML
    private HBox Layout;
    
    @FXML
    private Button BtnProfile;
    
    @FXML
    private Label HostName;
    
    private int Joinperson=0;
    
    private int currentnumber;
    
    private int limit=0;
    
    private DatingController dc;
    
    private Profile Userprofile;
    @FXML
    private ImageView HostImage;

    private ActivityInformation post;
    
    private Profile host;
    
    private Parent root;
    
    private Database db = new Database();
    
    private List<Profile> waiting = new ArrayList<>();

    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
    	FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("Dating.fxml"));
    	try {
			root = fxmlloader.load();
			dc = fxmlloader.getController();
			Userprofile = dc.getProfile();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
	
		
	}
    @FXML
    public void ShowHostInfo(ActionEvent event) throws IOException {
    	ShowProfileInfo(host);
    	
    }

    
    public void ShowProfileInfo(Profile p) throws IOException {
    	FXMLLoader Profileloader = new FXMLLoader(getClass().getResource("Showprofile.fxml"));
		root = (Parent) Profileloader.load();	
		ShowprofileController spc = Profileloader.getController();
		spc.setProfiledata(p);
		stage = new Stage();
		Scene scene = new Scene(root);
		stage.setTitle("NCCU Tinder");
        stage.setScene(scene);
        stage.show();
    	
    	
    	
    }
    
    
    @FXML
    public void DeleteThePost(ActionEvent event) throws SQLException {
    	//only creator can see the delete button and delete the post 
    	// check from db and drop in db
    	db.deletePost(post.getPostID());
    	Stage stage = (Stage) BtnDelete.getScene().getWindow();
        stage.close();

    	
    }
    
    public void SetDetailData(ActivityInformation info) throws IOException, SQLException {
    	
    	updatewaitingList(info);
    	this.host = db.getProfile(info.getHostID());
    	System.out.print(info.getHostID());
    	ShowTitleLabel.setText(info.getTitle());
    	ShowLocationLabel.setText(info.getLocation());
    	if(info.getDate()!=null) {
    		ShowDateLabel.setText(info.getDate().toString());
    	}
    	ShowTimeLabel.setText(info.getTime());
    	ShowDescriptionLabel.setText(info.getDescription());
    	HostName.setText(host.getName());
    	if(host.getImage()!= null) {
    		Image hostimage = new Image(host.getImage());
        	HostImage.setImage(hostimage);
    	}
    	
    	info.setPostID(db.getPostId(info.getTitle(), info.getHostID()));
    	this.post = info;
    	
    	if( post.getHostID() == Userprofile.getID()) {
    		BtnDelete.setVisible(true);
    		
    	}
    	Updatewaitingperson();
    	
		
	}
    public void Updatewaitingperson() {
	    Task<List<Profile>> waitingTask = db.getWaitingList(post.getPostID());
	    waitingTask.setOnSucceeded(e -> {
	        waiting = waitingTask.getValue();
	        Joinperson = waiting.size();
	        currentnumber = post.getPeoplenumbers();
	        ShowIntegerLabel.setText(String.format("%d / %d", Joinperson, currentnumber));
	    });
	    new Thread(waitingTask).start();
	}
    
   public void adding(ActivityInformation post, Profile Userprofile) throws SQLException, Error {
	   if( post.getHostID() == Userprofile.getID()) {
   		throw new Error("你不能加入自己的貼文");
   		
	   	}
	
	   	if (Joinperson == currentnumber) {
	   	    throw new Error("加入人數已滿");
	   	}
	   	
	   	if (db.doesProfileExist(Userprofile.getID(),post.getPostID())) {
	   		throw new Error("你已加入此活動");
	   	}
	   	
	   	db.addToWaitingList(post.getPostID(), Userprofile.getID());
	   	Updatewaitingperson() ;
	   	
	   
   }

   
    //加入成為等待成員
    @FXML
    public void AddinWaitingList(ActionEvent event) throws IOException, SQLException {
    	try {
    		
    		adding(post,Userprofile) ;
    		updatewaitingList(post) ;
    		
    	}catch (Error  e) {
			// TODO Auto-generated catch block
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		}

    }
    
    //退出貼文將使用者從等待名單中刪除
    @FXML
    public void QuitPost(ActionEvent event) throws IOException, SQLException {

    	try{
    		
	    	
	    	if(!db.doesProfileExist(Userprofile.getID(),post.getPostID())) {
	    		throw new Error("你不在等待名單中 無法退出");
	    	}
	    	
			db.removeFromWaitingList(post.getPostID(), Userprofile.getID());
			Updatewaitingperson() ;
			updatewaitingList(post) ;
			
			
	    }catch (Error  e) {
				// TODO Auto-generated catch block
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setContentText(e.getMessage());
				alert.showAndWait();
			}
		}
	    	
    
    public void updatewaitingList(ActivityInformation post) {
        Task<List<Profile>> waitingTask = db.getWaitingList(post.getPostID());
        waitingTask.setOnSucceeded(e -> {
            waiting = waitingTask.getValue();
            List<Profile> profiles = new ArrayList<>(waiting);
            Layout.getChildren().clear();
            if (profiles.size() > 0) {
                for (int i = 0; i < profiles.size(); i++) {
                    FXMLLoader profileloader = new FXMLLoader();
                    profileloader.setLocation(getClass().getResource("WaitingList.fxml"));
                    try {
                        AnchorPane waitp = profileloader.load();
                        WaitingListController wc = profileloader.getController();
                        wc.setProfile(profiles.get(i));
                        Layout.getChildren().add(waitp);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            } else {
                Layout.getChildren().clear();
            }
        });
    }


    public void setStage(Stage stage) {
        this.stage = stage;
    }
	public Profile getHost() {
		return host;
	}
	public void setHost(Profile host) {
		this.host = host;
	}

	public int getJoinperson() {
		return Joinperson;
	}

	public void setJoinperson(int joinperson) {
		Joinperson = joinperson;
	}
	
	


	


	

}
