package testrun;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Jed Gunderson
 */
public class Customer {
    private StringProperty customerId = new SimpleStringProperty();
    private StringProperty customerName = new SimpleStringProperty();
    private StringProperty isActive = new SimpleStringProperty();
    private StringProperty addressId = new SimpleStringProperty();
    private StringProperty address = new SimpleStringProperty();
    //private StringProperty address2 = new SimpleStringProperty();
    private StringProperty postalCode = new SimpleStringProperty();
    private StringProperty phone = new SimpleStringProperty();
    private StringProperty countryId = new SimpleStringProperty();
    private StringProperty country = new SimpleStringProperty();
    private StringProperty cityId = new SimpleStringProperty();
    private StringProperty city = new SimpleStringProperty();
    /**
     * Constructor
     */
    public Customer() {
    }

    public StringProperty getCustomerId() {
        return this.customerId;
    }

    public StringProperty getCustomerName() {
        return this.customerName;
    }

    public StringProperty getIsActive() {
        return this.isActive;
    }
    
    public StringProperty getAddressId() {
        return this.addressId;
    }
    
    public StringProperty getAddress() {
        return this.address;
    }
    
//    public StringProperty getAddress2() {
//        return this.address2;
//    }
  
    public StringProperty getPostalCode() {
        return this.postalCode;
    }
    
    public StringProperty getPhone() {
        return this.phone;
    }
    
    public StringProperty getCountryId() {
        return this.countryId;
    }

    public StringProperty getCountry() {
        return this.country;
    }
    
    public StringProperty getCityId() {
        return this.cityId;
    }
    
    public StringProperty getCity(){
        return this.city;
    }

    //Setters
    public void setCustomerId(String customerId) {
        this.customerId.set(customerId);
    }

    public void setCustomerName(String customerName) {
        this.customerName.set(customerName);
    }

    public void setIsActive(String isActive) {
        this.isActive.set(isActive);
    }

     public void setAddressId (String addressId) {
        this.addressId.set(addressId);
    }
    
    public void setAddress(String address) {
        this.address.set(address);
    }
    
//    public void setAddress2(String address2) {
//        this.address2.set(address2);
//    }
    
    public void setPostalCode (String postalCode) {
        this.postalCode.set(postalCode);
    }
    
    public void setPhone (String phone) {
        this.phone.set(phone);
    }
    
    public void setCountryId(String countryId) {
        this.countryId.set(countryId);
    }

    public void setCountry(String country) {
        this.country.set(country);
    }
    
    public void setCityId(String cityId) {
        this.cityId.set(cityId);
    }
    
    public void setCity(String city) {
        this.city.set(city);
    }
}
