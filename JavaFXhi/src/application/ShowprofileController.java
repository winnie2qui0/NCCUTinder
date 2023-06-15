package application;

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

public class ShowprofileController {
	@FXML
	private Label userName;
	@FXML
	private Label userDepartment;
	@FXML
	private Label userGrade;
	@FXML
	private Label userMBTI;
	@FXML
	private Label userMusic;
	@FXML
	private Label userMovie;
	@FXML
	private Label userBook;
	@FXML
	private Label userSport;
	@FXML
	private Label userCelebrity;
	@FXML
	private Label userDrinkinghabitSmokinghabit;
	@FXML
	private Label userSexualperference;
	@FXML
	private Button exit;
	@FXML
	private Label userPurpose;
	
	@FXML
	private ImageView ShowProfileImage;
	
	@FXML
	private Label userGender;
	@FXML
	private Label userIG;
	@FXML
	private Label userFB;
	
	
	public void setProfiledata(Profile p) {
		userName.setText(p.getName());
		userDepartment.setText(p.getDepartment());
		userGrade.setText(Integer.toString(p.getGrade()));
		userMBTI.setText(p.getMBTI());
		userMusic.setText(p.getMusic());
		userMovie.setText(p.getMovies());
		userBook.setText(p.getBook());
		userGender.setText(p.getGender());
		userIG.setText(p.getInstagram());
		userFB.setText(p.getFacebook());
		userPurpose.setText(p.getPurpose());
		
		userCelebrity.setText(p.getCelebrity());
		if(p.getDrinkinghabit()==1 && p.getSmokinghabit()==1){
			userDrinkinghabitSmokinghabit.setText("會喝酒 會抽煙");
		}
		else if(p.getDrinkinghabit()==0 && p.getSmokinghabit()==1) {
			userDrinkinghabitSmokinghabit.setText("不會喝酒 會抽煙");
		}
		else if(p.getDrinkinghabit()==1 && p.getSmokinghabit()==0) {
			userDrinkinghabitSmokinghabit.setText("會喝酒 不會抽煙");
		}
		else {
			userDrinkinghabitSmokinghabit.setText("不會喝酒 不會抽煙");
		}
		
		userSexualperference.setText(p.getSexualperference());
		Image image = new Image(p.getImage());
		ShowProfileImage.setImage(image);
	}
	
	@FXML
	public void exitToShowInfo(ActionEvent event) {
		Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
	
	}

}
