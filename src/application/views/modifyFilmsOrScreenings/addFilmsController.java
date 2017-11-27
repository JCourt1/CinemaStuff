package application.views.modifyFilmsOrScreenings;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import application.MainApplication;
import application.models.films.Film;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

public class addFilmsController implements Initializable {
	
	private MainApplication main;
	
	@FXML private TextField filmName;
	@FXML private Button saveBtn;
	@FXML private Label picName;
	@FXML private Button uploadPic;
	
	
	public addFilmsController(){
			
	};
	
	@FXML
	private void choosePicture(){
		FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "png files (*.png)", "*.png");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show open file dialog
        File file = fileChooser.showOpenDialog(main.getPrimaryStage());
        picName.setText(file.getPath());
	}
	
	@FXML
	private void saveFilm(){
		String name = filmName.getText();
		String path = picName.getText();
		Film film = new Film(name, path);
		main.getFilmData().add(film);
	}
	
	
	
	
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	public void setMain(MainApplication main) {
		// TODO Auto-generated method stub
		this.main = main;
	}
	
	

}
