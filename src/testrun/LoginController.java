package testrun;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import static java.time.LocalDateTime.now;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.util.Locale;
import java.util.Optional;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import static testrun.App.writeToLog;
import static testrun.SQLConnector.executeQuery;
//import testrun.App.writeToLog(String);

/**
 * @author Jed Gunderson
 */
public class LoginController implements Initializable {

    Locale currentLocale;
    ResourceBundle rb;
    @FXML
    private Label lblTitle;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button butLogin;
    @FXML
    private Label lblUsername;
    @FXML
    private Label lblPassword;
    private static String loggedInUser;

    //Get login info
    //Report login status with the writeToLog method from App
    @FXML
    private void loginAction(ActionEvent event) throws IOException, SQLException {
        // Capturing the data in the username and password fields
        //used to verify user in database
        String loginUsername = txtUsername.getText();
        String loginPassword = txtPassword.getText();

        Boolean loginSuccess = SQLConnectorLogin.loginUser(loginUsername, loginPassword);
        if (loginSuccess) {
            writeToLog("Username '" + this.txtUsername.getText() + "' was successfully logged in");
            loggedInUser = this.txtUsername.getText();
            Stage stage;
            Parent root;
            stage = (Stage) butLogin.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("MainLanding.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            writeToLog("Invalid credentials for the username '" + this.txtUsername.getText() + "'");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(this.rb.getString("LoginFailure"));
            alert.setHeaderText(this.rb.getString("LoginFailure"));
            alert.setContentText(this.rb.getString("LoginMessage"));
//        Optional<ButtonType> result = alert.showAndWait();
            alert.showAndWait();

        }

    }

////Plan was to clear the MainLanding table and then repopulate it with the appropriate appointment info. *incomplete....
////check if there is an appointment within 15 min
//    private void appointmentUpcomingAlert() throws SQLException, IOException {
//        LocalDateTime rightNow = now();
//        LocalDateTime nowPlusFifteen = now().plusMinutes(15);
//
//        //From appointment inner join customer.customerId / appointment.customerId
//        String appointmentAlert = ("SELECT title, description, contact, url, start, end, customerName  "
//                + "FROM appointment a "
//                + "INNER JOIN customer c ON c.customerId = a.customerId "
//                + "WHERE start BETWEEN ? AND ? "
//                + "AND a.createdBy = ? "
//                + "ORDER BY a.start ASC");
//        PreparedStatement statement = SQLConnector.getCon().prepareStatement(appointmentAlert);
//        
//        //convert to UTC
//        Timestamp tsNow = AppointmentManagerController.dateFormatter(rightNow.toString());
//        Timestamp tsFifteen = AppointmentManagerController.dateFormatter(nowPlusFifteen.toString());
//
//        statement.setTimestamp(1, tsNow);
//        statement.setTimestamp(2, tsFifteen);
//        statement.setString(3, loggedInUser);
//
//        ResultSet rs = executeQuery(appointmentAlert);
//        if (rs.isBeforeFirst()) {
//            String aptAlert = "";
//            aptAlert += ("You have appointments that are about to begin");
//            while (rs.next()) {
//                aptAlert += ("\n\n");
//                ZonedDateTime zoneStart = AppointmentManagerController.utcToLocal(rs.getTimestamp("start"));
//                ZonedDateTime zoneEnd = AppointmentManagerController.utcToLocal(rs.getTimestamp("end"));
//
//                //Information to supply the alert Type- Time- Customer
//                aptAlert += (rs.getString("title") + "\n");
//                aptAlert += ("From " + zoneStart.toLocalTime().toString() + " to " + zoneEnd.toLocalTime().toString() + "\n");
//                aptAlert += ("With " + rs.getString("customerName"));
//            }
//        }
//    }

    public static String getLoggedInUser() {
        return loggedInUser;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Uncomment below to test/verify locale is set to German.
        //Locale.setDefault(new Locale("de", "DE"));
        
        this.currentLocale = Locale.getDefault();
        this.rb = ResourceBundle.getBundle("MessagesBundle", this.currentLocale);
        butLogin.setText(this.rb.getString("Title"));
        lblUsername.setText(this.rb.getString("UsernameLabel"));
        lblPassword.setText(this.rb.getString("PasswordLabel"));
        lblTitle.setText(this.rb.getString("Title"));

    }
}
