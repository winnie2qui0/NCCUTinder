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
	public Profile getProfile() {
		return profile;
	}
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	@FXML
	private Button BtnWaitingProfile;
	private Parent root;
	private Stage stage;
	
	
	public void SetNewGuest(String Name,String Image) {
		
		Image image = new Image(Image);
		AddImage.setImage(image);
		AddName.setText(Name);
		
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
	public void showWaitingProfile(ActionEvent event) throws IOException {
		ShowProfileInfo(profile) ;
		
		
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
