package application.views.plan;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.MainApplication;
import application.models.films.Film;
import application.models.films.Seance;
import application.views.plan.util.MultiSelect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

public class AddScreeningsController implements Initializable {

	private MainApplication main;
	private ArrayList<String> tempDates;
	private ObservableList<String> list;
	private Stage tempStage;
	
//	@FXML private ComboBox<String> films;
	@FXML private ComboBox<Film> films;
	@FXML private ListView<String> times;
	
	
	
	
	@FXML private Label chooseADate;
	@FXML private DatePicker dates;
	@FXML private Button saveBtn;
	@FXML private ImageView pic;
	
	
	@FXML
	private void saveScreeningData() {
		
		ObservableList<String> listOfTimesPicked;
		listOfTimesPicked = times.getSelectionModel().getSelectedItems();
		LocalDate datePicked = dates.getValue();
		String filmName = films.getValue().getName();
		
		for (String time : listOfTimesPicked) {
			
			main.getSeanceData().add(new Seance(datePicked, time, filmName));
		}
		
		File file = new File("SeanceData.xml");
		
		main.saveData(file);
		
		Alert alert = new Alert(AlertType.INFORMATION);
        alert.initOwner(tempStage);
        alert.setTitle("Save successful");
        
        String pluralOrNot = "";
        if (listOfTimesPicked.size() > 1) {
        	pluralOrNot += "s";
        }
        alert.setHeaderText(String.format("Screening%s successfully saved", pluralOrNot));
        
        alert.showAndWait();
		
		
		
		
	}
	
	@FXML
	private void datePicked() {
		
		tempDates = new ArrayList<String>();
		times.getSelectionModel().clearSelection();
		
		if (dates.getValue() != null) {
			chooseADate.setText("");
			for (Seance screening : main.getSeanceData()) {
				if (screening.getDay().equals(dates.getValue())) {
				tempDates.add(screening.getTime());
				}
			}
		} else {
			chooseADate.setText("Choose a date to see what times are available");
		} 
		
		
		times.refresh();
		
		
	}
	
	
	

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		chooseADate.setText("Choose a date to see what times are available");
	}
	
	
	
	
	
	
	
	
	
	public void setMain(MainApplication main, Stage tempStage) {
		
		this.main = main;
		this.tempStage = tempStage;
		
		
		
		
		
		

		
		films.setItems(main.getFilmData());
		films.setCellFactory((comboBox) -> {
			return new ListCell<Film>() {
				@Override
				protected void updateItem(Film item, boolean empty) {
					super.updateItem(item, empty);
					
					if (item == null || empty) {
						setText(null);
					} else {
						setText(item.getName());
					}
						
				}
			};
		});
		
		films.setConverter(new StringConverter<Film>() {
			@Override
			public String toString(Film film) {
				if (film == null) {
					return null;
				} else {
					return film.getName();
				}
			}
			
			@Override
			public Film fromString(String filmString) {
				return null;
			}
		});
		
		films.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    if (newSelection != null) {
		    	String path = newSelection.getPath();
		    	File file = new File(path);
		    	Image image = new Image(file.toURI().toString());
		        pic.setImage(image);
		    }
		});
		

		
		list = FXCollections.observableArrayList();
		for (int i = 9; i < 20; i++) {
			if (i < 10) list.add("0" + Integer.toString(i) + ":00");
			else list.add(Integer.toString(i) + ":00");
		}
		
		
		times.setItems(list);
		
		times.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		times.addEventFilter( MouseEvent.MOUSE_PRESSED, MultiSelect.multiSelectEventHandler());
		times.addEventFilter( MouseEvent.MOUSE_RELEASED, MultiSelect.multiSelectEventHandler());
		
		times.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
			@Override 
			public ListCell<String> call(ListView<String> param){
				return new ListCell<String>() {
					@Override 
					protected void updateItem(String item, boolean empty) {
						super.updateItem(item, empty);

						if (item == null || empty) {
							setText(null);
							setGraphic(null);
						} else {
							setText(item);
							setDisable(false); //this was causing the problem. It needs to be reset
							setStyle("-fx-background-color: lightblue");

							if (dates.getValue() == null) {
								setDisable(true);
							} else {
								
								//
								setStyle("-fx-background-color: lightgreen");

								LocalDate date = dates.getValue();
								for (String time : tempDates) {
									if (time.equals(item)) {
										setText(item + " - already booked");
										setDisable(true);
										setStyle("-fx-background-color: red");
										return;
									}
								}
							}
						}
					}
				};
			}
		});
		
		
		
		
		
		
		
		
		
		
	}	

	
	
	
	
	
	
	
	
	
	
	
	
	
}
