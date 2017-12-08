package application;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import application.MainApplication;
import application.Client;
import application.Employee;
import application.Seance;
import application.RegistrationControl;
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
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainControl {
	
	public static String currentUsername;
	
	@FXML
	private Label lblStatus;
	
	@FXML
	private TextField txtUsername;
	
	@FXML
	private TextField txtPassword;
	
	@FXML private BorderPane bPane;
	
	@FXML
	private TextField confirmPassword;
	
	@FXML
	private TextField staffID;
	
	@FXML 
	private Button regButton;
	
	@FXML 
	private Button backButton;
	
	private MainApplication main;
	
	   private ArrayList<String> clientUsername = new ArrayList<String>();
	   private ArrayList<String> clientPassword = new ArrayList<String>();
	   private ArrayList<String> employeeUsername = new ArrayList<String>();
	   private ArrayList<String> employeePassword = new ArrayList<String>();
	
	public static Client getCurrentClientDetails(String username) {
		Client dummyclient = new Client();
		for(Client client : MainApplication.getClientData()) {
		if(client.getUserName().equals(username)) {
			dummyclient = client;
		}
		}
		return dummyclient;
	
	}
	
	public static int getClientIndex(String username) {
		int index = -1;
		for(Client client : MainApplication.getClientData()) {
		if(client.getUserName().equals(username)) {
			 index = MainApplication.getClientData().indexOf(client);
		}
		else {
			index = -1;
		}
		}
		
		return index;
	}
	   
	   
	@FXML
	public void Login(ActionEvent event) throws Exception {
		
		Stage stage;

		
		File file1 = new File("src/application/Clients.xml");
        
        MainApplication.loadClientDataFromFile(file1);
        
        File file2 = new File("src/application/Employees.xml");
        
        MainApplication.loadEmployeeDataFromFile(file2);
        
        // used to create usable data in  bookings.xml
        /*File file3 = new File("src/application/Bookings.xml");
        Booking booking = new Booking("drudolf","tarzan",LocalDate.of(2017, 11, 30),12,LocalDate.of(2017, 12, 01),"c4");
        MainApplication.getBookingData().add(booking);
        MainApplication.saveBookingDataToFile(file3);*/
		
		for(Client client : MainApplication.getClientData()) {
			clientUsername.add(client.getUserName());
			clientPassword.add(client.getPassword());
		}
		
		for(Employee employee : MainApplication.getEmployeeData()) {
			employeeUsername.add(employee.getUserName());
			employeePassword.add(employee.getPassword());
			System.out.println(employee.getUserName());
			System.out.println(employee.getPassword());
		}
		
		
		
		
			if(clientUsername.contains(txtUsername.getText())&&clientPassword.get(clientUsername.indexOf(txtUsername.getText())).equals(txtPassword.getText())) {
				
					currentUsername = txtUsername.getText();
					stage = (Stage) regButton.getScene().getWindow();
					Parent root = FXMLLoader.load(getClass().getResource("/application/Main_Client.fxml"));
					Scene scene = new Scene(root);
					scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					stage.setScene(scene);
					stage.show();
					
				}
				
				
		
			
			else if(employeeUsername.contains(txtUsername.getText())&&employeePassword.get(employeeUsername.indexOf(txtUsername.getText())).equals(txtPassword.getText())) {
				
					currentUsername = txtUsername.getText();
					stage = (Stage) regButton.getScene().getWindow();
					Parent root = FXMLLoader.load(getClass().getResource("/application/Main_Employee.fxml"));
					Scene scene = new Scene(root);
					scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					stage.setScene(scene);
					stage.show();
					
					
				}
				
				else {
					
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText(null);
					alert.setContentText("Your username/password combination is incorrect");

					alert.showAndWait();
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
	private void goToAddScreenings() {
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
	

	
	public void setMain(MainApplication main) {
        this.main = main;
    }
	
	
	
	
	

}
