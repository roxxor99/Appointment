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
public class SQLConnectorData extends SQLConnector {

    public static ObservableList<Appointment> databaseAppointments() throws SQLException, IOException {
        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
        String sqlQuery = "SELECT * FROM customer INNER JOIN appointment ON customer.customerid=appointment.customerId;";
        
        ResultSet queryResult = executeQuery(sqlQuery);
        while (queryResult.next()) {
            Appointment appointment = new Appointment();
            appointment.setCustomerId(queryResult.getInt("customerid"));
            appointment.setAppointmentId(queryResult.getInt("appointmentid"));
            appointment.setCustomerName(queryResult.getString("customerName"));
            appointment.setCreatedBy(queryResult.getString("createdBy"));
            appointment.setTitle(queryResult.getString("title"));
            appointment.setLocation(queryResult.getString("location"));
            appointment.setContact(queryResult.getString("contact"));
            appointment.setUrl(queryResult.getString("url"));
            appointment.setStartTime(queryResult.getString("start"));
            appointment.setEndTime(queryResult.getString("end"));
            
            //I think I need these 3 in order to save to the database in AppointmentManagerController.java saveAction() method
            appointment.setCreateDate(queryResult.getString("createDate"));
            appointment.setCreateDate(queryResult.getString("lastUpdate"));
            appointment.setCreateDate(queryResult.getString("lastUpdateBy"));
            
            appointmentList.add(appointment);
        }

        return appointmentList;
    }
 
    public static ObservableList<Customer> databaseCustomer() throws SQLException, IOException {
        ObservableList<Customer> customerList = FXCollections.observableArrayList();
        String sqlQuery = "SELECT * FROM customer INNER JOIN address ON customer.addressId=address.addressId INNER JOIN city ON address.cityId=city.cityId INNER JOIN country ON city.countryId=country.countryId;";
        ResultSet queryResult = executeQuery(sqlQuery);
        while (queryResult.next()) {
            Customer customer = new Customer();
            customer.setCustomerId(queryResult.getInt("customerid"));
            customer.setCustomerName(queryResult.getString("customerName"));
            customer.setAddressId(queryResult.getInt("addressId"));
            customer.setAddress(queryResult.getString("address"));
//            customer.setAddress2(queryResult.getString("address2"));
            customer.setCountryId(queryResult.getInt("countryId"));
            customer.setCountry(queryResult.getString("country"));
            customer.setCityId(queryResult.getInt("cityId"));
            customer.setCity(queryResult.getString("city"));
            customer.setPostalCode(queryResult.getString("postalCode"));
            customer.setPhone(queryResult.getString("phone"));

            customerList.add(customer);
        }

        return customerList;
    }
}
