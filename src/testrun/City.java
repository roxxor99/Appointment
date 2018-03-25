package testrun;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import testrun.Country;

/**
 *
 * @author Jed Gunderson
 */
public class City {

//    public int cityId;
//    public String city;

    public Country country;
    private IntegerProperty cityId = new SimpleIntegerProperty();
    private StringProperty city = new SimpleStringProperty();
    
    
//Constructor
    public City() {
    }

    //Getters
    public Country getCountry() {
        return this.country;
    }
    
    public IntegerProperty getCityId() {
        return this.cityId;
    }
    
    public StringProperty getCity(){
        return this.city;
    }
    
    //Setters
    public void setCountry(Country country) {
        this.country = country;
    }
//    public void setCountry(String country) {
//        this.country.set(country);
//    }
    
    public void setCityId(int cityId) {
        this.cityId.set(cityId);
    }
    
    public void setCity(String city) {
        this.city.set(city);
    }

}
