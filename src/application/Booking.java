package application;

import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;

import java.text.SimpleDateFormat;  

import javafx.scene.control.Button;

/**
 * Model class for a Person.
 *
 * @author Marco Jakob
 */
public class Booking {

    private final StringProperty UserName;
    private final StringProperty Title;
    private final ObjectProperty<LocalDate> ScreeningDate;
    private final IntegerProperty ScreeningTime;
    private final ObjectProperty<LocalDate> BookingDate;
    private final StringProperty Seat;

    
    		
    
 
    
    
    
    public Booking() {
        this(null, null, null,0, null, null);
    }
    

    public Booking(String UserName, String Title, LocalDate ScreeningDate , int ScreeningTime, LocalDate BookingDate, String Seat) {
        this.UserName = new SimpleStringProperty(UserName);
        this.Title = new SimpleStringProperty(Title);
        this.ScreeningDate = new SimpleObjectProperty<LocalDate>(ScreeningDate);
        this.ScreeningTime = new SimpleIntegerProperty(ScreeningTime);
        this.BookingDate = new SimpleObjectProperty<LocalDate>(BookingDate);
        this.Seat = new SimpleStringProperty(Seat);
    
        
        
    }
    
    
   
  
    

    public String getUserName() {
        return UserName.get();
    }

    public void setUserName(String UserName) {
        this.UserName.set(UserName);
    }
    
    public StringProperty UserNameProperty() {
        return UserName;
    }
    
    
    
    
    public String getTitle() {
        return Title.get();
    }

    public void setTitle(String Title) {
        this.Title.set(Title);
    }
    
    public StringProperty TitleProperty() {
        return Title;
    }
    
    
    
    
    
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
	public LocalDate getScreeningDate(){
		return ScreeningDate.get();
	}
	
	public void setScreeningDate(LocalDate screeningdate) {
        this.ScreeningDate.set(screeningdate);
    }

    public ObjectProperty<LocalDate> screeningDateProperty() {
        return ScreeningDate;
    }
    


    
    
    
    public int getScreeningTime() {
        return ScreeningTime.get();
    }

    public void setScreeningTime(int time) {
        this.ScreeningTime.set(time);
    }

    public IntegerProperty ScreeningTimeProperty() {
        return ScreeningTime;
    }
    
    
    
    
    
    
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    
   	public LocalDate getBookingDate(){
   		return BookingDate.get();
   	}
   	
   	public void setBookingDate(LocalDate bookingdate) {
           this.BookingDate.set(bookingdate);
       }

       public ObjectProperty<LocalDate> bookingDateProperty() {
           return BookingDate;
       }
    
    
    
    
    
    public String getSeat() {
        return Seat.get();
    }

    public void setSeat(String Seat) {
        this.Seat.set(Seat);
    }
    
    public StringProperty SeatProperty() {
        return Seat;
    }
    

    

}