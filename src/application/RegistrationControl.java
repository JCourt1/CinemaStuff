package application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import application.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import application.Film;
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
	
	private MainApplication main;
	private Stage tempStage;
	
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
					
		           Client client = new Client(txtUsername.getText(),txtPassword.getText(), txtFirstName.getText(), txtLastName.getText(),txtEmail.getText());
		            
		           MainApplication.getClientData().add(client);
		           System.out.println(MainApplication.getClientData());
		    		File file = new File("src/application/Clients.xml");
		    		MainApplication.saveClientDataToFile(file);
		    		
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
	
	 public void setMain(MainApplication main) {
			
			this.main = main;

	 }
	 
	 
	 @FXML
		void initialize() throws IOException {	
		 
		 File file1 = new File("src/application/Clients.xml");
		
		MainApplication.loadClientDataFromFile(file1);
		for(Client client : MainApplication.getClientData()) {
			System.out.println(client.getUserName());
			System.out.println(client.getPassword());
			
		}
		
		 
	 }
	 
			
	}
	
	
	
	