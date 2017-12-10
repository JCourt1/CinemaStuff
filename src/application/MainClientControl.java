package application;

import java.beans.EventHandler;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;


import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.control.Button;
import javafx.collections.ObservableList;
import javafx.scene.input.MouseEvent;




public class MainClientControl {
	
	private ArrayList<String> filmList = new ArrayList<String>();
	
	int rowCounter =  0;
	
	private static String buttonId;
	
	private ObjectProperty<TableRow<Film>> selectedRow = new SimpleObjectProperty<>();

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
	private Button cancelButton;
	
	@FXML 
	private TableView<Booking> bookingTable;
	
	@FXML private TableColumn<Booking, String> movieColumn;
	@FXML private TableColumn<Booking, LocalDate> screeningDateColumn;
	@FXML private TableColumn<Booking, Integer> screeningTimeColumn;
	@FXML private TableColumn<Booking, LocalDate> bookingDateColumn;
	@FXML private TableColumn<Booking, String> seatColumn;

	@FXML
	private GridPane gridPane;
	
	private  ObservableList<Booking> filteredList = FXCollections.observableArrayList();
	
	
	@FXML
	private ScrollPane scrollPane;
	
	
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

fillFilmTable();

scrollPane.setContent(gridPane);

disableCancelButton();

File file1 = new File("src/application/Bookings.xml");
    
MainApplication.loadBookingDataFromFile(file1);
    
filterBookingData();
bookingTable.setItems(filteredList);
updateBookingTable();	



firstName.setText(MainControl.getCurrentClientDetails(MainControl.currentUsername).getFirstName());
lastName.setText(MainControl.getCurrentClientDetails(MainControl.currentUsername).getLastName());
email.setText(MainControl.getCurrentClientDetails(MainControl.currentUsername).getEmail());
username.setText(MainControl.getCurrentClientDetails(MainControl.currentUsername).getUserName());
password.setText(MainControl.getCurrentClientDetails(MainControl.currentUsername).getPassword());
	

bookingTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->{
	
	 if (newSelection == null) {
	        bookingTable.getSelectionModel().clearSelection();
	    
	    }
	 
	 else {
		
		 // updating the booking table after cancelation doesn't work yet
		 
		 if(newSelection.getScreeningDate().isAfter(LocalDate.now())) {
			
				cancelButton.setDisable(false);
				cancelButton.textFillProperty().set(Color.GREEN);
				
				
		 
		 }
		 else if(newSelection.getScreeningDate().isEqual(LocalDate.now())&& newSelection.getScreeningTime()>LocalTime.now().getHour()) {
			 cancelButton.setDisable(false);
				cancelButton.textFillProperty().set(Color.GREEN);
			
		 }
		 
		 else {
			 disableCancelButton();
		 }
		
	 }
	});





//deselect rows if you click on them when they are already selected

		bookingTable.addEventFilter(MouseEvent.MOUSE_CLICKED, evt -> {
			Node source = evt.getPickResult().getIntersectedNode();

			while (source != null && !(source instanceof TableRow)) {
				
				source = source.getParent();
				
			}

			if (source == null || (source instanceof TableRow && ((TableRow) source).equals(selectedRow.get()))) {
				bookingTable.getSelectionModel().clearSelection();
				selectedRow = new SimpleObjectProperty<>();
				disableCancelButton();
				return;
				
			}

			selectedRow.set((TableRow) source);

		});
		


}

/*For loops run through all films and seances trying to find a matching film title. Once that is done it checks whether that movie contains seances at a future time and/or date
 * then it checks whether this movie was already added to the gridpane, a record of all the movies added to the gridpane is kept on the filmList arraylist
 * 
 */

