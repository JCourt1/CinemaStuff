package application.views.employee;

import java.io.IOException;

import application.MainApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class EmployeeController extends ControllerDaddy {
	
	@FXML Button films;
	
	@FXML Button screenings;
	
	@FXML AnchorPane anchorPane1;
	
	
	@FXML
	private void searchFilms() {
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApplication.class.getResource("views/employee/searchFilms.fxml"));
			
			AnchorPane content = (AnchorPane) loader.load();
			anchorPane1.getChildren().clear();
			anchorPane1.getChildren().add(content);
			
		} catch (IOException e) {
			System.out.println("errrooooor");
		}
	}
	
	@FXML
	private void modifyScreenings() {
		
		try {
			FXMLLoader loader = loadSomeFXML("views/employee/modifyScreenings.fxml");
			AnchorPane content = (AnchorPane) loader.load();
			anchorPane1.getChildren().clear();
			anchorPane1.getChildren().add(content);
			
		} catch (IOException e) {
			System.out.println("errrooooor");
		}
	}

}
