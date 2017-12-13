package application;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import javafx.beans.property.ObjectProperty;

import javafx.beans.property.SimpleObjectProperty;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

import javafx.scene.text.Font;
import javafx.stage.Stage;

import javafx.scene.input.MouseEvent;

/**
 * This class is the controller for the Main_Client.fxml file. It controls the
 * list of currently available movies in the Cinema, the list of bookings made
 * and the change contact details form.
 * 
 * @author David Rudolf
 * @author Joseph Courtley
 */

public class MainClientControl {

	/**
	 * This ArrayList is a record of all the films that have been added to the film
	 * list JavaFX GridPane.
	 * 
	 */
	private ArrayList<String> filmList = new ArrayList<String>();
	
	private ArrayList<String> filmList2 = new ArrayList<String>();

	/**
	 * This integer is used to count the rows in the JavaFX GridPane and helps the
	 * correct rendering in the GridPane.
	 */
	int rowCounter = 0;

	/**
	 * This string is used keep a record of which button was pressed in the
	 * GridPane. I will be assigned the movie title and later called in the
	 * MediaPlayerController.java and CinemaRoomController.java files.
	 */
	private static String buttonId;

	/**
	 * This JavaFX class keeps a record of which row inside the Bookings table has
	 * been selected by the user. It is used to implement the method that deselects
	 * the row when it is pressed a second time.
	 */
	private ObjectProperty<TableRow<Film>> selectedRow = new SimpleObjectProperty<>();

	private ObservableList<Booking> filteredList = FXCollections.observableArrayList();

	String currentClientFirstName;
	String currentClientLastName;
	
	private static LocalDate datePickerInput = null;
	private String availableTimes;

	@FXML
	private TabPane tabPane;

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

	@FXML
	private Label titleLabel;

	@FXML
	private TableColumn<Booking, String> movieColumn;
	@FXML
	private TableColumn<Booking, LocalDate> screeningDateColumn;
	@FXML
	private TableColumn<Booking, Integer> screeningTimeColumn;
	@FXML
	private TableColumn<Booking, LocalDate> bookingDateColumn;
	@FXML
	private TableColumn<Booking, String> seatColumn;

	@FXML
	private GridPane gridPane;

	@FXML
	private ScrollPane scrollPane;
	
	@FXML
	private DatePicker datePicker;

