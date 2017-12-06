package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import application.Seance;
import application.ModifyExisting;
import application.addFilmsController;
import application.MainApplication;
import application.Film;

public class EmployeeController implements Initializable{
	
	
	
	@FXML private ListView<String> seanceList;
	@FXML private DatePicker datePicker;
	
	@FXML private Button searchButton;
	
	@FXML private ComboBox<String> films;
	
	@FXML private Button addFilm;
	@FXML private Button modifyExisting;
	
	@FXML private BorderPane bPane;
	
	
	private MainApplication main;
	
	
	public EmployeeController(){
		
	};
	
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		
	}
	
	
	
	
	
	@FXML
	private void saveSeances(){
		File file = new File("SeanceData.xml");
		main.saveSeanceDataToFile(file);
	}
	
	// test
	
	@FXML
	private void displayAddFilmPane(){
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApplication.class.getResource("ModifyFilms.fxml"));
        try {
        	AnchorPane modifyFilms = (AnchorPane) loader.load();
        
			bPane.setRight(modifyFilms);
			addFilmsController controller = loader.getController();
    		controller.setMain(this.main);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("error!");
		}
	}
	
	
	@FXML
	private void displayModifyExisting(){
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApplication.class.getResource("ModifyExisting.fxml"));
        try {
        	AnchorPane modifyExisting = (AnchorPane) loader.load();
        
			bPane.setRight(modifyExisting);
			ModifyExisting controller = loader.getController();
    		controller.setMain(this.main);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("error!");
		}
	}
	
	
	
	
	// test
	
	
	@FXML
	private void showScreenings() {
		String selectedFilm = films.getSelectionModel().getSelectedItem();
		ObservableList<String> tempSeanceData = FXCollections.observableArrayList();
		LocalDate ld = datePicker.getValue();
		
		
		for (Seance seance : main.getSeanceData()) {
			
			
			String seanceFilm = seance.filmProperty().get();
			String seanceDay = seance.getDay().toString();
			String chosenDate = ld.toString();
			
			String seanceTime = Integer.toString(seance.getTime());
			
			if (seanceFilm.equals(selectedFilm)) {
				if (seanceDay.equals(chosenDate)) {
					
					tempSeanceData.add(seanceTime);
					
				}
			} else {
				System.out.println("something went wrong");
				
			}
		}
		seanceList.setItems(tempSeanceData);
	}
	
	
	public void setMain(MainApplication main) {
        this.main = main;
        ObservableList<String> listOfFilms = FXCollections.observableArrayList();
        for (Film film: main.getFilmData()) {
        	listOfFilms.add(film.getName());
        }
        films.setItems(listOfFilms);
    }
	

}
