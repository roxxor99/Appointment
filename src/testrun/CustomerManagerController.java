package testrun;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
import static testrun.SQLConnector.executeQuery;

/**
 * FXML Controller class
 *
 * @author Jed Gunderson
 */
public class CustomerManagerController {

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
    private TableView<Customer> tableCurrentSchedule;
    @FXML
    private TableColumn<Customer, String> columnAddressCurrentCustomers;
    @FXML
    private TableColumn<Customer, String> columnDescriptionCurrentSchedule;
    @FXML
    private TableColumn<Customer, String> columnAddress2CurrentCustomers;
    @FXML
    private TableColumn<Customer, String> columnCountryCurrentCustomers;
    @FXML
    private TableColumn<Customer, String> columnCityCurrentCustomers;
    @FXML
    private TableColumn<Customer, String> columnZipCurrentCustomers;
    @FXML
    private TableColumn<Customer, String> columnPhoneCurrentCustomers;
    //static ObservableList<Customer> customerList;

    /**
     * Initializes the controller class.
     */
    //@Override
    public void initialize() throws SQLException, IOException {
        // Assign data to columns in =>tableCurrentSchedule
        columnAddressCurrentCustomers.setCellValueFactory(cellData -> cellData.getValue().getCustomerName());
        columnDescriptionCurrentSchedule.setCellValueFactory(cellData -> cellData.getValue().getAddress());
        //columnAddress2CurrentCustomers.setCellValueFactory(cellData -> cellData.getValue().getAddress2());
        columnCityCurrentCustomers.setCellValueFactory(cellData -> cellData.getValue().getCity());
        columnCountryCurrentCustomers.setCellValueFactory(cellData -> cellData.getValue().getCountry());
        columnZipCurrentCustomers.setCellValueFactory(cellData -> cellData.getValue().getPostalCode());
        columnPhoneCurrentCustomers.setCellValueFactory(cellData -> cellData.getValue().getPhone());

        tableCurrentSchedule.setItems(SQLConnectorData.databaseCustomer());
    }

    @FXML
    private void saveAction(ActionEvent event) {
//        String customerName = txtCustomerName.getText();
//        String address = txtAddress1.getText();
//        String country = comboCountry.getSelectionModel().getSelectedItem();
//        String city = comboCity.getSelectionModel().getSelectedItem();
//        String postalCode = txtZip.getText();
//        String phone = txtPhone.getText();
//        
//        try {
//            Parent mainScreenParent = FXMLLoader.load(getClass().getResource("MainLanding.fxml"));
//            Scene mainScreenScene = new Scene(mainScreenParent);
//            Stage mainScreenStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            mainScreenStage.setScene(mainScreenScene);
//            mainScreenStage.show();
//        
//        String sqlCustomerInsert = "INSERT INTO Customer (customerName,addressId, active, createDate, createdBy, lastUpdate, lastUpdateBy,  address, address2, cityId, city, countryId, country, postalCode, phone)" 
//                +"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//
//        PreparedStatement statement = SQLConnector.getCon().prepareStatement(sqlCustomerInsert);
//        statement.setString(1, customerName);
//        statement.setString(2, address);
//        statement.setString(3, country);
//        statement.setString(4, city);
//        statement.setString(5, postalCode);
//        statement.setString(6, phone);
//        
//        //Use executeUpdate instead of executeQuery
//        int rowsInserted = statement.executeUpdate();
//        if (rowsInserted > 0) {
//            System.out.println("A new user was inserted successfully!");
//        }
//        
//        }
//        catch (IOException e) {
//        }catch (SQLException ex) {
//            Logger.getLogger(CustomerManagerController.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
    }
        @FXML
        private void countryAction(ActionEvent event) {
    }

    @FXML
        private void cityAction(ActionEvent event) {
    }

    @FXML
        private void deleteAction (ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Confirmation Delete Dialog");
            alert.setContentText("Are you sure you want to delete the selected record?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                //This will be different... needs to delete from database not from the display table
                //customerList.remove(tableCurrentSchedule.getSelectionModel().getSelectedItem());
            }
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
