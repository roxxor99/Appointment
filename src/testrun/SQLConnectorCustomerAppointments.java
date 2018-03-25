package testrun;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Jed Gunderson
 */
public class SQLConnectorCustomerAppointments extends SQLConnector {

    public static ArrayList<Customer> databaseAppointments() throws SQLException, IOException {
        ArrayList<Customer> customerList = new ArrayList<Customer>();
        String sqlQuery = "SELECT * FROM customer INNER JOIN appointment ON customer.customerId=appointment.customerId;";
        
       ResultSet queryResult = executeQuery(sqlQuery);
        while (queryResult.next()) {
        }
        
        return customerList;
    }
}