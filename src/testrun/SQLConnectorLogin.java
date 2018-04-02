package testrun;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.ResultSet;


/**
 *
 * @author Jed Gunderson
 */
public class SQLConnectorLogin extends SQLConnector {

    //First query finds user and password by count
    public static Boolean loginUser(String username, String password) throws SQLException, IOException {
        Boolean userMatch = false;
        String sqlQuery = String.format("SELECT COUNT(userName) FROM user WHERE userName = '%s' AND password = '%s'", username, password);
        ResultSet queryResult = executeQuery(sqlQuery);
        //Check first obj in collection
        if (queryResult.first()) {
            //if match == 1 else != 0
            userMatch = queryResult.getInt(1) == 1;
        }
        return userMatch;
    }
}


