package testrun;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Jed Gunderson
 */
public class User {
//    public int isActive;
//    public String password;
//    public int userId;
//    public String username;
    
    private IntegerProperty isActive = new SimpleIntegerProperty();
    private IntegerProperty userId = new SimpleIntegerProperty();
    private StringProperty password = new SimpleStringProperty();
    private StringProperty username = new SimpleStringProperty();
    
    //Constructor
    public User(){
        
    }
    
    //Getters
    public IntegerProperty getIsActive() {
        return this.isActive;
    }
    
    public IntegerProperty getUserId() {
        return this.userId;
    }
    
    public StringProperty getUsername() {
        return this.username;
    }
    
    public StringProperty getPassword() {
        return this.password;
    }
    
    //Setters
    public void setIsActive (int isActive) {
        this.isActive.set(isActive);
    }
    
    public void setUserId(int userId) {
        this.userId.set(userId);
    }
    
    public void setUsername(String username) {
        this.username.set(username);
    }
    
    public void setPassword(String password) {
        this.password.set(password);
    }
    
    
    
}