public void fillFilmTable() {
	
	File file = new File("src/application/FilmData.xml");
	MainApplication.loadFilmDataFromFile(file);
	File file2 = new File("src/application/SeanceData.xml");
	MainApplication.loadSeanceDataFromFile(file2);
	for(Film film: MainApplication.getFilmData()) {
		for(Seance seance: MainApplication.getSeanceData()) {
			if(film.getName().equals(seance.getFilm())) {
			if(seance.getDay().isAfter(LocalDate.now()) || (seance.getDay().isEqual(LocalDate.now()) && Integer.parseInt(seance.getTime().substring(0, 2))>LocalTime.now().getHour())) {
				if(!filmList.contains(film.getName())){
					
					Button button = new Button("Book");
					button.setId(film.getName());
					button.setMinWidth(65);
					button.setMinHeight(40);
					button.setOnAction((event) -> {
					    // Button was clicked, do something...
						try {
							buttonId = button.getId();
							GoToBook(event);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					});
					
					
					
					
					File file1 = new File(film.getPath());
					Image image = new Image(file1.toURI().toString());
					ImageView imageview = new ImageView();
					imageview.setImage(image); 
					
				
					
					
					
					
					
					
					HBox box= new HBox();
					imageview.setPreserveRatio(true); 
					imageview.setFitHeight(70);
			
					
					box.getChildren().addAll(imageview);
					box.setPrefHeight(70);
					box.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
						        + "-fx-border-width: 2;" + "-fx-border-insets: 5;"
						        + "-fx-border-radius: 0;" + "-fx-border-color: black;");
					
					Label title =  new Label(film.getName());
					Label description = new Label(film.getDescription());
					gridPane.addRow(rowCounter, title);

					gridPane.addRow(rowCounter+1, box, description, button);
					
					rowCounter=rowCounter+2;
					

					for(Node item: gridPane.getChildren()) {
					gridPane.setMargin(item,new Insets(15,15,15,15));
					}
					
					filmList.add(film.getName());
					
					
					
				}
			}
			}
		}
	}
	
	
	
}



public void disableCancelButton(){
	cancelButton.setDisable(true);
	cancelButton.textFillProperty().set(Color.RED);
}





public void filterBookingData(){
	filteredList.clear();
	 for(Booking booking: MainApplication.getBookingData()) {
		 if(booking.getUserName().equals(MainControl.currentUsername)){
			 filteredList.add(booking);
		 }
	 }
	
	
}
	
public void updateBookingTable() {
	
		
		movieColumn.setCellValueFactory(cellData -> cellData.getValue().TitleProperty());
		
		screeningDateColumn.setCellValueFactory(new PropertyValueFactory<Booking, LocalDate>("ScreeningDate")); 
		
		screeningTimeColumn.setCellValueFactory(cellData -> cellData.getValue().ScreeningTimeProperty().asObject());

		bookingDateColumn.setCellValueFactory(new PropertyValueFactory<Booking, LocalDate>("BookingDate")); 

		
		seatColumn.setCellValueFactory(cellData -> cellData.getValue().SeatProperty());
		
	

	}	


void updateLocalLists(){
	
	File file1 = new File("src/application/Bookings.xml");
    
    MainApplication.loadBookingDataFromFile(file1);
	
	
}

public void cancelButtonVisibility(){
	
		if(bookingTable.getSelectionModel().getSelectedItem().getScreeningDate().isAfter(LocalDate.now())&& bookingTable.getSelectionModel().getSelectedItem().getScreeningTime()<LocalTime.now().getHour()) {
					
	}
		else {
			cancelButton.disableProperty();
		}
}

public void Cancel() {
	File file1 = new File("src/application/Bookings.xml");
	MainApplication.getBookingData().remove(bookingTable.getSelectionModel().getSelectedItem());
	MainApplication.saveBookingDataToFile(file1);
	
	filterBookingData(); // refreshes what is in the filtered booking data array list (clears the array and re-populates it)
	bookingTable.refresh(); //refreshes the table
	System.out.println("Booking Cancelled");
}


public static String getButtonId() {
	return buttonId;
}








}
