package application.views.plan;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;

import application.MainApplication;
import application.models.films.Film;
import application.models.films.Seance;
import application.util.DateConversion;
import application.util.PlaceHolderUtil;
import application.views.plan.util.DataSaveException;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.SortType;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ViewByDateController implements Initializable {
	
	
	private HashMap<String, Integer> rowComparison = new HashMap<String, Integer>();
	private MainApplication main;
	private ObjectProperty<TableRow<Film>> selectedRow = new SimpleObjectProperty<>();

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
	
	@FXML private Button screeningDetail;
	@FXML private ComboBox<String> genres;
	
	@FXML private Button addScreenings;
	@FXML private Button deleteScreenings;
	@FXML private Button clearDate;
	@FXML private Button clearGenre;
	
	
	@FXML
	public void refreshData() {
		filteredFilmData.clear();
		
		for (Film film : main.getFilmData()) {
			filteredFilmData.add(film);
		}
		rowComparison.clear();
		refreshTable();
		placeHolderInitialize();
	}
	
	
	public void refreshTable() {
		rowComparison.clear();
		filmTable.refresh();
		
		screeningsTable.refresh();
		placeHolderInitialize();
	}
	
	@FXML 
	private void clearDate() {
		datePicker.setValue(null);
	}
	
	@FXML
	private void clearGenre() {
		genres.valueProperty().set(null);
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		setUpTable();
		ObservableList<String> genresOfFilm = FXCollections.observableArrayList();
		genresOfFilm.addAll("Comedy", "Horror", "Action", "Romantic Comedy", "Fantasy");
		genres.setItems(genresOfFilm);
		
	}
	
	
	private void placeHolderInitialize() {
		
		
		File file = new File("resources/images/rodin.png");
		Image image1 = new Image(file.toURI().toString());
		ImageView imVu = new ImageView(image1);
		imVu.setPreserveRatio(true);
		imVu.setFitWidth(70);
		
		VBox vbox = new VBox();
		vbox.setAlignment(Pos.CENTER);
		vbox.setSpacing(20);
		
		vbox.setStyle("-fx-border-color: red;\n" +
                "-fx-border-insets: 5;\n" +
                "-fx-border-width: 3;\n" +
                "-fx-border-style: dashed;\n");
		
		vbox.getChildren().addAll(imVu, PlaceHolderUtil.getMessage(), PlaceHolderUtil.getQuote());
		
		
		screeningsTable.setPlaceholder(vbox);
		filmTable.setPlaceholder(vbox);
	}
	
	
	
	
	
	
	@FXML
	private void chooseGenre() {
		
		if (genres.getValue() == null) {
			
			if (datePicker.getValue() == null) {
			filteredSeanceData.clear();
			filteredFilmData.clear();
			filmTable.getSelectionModel().select(null);
			
			for (Film film : main.getFilmData()) {
				filteredFilmData.add(film);
			}
			refreshTable();
			} else {
				dateChanged();
			}

			return;
		} else {
			
			if (datePicker.getValue() == null) {
				
				filteredFilmData.clear();
				for (Film film : main.getFilmData()) {
					if (film.getMainGenre() == null) {
						continue;
					}
					if (film.getMainGenre().equals(genres.getValue())) {
						filteredFilmData.add(film);
					}
				}
				
				refreshTable();
				
			} else {
				dateChanged();
			}
			
			
			
		}

		
	}
	
	
	
	@FXML
	private void dateChanged() {
			
			LocalDate dateChosen = datePicker.getValue();
			
			if (dateChosen == null) {
				
				if (genres.getValue() == null) {
				filteredSeanceData.clear();
				filteredFilmData.clear();
				filmTable.getSelectionModel().select(null);
				
				for (Film film : main.getFilmData()) {
					filteredFilmData.add(film);
				}
				refreshTable();
				} else {
					chooseGenre();
				}

				return;
			}
			
			
			if (dateChosen.isBefore(LocalDate.now())) {
				System.out.println("You can't pick a time before today");
				return;
			}
			
			
			
			Film chosenFilm = null;
			
			if (! filmTable.getSelectionModel().isEmpty()) {
				chosenFilm = filmTable.getSelectionModel().getSelectedItem();
			}
			
			filteredSeanceData.clear();
			filteredFilmData.clear();

				ArrayList<String> filmNames = new ArrayList<String>();
				
				for (Seance seance : main.getSeanceData()) {

					if (seance.getDay().equals(dateChosen)) {
						filteredSeanceData.add(seance);

						String filmName = seance.getFilm();
						if (!(filmNames.contains(filmName))) {

							filmNames.add(filmName);
							
						}
					}
				}
				
				String genre = genres.getValue();
				
				for (Film film : main.getFilmData()) {
					if (filmNames.contains(film.getName())) {
						if (genre == null || genre.equals(film.getMainGenre())) {
							filteredFilmData.add(film);
						} else {
							
							for (Seance seance : filteredSeanceData) {
								if (seance.getFilm() == film.getName()) {
									filteredSeanceData.remove(seance);
								}
							}
							
						}
						
					}
				}
				
				if (chosenFilm != null) {
					filmTable.getSelectionModel().selectFirst();
					for (int i = 0; i < filmTable.getItems().size(); i++) {
				        if (filmTable.getItems().get(i).getName() == chosenFilm.getName()) {
				            filmTable.getSelectionModel().select(i);
				        }
				    }

				}
				
				refreshTable();
	}
	
	@FXML
	private void goToScreeningDetails() {
		
		if (screeningsTable.getSelectionModel().isEmpty()) {
			System.out.println("Select a screening first.");
			return;
		}
		
		Seance seance = screeningsTable.getSelectionModel().getSelectedItem();
		
		try {

			FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource("views/plan/ScreeningDetails.fxml"));
            BorderPane newPage = (BorderPane) loader.load();
            
            
            
            Stage tempStage = new Stage();
            tempStage.setTitle("Manage Films");
            tempStage.initModality(Modality.WINDOW_MODAL);
            tempStage.initOwner(main.getPrimaryStage());
            
             
            
            ScreeningDetailsController controller = loader.getController();
            controller.setMain(this.main, seance);
            controller.setTheStage(tempStage);
            
            Scene scene = new Scene(newPage);
            tempStage.setScene(scene);
            
            tempStage.showAndWait();
            
		} catch(Exception e) {
			e.printStackTrace();
		}
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
	
	@FXML
	private void deleteScreenings() {
		
		Seance screening = screeningsTable.getSelectionModel().getSelectedItem();
		
		if (screening == null) {
			System.out.println("No screening selected");
			return;
		}
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.initOwner(filmTable.getScene().getWindow());
		alert.setTitle("Delete Screenings");
		alert.setHeaderText(String.format("Delete Existing Screenings?"));
		Optional<ButtonType> result = alert.showAndWait();
		try {
			if(!result.isPresent()) {
				throw new DataSaveException("You need to confirm the save");

			} else if(result.get() == ButtonType.OK) {
				
				main.getSeanceData().remove(screening);
				File file = new File("SeanceData.xml");
				main.saveData(file);
				filteredSeanceData.remove(screening);
				
				refreshTable();
				
				
			} else if(result.get() == ButtonType.CANCEL) {
				throw new DataSaveException("Cancel selected");
			} 
		} catch (DataSaveException e) {
			e.toString();
		}


	}
	
	
	public void setMain(MainApplication main) {
		this.main = main;
		filteredFilmData = FXCollections.observableArrayList(main.getFilmData());
		filmTable.setItems(filteredFilmData);
		filteredSeanceData = FXCollections.observableArrayList();
		dateColumn.setSortType(TableColumn.SortType.ASCENDING);
//		screeningsTable.setItems(filteredSeanceData);
		
		
		
		SortedList<Seance> sortedScreenings = new SortedList<>(filteredSeanceData);
		sortedScreenings.comparatorProperty().bind(screeningsTable.comparatorProperty());
		screeningsTable.getSortOrder().addAll(dateColumn, timeColumn);
		
		screeningsTable.getSortOrder().addListener( (ListChangeListener<? super TableColumn<?,?>>)observable -> {
		    if( !screeningsTable.getSortOrder().contains(dateColumn) ){
		    	dateColumn.setSortType(SortType.ASCENDING);
		    	screeningsTable.getSortOrder().add(dateColumn);
		    }
		    if( !screeningsTable.getSortOrder().contains(timeColumn) ){
		    	timeColumn.setSortType(SortType.ASCENDING);
		    	screeningsTable.getSortOrder().add(timeColumn);
		    }
		});
		
		screeningsTable.setItems(sortedScreenings);
		screeningsTable.sort();
	}
	
	
	
	public void setUpTable() {
		
		
		placeHolderInitialize();
		filmNameColumn.setCellValueFactory(new PropertyValueFactory<Film, String>("name"));
		filmDescripColumn.setCellValueFactory(new PropertyValueFactory<Film, String>("description"));
		imageColumn.setCellValueFactory(cellData -> cellData.getValue().pathProperty());
		
		
		
		//setting how to render the Film Description column
		
		filmDescripColumn.setCellFactory(new Callback<TableColumn<Film, String>, TableCell<Film, String>>() {

			@Override
			public TableCell<Film, String> call(
					TableColumn<Film, String> param) {
				TableCell<Film, String> cell = new TableCell<Film, String>();
				
				if (cell.itemProperty() != null) {
				
//					TableView table = cell.getTableView();
//					TablePosition firstCell = new TablePosition(table, cell.getTableRow().getIndex(), filmDescripColumn);
//				System.out.println(firstCell.toString());	
				
				Text text = new Text();
				cell.setGraphic(text);
				cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
				text.wrappingWidthProperty().bind(cell.widthProperty());
				text.textProperty().bind(cell.itemProperty());
				text.setFill(Color.ORANGE);
				} else {
				}
				
				
				
				return cell ;
			}

		});
		
		//setting the rendering of images in the table
		
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
		
		
		// setting a listener on the selected item in the film table to update the screenings table

		filmTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

			filteredSeanceData.clear();
			screeningsTable.refresh();
			rowComparison.clear();
			
			if (newSelection == null) {
				screeningsTable.getSelectionModel().clearSelection();
			} else {

				String name = newSelection.getName();
				for (Seance seance : main.getSeanceData()) {
					if (seance.getFilm().equals(name)) {

						if (datePicker.getValue() != null) {
							if (datePicker.getValue().equals(seance.getDay())) {
								filteredSeanceData.add(seance);
							}
						} else {
							filteredSeanceData.add(seance);
						}
					}
				}
			}
		});

		// deselect rows if you click on them when they are already selected

		filmTable.addEventFilter(MouseEvent.MOUSE_CLICKED, evt -> {
			Node source = evt.getPickResult().getIntersectedNode();
			
			
			
			
			//look for the TableRow that is the source of this mouse click event
			while (source != null && !(source instanceof TableRow)) {
				source = source.getParent();
			}
			
			// if the row is the currently selected row, deselect it
			if (source == null || (source instanceof TableRow && ((TableRow) source).equals(selectedRow.get()))) {
				
				filmTable.getSelectionModel().clearSelection();
				selectedRow = new SimpleObjectProperty<>();
				
				if (datePicker.getValue() != null) {
					ArrayList<String> filmNames = new ArrayList<String>();
					for (Film film : filteredFilmData) {
						filmNames.add(film.getName());
					}
					for (Seance seance : main.getSeanceData()) {
						if (filmNames.contains(seance.getFilm())) {
							if (datePicker.getValue().equals(seance.getDay())) {
									filteredSeanceData.add(seance);
							}
						}
					}
				}
				return;
			}

			selectedRow.set((TableRow) source);

		});
		

		// setting what goes in the screenings table

		filmNameColumn2.setCellValueFactory(cellData -> cellData.getValue().filmProperty());
		dateColumn.setCellValueFactory(new PropertyValueFactory<Seance, LocalDate>("day"));
		timeColumn.setCellValueFactory(new PropertyValueFactory<Seance, Integer>("time"));

		// setting how to render the dates
		
		dateColumn.setCellFactory(tc -> new TableCell<Seance, LocalDate>() {
			@Override
			protected void updateItem(LocalDate date, boolean empty) {
				super.updateItem(date, empty);
				
				boolean toShowOrNotToShow_ThatIsTheQuestion = false;
				
				if (empty) {
					//setText(null);
				} else {
					String str = date.toString();
					
					
					// if the hashtable is empty, then it's the first row, so store the date and row index to the hashmap and display date in table
					if (rowComparison.isEmpty()) {
//						setText(DateConversion.format(date));
						rowComparison.put(str, 1);
						
						toShowOrNotToShow_ThatIsTheQuestion = true;
						
					} 
					// if the date of the current row is in the hashmap and there is only 1 date in it, if the row is greater than or
					//equal to the current row, display the date.
					else if ((rowComparison.keySet().contains(str) && rowComparison.size() == 1)){
						
						if (rowComparison.get(str) >= getIndex() + 1) {
							toShowOrNotToShow_ThatIsTheQuestion = true;
						}
					} 
					// if the date is in the hashmap, print the date only if the row is the same
					// (necessary when the display is out of synch with the hasmap's data)
					else if (rowComparison.keySet().contains(str)) {
						
						if (rowComparison.get(str) == getIndex() + 1) {

							toShowOrNotToShow_ThatIsTheQuestion = true;
						}
					// if not, that means the date isn't in the hashmap, so store it and display it in table
					} else {
						rowComparison.put(str, getIndex() + 1);
						toShowOrNotToShow_ThatIsTheQuestion = true;
					}
					
					
					if (toShowOrNotToShow_ThatIsTheQuestion) {
						setText(DateConversion.format(date));
					}
					
					
					///// it was just this \/ before
					
//					setText(DateConversion.format(date));
					
					
				}
			}
		});
		
	}
	

}
