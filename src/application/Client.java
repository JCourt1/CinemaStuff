package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model class for a Person.
 *
 * @author Marco Jakob
 */
public class Client {

    private final StringProperty UserName;
    private final StringProperty Password;
    private final StringProperty FirstName;
    private final StringProperty LastName;
    private final StringProperty Email;
 
    
    
    
    public Client() {
        this(null, null, null, null, null);
    }
    

    public Client(String UserName, String Password, String FirstName, String LastName, String Email) {
        this.UserName = new SimpleStringProperty(UserName);
        this.Password = new SimpleStringProperty(Password);
        this.FirstName = new SimpleStringProperty(FirstName);
        this.LastName = new SimpleStringProperty(LastName);
        this.Email = new SimpleStringProperty(Email);
        
        
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
        this.LastName.set(LastName);
    }
    
    public StringProperty LastNameProperty() {
        return LastName;
    }
    
    
    
    
    
    public String getEmail() {
        return Email.get();
    }

    public void setEmail(String Email) {
        this.Email.set(Email);
    }
    
    public StringProperty EmailProperty() {
        return Email;
    }
    

    

}