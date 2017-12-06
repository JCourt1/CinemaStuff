package application;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import application.MainApplication;
import application.Film;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ModifyExisting implements Initializable {
	
	
	private MainApplication main;
	
	@FXML private TableView<Film> filmTable;
	
	@FXML private TableColumn<Film, String> filmNameColumn;
	@FXML Button deleteBtn;
	@FXML ImageView imgView;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		filmNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		
		filmTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    if (newSelection != null) {
		    	String path = newSelection.getPath();
		    	File file = new File(path);
		    	Image image = new Image(file.toURI().toString());
		        imgView.setImage(image);
		    }
		});
		
	}
	
	public ModifyExisting(){
		
	};
	
	@FXML
	public void deleteFilms(){
		String name = filmTable.getSelectionModel().getSelectedItem().getName();
		for (Film film : main.getFilmData()) {
			if (film.getName().equals(name)) {
				main.getFilmData().remove(film);
			}
		}
	}
	
	
	
	
	public void setMain(MainApplication main) {
		this.main = main;
		// Add observable list data to the table
        filmTable.setItems(main.getFilmData());
		
	}

}
