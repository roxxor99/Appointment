package testrun;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Jed Gunderson
 */
public class Country {

//    public int countryId;
//    public String country;
    private IntegerProperty countryId = new SimpleIntegerProperty();
    private StringProperty country = new SimpleStringProperty();
    
//Constructor
    public Country() {
    }

    //Getters
    public IntegerProperty getCountryId() {
        return this.countryId;
    }
    
    public StringProperty getCountry(){
        return this.country;
    }
    
    //Setters
    public void setCountryId(int countryId) {
        this.countryId.set(countryId);
    }
    
    public void setCountry(String country) {
        this.country.set(country);
    }

}
