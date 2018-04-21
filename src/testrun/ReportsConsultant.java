
package testrun;

import java.util.Date;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Jed Gunderson
 */
public class ReportsConsultant {
    private StringProperty customerName = new SimpleStringProperty();
    private StringProperty title = new SimpleStringProperty();
    private StringProperty location = new SimpleStringProperty();
    private StringProperty start = new SimpleStringProperty();
    private StringProperty end = new SimpleStringProperty();
    private StringProperty createdBy = new SimpleStringProperty();
    private IntegerProperty customerId = new SimpleIntegerProperty();
    private IntegerProperty appointmentId = new SimpleIntegerProperty();

//    private final StringProperty customerName;
//    private final StringProperty location;
//    private final StringProperty title;
//    private final StringProperty date;
//    private final StringProperty start;
//    private final StringProperty end;

    ReportsConsultant(String string, String string0, String string1, String string2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    ReportsConsultant(String string, String string0, String string1, String string2, String string3) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

//    public ReportsConsultant(String customerName, String title, String date, String start, String end, String location) {
//        this.customerName = new SimpleStringProperty(customerName);
//        this.location = new SimpleStringProperty(location);
//        this.title = new SimpleStringProperty(title);
//        this.date = new SimpleStringProperty(date);
//        this.start = new SimpleStringProperty(start);
//        this.end = new SimpleStringProperty(end);
//  }
    
    //Getters
    public StringProperty getCustomerName() {
        return this.customerName;
    }
    
     public StringProperty getLocation() {
        return this.location;
    }
    
    public StringProperty getTitle() {
        return this.title;
    }

    public StringProperty getStart() {
        return this.start;
    }

    public StringProperty getEnd() {
        return this.end;
    }
    public StringProperty getCreatedBy() {
        return this.createdBy;
    }

    //Setters

    public void setCustomerName(String customerName) {
        this.customerName.set(customerName);
    }
    
    public void setLocation(String location) {
        this.location.set(location);
    }
    
    public void setTitle(String title) {
        this.title.set(title);
    }

    public void setStartTime(String startTime) {
        this.start.set(startTime);
    }

    public void setEndTime(String endTime) {
        this.end.set(endTime);
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy.set(createdBy);
    }
    
}
