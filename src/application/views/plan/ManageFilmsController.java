package application.views.plan;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import application.MainApplication;
import application.models.films.Film;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

public class ManageFilmsController implements Initializable {

	private MainApplication main;
	
	@FXML private TextField filmName;
	@FXML private TextField description;
	@FXML private ComboBox<Integer> ticketCost;
	@FXML private Button uploadImage;
	@FXML private ImageView pic;
	@FXML private Button saveDeets;
	@FXML private Button cancel;
	
	@FXML private CheckBox toEditFilm;
	@FXML private ComboBox<Film> chooseExistingFilm;
	@FXML private Button deleteFilm;
	@FXML private Label oldPicPath;

	private Stage stage;
	
	
	@FXML
	private void enableCombo() {
		
		boolean bool = !(toEditFilm.isSelected()) ? true:false;
		
		chooseExistingFilm.setDisable(bool);
		
		if (bool) {
			
			chooseExistingFilm.valueProperty().set(null);
			pic.setImage(null);
			description.setText(null);
			oldPicPath.setText(null);
			ticketCost.valueProperty().set(null);
			
		}
		
	     
	}
	
	@FXML
	private void existingFilmPicked(){
		
		if (chooseExistingFilm.getValue() != null) {
		Film film = chooseExistingFilm.getValue();
		oldPicPath.setText(film.getPath());
		filmName.setText(film.getName());
		description.setText(film.getDescription());
		
		
    	Image image = new Image((new File(film.getPath()).toURI().toString()));
        pic.setImage(image);
        
        
        
        
		}
		
	}
	
	@FXML
	private void uploadPic() {
		FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "png files (*.png)", "*.png");
        fileChooser.getExtensionFilters().add(extFilter);
        
        try {
        	File file = fileChooser.showOpenDialog(main.getPrimaryStage());
            
            Image image = new Image(file.toURI().toString());
            pic.setImage(image);
            oldPicPath.setText(file.getPath());
        } catch (Exception e) {
        	
        }
        
	}
	
	
	@FXML
	private void saveDetails() {
		
		String filmN = filmName.getText();
		String oldPath = oldPicPath.getText();
		
		String newPath = "resources/images/" + filmN + ".png";
		String descrip = description.getText();
		
		Integer ticketPrice = ticketCost.getValue();
		
		try {
            BufferedImage image;
            File imageFile = new File(oldPath);
            image = ImageIO.read(imageFile);
            ImageIO.write(image, "png",new File(newPath));
            
            Film film = new Film(filmN, newPath, descrip, ticketPrice);
    		main.getFilmData().add(film);
    		File file = new File("FilmData.xml");
    		main.saveFilmDataToFile(file);

        } catch (IOException e) {
        	e.printStackTrace();
        }
		
	}
	
	@FXML
	private void cancelModify() {
		stage.close();
	}
	
	
	
	
	
	
	
	
	public void setTheStage(Stage stage) {
		this.stage = stage;
	}
	
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		ObservableList<Integer> prices = FXCollections.observableArrayList();
		prices.addAll(5, 8, 10);
		ticketCost.setItems(prices);
		
		ticketCost.setCellFactory(new Callback<ListView<Integer>, ListCell<Integer>>() {
			@Override public ListCell<Integer> call(ListView<Integer> p) {
				return new ListCell<Integer>() {
					protected void updateItem(Integer item, boolean empty) {
						super.updateItem(item, empty);

						if (empty || item == null) {
							setText(null);
							setGraphic(null);
						} else {
							setText("£" + item.toString());
						}
					}
				};
			}

		});
		
		ticketCost.setConverter(new StringConverter<Integer>() {
			@Override
			public String toString(Integer integer) {
				if (integer == null) {
					return null;
				} else {
					return "£" + integer.toString();
				}
			}
			
			@Override
			public Integer fromString(String integerString) {
				return null;
			}
		});
		
		
		chooseExistingFilm.setDisable(true);
		oldPicPath.setVisible(false);
		
		
		
		
	}
	
	public void setMain(MainApplication main) {
		this.main = main;
		
		chooseExistingFilm.setItems(main.getFilmData());
		chooseExistingFilm.setCellFactory((comboBox) -> {
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
		
		chooseExistingFilm.setConverter(new StringConverter<Film>() {
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
		
		chooseExistingFilm.setDisable(true);
		oldPicPath.setVisible(false);
		
	}

}
