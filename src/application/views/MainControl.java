package application.views;

import java.io.File;
import java.io.IOException;

import application.MainApplication;
import application.views.plan.BaseEmployeeController;
import application.views.plan.util.Fader;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainControl {
	
	@FXML private Label lblStatus;
	@FXML private TextField txtUsername;
	@FXML private TextField txtPassword;
	@FXML private TextField confirmPassword;
	@FXML private TextField staffID;
	
	@FXML private Button regButton;
	@FXML private Button backButton;
	
	private MainApplication main;
	
	@FXML
	public void Login(ActionEvent event) throws Exception {
		
		Stage stage;
		
		if (txtUsername.getText().equals("client") && txtPassword.getText().equals("pass")) {
			lblStatus.setText("Login Successful");
			stage = (Stage) regButton.getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("/application/Main_Client.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
			
		} 
		
		else if((txtUsername.getText().equals("employee") && txtPassword.getText().equals("password")) || (txtUsername.getText().equals("") && txtPassword.getText().equals(""))) {
			lblStatus.setText("Employee login Successful");
			
			try {
	    		File file1 = new File("FilmData.xml");
	    		main.loadData(file1);
	            
	            File file2 = new File("SeanceData.xml");
	            main.loadData(file2);
	    		
	    	} catch (Exception e) {
	    		
	    		System.out.println("can't load data");
	    		return;
	    	}
			
			FadeTransition fadeTrans = new FadeTransition();
			fadeTrans.setDuration(Duration.millis(1000));
			fadeTrans.setNode(txtUsername.getParent());
			fadeTrans.setFromValue(1);
			fadeTrans.setToValue(0);
			
			fadeTrans.setOnFinished(e -> showBase_Employee());
			
			fadeTrans.play();
//			showBase_Employee();
		}
		
		else {
			
			lblStatus.setText("Login Failed");
		}
	}
	
	
	public void showBase_Employee(){
    	
    	
    	try {
    	FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApplication.class.getResource("views/plan/baseEmployee.fxml"));
		AnchorPane baseEmployeeView = (AnchorPane) loader.load();
		
		Scene scene = new Scene(baseEmployeeView);
		
		main.getPrimaryStage().setScene(scene);
		baseEmployeeView.setOpacity(0);
		main.getPrimaryStage().setScene(scene);
		BaseEmployeeController controller = loader.getController();
		controller.setMain(this.main, baseEmployeeView);
    	} catch (IOException e) {
    		
    		e.printStackTrace();
    	}
    	
    	
    	
    	
    	
    }
	
	
	
	
	
	
	
	
	
	
	
	///////// used in Registration
	
	
	
	@FXML 
	public void GoToReg(ActionEvent event) throws Exception{
		
		Stage stage;
		Parent root;
		
		if (event.getSource()==regButton) {
			stage = (Stage) regButton.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("/application/Registration.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
		}
		
		
	}
	
	@FXML
	public void Back(ActionEvent event) throws Exception {
		Stage stage;
		Parent root;
		
		if (event.getSource()==backButton) {
			stage = (Stage) backButton.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("/application/Login.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
		}
	}
	
	@FXML
	public void Register(ActionEvent event) throws Exception {
		/*
		if (Staff id has been filled out) {
			check if id is valid
			if so check user doesn't alread exist, and register this user's details as staff 
			and go back to login form with a message saying success
			
			else show an error message 
			
		} else {
			check no duplicate
			register the user as a client
		}
		
		*/
		
	}
	
	
	public void setMain(MainApplication main) {
        this.main = main;
    }
	
	

}
