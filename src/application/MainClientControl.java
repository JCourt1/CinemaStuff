package application;

import java.io.File;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;



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
	private TableView<Film> bookingTable;
	
	@FXML private TableColumn<Booking, String> movieColumn;
	@FXML private TableColumn<Booking, LocalDate> screeningDateColumn;
	@FXML private TableColumn<Booking, Integer> screeningTimeColumn;
	@FXML private TableColumn<Booking, LocalDate> bookingDateColumn;
	@FXML private TableColumn<Booking, String> seatColumn;
	@FXML private TableColumn  cancelColumn;
	

	
	
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
	
public void updateFilmTable() {
		
		movieColumn.setCellValueFactory(cellData -> cellData.getValue().TitleProperty());
		
		screeningDateColumn.setCellValueFactory(cellData -> cellData.getValue().screeningDateProperty()); //maybe needs .asObject()
		
		screeningTimeColumn.setCellValueFactory(cellData -> cellData.getValue().ScreeningTimeProperty().asObject());

		bookingDateColumn.setCellValueFactory(cellData -> cellData.getValue().bookingDateProperty()); //maybe needs .asObject()
		
		seatColumn.setCellValueFactory(cellData -> cellData.getValue().SeatProperty());
		

	}	

@FXML 
public void Save(ActionEvent event) throws Exception{
	
	int index = MainControl.getClientIndex(MainControl.currentUsername);
	MainApplication.getClientData().get(index).setUserName(username.getText());
	MainApplication.getClientData().get(index).setPassword(password.getText());
	MainApplication.getClientData().get(index).setFirstName(firstName.getText());
	MainApplication.getClientData().get(index).setLastName(lastName.getText());
	MainApplication.getClientData().get(index).setEmail(email.getText());
	
	File file = new File("src/application/Clients.xml");
	MainApplication.saveClientDataToFile(file);
	
	Alert alert = new Alert (AlertType.INFORMATION);
	alert.setTitle("Information updated");
	alert.setHeaderText(null);
	alert.setContentText("Thank you. Your details have been udpated");
	alert.showAndWait();
	
}
@FXML 
public void Logout(ActionEvent event) throws Exception{
MainControl.currentUsername = "";

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
	
firstName.setText(MainControl.getCurrentClientDetails(MainControl.currentUsername).getFirstName());
lastName.setText(MainControl.getCurrentClientDetails(MainControl.currentUsername).getLastName());
email.setText(MainControl.getCurrentClientDetails(MainControl.currentUsername).getEmail());
username.setText(MainControl.getCurrentClientDetails(MainControl.currentUsername).getUserName());
password.setText(MainControl.getCurrentClientDetails(MainControl.currentUsername).getPassword());
	

updateFilmTable();

}	





}
