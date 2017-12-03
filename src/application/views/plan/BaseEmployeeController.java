package application.views.plan;

import java.net.URL;
import java.util.ResourceBundle;

import application.MainApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class BaseEmployeeController implements Initializable{
	
	
	
	@FXML private Button exportData;
	@FXML private Button logOut;
	@FXML private ComboBox<String> viewBy;
//	@FXML private BorderPane bPane;
	@FXML private AnchorPane viewByWindow;
	private MainApplication main;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		viewBy.getItems().addAll("Date", "Films");
	}
	
	public void setMain(MainApplication main) {
        this.main = main;
    }
	
	@FXML
	private void displayContent() {
		String fxmlFile;
		if (viewBy.getValue().equals("Date")) {
			fxmlFile = "viewByDate.fxml";
		} else {
			fxmlFile = "viewFilmInfo.fxml";
		}
		
		String path = "views/plan/" + fxmlFile;
		
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApplication.class.getResource(path));
        
        try {
        AnchorPane aP = (AnchorPane) loader.load();
        
        
//		bPane.setCenter(aP);
        
        viewByWindow.getChildren().clear();
        viewByWindow.getChildren().add(aP);
        
        
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        
        if (fxmlFile.equals("viewByDate.fxml")) {
        	ViewByDateController controller = loader.getController();
    		controller.setMain(this.main);
        } else {
        	ViewFilmInfoController controller = loader.getController();
    		controller.setMain(this.main);
        }
	}
	
	
}
