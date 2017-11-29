package application.views.employee;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Movies;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;

public class ModifyScreeningsController extends ControllerDaddy {
	
	
	
	@FXML DatePicker dateP;
	@FXML ComboBox times;
	@FXML ComboBox films;
	@FXML Button add;
	@FXML Button change;
	@FXML Button delete;
	@FXML ListView<String> screeningList;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		for (int i = 9; i < 21; i++){
			String j;
			if (i<10) j = "0" + Integer.toString(i);
			else j = Integer.toString(i);
			j = j+":00";
			
			times.getItems().add(j);
			
		}
		films.getItems().add("None");
		for (String title: Movies.getTitles()) {
			films.getItems().add(title);
		}
		
		

	}
	
	
	// if date and time, show films
	// if date and film, show all screenings on that day of that film.
	// if just film, show all screenings of that film.
	// if film and time... etc.
	
	
	

}
