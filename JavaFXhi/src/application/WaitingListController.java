package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class WaitingListController {
	@FXML
	private Label AddName;
	@FXML
	private ImageView AddImage;
	
	private Profile profile;
	
	@FXML
	private Button BtnWaitingProfile;
	private Parent root;
	private Stage stage;
	private Scene scene;
	
	
	
	
	public void ShowProfileInfo(Profile p) throws IOException {
    	FXMLLoader Profileloader = new FXMLLoader(getClass().getResource("Showprofile.fxml"));
		root = (Parent) Profileloader.load();	
		ShowprofileController spc = Profileloader.getController();
		spc.setProfiledata(p);
		
		stage = new Stage();
		scene = new Scene(root);
		stage.setTitle("NCCU Tinder");
        stage.setScene(scene);
        stage.show();
    	
    }
	
	
	@FXML
	public void showWaitingProfile(ActionEvent event) throws IOException {
		ShowProfileInfo(profile) ;
		
		
	}
	
	public Profile getProfile() {
		return profile;
	}
	
	
	public void setProfile(Profile profile) {
		this.profile = profile;
		Image image = new Image(profile.getImage());
		AddImage.setImage(image);
		AddName.setText(profile.getName());
	}
	

	public Label getAddName() {
		return AddName;
	}
	public void setAddName(Label addName) {
		AddName = addName;
	}
	public ImageView getAddImage() {
		return AddImage;
	}
	public void setAddImage(ImageView addImage) {
		AddImage = addImage;
	}





	

	
	
	

}
