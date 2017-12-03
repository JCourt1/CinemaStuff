package application.views.plan;

import java.net.URL;
import java.util.ResourceBundle;

import application.MainApplication;
import application.views.plan.util.Fader;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class BaseEmployeeController implements Initializable{
	
	
	
	@FXML private Button exportData;
	@FXML private Button manageFilms;
	@FXML private Button logOut;
	@FXML private ComboBox<String> viewBy;
	@FXML private AnchorPane viewByWindow;
	
	
	private MainApplication main;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
        viewBy.getItems().addAll("Date", "Films");
        viewBy.setValue("Date");
       
		
	}
	
	public void setMain(MainApplication main) {
        this.main = main;
        
        displayContent();
    }
	
	
	
	@FXML
	private void chooseView() {
		displayContent();
		Fader.fadeIn(viewByWindow);
	}

	private void displayContent() {
		String path = "views/plan/";
		String doc = (viewBy.getValue().equals("Date") ? "viewByDate.fxml":"viewFilmInfo.fxml");
		path += doc;
		
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApplication.class.getResource(path));
        
        try {
        viewByWindow.getChildren().clear();
        viewByWindow.getChildren().add((AnchorPane) loader.load());
        
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        switch (doc) {
        	case "viewFilmInfo.fxml":
        		ViewFilmInfoController controller = loader.getController();
        		controller.setMain(this.main);
        		break;
        	default: 
        		ViewByDateController controller2 = loader.getController();
        		controller2.setMain(this.main);
        }
	}
	
	@FXML
	private void goToManageFilms() {
		try {

			FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource("views/plan/ManageFilms.fxml"));
            BorderPane newPage = (BorderPane) loader.load();
            
            
            
            Stage tempStage = new Stage();
            tempStage.setTitle("Manage Films");
            tempStage.initModality(Modality.WINDOW_MODAL);
            tempStage.initOwner(main.getPrimaryStage());
            
            
            
            ManageFilmsController controller = loader.getController();
            controller.setMain(this.main);
            controller.setTheStage(tempStage);
            
            Scene scene = new Scene(newPage);
            tempStage.setScene(scene);
            
            tempStage.showAndWait();
//            tempStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	@FXML
	private void logOut() {
		
		FadeTransition fadeTrans = new FadeTransition();
		fadeTrans.setDuration(Duration.millis(500));
		fadeTrans.setNode(viewByWindow.getParent());
		fadeTrans.setFromValue(1);
		fadeTrans.setToValue(0);
		
		fadeTrans.setOnFinished(e -> main.initLogin());
		
		fadeTrans.play();
	}
	
	
	
}
