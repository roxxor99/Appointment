package testrun;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Jed Gunderson
 */
public class SQLConnectorCustomerAppointments extends SQLConnector {

    public static ObservableList<Appointment> databaseAppointments() throws SQLException, IOException {
        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
        String sqlQuery = "SELECT * FROM customer INNER JOIN appointment ON customer.customerid=appointment.customerId;";
        
       ResultSet queryResult = executeQuery(sqlQuery);
       while (queryResult.next()) {
        Appointment appointment = new Appointment();
        appointment.setCustomerId(queryResult.getInt("customerid"));
        appointment.setAppointmentId(queryResult.getInt("appointmentid"));
        appointment.setCustomerName(queryResult.getString("customerName"));
        appointment.setDescription(queryResult.getString("description"));
        appointment.setTitle(queryResult.getString("title"));
        appointment.setLocation(queryResult.getString("location"));
        appointment.setContact(queryResult.getString("contact"));
        appointment.setUrl(queryResult.getString("url"));
        appointment.setStartTime(queryResult.getTimestamp("start"));
        appointment.setEndTime(queryResult.getTimestamp("end"));
        appointmentList.add(appointment);
        }
        
        return appointmentList;
    }
}