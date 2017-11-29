package application.views.employee;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.MainApplication;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

public class ControllerDaddy implements Initializable {
	
	
	private MainApplication main;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void setMain(MainApplication main) {
		this.main = main;
	};
	
	public MainApplication getMain() {
		return this.main;
	}
	
	
	public FXMLLoader loadSomeFXML(String fxmlPath) throws IOException {
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApplication.class.getResource(fxmlPath));
		return loader;
	}

}
