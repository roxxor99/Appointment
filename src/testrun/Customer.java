package testrun;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Jed Gunderson
 */
public class Customer {
    
//    public int customerId;
//    public String customerName;
//    public boolean isActive;

    public Address address;
    private IntegerProperty customerId = new SimpleIntegerProperty();
    private StringProperty customerName = new SimpleStringProperty();
    private IntegerProperty isActive = new SimpleIntegerProperty();
    
    /**
     * Constructor
     */
    public Customer() {
    }

    public Address getAddress() {
        return this.address;
    }
    
    public IntegerProperty getCustomerId() {
        return this.customerId;
    }
    
    public StringProperty getCustomerName() {
        return this.customerName;
    }

    public IntegerProperty getIsActive() {
        return this.isActive;
    }
    
 
    //Setters
    public void setAddress(Address address) {
        this.address = address;
    }
    
    public void setCustomerId(int customerId) {
        this.customerId.set(customerId);
    }
    

    public void setCustomerName(String customerName) {
        this.customerName.set(customerName);
    }

    
    public void setIsActive (int isActive) {
        this.isActive.set(isActive);
    }
    
    
//    @Override
//    public String toString() {
//        return getCustomerName();
//    }
    
    
    
}
