package application;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.FileChooser;

public class DatingController implements Initializable {
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	
	 FileChooser fileChooser = new FileChooser();
	 
	 @FXML
	 private Button BtnUpdate;
	
	 @FXML
	 private TextArea FileInput;
	 
	 @FXML
	 private Button FileButton;

	 @FXML
    private TextField AgeField;

    @FXML
    private TextField BookField;

    @FXML
    private Button BtnPost;

    @FXML
    private TextField CelebrityField;

    @FXML
    private TextField ContactFBField;

    @FXML
    private TextField ContactIGField;

    @FXML
    private CheckBox DrinkingCheck;

    @FXML
    private VBox Layout;

    @FXML
    private TextField MBTIField;

    @FXML
    private TextField MovieField;

    @FXML
    private TextField MusicField;

    @FXML
    private TextField NameField;

    @FXML
    private CheckBox NonDrinkingCheck;

    @FXML
    private CheckBox NonSmokingCheck;

    @FXML
    private TextField PasswordField;

    @FXML
    private DatePicker PostDatePicker;

    @FXML
    private TextArea PostDescriptionField;

    @FXML
    private TextField PostLocationField;

    @FXML
    private TextField PostPeopleField;

    @FXML
    private TextField PostTimeField;

    @FXML
    private TextField PostTitleField;

    @FXML
    private ChoiceBox<String> PurposeChoice;

    @FXML
    private TextField SexualPerferenceField;

    @FXML
    private CheckBox SmokingCheck;

    @FXML
    private ImageView UserImage;

    @FXML
    private Label UserNameLabel;

    @FXML
    private Button btnEditprofile;

    @FXML
    private Button btnSaveChanges;

    @FXML
    private Button btndating;

    @FXML
    private Button btnpasswordchange;

    @FXML
    private Button btnposting;

    @FXML
    private Button btnprofile;

    @FXML
    private Button btnsetting;

    @FXML
    private AnchorPane datingpage;

    @FXML
    private TextField departmentField;

    @FXML
    private TextField gradeField;
    
    @FXML
    private TextField GenderField;

    @FXML
    private Button logout;

    @FXML
    private AnchorPane postingpage;

    @FXML
    private AnchorPane profilepage;

    @FXML
    private AnchorPane settingpage;
    
    @FXML
    private Button BtnPostIt ;
    
    @FXML
    private Label NameLabel;
    
    @FXML
    private Label  UserIDLabel;
    
    
    

    
    private boolean isEditMode = true;
    
    private File file;
  

	private Database db = new Database();
	
    private LoginController loginController;
    
    private static Profile profile;
    
    public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	public Profile getProfile( ) {
		return profile;
	}
	
	public void setProfile(Profile p) {
    	this.profile = p ;
    }

    
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	    

