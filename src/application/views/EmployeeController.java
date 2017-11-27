package application.views;

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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import application.models.films.Seance;
import application.views.modifyFilmsOrScreenings.addFilmsController;
import application.MainApplication;
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
	
	
	
	// test 
	@FXML
	private Button addFilm;
	// test 
	
	@FXML
	private BorderPane bPane;
	
	
	private MainApplication main;
	
	
	public EmployeeController(){
		
	};
	
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		filmNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		
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
        loader.setLocation(MainApplication.class.getResource("views/modifyFilmsOrScreenings/ModifyFilms.fxml"));
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
			
			if (seanceFilm.equals(selectedFilmName)) {
				
				if (seanceDay.equals(chosenDate)) {
					
					tempSeanceData.add(seanceTime);
				}
			}
		}
		seanceList.setItems(tempSeanceData);
	}
	
	
	public void setMain(MainApplication main) {
        this.main = main;

        // Add observable list data to the table
        filmTable.setItems(main.getFilmData());
    }
	

}
