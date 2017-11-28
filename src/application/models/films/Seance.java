package application.models.films;

import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import application.util.LocalDateAdapter;

public class Seance {
	
	
	private final ObjectProperty<LocalDate> day;
	private final IntegerProperty time;
	private final StringProperty film;
	
	
	public Seance() {
        this(0, null);
    }
	
	
	public Seance(int time, String film){
		this.day = new SimpleObjectProperty<LocalDate>(LocalDate.of(2017, 12, 10));
		this.time = new SimpleIntegerProperty(time);
		this.film = new SimpleStringProperty(film);
	}
	
	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	public LocalDate getDay(){
		return day.get();
	}
	
	public void setDay(LocalDate day) {
        this.day.set(day);
    }

    public ObjectProperty<LocalDate> dayProperty() {
        return day;
    }
	
	
	
	
	
	
	public String getFilm() {
        return film.get();
    }

    public void setFilm(String film) {
        this.film.set(film);
    }
    
    public StringProperty filmProperty() {
        return film;
    }
    
    
    public int getTime() {
        return time.get();
    }

    public void setTime(int time) {
        this.time.set(time);
    }

    public IntegerProperty timeProperty() {
        return time;
    }

	
	
	

}
