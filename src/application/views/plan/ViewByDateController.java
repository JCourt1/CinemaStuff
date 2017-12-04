package application.views.plan;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.MainApplication;
import application.models.films.Film;
import application.models.films.Seance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ViewByDateController implements Initializable {
	
	
	private MainApplication main;

	private ObservableList<Film> filteredFilmData;
	private ObservableList<Seance> filteredSeanceData;
	
	@FXML private TableView<Film> filmTable;

	@FXML private TableColumn<Film, String> imageColumn;
	@FXML private TableColumn<Film, String> filmNameColumn;
	@FXML private TableColumn<Film, String> filmDescripColumn;

	@FXML private DatePicker datePicker;

	@FXML private TableView<Seance> screeningsTable;
	@FXML private TableColumn<Seance, String> filmNameColumn2;
	@FXML private TableColumn<Seance, LocalDate> dateColumn;
	@FXML private TableColumn<Seance, Integer> timeColumn;


	@FXML private Button addScreenings;


	@FXML
	public void refreshData() {
		filmTable.refresh();
		
		screeningsTable.refresh();
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		

		filmNameColumn.setCellValueFactory(new PropertyValueFactory<Film, String>("name"));



		filmDescripColumn.setCellValueFactory(new PropertyValueFactory<Film, String>("description"));

		filmDescripColumn.setCellFactory(new Callback<TableColumn<Film, String>, TableCell<Film, String>>() {

			@Override
			public TableCell<Film, String> call(
					TableColumn<Film, String> param) {
				TableCell<Film, String> cell = new TableCell<Film, String>();
				
				
				Text text = new Text();
				cell.setGraphic(text);
				cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
				text.wrappingWidthProperty().bind(cell.widthProperty());
				text.textProperty().bind(cell.itemProperty());
				text.setFill(Color.YELLOW);
				return cell ;
			}

		});


		imageColumn.setCellValueFactory(cellData -> cellData.getValue().pathProperty());

		imageColumn.setCellFactory(new Callback<TableColumn<Film,String>,TableCell<Film,String>>(){        
			@Override
			public TableCell<Film,String> call(TableColumn<Film,String> param) {                
				TableCell<Film,String> cell = new TableCell<Film,String>(){
					ImageView imageview = new ImageView();
					@Override
					public void updateItem(String item, boolean empty) {                        
						if(item!=null){
							File file = new File(item);
							Image image = new Image(file.toURI().toString());

							HBox box= new HBox();
							box.setSpacing(10) ;
							imageview.setPreserveRatio(true);
							imageview.setImage(image); 
							imageview.setFitHeight(130);
//							imageview.setFitWidth(70);
							


							box.getChildren().addAll(imageview);
							setGraphic(box);
						}
					}
				};              
				return cell;
			}

		});


		filmTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			filteredSeanceData.clear();
			if (newSelection == null) {
				screeningsTable.getSelectionModel().clearSelection();
			} else {

				String name = newSelection.getName();
				for (Seance seance : main.getSeanceData()) {
					if (seance.getFilm().equals(name)) {
						filteredSeanceData.add(seance);
					}
				}



			} 

			
		});
		
		
		filmNameColumn2.setCellValueFactory(cellData -> cellData.getValue().filmProperty());
		dateColumn.setCellValueFactory(new PropertyValueFactory<Seance, LocalDate>("day"));
		timeColumn.setCellValueFactory(new PropertyValueFactory<Seance, Integer>("time"));
		
	}




	@FXML
	private void chooseDate() {
		
		System.out.println("h1");

		if (datePicker.getValue() == null) {
			System.out.println("h2");
			filteredFilmData = FXCollections.observableArrayList(main.getFilmData());
			refreshData();
			//			FXCollections.copy(filteredFilmData, main.getFilmData());
			return;
		}

		LocalDate datePicked = datePicker.getValue();

		if (datePicked.isBefore(LocalDate.now())) {
			System.out.println("You can't pick a time before today");
			return;
		} 

		filteredFilmData.clear();
		ArrayList<String> filmNames = new ArrayList<String>();

		for (Seance seance : main.getSeanceData()) {
			if (seance.getDay().equals(datePicked)) {

				String filmName = seance.getFilm();
				if (!(filmNames.contains(filmName))) {

					filmNames.add(filmName);
				}
			}
		}

		for (Film film : main.getFilmData()) {
			if (filmNames.contains(film.getName())) {
				filteredFilmData.add(film);
			}
		}

		for (Film film: filteredFilmData) {
			System.out.println(film.getName() + film.getPath());
		}

		refreshData();

	}


	@FXML
	private void goToAddScreenings() {
		try {

			FXMLLoader loader = new FXMLLoader();

			loader.setLocation(MainApplication.class.getResource("views/plan/AddScreenings.fxml"));


			AnchorPane newPage = (AnchorPane) loader.load();




			Stage tempStage = new Stage();
			tempStage.setTitle("Add Screening");
			//tempStage.initModality(Modality.WINDOW_MODAL);

			AddScreeningsController controller = loader.getController();
			controller.setMain(this.main, tempStage);

			Scene scene = new Scene(newPage);
			tempStage.setScene(scene);

			//tempStage.showAndWait();
			tempStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setMain(MainApplication main) {
		this.main = main;
		filteredFilmData = FXCollections.observableArrayList(main.getFilmData());
		filmTable.setItems(filteredFilmData);
		filteredSeanceData = FXCollections.observableArrayList();
		screeningsTable.setItems(filteredSeanceData);
	}
	

}
