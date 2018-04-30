package testrun;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
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
    private StringProperty createDate = new SimpleStringProperty();
    private StringProperty lastUpdate = new SimpleStringProperty();
    private StringProperty lastUpdateBy = new SimpleStringProperty();

    public Appointment() {
    }

    Appointment(String string, String string0, String string1, String string2, String string3) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        this.startTime.set(utcToLocal(startTime));
    }

    public void setEndTime(String endTime) {
        this.endTime.set(utcToLocal(endTime));
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

    public static String utcToLocal(String catDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm:ss.S");
        Timestamp ts = Timestamp.valueOf(catDate);
        ZoneId newzid = ZoneId.systemDefault();
        ZonedDateTime newzdtStart = ts.toLocalDateTime().atZone(ZoneId.of("UTC"));
        ZonedDateTime newLocalStart = newzdtStart.withZoneSameInstant(newzid);
        String testDelete = newLocalStart.format(formatter);
        return newLocalStart.format(formatter);

        
//        LocalDateTime dateTime = LocalDateTime.parse(catDate, formatter);
//        ZoneId zid = ZoneId.systemDefault();
//        ZonedDateTime zDateTime = dateTime.atZone(zid);
////        zDateTime.withZoneSameInstant(ZoneId.of("UTC"));
//        ZonedDateTime localStart = zDateTime.withZoneSameInstant(zid);
//
//        String currentTime = localStart.format(formatter);
//        return localStart.format(formatter);
    }
}
