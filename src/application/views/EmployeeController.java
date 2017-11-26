package application.views;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import application.models.films.Seance;
import application.Main;
import application.models.films.Film;

public class EmployeeController implements Initializable{
	
	@FXML
	private TableView<Film> filmTable;
	
	@FXML
	private TableColumn<Film, String> filmNameColumn;
	
	@FXML
	private ListView<String> seanceList;
	
	@FXML
	private DatePicker datePicker;
	
	@FXML
	private Button searchButton;
	
	@FXML
	private Button saveBtn;
	
	// test 
	@FXML
	private Button addSeance;
	// test 
	
	
	
	private Main main;
	
	
	public EmployeeController(){
		
	};
	
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		filmNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		
	}
	
	
	
	
	
	@FXML
	private void save(){
		File file = new File("SeanceData.xml");
		main.saveSeanceDataToFile(file);
	}
	
	// test
	
	@FXML
	private void addSeance(){
		Seance anotherSeance = new Seance(2017, 12, 18, 18, "Jaws");
		main.getSeanceData().add(anotherSeance);
	}
	
	
	// test
	
	
	@FXML
	private void showScreenings() {
		Film selectedFilm = filmTable.getSelectionModel().getSelectedItem();
		ObservableList<String> tempSeanceData = FXCollections.observableArrayList();
		LocalDate ld = datePicker.getValue();
		
		
		for (Seance seance : main.getSeanceData()) {
			
			
			String seanceFilm = seance.filmProperty().get();
			String selectedFilmName = selectedFilm.nameProperty().get();
			String seanceDay = seance.getDay().toString();
			String chosenDate = ld.toString();
			
			String seanceTime = Integer.toString(seance.getTime());
			
			System.out.println(seanceFilm + selectedFilmName + seanceDay + chosenDate);
			
			if (seanceFilm.equals(selectedFilmName)) {
				
				if (seanceDay.equals(chosenDate)) {
					
					String result = seanceFilm + " " + seanceDay + seanceTime;
					tempSeanceData.add(result);
				}
			}
			
			System.out.println(tempSeanceData);
		}
		seanceList.setItems(tempSeanceData);
	}
	
	
	public void setMain(Main main) {
        this.main = main;

        // Add observable list data to the table
        filmTable.setItems(main.getFilmData());
    }
	

}
