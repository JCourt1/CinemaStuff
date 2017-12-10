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
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class BaseEmployeeController implements Initializable{
	

	private MainApplication main;
	private ViewByDateController viewByDateController;
	
	@FXML private Button exportData;
	@FXML private Button manageFilms;
	@FXML private Button logOut;
	@FXML private ComboBox<String> viewBy;
	@FXML private BorderPane viewByWindow;
	private AnchorPane root;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
        viewBy.getItems().addAll("Date", "Films");
        viewBy.setValue("Date");
       
		
	}
	
	public void setMain(MainApplication main, AnchorPane root) {
        this.root = root;
        this.main = main;
        this.main.getPrimaryStage().sizeToScene();
        
        root.setOpacity(0);
		Fader.fadeIn(root);
        
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
            viewByWindow.setCenter((AnchorPane) loader.load());
//            viewByWindow.getChildren().add((AnchorPane) loader.load());
            
            } catch (Exception e) {
            	e.printStackTrace();
            }
        
        if (doc.equals("viewByDate.fxml")) {
        	viewByDateController = loader.getController();
        	viewByDateController.setMain(this.main);
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
            controller.setMain(this.main, viewByDateController);
            controller.setTheStage(tempStage);
            
            Scene scene = new Scene(newPage);
            tempStage.setScene(scene);
            
            tempStage.showAndWait();
            
//            displayContent();
            
//            viewByDateController.refreshData();
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
