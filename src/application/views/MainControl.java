package application.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainControl {
	
	@FXML
	private Label lblStatus;
	
	@FXML
	private TextField txtUsername;
	
	@FXML
	private TextField txtPassword;
	
	@FXML
	private TextField confirmPassword;
	
	@FXML
	private TextField staffID;
	
	@FXML 
	private Button regButton;
	
	@FXML 
	private Button backButton;
	
	
	@FXML
	public void Login(ActionEvent event) throws Exception {
		
		/* check to see if username/password combo corresponds to a registered staff member, or a client */
		
		Stage stage;
		
		if (txtUsername.getText().equals("client") && txtPassword.getText().equals("pass")) {
			lblStatus.setText("Login Successful");
			
			
			
			
			
			/* if user is staff:
			 * Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			 * 
			 * */
			
			/* else if user is client : */
			stage = (Stage) regButton.getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("/application/Main_Client.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
			
		} 
		
		else if(txtUsername.getText().equals("employee") && txtPassword.getText().equals("password")) {
			lblStatus.setText("Employee login Successful");
			
			stage = (Stage) regButton.getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("/application/Main_Client.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
		}
		
		else {
			
			lblStatus.setText("Login Failed");
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
	
	
	
	
	

}
