package testrun;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import static testrun.App.writeToLog;
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

//            if (user == null) {
//                throw new InvalidLoginException("Incorrect username or password.");
//            }
//            
//            this.mainApp.setUser(user);
//        } catch (InvalidLoginException | AssertionError e) {
//            MessageBox.setContentText(e.getMessage());
//        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Locale will be set on startup
        //Uncomment below to test/verify locale is set to German.
        //Locale.setDefault(new Locale("de", "DE"));
        this.currentLocale = Locale.getDefault();
        this.rb = ResourceBundle.getBundle("MessagesBundle", this.currentLocale);
        butLogin.setText(this.rb.getString("Title"));
        lblUsername.setText(this.rb.getString("UsernameLabel"));
        lblPassword.setText(this.rb.getString("PasswordLabel"));
        lblTitle.setText(this.rb.getString("Title"));

//        try {
//            SQLConnector.main(new String[1]);
//        } catch (SQLException ex) {
//            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

}
