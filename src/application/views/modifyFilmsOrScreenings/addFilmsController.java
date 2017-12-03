package application.views.modifyFilmsOrScreenings;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import application.MainApplication;
import application.models.films.Film;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

public class addFilmsController implements Initializable {
	
	private MainApplication main;
	
	@FXML private TextField filmName;
	@FXML private Button saveBtn;
	@FXML private Label picName;
	@FXML private Button uploadPic;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	
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
//		String filmN = filmName.getText();
//		String oldPath = picName.getText();
//		String newPath = "resources/images/" + filmN + ".png";
//		
//		try {
//            BufferedImage image;
//            File imageFile = new File(oldPath);
//            image = ImageIO.read(imageFile);
//            ImageIO.write(image, "png",new File(newPath));
//            
//            Film film = new Film(filmN, newPath, description, ticketPrice);
//    		main.getFilmData().add(film);
//    		File file = new File("FilmData.xml");
//    		main.saveFilmDataToFile(file);
//
//        } catch (IOException e) {
//        	e.printStackTrace();
//        }
		
		
		
	}

	public void setMain(MainApplication main) {
		this.main = main;
		
	}
	
	

}



