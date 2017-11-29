package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;



public class MainClientControl {

	@FXML
	private TextField firstName;

	@FXML
	private TextField lastName;

	@FXML
	private TextField email;

	@FXML
	private TextField username;
	
	@FXML
	private TextField password;
	
	@FXML
	private Button LogOutButton;
	
	@FXML
	private Button SaveButton;
	
	@FXML
	private TableView table;
	
	
	@FXML 
	public void GoToBook(ActionEvent event) throws Exception{
		
		Stage stage;
		stage = (Stage) LogOutButton.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("/application/Cinema_Room.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	

}

@FXML 
public void Save(ActionEvent event) throws Exception{
	Client.setUserName(MainControl.id, username.getText());
	Client.setPassword(MainControl.id, password.getText());
	Client.setEmail(MainControl.id, email.getText());
	Client.setFirstName(MainControl.id, firstName.getText());
	Client.setLastName(MainControl.id, lastName.getText());
	ClientDatabaseWriter.writeFile();
	
	Alert alert = new Alert (AlertType.INFORMATION);
	alert.setTitle("Information updated");
	alert.setHeaderText(null);
	alert.setContentText("Thank you. Your details have been udpated");
	alert.showAndWait();
	
}
@FXML 
public void Logout(ActionEvent event) throws Exception{
MainControl.id=0;

Stage stage;
stage = (Stage) LogOutButton.getScene().getWindow();
Parent root = FXMLLoader.load(getClass().getResource("/application/Login.fxml"));
Scene scene = new Scene(root);
scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
stage.setScene(scene);
stage.show();

}

@FXML
void initialize() {
	
firstName.setText(Client.getClientDataExternal(MainControl.id).get(3));
lastName.setText(Client.getClientDataExternal(MainControl.id).get(4));
email.setText(Client.getClientDataExternal(MainControl.id).get(5));
username.setText(Client.getClientDataExternal(MainControl.id).get(1));
password.setText(Client.getClientDataExternal(MainControl.id).get(2));
	

}	



}
