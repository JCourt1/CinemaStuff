package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model class for a Person.
 *
 * @author Marco Jakob
 */
public class Employee {

    private final StringProperty UserName;
    private final StringProperty Password;
    private final StringProperty FirstName;
    private final StringProperty LastName;
 
    
    
    
    public Employee() {
        this(null, null, null, null);
    }
    

    public Employee(String UserName, String Password, String FirstName, String LastName) {
        this.UserName = new SimpleStringProperty(UserName);
        this.Password = new SimpleStringProperty(Password);
        this.FirstName = new SimpleStringProperty(FirstName);
        this.LastName = new SimpleStringProperty(LastName);
        
        
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
    
    
    
    
    public String getPassword() {
        return Password.get();
    }

    public void setPassword(String Password) {
        this.Password.set(Password);
    }
    
    public StringProperty PasswordProperty() {
        return Password;
    }
    
    
    
    public String getFirstName() {
        return FirstName.get();
    }

    public void setFirstName(String FirstName) {
        this.FirstName.set(FirstName);
    }
    
    public StringProperty FirstNameProperty() {
        return FirstName;
    }
    


    
    public String getLastName() {
        return LastName.get();
    }

    public void setLastName(String LastName) {
        this.UserName.set(LastName);
    }
    
    public StringProperty LastNameProperty() {
        return LastName;
    }

    

    

}