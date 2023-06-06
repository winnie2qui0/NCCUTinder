package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
	
		
	}
    @FXML
    public void ShowHostInfo(ActionEvent event) throws IOException {
    	ShowProfileInfo(host);
    	
    }

    
    public void ShowProfileInfo(Profile p) throws IOException {
    	FXMLLoader Profileloader = new FXMLLoader(getClass().getResource("Showprofile.fxml"));
		root = (Parent) Profileloader.load();	
		ShowprofileController spc = Profileloader.getController();
		spc.setProfiledate(p);
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
    	this.post = info;
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
    	
    	post.setPostID(db.getPostId(info.getTitle(), info.getHostID()));
    	System.out.printf("this is test of adding list has correct post id and profileid, postid :"+info.getPostID());
    	
    	FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("Dating.fxml"));
    	root = fxmlloader.load();
    	DatingController dc = fxmlloader.getController();
    	if( post.getHostID() == dc.getProfile().getID()) {
    		BtnDelete.setVisible(true);
    		
    	}
    	
    	
		waiting = db.getWaitingList(post.getPostID());
    	
    	
    	//顯示目前加入人數
		this.Joinperson = waiting.size();
    	currentnumber =info.getPeoplenumbers();
    	
    	ShowIntegerLabel.setText(String.format("%d / %d",  Joinperson,currentnumber));
    
    	
    	List<Profile> profiles = new ArrayList<>(waiting);
		Layout.getChildren().clear();
		if(profiles.size()>0) {
			for(int i= 0; i< profiles.size();i++) {
				FXMLLoader profileloader = new FXMLLoader();
				profileloader.setLocation(getClass().getResource("WaitingList.fxml"));
				
				try {
					AnchorPane waitp = profileloader.load();
					WaitingListController ac = profileloader.getController();
					ac.ShowProfileInfo(profiles.get(i));
					ac.SetNewGuest(profiles.get(i).getName(), profiles.get(i).getImage());
					Layout.getChildren().add(waitp);
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	
    			}
    	}
    	
		
	}
   

   
    //加入成為等待成員
    @FXML
    public void AddinWaitingList(ActionEvent event) throws IOException, SQLException {
    	try {
    		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("Dating.fxml"));
        	root = fxmlloader.load();
        	DatingController dc = fxmlloader.getController();
        	if( post.getHostID() == dc.getProfile().getID()) {
        		throw new Error("你不能加入自己的貼文");
        		
        	}
        	
        	if (limit == 1) {
        	    throw new Error("你已加入此活動");
        	}

        	if (Joinperson == currentnumber) {
        	    throw new Error("加入人數已滿");
        	}
        	
        	waiting = db.getWaitingList(post.getPostID());
        	if(waiting.contains(dc.getProfile().getID())) {
        		throw new Error("你已加入此活動");
        	}

        	db.addToWaitingList(post.getPostID(), dc.getProfile().getID());
        	Joinperson++;
        	limit++;
        	ShowIntegerLabel.setText(String.format("%d/%d", Joinperson, currentnumber));
        	waiting = db.getWaitingList(post.getPostID());
        	List<Profile> profiles = new ArrayList<>(waiting);
        	
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("Activityitem.fxml"));
        	root = loader.load();
        	ActivityitemController ac = loader.getController();
        	ac.setData(post);
        	ac.Updatewaitingperson();
        	
    		Layout.getChildren().clear();
    		if(profiles.size()>0) {
    			for(int i= 0; i< profiles.size();i++) {
    				FXMLLoader profileloader = new FXMLLoader();
    				profileloader.setLocation(getClass().getResource("WaitingList.fxml"));
    				
    				try {
    					AnchorPane waitp = profileloader.load();
    					WaitingListController wc = profileloader.getController();
    					wc.SetNewGuest(profiles.get(i).getName(), profiles.get(i).getImage());
    					Layout.getChildren().add(waitp);
    					
    					
    				} catch (IOException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
        	
    			}
    		
    		}
    	}catch (Error  e) {
			// TODO Auto-generated catch block
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		}

    }
    //退出貼文
    @FXML
    public void QuitPost(ActionEvent event) throws IOException, SQLException {
    	
    	
    	FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("Dating.fxml"));
    	root = fxmlloader.load();
    	DatingController dc = fxmlloader.getController();
    	waiting = db.getWaitingList(post.getPostID());
    	if(!waiting.contains(dc.getProfile())) {
    		throw new Error("你不在等待名單中 無法退出");
    	}
    	
		db.removeFromWaitingList(post.getPostID(), dc.getProfile().getID());
		Joinperson--;
		limit--;
		ShowIntegerLabel.setText(String.format("%d / %d", Joinperson, currentnumber));
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Activityitem.fxml"));
    	root = loader.load();
    	ActivityitemController ac = loader.getController();
    	ac.setData(post);
    	ac.Updatewaitingperson();
    	
		waiting = db.getWaitingList(post.getPostID());
    	List<Profile> profiles = new ArrayList<>(waiting);
    	Layout.getChildren().clear();
		if(profiles.size()>0) {
			for(int i= 0; i< profiles.size();i++) {
				FXMLLoader profileloader = new FXMLLoader();
				profileloader.setLocation(getClass().getResource("WaitingList.fxml"));
				
				try {
					AnchorPane waitp = profileloader.load();
					WaitingListController wc = profileloader.getController();
					wc.SetNewGuest(profiles.get(i).getName(), profiles.get(i).getImage());
					Layout.getChildren().add(waitp);
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}catch (Error  e) {
					// TODO Auto-generated catch block
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error");
					alert.setContentText(e.getMessage());
					alert.showAndWait();
				}
    	
			}
		}
		else {
			Layout.getChildren().clear();
		}
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
