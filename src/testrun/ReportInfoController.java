
package testrun;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jed Gunderson
 */
public class ReportInfoController implements Initializable {

    @FXML
    private Label lblReports;
    @FXML
    private Button butAptType;
    @FXML
    private Button butAptLocation;
    @FXML
    private Button butConsult;
    @FXML
    private Button butBack;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void reportByTypeAction(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) butAptType.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("ReportAptType.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void reportByLocationAction(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) butAptLocation.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("ReportLocation.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void reportConsultantAction(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) butConsult.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("ReportConsultant.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void backAction(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirm Back Navigation");
        alert.setContentText("Are you sure you would like to return?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            Stage stage;
            Parent root;
            stage = (Stage) butBack.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("MainLanding.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
    
}
