package application.models.films;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model class for a Person.
 *
 * @author Marco Jakob
 */
public class Film {

    private final StringProperty name;
    

    /**
     * 
     * 
     * @param name
     */
    public Film(String name) {
        this.name = new SimpleStringProperty(name);
        
        
        
        
        
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }
    
    public StringProperty nameProperty() {
        return name;
    }

}