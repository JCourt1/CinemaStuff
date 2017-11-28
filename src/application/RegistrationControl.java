package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class RegistrationControl  {
	
	@FXML
	private TextField txtUsername;
	
	@FXML
	private TextField txtPassword;
	
	@FXML
	private TextField txtFirstName;
	
	@FXML
	private TextField txtLastName;
	
	@FXML
	private TextField txtEmail;
	
	@FXML 
	private Button backButton;
	
	
	
	@FXML
	public void Register(ActionEvent event) throws Exception {
		
		
		
		if (txtUsername.getText().isEmpty()==false && txtPassword.getText().isEmpty()==false && txtFirstName.getText().isEmpty()==false && txtLastName.getText().isEmpty()==false && txtEmail.getText().isEmpty()==false) {
		
		
		Client.addToList(txtUsername.getText(),txtPassword.getText(),txtFirstName.getText(),txtLastName.getText(),txtEmail.getText() );
		ClientDatabaseWriter.writeFile();
		
		Alert alert = new Alert (AlertType.INFORMATION);
		alert.setTitle("New registration");
		alert.setHeaderText(null);
		alert.setContentText("Thank you. Your details have been added");
		alert.showAndWait();
			
		
		
		
		

		}
		
		else {
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("Please fill out all the fields");

			alert.showAndWait();
			
		}
		
	}
	
	 public void Back() throws Exception {
		Stage stage;
		Parent root;
		

			stage = (Stage) backButton.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("/application/Login.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
		}
	
			
			
			
			
	}
	
	
	
	