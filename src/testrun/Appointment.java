package testrun;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Locale;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Jed Gunderson
 */
public class Appointment {
    private StringProperty customerName = new SimpleStringProperty();
    private IntegerProperty customerId = new SimpleIntegerProperty();
    private IntegerProperty appointmentId  = new SimpleIntegerProperty();
    private StringProperty title = new SimpleStringProperty();
    private StringProperty contact = new SimpleStringProperty();
    private StringProperty description = new SimpleStringProperty();
    private StringProperty location = new SimpleStringProperty();
    private StringProperty url = new SimpleStringProperty();
    private Timestamp startTime;
    private Timestamp endTime;
    private Date startDate;
    private Date endDate;
    private StringProperty dateTxt = new SimpleStringProperty();
    private StringProperty startTxt = new SimpleStringProperty();
    private StringProperty endTxt = new SimpleStringProperty();
//    private LocalDateTime start = new LocalDateTime();
//    private LocalDateTime end = new LocalDateTime();
//    private Date date = new Date();
    
    //Constructor
    public Appointment() {
        
    }
    
    //Getters
    public IntegerProperty getAppointmentId() {
        return this.appointmentId;
        // appointmentId = String query = "SELECT * FROM appointment WHERE appointmentId =" + appointmentId;
    }
    public IntegerProperty getCustomerId() {
        return this.customerId;
    }

    public StringProperty getTitle() {
        return this.title;
        //title = String query = "SELECT * FROM appointment WHERE title ="title;
    }

    public StringProperty getContact() {
        return this.contact;
        //contact = String query = "SELECT * FROM appointment WHERE contact =" + contact;
    }

    public StringProperty getDescription() {
        return this.description;
    }
    
    public StringProperty getLocation() {
        return this.location;
    }
        
    public StringProperty getCustomerName() {
        return this.customerName;
    }
    
    public StringProperty getUrl() {
        return this.url;
    }
    
    public Timestamp getStartTime() {
        return this.startTime;
    }
    
    public Timestamp getEndTime() {
        return this.endTime;
    }
    
    public Date getStartDate() {
        return this.startDate;
    }
    
    public Date getEndDate() {
        return this.endDate;
    }
    
    public String getStartTxt() {
        return this.startTxt.get();
    }
    public StringProperty startTxtValue(){
        return this.startTxt;
    }
    
    public StringProperty getEndTxt() {
        return this.endTxt;
    }
    
    public StringProperty getDateTxt() {
        return this.dateTxt;
    }
    
        
    //Setters
    public void setAppointmentId(int appointmentId) {
        this.appointmentId.set(appointmentId);
    }
    
    public void setCustomerId(int customerId) {
        this.customerId.set(customerId);
    }
    
    public void setTitle(String title) {
        this.title.set(title);
    }
    
    public void setContact(String contact) {
        this.contact.set(contact);
    }
    
    public void setDescription(String description) {
        this.description.set(description);
    }
    
    public void setLocation(String location) {
        this.location.set(location);
    }
    
    public void setCustomerName(String customerName) {
        this.customerName.set(customerName);
    }
    
    public void setUrl(String url) {
        this.url.set(url);
    }
    
    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
//    public void setStart(LocalDateTime start) {
//    }
//    
//    public void setEnd(LocalDateTime start) {
//    }
    
}
