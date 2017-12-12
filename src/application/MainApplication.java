package application;
	
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import application.Client;
import application.ClientWrapper;
import application.Employee;
import application.EmployeeWrapper;
import application.Film;
import application.FilmListWrapper;
import application.Seance;
import application.SeanceListWrapper;
import application.EmployeeController;
import application.MainControl;
import application.BaseEmployeeController;
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

/**
 * 
 * This class is the Main class of the program. 
 * It launches the first (login-in) window when the program is run.
 * It contains the methods and variables to load and save data from the xml files.
 * The data is loaded into the Observable Lists. 
 * 
 * 
 * @author David Rudolf
 * 
 *
 */

public class MainApplication extends Application {
	
	private Stage primaryStage;
	
	/**
	 * This ObservableList contains the  sessions (wievings) that we refer to as "Seance". 
	 * These are loaded from the SeanceData.xml file. 
	 * 
	 */
	private static ObservableList<Seance> seanceData = FXCollections.observableArrayList();
	/**
	 * This JavaFX ObservableList contains the film objects that are loaded from the FilmData.xml file.
	 */
	private static ObservableList<Film> filmData = FXCollections.observableArrayList();
	/**
	 * This JavaFX ObservableList contains the client objects that are loaded from the Clients.xml file.
	 */
	private static ObservableList<Client> clientData = FXCollections.observableArrayList();
	/**
	 * This JavaFX ObservableList contains the employee objects that are loaded from the Employees.xml file.
	 */
	private static ObservableList<Employee> employeeData = FXCollections.observableArrayList();
	/**
	 * This JavaFX ObservableList contains the booking objects that are loaded from the Bookings.xml file.
	 */
	private static ObservableList<Booking> bookingData = FXCollections.observableArrayList();
	

/**
 * This is the getter method for the Seance list.
 * 
 * @return seanceData 
 */
    public static ObservableList<Seance> getSeanceData() {
        return seanceData;
    }
    
    /**
     * This is the getter method for the Film list.
     * 
     * @return filmData 
     */
    
    public static ObservableList<Film> getFilmData() {
        return filmData;
    }
    
    /**
     * This is the getter method for the Client list.
     * 
     * @return clientData 
     */
    
    public static ObservableList<Client> getClientData() {
        return clientData;
    }
    
    public static ObservableList<Employee> getEmployeeData() {
        return employeeData;
    }
    
    /**
     * This is the getter method for the Booking list.
     * 
     * @return bookingData 
     */
    
    public static ObservableList<Booking> getBookingData() {
        return bookingData;
    }
    
    
    
    
    
    
    
    /*
     * This method initiates the first window in the program - the login window. 
     *
     * @throws IOException 
     */
    
    public void initLogin() {
    	try {
    		
    		FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource("/application/Login.fxml"));
    		
			Parent root = loader.load();
			Scene scene = new Scene(root,800,600);
			primaryStage.setScene(scene);
			
			MainControl controller = loader.getController();
            controller.setMain(this);
			
			
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }
    

    
  
    /**
     * This is the main method that will start the program. It is the main entry point for the JavaFX application.
     * @param Stage
     */
	
	@Override
	public void start(Stage pStage) {
		primaryStage = pStage;
		primaryStage.setTitle("Cinema Booking System");
		
		initLogin();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	
// Before adding javadoc to here, change the paramater to a set file
	
	
	public static void loadSeanceDataFromFile(File file) {
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
    		System.out.println(e);
            alert.showAndWait();
    	}
    }
    
    public static void  loadFilmDataFromFile(File file) {
    	try {
    		
    		JAXBContext context = JAXBContext.newInstance(FilmListWrapper.class);
    		Unmarshaller unmarshalleur = context.createUnmarshaller();
    		
    		FilmListWrapper wrap = (FilmListWrapper) unmarshalleur.unmarshal(file);
    		
    		filmData.clear();
    		filmData.addAll(wrap.getFilms());
    		
    	} catch (Exception e) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setContentText("Could not load: " + file.getPath());
    		System.out.println(e);
            alert.showAndWait();
    	}
    }
    
    public static void saveFilmDataToFile(File file) {
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
    		System.out.println(e);
            alert.showAndWait();
    	}
    }
    
  
    
    public static void loadClientDataFromFile(File file) {
    	try {
    		
    		JAXBContext context = JAXBContext.newInstance(ClientWrapper.class);
    		Unmarshaller unmarshalleur = context.createUnmarshaller();
    		
    		ClientWrapper wrap = (ClientWrapper) unmarshalleur.unmarshal(file);
    		
    		clientData.clear();
    		clientData.addAll(wrap.getClients());
    		
    	} catch (Exception e) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setContentText("Could not load: " + file.getPath());
    		System.out.println(e);
            alert.showAndWait();
    	}
    }
    
    
    
    
    
    
    public static void saveClientDataToFile(File file) {
    	try {
    		
    		JAXBContext context = JAXBContext.newInstance(ClientWrapper.class);
    		Marshaller marshalleur = context.createMarshaller();
    		marshalleur.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    		
    		ClientWrapper wrap = new ClientWrapper();
    		wrap.setClients(clientData);
    		
    		//Save XML to the file
    		marshalleur.marshal(wrap, file);
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setContentText("Could not save data in file: " + file.getPath());
    		System.out.println(e);
            alert.showAndWait();
    	}
    }
    
    
    
    
    
    public static void loadEmployeeDataFromFile(File file) {
    	try {
    		
    		JAXBContext context = JAXBContext.newInstance(EmployeeWrapper.class);
    		Unmarshaller unmarshalleur = context.createUnmarshaller();
    		
    		EmployeeWrapper wrap = (EmployeeWrapper) unmarshalleur.unmarshal(file);
    		
    		employeeData.clear();
    		employeeData.addAll(wrap.getEmployees());
    		
    	} catch (Exception e) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setContentText("Could not load: " + file.getPath());
    		System.out.println(e);
            alert.showAndWait();
    	}
    }
    
    
    
    
    public static void loadBookingDataFromFile(File file) {
    	try {
    		
    		JAXBContext context = JAXBContext.newInstance(BookingWrapper.class);
    		Unmarshaller unmarshalleur = context.createUnmarshaller();
    		
    		BookingWrapper wrap = (BookingWrapper) unmarshalleur.unmarshal(file);
    		
    		bookingData.clear();
    		bookingData.addAll(wrap.getBookings());
    		
    	} catch (Exception e) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setContentText("Could not load: " + file.getPath());
    		System.out.println(e);
            alert.showAndWait();
    	}
    }
    
    
    
    
    
    
    public static void saveBookingDataToFile(File file) {
    	try {
    		
    		JAXBContext context = JAXBContext.newInstance(BookingWrapper.class);
    		Marshaller marshalleur = context.createMarshaller();
    		marshalleur.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    		
    		BookingWrapper wrap = new BookingWrapper();
    		wrap.setBookings(bookingData);
    		
    		//Save XML to the file
    		marshalleur.marshal(wrap, file);
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setContentText("Could not save data in file: " + file.getPath());
    		System.out.println(e);

            alert.showAndWait();
    	}
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
