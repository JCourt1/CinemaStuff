package application;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import com.sun.javafx.tk.Toolkit;

import application.MainApplication;
import application.Film;
import application.MultiSelect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;

public class AddScreeningsController implements Initializable {

	private MainApplication main;
	
//	@FXML private ComboBox<String> films;
	@FXML private ComboBox<Film> films;
	@FXML private ListView<Integer> times;
	@FXML private DatePicker dates;
	@FXML private Button saveBtn;
	@FXML private ImageView pic;
	
	
	
	
	@FXML
	private void saveScreeningData() {
		
		
		for (int i = 0; i < numOftimepicked; i ++) {
			
		}
		
		
		
		main.getSeanceData().add(e);
		
		
		
		
	}

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		ObservableList<Integer> list = FXCollections.observableArrayList();
		for (int i = 9; i < 20; i++) {
			list.add(i);
		}
		times.setItems(list);
		
		times.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		times.addEventFilter( MouseEvent.MOUSE_PRESSED, MultiSelect.multiSelectEventHandler());
		times.addEventFilter( MouseEvent.MOUSE_RELEASED, MultiSelect.multiSelectEventHandler());
		
	}
	
	
	
	
	
	public void setMain(MainApplication main) {
		this.main = main;
		
		
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
		
	}

}
