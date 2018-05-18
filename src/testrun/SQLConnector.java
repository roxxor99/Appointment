package testrun;

/**
 *
 * @author Jed Gunderson
 */
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;

public class SQLConnector {
    //private ResultSet rs;
    private static Connection con = null;

    //This is the DataBase Connection info
    private static void queryDatabase() throws SQLException {
        String host = "jdbc:mysql://52.206.157.109/U04E2d";
        String username = "U04E2d";
        String password = "53688213649";
        con = DriverManager.getConnection(host, username, password);
    }

    //Execute SQL query and return info
    public static ResultSet executeQuery(String query) throws SQLException, IOException {
        ResultSet rs = null;

        try {
            queryDatabase();
            //create a statement from DB
            Statement queryStatement = con.createStatement();
            //this executes SQL query
            rs = queryStatement.executeQuery(query);
        } catch (SQLException e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.NONE);
            alert.setTitle("Database Error ");
            alert.setHeaderText("Database query fail ");
            alert.setContentText(String.format("SQLException: %s;SQLState: %s;SQLVendorError: %s;", e.getMessage(), e.getSQLState(), e.getErrorCode()));
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                System.exit(0);
            }
        }
        return rs;
    }

    public static Connection getCon() {
        return con;
    }
}
