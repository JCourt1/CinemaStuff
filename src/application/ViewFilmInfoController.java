package application;

import java.net.URL;
import java.util.ResourceBundle;

import application.MainApplication;
import javafx.fxml.Initializable;

public class ViewFilmInfoController implements Initializable{
	
	private MainApplication main;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void setMain(MainApplication main) {
		this.main = main;
	}

}