	@FXML
	/**
	 * This is the main method that is called automatically after the FXML element
	 * have been populated. It contains instructions to populate the GridPane,
	 * Tableview, Contact details text fields, and the window title.
	 */
	void initialize() {
		
		setTitleLabel();

		fillFilmTable();

		scrollPane.setContent(gridPane);

		disableCancelButton();

		File file1 = new File("src/application/Bookings.xml");

		MainApplication.loadBookingDataFromFile(file1);

		File file2 = new File("src/application/Clients.xml");
		MainApplication.loadClientDataFromFile(file2);

		

		

		filterBookingData();
		bookingTable.setItems(filteredList);
		updateBookingTable();
		for (Client client : MainApplication.getClientData()) {
			System.out.println(client.getUserName());
		}
		System.out.println("current username:" + MainControl.getCurrentUsername());

		firstName.setText(MainControl.getCurrentClientDetails(MainControl.getCurrentUsername()).getFirstName());
		lastName.setText(MainControl.getCurrentClientDetails(MainControl.getCurrentUsername()).getLastName());
		email.setText(MainControl.getCurrentClientDetails(MainControl.getCurrentUsername()).getEmail());
		username.setText(MainControl.getCurrentClientDetails(MainControl.getCurrentUsername()).getUserName());
		password.setText(MainControl.getCurrentClientDetails(MainControl.getCurrentUsername()).getPassword());

		bookingTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

			if (newSelection == null) {
				bookingTable.getSelectionModel().clearSelection();

			}

			else {

				// updating the booking table after cancelation doesn't work yet

				if (newSelection.getScreeningDate().isAfter(LocalDate.now())) {

					cancelButton.setDisable(false);

				} else if (newSelection.getScreeningDate().isEqual(LocalDate.now())
						&& newSelection.getScreeningTime() > LocalTime.now().getHour()) {
					cancelButton.setDisable(false);

				}

				else {
					disableCancelButton();
				}

			}
		});

		// deselect rows if you click on them when they are already selected

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

	@FXML
	public void GoToBook(ActionEvent event) throws Exception {

		Stage stage = new Stage();

		stage = (Stage) LogOutButton.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("/application/Cinema_Room.fxml"));
		System.out.println("GoToBook function has been triggered");
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();

	}

	@FXML
	public void goToTRailer(ActionEvent event2) {
		try {
			Parent root;
			root = FXMLLoader.load(getClass().getResource("/application/MediaPlayer.fxml"));
			Stage stage = new Stage();
			stage.setScene(new Scene(root, 800, 600));
			System.out.println("gotoTrailer function has been triggered");
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@FXML
	public void Save(ActionEvent event) throws Exception {

		int index = MainControl.getClientIndex(MainControl.getCurrentUsername());
		System.out.println(index);
		MainApplication.getClientData().get(index).setUserName(username.getText());
		MainApplication.getClientData().get(index).setPassword(password.getText());
		MainApplication.getClientData().get(index).setFirstName(firstName.getText());
		MainApplication.getClientData().get(index).setLastName(lastName.getText());
		MainApplication.getClientData().get(index).setEmail(email.getText());

		File file = new File("src/application/Clients.xml");
		MainApplication.saveClientDataToFile(file);

		File file2 = new File("src/application/Bookings.xml");
		MainApplication.loadBookingDataFromFile(file2);

		for (Booking booking : MainApplication.getBookingData()) {
			if (booking.getUserName().equals(MainControl.getCurrentUsername())) {
				booking.setUserName(username.getText());
			}
		}

		MainApplication.saveBookingDataToFile(file2);
		MainControl.setCurrenUsername(username.getText());

		setTitleLabel();
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information updated");
		alert.setHeaderText(null);
		alert.setContentText("Thank you. Your details have been udpated");
		alert.showAndWait();
		
		

	}

	@FXML
	public void Logout(ActionEvent event) throws Exception {
		MainControl.setCurrenUsername(null);

		Stage stage;
		stage = (Stage) LogOutButton.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("/application/Login.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();

	}

	/**
	 * This method is responsible for filling the "Now in Cinema" TableView within
	 * the Main_Client.fxml file. First, the Films and the Viewings are reloaded
	 * from the xml files to make sure that the elements 'FilmData' and 'SeanceData'
	 * in MainApplication.java contain an up to date version of the data.
	 * 
	 * For loops the run through all the films and viewings (seances) trying to find
	 * matching film titles. Once this is done, it checks whether that movie
	 * contains viewings at a future date or time. If there are matches at this
	 * point, it will check whether the film has already been added to the JavaFX
	 * GridPane by looping through the filmList ArrayList which contains a record of
	 * all the movies that have been added to the GridPane in the current
	 * initialisation of Main_Client.fxml.
	 * 
	 * If it is found that the ArrayList does not contain the film, the new elements
	 * for a new row in the JavaFX GridPane are created. These consist of a "Book"
	 * Button, a "Watch Trailer" Button, a "Title" Label, a "Description" Label and
	 * an ImageView.
	 * 
	 * The "Book Button" will trigger when pressed and will call the GoToBook(event)
	 * method which will take the user to the Cinema_Room.fxml window. The "Watch
	 * Trailer" will trigger when pressed. If a path for the specific film's trailer
	 * exists, it will call the goToTrailer(event2) method which will take the user
	 * to the MediaPlayer.fxml window. If however the path to the film's trailer
	 * does not exist, then an alert will be shown.
	 * 
	 * The "Title" and "Description" labels contain the title and description of the
	 * particular film, taken from the Film.java class through the FilmData
	 * Observable List in MainApplication.java.
	 * 
	 * The ImageView contains the poster of the specific film.
	 * 
	 * The JavaFx GridPane is then populated with these items. Here the rowCounter
	 * integer is used so that the "Title" Label will always be put one row above
	 * the rest of the elements for a specific movie.
	 */
	public void fillFilmTable() {

		File file = new File("src/application/FilmData.xml");
		MainApplication.loadFilmDataFromFile(file);
		File file2 = new File("src/application/SeanceData.xml");
		MainApplication.loadSeanceDataFromFile(file2);

		for (Film film : MainApplication.getFilmData()) {
			for (Seance seance : MainApplication.getSeanceData()) {
				if (film.getName().equals(seance.getFilm())) {
					if (seance.getDay().isAfter(LocalDate.now()) || (seance.getDay().isEqual(LocalDate.now())
							&& Integer.parseInt(seance.getTime().substring(0, 2)) > LocalTime.now().getHour())) {
						if (!filmList.contains(film.getName())) {

							Button button = new Button("Book");
							button.setId(film.getName());
							button.setMinWidth(65);
							button.setMinHeight(40);
							button.setOnAction((event) -> {

								try {

									buttonId = button.getId();
									GoToBook(event);

								} catch (Exception e) {

									e.printStackTrace();
								}
							});

							Button trailerButton = new Button("Watch trailer");
							trailerButton.setId("Trailer:" + film.getName());
							trailerButton.setMinWidth(120);
							trailerButton.setMinHeight(40);
							trailerButton.setOnAction((event2) -> {

								try {
									//if (film.gettrailerPath() != null) {
										buttonId = trailerButton.getId();
										goToTRailer(event2);
									//} 
										/*else {
										Alert alert = new Alert(AlertType.ERROR);
										alert.setTitle("No Trailer");
										alert.setHeaderText(null);
										alert.setContentText("Sorry, a trailer for this movie does not exist.");
										alert.showAndWait();
									}*/
								} catch (Exception e) {

									e.printStackTrace();
									System.out.println(e);
								}
							});

							File file1 = new File(film.getPath());
							Image image = new Image(file1.toURI().toString());
							ImageView imageview = new ImageView();
							imageview.setImage(image);

							// This is a container for the ImageView
							HBox box = new HBox();
							imageview.setPreserveRatio(true);
							imageview.setFitHeight(70);

							// This adds the ImageView to the HBox
							box.getChildren().addAll(imageview);
							box.setPrefHeight(70);
							box.setStyle("-fx-padding: 5;" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
									+ "-fx-border-insets: 5;" + "-fx-border-radius: 0;" + "-fx-border-color: black;");

							Label title = new Label(film.getName());
							title.setMinWidth(Region.USE_PREF_SIZE);
							title.setFont(new Font("Arial Bold", 20));
							Label description = new Label(film.getDescription());
							description.setWrapText(true);

							gridPane.addRow(rowCounter, title);

							gridPane.addRow(rowCounter + 1, box, description, trailerButton, button);

							rowCounter = rowCounter + 2;

							for (Node item : gridPane.getChildren()) {
								gridPane.setMargin(item, new Insets(15, 15, 15, 15));
							}

							filmList.add(film.getName());

						}
					}
				}
			}
		}
		
	}

	/**
	 * This method disables the cancel button.
	 */
	public void disableCancelButton() {
		cancelButton.setDisable(true);

	}

	/**
	 * This method populates the "filteredList" ArrayList with all the bookings that
	 * have the client's user name as on of the parameters. So the filetereList will
	 * contain only bookings made by logged-in client.
	 */
	public void filterBookingData() {
		filteredList.clear();
		for (Booking booking : MainApplication.getBookingData()) {
			if (booking.getUserName().equals(MainControl.getCurrentUsername())) {
				filteredList.add(booking);
			}
		}

	}

	/**
	 * This method populates the JavaFX TableView bookingTable with the booking data
	 * from Bookings.java
	 */

	public void updateBookingTable() {

		movieColumn.setCellValueFactory(cellData -> cellData.getValue().TitleProperty());

		screeningDateColumn.setCellValueFactory(new PropertyValueFactory<Booking, LocalDate>("ScreeningDate"));

		screeningTimeColumn.setCellValueFactory(cellData -> cellData.getValue().ScreeningTimeProperty().asObject());

		bookingDateColumn.setCellValueFactory(new PropertyValueFactory<Booking, LocalDate>("BookingDate"));

		seatColumn.setCellValueFactory(cellData -> cellData.getValue().SeatProperty());

	}

	/**
	 * This method deletes the selected booking in the TableView. It then refreshes
	 * the filteredBookingData list and the TableView . Then it shows an alert
	 * window, informing the client that his booking as been canceleld succesfully.
	 */
	public void Cancel() {
		File file1 = new File("src/application/Bookings.xml");
		MainApplication.getBookingData().remove(bookingTable.getSelectionModel().getSelectedItem());
		MainApplication.saveBookingDataToFile(file1);

		filterBookingData();

		bookingTable.refresh();

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Booking canceled");
		alert.setHeaderText(null);
		alert.setContentText("Thank you. Your booking has been canceled");
		alert.showAndWait();

	}

	/**
	 * This is the getter method for the ButtonId attribute. It is used in the
	 * CinemaRoomController class and the MediaPlayerController class.
	 * 
	 * @return String
	 */
	public static String getButtonId() {
		return buttonId;
	}
	
	/**
	 * This method sets the the title label to Welcome  followed by the logged-in user's first name followed by the logged-in user's last name.
	 * This method is called at initialization and whenever the contact details of the user are changed.
	 */
	public  void setTitleLabel() {
		for (Client client : MainApplication.getClientData()) {
			if (client.getUserName().equals(MainControl.getCurrentUsername())) {
				currentClientFirstName = client.getFirstName();
				currentClientLastName = client.getLastName();
			}
		}
		titleLabel.setText("Welcome " + currentClientFirstName + " " + currentClientLastName);
	}
	
	
	public void selectedDate(ActionEvent event) throws Exception {
		
//code of what happens when a date is picked, have to rebuild the gridpane with only films available on that date.
		
		File file = new File("src/application/FilmData.xml");
		MainApplication.loadFilmDataFromFile(file);
		File file2 = new File("src/application/SeanceData.xml");
		MainApplication.loadSeanceDataFromFile(file2);
		
		gridPane.getChildren().clear();
		

		for (Film film : MainApplication.getFilmData()) {
			for (Seance seance : MainApplication.getSeanceData()) {
				if (film.getName().equals(seance.getFilm())) {
					if (seance.getDay().isEqual(datePicker.getValue())) {
						
						
						if (!filmList2.contains(film.getName())) {

							Button button = new Button("Book");
							button.setId(film.getName());
							button.setMinWidth(65);
							button.setMinHeight(40);
							button.setOnAction((event3) -> {

								try {

									buttonId = button.getId();
									GoToBook(event3);

								} catch (Exception e) {

									e.printStackTrace();
								}
							});

							Button trailerButton = new Button("Watch trailer");
							trailerButton.setId("Trailer:" + film.getName());
							trailerButton.setMinWidth(120);
							trailerButton.setMinHeight(40);
							trailerButton.setOnAction((event2) -> {

								try {
									//if (film.gettrailerPath() != null) {
										buttonId = trailerButton.getId();
										goToTRailer(event2);
									//} 
										/*else {
										Alert alert = new Alert(AlertType.ERROR);
										alert.setTitle("No Trailer");
										alert.setHeaderText(null);
										alert.setContentText("Sorry, a trailer for this movie does not exist.");
										alert.showAndWait();
									}*/
								} catch (Exception e) {

									e.printStackTrace();
									System.out.println(e);
								}
							});

							File file1 = new File(film.getPath());
							Image image = new Image(file1.toURI().toString());
							ImageView imageview = new ImageView();
							imageview.setImage(image);

							// This is a container for the ImageView
							HBox box = new HBox();
							imageview.setPreserveRatio(true);
							imageview.setFitHeight(70);

							// This adds the ImageView to the HBox
							box.getChildren().addAll(imageview);
							box.setPrefHeight(70);
							box.setStyle("-fx-padding: 5;" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
									+ "-fx-border-insets: 5;" + "-fx-border-radius: 0;" + "-fx-border-color: black;");

							Label title = new Label(film.getName());
							title.setMinWidth(Region.USE_PREF_SIZE);
							title.setFont(new Font("Arial Bold", 20));
							Label description = new Label(film.getDescription());
							description.setWrapText(true);
							
							availableTimes = getAllSeanceTimes(film.getName(),datePicker.getValue());
							
							Label times = new Label(availableTimes);
							title.setMinWidth(Region.USE_COMPUTED_SIZE);
							
							

							gridPane.addRow(rowCounter, title);

							gridPane.addRow(rowCounter + 1, box, description, trailerButton, button);
							
							gridPane.addRow(rowCounter, times);

							rowCounter = rowCounter + 3;

							for (Node item : gridPane.getChildren()) {
								gridPane.setMargin(item, new Insets(15, 15, 15, 15));
							}

							filmList2.add(film.getName());

						}
					}
				}
			}
		}
		datePickerInput = datePicker.getValue();
	}
	
	public String getAllSeanceTimes(String title, LocalDate date){
		String availableTimes = "";
		for (Film film : MainApplication.getFilmData()) {
			for (Seance seance : MainApplication.getSeanceData()) {
				if (film.getName().equals(title)&&film.getName().equals(seance.getFilm())) {
					if (seance.getDay().isEqual(date)) {
		
						availableTimes = availableTimes + " " + seance.getTime();
					}
				}
			}
		}
		return availableTimes;
	}
	
	public static LocalDate getDatePickerInput() {
		return datePickerInput;
	}

}
