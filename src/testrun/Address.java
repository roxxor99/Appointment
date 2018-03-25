package testrun;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Jed Gunderson
 */
public class Address {
   //public City city;
//   private StringProperty city = new SimpleStringProperty();
   private IntegerProperty addressId = new SimpleIntegerProperty();
   private StringProperty address = new SimpleStringProperty();
   private StringProperty address2 = new SimpleStringProperty();
   private StringProperty postalCode = new SimpleStringProperty();
   private StringProperty phone = new SimpleStringProperty();

    /**
     * Constructor
     */
    public Address() {
        }
    
    //Getters
    public IntegerProperty getAddressId() {
        return this.addressId;
    }
    
    public StringProperty getAddress() {
        return this.address;
    }
    
    public StringProperty getAddress2() {
        return this.address2;
    }
    
//    public City getCity() {
//        return this.city;
//    }
    
    public StringProperty getPostalCode() {
        return this.postalCode;
    }
    
    public StringProperty getPhone() {
        return this.phone;
    }
    
    //Setters
//     public void setCity (City city) {
//        this.city.set(city);
//    }
     
    public void setAddressId (int addressId) {
        this.addressId.set(addressId);
    }
    
    public void setAddress(String address) {
        this.address.set(address);
    }
    
    public void setAddress2(String address2) {
        this.address2.set(address2);
    }
    
    public void setPostalCode (String postalCode) {
        this.postalCode.set(postalCode);
    }
    
    public void setPhone (String phone) {
        this.phone.set(phone);
    }
}