	    // Move lengthy operations to another thread
	    CompletableFuture.runAsync(() -> {
	        try {
	        	
	    	    
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
	            root = loader.load();
	            loginController = loader.getController();
	            profile = loginController.getProfile();


	            // Database operation
	            int id = db.getUserId(profile.getUsername());
	            profile.setID(id);
	            db.show(profile, profile.getID());

	            // Update the UI on JavaFX Application Thread
	            Platform.runLater(() -> {
	                UserNameLabel.setText(profile.getUsername());
	                NameLabel.setText(profile.getUsername());
	                UserIDLabel.setText(Integer.toString(id));
	                updateGUIWithProfile(profile);
	                PurposeChoice.getItems().addAll("純交友", "找另一半", "就玩玩");

	                // Rest of your code
	                setFieldsEditable(false);
		    	    btnSaveChanges.setDisable(true);
	            });
	            
	        } catch (SQLException | IOException e) {
	            e.printStackTrace();
	        }
	    });
	}

	  
				
			
		
	
	 //把資料庫的posts撈出來 
	public void showThePosts() {
	    CompletableFuture<List<ActivityInformation>> actisFuture = CompletableFuture.supplyAsync(() -> {
	        try {
	            return new ArrayList<>(db.GetActivity());
	        } catch (SQLException | InterruptedException | ExecutionException e) {
	            // Handle exception...
	            return new ArrayList<>();
	        }
	    });

	    actisFuture.thenAccept(actis -> {
	        Platform.runLater(() -> {
	            Layout.getChildren().clear();
	            if(actis.size()>0) {
	                for(int i= 0; i< actis.size();i++) {
	                    FXMLLoader fxmlloader = new FXMLLoader();
	                    fxmlloader.setLocation(getClass().getResource("Activityitem.fxml"));

	                    try {
	                        HBox hbox = fxmlloader.load();
	                        ActivityitemController ac = fxmlloader.getController();
	                        ac.setData(actis.get(i));
	                        ac.setProfile(profile);
	                        ac.Updatewaitingperson();
	                        Layout.getChildren().add(hbox);
	                    } catch (IOException e) {
	                        // Exception handling...
	                    } catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                }
	            }
	        });
	    });
	}
	

	//更新post表單
	  @FXML
	private void UpdateThePost(ActionEvent event) throws SQLException {
		  showThePosts();
	}

   
    
    @FXML
    public void UploadImage(ActionEvent event) {
    	file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            Image image = new Image(file.toURI().toString());
            UserImage.setImage(image);
        }
    }
    
    
    
    @FXML
    public void btnchangesaving(ActionEvent event) {
    	
    	 // Update the profile changes in the database
        try 
        {
        	String newName = NameField.getText();
        	int newAge = Integer.parseInt(AgeField.getText());
        	String newIG = ContactIGField.getText();
        	String newFB = ContactFBField.getText();
        	String newDept = departmentField.getText();
        	int newGrade = Integer.parseInt(gradeField.getText());
        	String newGender = GenderField.getText();
        	String newMBTI = MBTIField.getText();
        	String newFavMovie = MovieField.getText();
        	String newFavMusic = MusicField.getText();
        	String newFavBook = BookField.getText();
        	String newFavCelebrity = CelebrityField.getText();
        	String newSexualPerference = SexualPerferenceField.getText();
            boolean newNonDrinking = NonDrinkingCheck.isSelected();
            boolean newNonSmoking = NonSmokingCheck.isSelected();
            boolean newDrinking = DrinkingCheck.isSelected();
            boolean newSmoking = SmokingCheck.isSelected();
            String newPurpose = PurposeChoice.getValue();
 
        	profile.setName(newName);
        	profile.setAge(newAge);
        	profile.setInstagram(newIG);
        	profile.setFacebook(newFB);
        	profile.setDepartment(newDept);
        	profile.setGrade(newGrade);
        	profile.setMBTI(newMBTI);
        	profile.setMovies(newFavMovie);
        	profile.setMusic(newFavMusic);
        	profile.setBook(newFavBook);
        	profile.setCelebrity(newFavCelebrity);
        	profile.setSexualperference(newSexualPerference);
        	profile.setPurpose(newPurpose);
        	profile.setGender(newGender);
        	if(file!=null) {
        		profile.setImage(file.toURI().toString());
        	}
        	
        	if(newNonDrinking) {
        		profile.setDrinkinghabit(0);
        	}else {
        		profile.setDrinkinghabit(1);
        	}
        	
        	if(newNonSmoking) {
        		profile.setSmokinghabit(0);
        	}else {
        		profile.setSmokinghabit(1);
        	}
        	
        	db.update(profile);


        } catch (SQLException e) {
            e.printStackTrace();
        }

        btnSaveChanges.setDisable(true);
        toggleEditMode();
        setFieldsEditable(false);
        
        

    }
    
    
    ///ADD METHOD///
    public void updateGUIWithProfile(Profile profile) {
    	
    	setFieldsEditable(isEditMode);
        btnSaveChanges.setDisable(!isEditMode);

    	NameField.setText(profile.getName());
        AgeField.setText(Integer.toString(profile.getAge()));
        ContactIGField.setText(profile.getInstagram());
        ContactFBField.setText(profile.getFacebook());
        departmentField.setText(profile.getDepartment());
        gradeField.setText(Integer.toString(profile.getGrade()));
        MBTIField.setText(profile.getMBTI());
        MovieField.setText(profile.getMovies());
        MusicField.setText(profile.getMusic());
        BookField.setText(profile.getBook());
        CelebrityField.setText(profile.getCelebrity());
        SexualPerferenceField.setText(profile.getSexualperference());
        GenderField.setText(profile.getGender());
        
        if(profile.getImage() != null) {
        	Image image = new Image(profile.getImage());
            UserImage.setImage(image);
        }
        
        if (profile.getDrinkinghabit() == 0) {
            NonDrinkingCheck.setSelected(true);
            DrinkingCheck.setSelected(false);
        } else {
            NonDrinkingCheck.setSelected(false);
            DrinkingCheck.setSelected(true);
        }
        
        if (profile.getSmokinghabit() == 0) {
            NonSmokingCheck.setSelected(true);
            SmokingCheck.setSelected(false);
        } else {
            NonSmokingCheck.setSelected(false);
            SmokingCheck.setSelected(true);
        }
        

        PurposeChoice.setValue(profile.getPurpose());
    }
    
    
    private void setFieldsEditable(boolean editable) {
        // Set the editable property for all input fields
    	NameField.setEditable(editable);
        AgeField.setEditable(editable);
        BookField.setEditable(editable);
        CelebrityField.setEditable(editable);
        ContactFBField.setEditable(editable);
        ContactIGField.setEditable(editable);
        DrinkingCheck.setDisable(!editable);
        NonDrinkingCheck.setDisable(!editable);
        SmokingCheck.setDisable(!editable);
        NonSmokingCheck.setDisable(!editable);
        MBTIField.setEditable(editable);
        MovieField.setEditable(editable);
        MusicField.setEditable(editable);
        PurposeChoice.setDisable(!editable);
        SexualPerferenceField.setEditable(editable);
        departmentField.setEditable(editable);
        gradeField.setEditable(editable);
        GenderField.setEditable(editable);
        FileButton.setDisable(!editable);
        
    }
    
    
    @FXML
    public void btneditprofile(ActionEvent event) {
    	isEditMode = false;
    	toggleEditMode();
    }
    
    private void toggleEditMode() {
        isEditMode = true ;  //editable
        setFieldsEditable(isEditMode);
        btnSaveChanges.setDisable(!isEditMode);
        
        NameField.setEditable(isEditMode);
        AgeField.setEditable(isEditMode);
        MBTIField.setEditable(isEditMode);
        departmentField.setEditable(isEditMode);
        gradeField.setEditable(isEditMode);
        MusicField.setEditable(isEditMode);
        MovieField.setEditable(isEditMode);
        BookField.setEditable(isEditMode);
        CelebrityField.setEditable(isEditMode);
        ContactFBField.setEditable(isEditMode);
        ContactIGField.setEditable(isEditMode);
        GenderField.setEditable(isEditMode);
        

        btnSaveChanges.setDisable(!isEditMode);
    }
	
	
	public Parent getRoot() {
				return root;
	}



	public void setRoot(Parent root) {
		this.root = root;
	}



	@FXML
	public void btnpasswordsaving(ActionEvent event) throws SQLException {
		// TODO Autogenerated
		String newPassword = PasswordField.getText();
		profile.setPassword(newPassword);
		db.updatePassword(profile);
		
	}
	
	@FXML
	private void handleclicked(ActionEvent event) {
		if(event.getSource()== btnprofile) {
			profilepage.toFront();
			
		}
		else if(event.getSource()== btnsetting) {
			settingpage.toFront();
			
		}
		else if(event.getSource()== btnposting) {
			postingpage.toFront();
		}
		else if(event.getSource()== btndating) {
			datingpage.toFront();
		}
		
	}
	
	@FXML
	private void logout(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
		root = loader.load();
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	
	public Stage getStage() {
		return stage;
	}
	
	
	
	@FXML
    public void SavePost(ActionEvent event) throws SQLException {
		String title =PostTitleField.getText();
		String location = PostLocationField.getText(); 
		LocalDate date = PostDatePicker.getValue();
		String time = PostTimeField.getText();
		int num = Integer.parseInt(PostPeopleField.getText());
		String description = PostDescriptionField.getText();
		ActivityInformation NewPost = new ActivityInformation(title,location,date,time,num,description,profile);
		NewPost.setsqlDate(date);
		db.insert(NewPost);
		
		
    }
	
	
}	


	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

		
	
	
	



	