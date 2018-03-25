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
    public Customer customer;
    
    private IntegerProperty appointmentId;
    private StringProperty title;
    private StringProperty contact;
    private StringProperty description;
    private StringProperty location;
    private StringProperty url;
    private Timestamp startTime;
    private Timestamp endTime;
    private Date startDate;
    private Date endDate;
    private StringProperty dateTxt;
    private StringProperty startTxt;
    private StringProperty endTxt;
//    private LocalDateTime start = new LocalDateTime();
//    private LocalDateTime end = new LocalDateTime();
//    private Date date = new Date();
    
    //something similar to this?.. querry db => SELECT * FROM appointment
//    public static ObservableList <Appointment> allAppointments = FXCollections.observableArrayList();
   
    
    //Constructor
    public Appointment(int appointmentId, String title, String contact, String description, String location,
                       String url, LocalDateTime start, LocalDateTime end) {
        this.appointmentId = new SimpleIntegerProperty(appointmentId);
        this.title = new SimpleStringProperty(title);
        this.contact = new SimpleStringProperty(contact);
        this.description = new SimpleStringProperty(description);
        this.location = new SimpleStringProperty(location);
        this.url = new SimpleStringProperty(url);
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        this.dateTxt = new SimpleStringProperty(dateFormat.format(startDate));
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a z");
        this.startTxt = new SimpleStringProperty(timeFormat.format(startDate));
        this.endTxt = new SimpleStringProperty(timeFormat.format(endDate));
    }
    
    //Getters
    public IntegerProperty getAppointmentId() {
        return this.appointmentId;
        // appointmentId = String query = "SELECT * FROM appointment WHERE appointmentId =" + appointmentId;
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
        
    public Customer getCustomer() {
        return this.customer;
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
    
    public void setCustomer(Customer customer) {
        this.customer = customer;
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
