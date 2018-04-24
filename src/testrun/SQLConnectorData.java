package testrun;

import java.io.IOException;
import java.sql.PreparedStatement;
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
        String sqlQuery = "SELECT * FROM appointment INNER JOIN customer ON appointment.customerid=customer.customerId;";
        
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
    
    
    
//public int addCustomer(Customer customer) {
//        String addCustomerQuery = String.join(" ",
//            "INSERT INTO customer (customerId, customerName, addressId, active, createDate, createdBy, lastUpdate, lastUpdateBy)",
//            "VALUES (?, ?, ?, 1, NOW(), ?, NOW(), ?)"
//        );
//                
//        int customerId = getMaxId();
//        try {
//            PreparedStatement stmt = conn.prepareStatement(addCustomerQuery);
//            stmt.setInt(1, customerId);
//            stmt.setString(2, customer.getCustomerName());
//            stmt.setInt(3, customer.getAddress().getAddressId());
//            stmt.setString(4, SchedulingApp.user.username);
//            stmt.setString(5, SchedulingApp.user.username);
//            stmt.executeUpdate();
//        } catch(SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        
//        return customerId;
//    }
//
//
//    public int addCity(City city) {
//        String addCityQuery = String.join(" ",
//            "INSERT INTO city (cityId, city, countryId, createDate, createdBy, lastUpdate, lastUpdateBy)",
//            "VALUES (?, ?, ?, NOW(), ?, NOW(), ?)"
//        );
//    
//        int cityId = getMaxId();
//        try {
//            PreparedStatement stmt = conn.prepareStatement(addCityQuery);
//            stmt.setInt(1, cityId);
//            stmt.setString(2, city.getCityName());
//            stmt.setInt(3, city.getCountry().getCountryId());
//            stmt.setString(4, SchedulingApp.user.username);
//            stmt.setString(5, SchedulingApp.user.username);
//            stmt.executeUpdate();
//        } catch(SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        
//        return cityId;
//    }
//    
//    public int addCountry(Country country) {
//        String addCountryQuery = String.join(" ",
//            "INSERT INTO country (countryId, country, createDate, createdBy, lastUpdate, lastUpdateBy)",
//            "VALUES (?, ?, NOW(), ?, NOW(), ?)"
//        );
//    
//        int countryId = getMaxId();
//        try {
//            PreparedStatement stmt = conn.prepareStatement(addCountryQuery);
//            stmt.setInt(1, countryId);
//            stmt.setString(2, country.getCountryName());
//            stmt.setString(3, SchedulingApp.user.username);
//            stmt.setString(4, SchedulingApp.user.username);
//            stmt.executeUpdate();
//        } catch(SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        
//        return countryId;
//    }
//    
//    
//    public int addAddress(Address address) {
//        String addAddressQuery = String.join(" ", 
//            "INSERT INTO address (addressId,  address, address2, cityId, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdateBy)",
//            "VALUES (?, ?, ?, ?, ?, ?, NOW(), ?, NOW(), ?)"
//        );
//    
//        int addressId = getMaxId();
//        try {
//            PreparedStatement stmt = conn.prepareStatement(addAddressQuery);
//            stmt.setInt(1, addressId);
//            stmt.setString(2, address.getAddress1());
//            stmt.setString(3, address.getAddress2());
//            stmt.setInt(4, address.getCity().getCityId());
//            stmt.setString(5, address.getPostalCode());
//            stmt.setString(6, address.getPhone());
//            stmt.setString(7, SchedulingApp.user.username);
//            stmt.setString(8, SchedulingApp.user.username);
//            
//            stmt.executeUpdate();
//        } catch(SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        
//        return addressId;
//    }
    
}