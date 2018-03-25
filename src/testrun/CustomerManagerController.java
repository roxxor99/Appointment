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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jed Gunderson
 */
public class CustomerManagerController implements Initializable {

    @FXML
    private Label lblCustomerManager;
    @FXML
    private Label lblManageCustomer;
    @FXML
    private TextField txtAddress2;
    @FXML
    private TextField txtZip;
    @FXML
    private Button butSave;
    @FXML
    private TextField txtAddress1;
    @FXML
    private ComboBox<?> comboCountry;
    @FXML
    private TextField txtPhone;
    @FXML
    private ComboBox<?> comboCity;
    @FXML
    private TextField txtCustomerName;
    @FXML
    private Label lblCurrentCustomers;
    @FXML
    private Button butDelete;
    @FXML
    private Button butBack;
    @FXML
    private Button butModify;
    @FXML
    private TableView<?> tableCurrentSchedule;
    @FXML
    private TableColumn<?, ?> columnAddressCurrentCustomers;
    @FXML
    private TableColumn<?, ?> columnDescriptionCurrentSchedule;
    @FXML
    private TableColumn<?, ?> columnAddress2CurrentCustomers;
    @FXML
    private TableColumn<?, ?> columnCountryCurrentCustomers;
    @FXML
    private TableColumn<?, ?> columnCityCurrentCustomers;
    @FXML
    private TableColumn<?, ?> columnZipCurrentCustomers;
    @FXML
    private TableColumn<?, ?> columnPhoneCurrentCustomers;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void saveAction(ActionEvent event) {
    }

    @FXML
    private void countryAction(ActionEvent event) {
    }

    @FXML
    private void cityAction(ActionEvent event) {
    }

    @FXML
    private void deleteAction(ActionEvent event) {
    }

    @FXML
    private void backAction(ActionEvent event) throws IOException {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirm Back");
        alert.setContentText("Are you sure you would like to go back? Any un-saved work will be lost.");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            Stage stage;
            Parent root;
            stage = (Stage) lblCustomerManager.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("MainLanding.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    private void modifyAction(ActionEvent event) {
    }
    
}
