package testrun;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Jed Gunderson
 */
public class SQLConnectorData extends SQLConnector {

    public static ObservableList<Appointment> databaseAppointments() throws SQLException, IOException {
        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
        String sqlQuery = "SELECT * FROM appointment "
                + "INNER JOIN customer ON appointment.customerid=customer.customerId;";

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
            appointment.setCreateDate(queryResult.getString("createDate"));
            appointment.setCreateDate(queryResult.getString("lastUpdate"));
            appointment.setCreateDate(queryResult.getString("lastUpdateBy"));

            appointmentList.add(appointment);
        }
        return appointmentList;
    }

    public static ObservableList<Customer> databaseCustomer() throws SQLException, IOException {
        ObservableList<Customer> customerList = FXCollections.observableArrayList();
        String sqlQuery = "SELECT * FROM customer "
                + "INNER JOIN address ON customer.addressId=address.addressId "
                + "INNER JOIN city ON address.cityId=city.cityId "
                + "INNER JOIN country ON city.countryId=country.countryId;";
        
        ResultSet queryResult = executeQuery(sqlQuery);
        while (queryResult.next()) {
            Customer customer = new Customer();
            customer.setCustomerId(queryResult.getString("customerid"));
            customer.setCustomerName(queryResult.getString("customerName"));
            System.out.println(queryResult.getString("customerName"));
            
            customer.setAddressId(queryResult.getString("addressId"));
            System.out.println(queryResult.getString("addressId"));
            customer.setAddress(queryResult.getString("address"));
            customer.setCountryId(queryResult.getString("countryId"));
            customer.setCountry(queryResult.getString("country"));
            customer.setCityId(queryResult.getString("cityId"));
            customer.setCity(queryResult.getString("city"));
            customer.setPostalCode(queryResult.getString("postalCode"));
            customer.setPhone(queryResult.getString("phone"));

            customerList.add(customer);
        }
        return customerList;
    }
}
