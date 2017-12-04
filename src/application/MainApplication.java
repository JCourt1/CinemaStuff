package application;
	
import java.io.File;
import java.time.LocalDate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import application.models.films.Film;
import application.models.films.FilmListWrapper;
import application.models.films.Seance;
import application.models.films.SeanceListWrapper;
import application.views.MainControl;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;


public class MainApplication extends Application {
	
	private Stage primaryStage;
	private Parent root;
	
	private ObservableList<Seance> seanceData = FXCollections.observableArrayList();
	private ObservableList<Film> filmData = FXCollections.observableArrayList();
	
	@Override
	public void start(Stage pStage) {
		primaryStage = pStage;
		primaryStage.setTitle("Cinema Booking System");
		
		initLogin();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

    /**
     * Constructor
     */
    public MainApplication() {
    	
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
    
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    
    
    
    public void initLogin() {
    	try {
    		FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource("views/Login.fxml"));
    		
			root = loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("views/application.css").toExternalForm());
			primaryStage.setScene(scene);
			MainControl controller = loader.getController();
            controller.setMain(this);
            primaryStage.sizeToScene();
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }
    
	
	
	///// Loading and saving data to and from filmData and seanceData
	
    public void loadData(File file) {
		try {
			
			
			switch (file.toString()) {
				case "FilmData.xml":
					JAXBContext context = JAXBContext.newInstance(FilmListWrapper.class);
		    		Unmarshaller unmarshalleur = context.createUnmarshaller();
		    		FilmListWrapper wrap = (FilmListWrapper) unmarshalleur.unmarshal(file);
		    		filmData.clear();
		    		filmData.addAll(wrap.getFilms());
					break;
				case "SeanceData.xml":
					JAXBContext context2 = JAXBContext.newInstance(SeanceListWrapper.class);
					Unmarshaller unmarshalleur2 = context2.createUnmarshaller();
					SeanceListWrapper wrap2 = (SeanceListWrapper) unmarshalleur2.unmarshal(file);
					seanceData.clear();
					seanceData.addAll(wrap2.getSeances());
					break;	
			}

		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Could not load: " + file.getPath());

			alert.showAndWait();
		}
		
	}
    
    
    
    
    public void saveData(File file) {
		try {
			
			
			switch (file.toString()) {
				case "FilmData.xml":
					JAXBContext context = JAXBContext.newInstance(FilmListWrapper.class);
		    		Marshaller marshalleur = context.createMarshaller();
		    		marshalleur.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		    		
		    		FilmListWrapper wrap = new FilmListWrapper();
		    		wrap.setFilms(filmData);
		    		
		    		//Save XML to the file
		    		marshalleur.marshal(wrap, file);
					break;
				case "SeanceData.xml":
					JAXBContext context2 = JAXBContext.newInstance(SeanceListWrapper.class);
		    		Marshaller marshalleur2 = context2.createMarshaller();
		    		marshalleur2.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		    		
		    		SeanceListWrapper wrap2 = new SeanceListWrapper();
		    		wrap2.setSeances(seanceData);
		    		
		    		marshalleur2.marshal(wrap2, file);
					break;	
			}

		} catch (Exception e) {
			e.printStackTrace();
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setContentText("Could not save data in file: " + file.getPath());

            alert.showAndWait();
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
