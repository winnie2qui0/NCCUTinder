package application;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;



public class ShowinfoController {

    @FXML
    private Button BtnCancel;

    @FXML
    private Button BtnJoinActibity;

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
    
    private ActivityInformation info;
    
    @FXML
    public void Setdata(ActionEvent event) {
    	ShowTitleLabel.setText(info.getTitle());
    	ShowLocationLabel.setText(info.getLocation());
    	ShowDateLabel.setText(info.getDate().toString());
    	ShowTimeLabel.setText(info.getTime());
    	ShowDescriptionLabel.setText(info.getDescription());
    	ShowIntegerLabel.setText(Integer.toString(info.getPeoplenumbers()));
    	
    }

   

    @FXML
    public void AddinWaitingList(ActionEvent event) {
    	

    }
  


	

}
