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
public class Appointment {

    private StringProperty customerName = new SimpleStringProperty();
    private IntegerProperty customerId = new SimpleIntegerProperty();
    private IntegerProperty appointmentId = new SimpleIntegerProperty();
    private StringProperty title = new SimpleStringProperty();
    private StringProperty contact = new SimpleStringProperty();
    private StringProperty description = new SimpleStringProperty();
    private StringProperty location = new SimpleStringProperty();
    private StringProperty url = new SimpleStringProperty();
    private StringProperty startTime = new SimpleStringProperty();
    private StringProperty endTime = new SimpleStringProperty();
    private StringProperty createdBy = new SimpleStringProperty();
    
    //I think I need these 3 in order to save to the database in AppointmentManagerController.java saveAction() method
    private StringProperty createDate = new SimpleStringProperty();
    private StringProperty lastUpdate = new SimpleStringProperty();
    private StringProperty lastUpdateBy = new SimpleStringProperty();
    
    private Date startDate;
    private Date endDate;
//    private StringProperty dateTxt = new SimpleStringProperty();
//    private StringProperty startTxt = new SimpleStringProperty();
//    private StringProperty endTxt = new SimpleStringProperty();

    public Appointment() {
    }

    //Getters
    public IntegerProperty getAppointmentId() {
        return this.appointmentId;
    }

    public IntegerProperty getCustomerId() {
        return this.customerId;
    }

    public StringProperty getTitle() {
        return this.title;

    }

    public StringProperty getContact() {
        return this.contact;
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

    public StringProperty getStartTime() {
        return this.startTime;
    }

    public StringProperty getEndTime() {
        return this.endTime;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }
    
    public StringProperty getCreatedBy() {
        return this.createdBy;

    }
    
    public StringProperty createDate() {
        return this.createDate;

    }
    
    public StringProperty lastUpdate() {
        return this.lastUpdate;

    }
    
    public StringProperty lastUpdateBy() {
        return this.lastUpdateBy;

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

    public void setStartTime(String startTime) {
        this.startTime.set(startTime);
    }

    public void setEndTime(String endTime) {
        this.endTime.set(endTime);
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy.set(createdBy);
    }
    
    public void setCreateDate(String createDate) {
        this.createDate.set(createDate);
    }
    
    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate.set(lastUpdate);
    }
    
    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy.set(lastUpdateBy);
    }
    
}
