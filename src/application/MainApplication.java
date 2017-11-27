package application;
	
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import application.models.films.Film;
import application.models.films.FilmListWrapper;
import application.models.films.Seance;
import application.models.films.SeanceListWrapper;
import application.views.EmployeeController;
import application.views.MainControl;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class MainApplication extends Application {
	
	private Stage primaryStage;
	
	private ObservableList<Seance> seanceData = FXCollections.observableArrayList();
	private ObservableList<Film> filmData = FXCollections.observableArrayList();

    /**
     * Constructor
     */
    public MainApplication() {
        // Add some sample data
    	seanceData.add(new Seance(12, "Indiana Jones"));
    	seanceData.add(new Seance(13, "Indiana Jones"));
    	seanceData.add(new Seance(14, "Indiana Jones"));
    	
        
    }

    /**
     * Returns the data as an observable list of Seances. 
     * @return
     */
    public ObservableList<Seance> getSeanceData() {
        return seanceData;
    }
    
    public ObservableList<Film> getFilmData() {
        return filmData;
    }
    
    
    public void showMain_Employee(){
    	try {
    		
    		File file1 = new File("FilmData.xml");
            
            loadFilmDataFromFile(file1);
            
            
            File file2 = new File("SeanceData.xml");
            if (file2 != null) {
            	loadSeanceDataFromFile(file2);
            }
    		
    		
    		
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(MainApplication.class.getResource("views/Main_Employee.fxml"));
    		AnchorPane mainEmployeeView = (AnchorPane) loader.load();
    		
    		Scene scene = new Scene(mainEmployeeView);
    		primaryStage.setScene(scene);
    		
    		EmployeeController controller = loader.getController();
    		controller.setMain(this);
    		
    		
    		
    		
    		
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    
    public void initLogin() {
    	try {
    		
    		FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource("views/Login.fxml"));
    		
			Parent root = loader.load();
			Scene scene = new Scene(root,400,400);
			//scene.getStylesheets().add(getClass().getResource("application/views.css").toExternalForm());
			primaryStage.setScene(scene);
			
			MainControl controller = loader.getController();
            controller.setMain(this);
			
			
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }
    
    
    
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
  
    
	
	@Override
	public void start(Stage pStage) {
		primaryStage = pStage;
		primaryStage.setTitle("Cinema Booking System");
		
		initLogin();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	///// Loading and saving data to and from filmData and seanceData
	
	
	
	
	
	public void loadSeanceDataFromFile(File file) {
    	try {
    		
    		JAXBContext context = JAXBContext.newInstance(SeanceListWrapper.class);
    		Unmarshaller unmarshalleur = context.createUnmarshaller();
    		
    		SeanceListWrapper wrap = (SeanceListWrapper) unmarshalleur.unmarshal(file);
    		
    		seanceData.clear();
    		seanceData.addAll(wrap.getSeances());
    		
    	} catch (Exception e) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setContentText("Could not load: " + file.getPath());

            alert.showAndWait();
    	}
    }
    
    public void saveSeanceDataToFile(File file) {
    	try {
    		
    		JAXBContext context = JAXBContext.newInstance(SeanceListWrapper.class);
    		Marshaller marshalleur = context.createMarshaller();
    		marshalleur.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    		
    		SeanceListWrapper wrap = new SeanceListWrapper();
    		wrap.setSeances(seanceData);
    		
    		//Save XML to the file
    		marshalleur.marshal(wrap, file);
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setContentText("Could not save data in file: " + file.getPath());

            alert.showAndWait();
    	}
    }
    
    public void loadFilmDataFromFile(File file) {
    	try {
    		
    		JAXBContext context = JAXBContext.newInstance(FilmListWrapper.class);
    		Unmarshaller unmarshalleur = context.createUnmarshaller();
    		
    		FilmListWrapper wrap = (FilmListWrapper) unmarshalleur.unmarshal(file);
    		
    		filmData.clear();
    		filmData.addAll(wrap.getFilms());
    		
    	} catch (Exception e) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setContentText("Could not load: " + file.getPath());

            alert.showAndWait();
    	}
    }
    
    public void saveFilmDataToFile(File file) {
    	try {
    		
    		JAXBContext context = JAXBContext.newInstance(FilmListWrapper.class);
    		Marshaller marshalleur = context.createMarshaller();
    		marshalleur.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    		
    		FilmListWrapper wrap = new FilmListWrapper();
    		wrap.setFilms(filmData);
    		
    		//Save XML to the file
    		marshalleur.marshal(wrap, file);
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setContentText("Could not save data in file: " + file.getPath());

            alert.showAndWait();
    	}
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
