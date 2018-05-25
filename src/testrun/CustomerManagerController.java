package testrun;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
    private TextField txtZip;
    @FXML
    private Button butSave;
    @FXML
    private TextField txtAddress;
    @FXML
    private ComboBox<String> comboCountry;
    @FXML
    private ComboBox<String> comboCity;
    @FXML
    private TextField txtPhone;
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
    private TableColumn<Customer, String> columnCountryCurrentCustomers;
    @FXML
    private TableColumn<Customer, String> columnCityCurrentCustomers;
    @FXML
    private TableColumn<Customer, String> columnZipCurrentCustomers;
    @FXML
    private TableColumn<Customer, String> columnPhoneCurrentCustomers;
    @FXML
    private TableColumn<Customer, String> columnCityIdCurrentCustomers;

    /**
     * Initializes the controller class.
     */
    //@Override
    public void initialize() throws SQLException, IOException {
        //Populate Tableview
        tableData();
        
        //Populate ComboBox Country
        String sqlCountry = "SELECT countryId, country FROM country;";
        try {
            ResultSet rs = executeQuery(sqlCountry);
            {
                while (rs.next()) {
                    comboCountry.getItems().add(rs.getString("country") + ":" + rs.getString("countryId"));
                }
            }
        } catch (SQLException ex) {
        }
    }

    @FXML
    private void saveAction(ActionEvent event) throws SQLException, IOException {
        if(!isValid()){
        return;    
        }
        
        String customerName = txtCustomerName.getText();
        String address = txtAddress.getText();
        String country = comboCountry.getSelectionModel().getSelectedItem();
        String city = comboCity.getSelectionModel().getSelectedItem();
        String postalCode = txtZip.getText();
        String phone = txtPhone.getText();
        String cityId = comboCountry.getSelectionModel().getSelectedItem().split(":")[1];
//        String sqlCityId = "SELECT cityId, city FROM city WHERE cityId ="+cityId;

        try {
            Parent mainScreenParent = FXMLLoader.load(getClass().getResource("MainLanding.fxml"));
            Scene mainScreenScene = new Scene(mainScreenParent);
            Stage mainScreenStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            mainScreenStage.setScene(mainScreenScene);
            mainScreenStage.show();

            if (tableCurrentSchedule.getSelectionModel().isEmpty()) {
                //insert Address
                String sqlAddressInsert = "INSERT INTO address (address, address2, cityId, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdateBy)"
                        + "VALUES (?,'', ?, ?, ?, now(), ?, now(), ?)";
                PreparedStatement statement = SQLConnector.getCon().prepareStatement(sqlAddressInsert);
                statement.setString(1, address);
                statement.setString(2, cityId);
                statement.setString(3, postalCode);
                statement.setString(4, phone);
                statement.setString(5, LoginController.getLoggedInUser());
                statement.setString(6, LoginController.getLoggedInUser());
                
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("A new address was inserted successfully!");
                }
                
                //insert Customer 
                String sqlCustomerInsert = "INSERT INTO customer (customerName, addressId, active, createDate, createdBy, lastUpdate, lastUpdateBy)"
                        + "VALUES (?, LAST_INSERT_ID(), 0, now(), ?, now(), ?)";
                statement = SQLConnector.getCon().prepareStatement(sqlCustomerInsert);
                statement.setString(1, customerName);
                statement.setString(2, LoginController.getLoggedInUser());
                statement.setString(3, LoginController.getLoggedInUser());

                //Use executeUpdate instead of executeQuery
                rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("A new customer was inserted successfully!");
                }
            } else {
                String customerId = tableCurrentSchedule.getSelectionModel().getSelectedItem().getCustomerId().getValue();
                String addressId = tableCurrentSchedule.getSelectionModel().getSelectedItem().getAddressId().getValue();
                
                String sqlAddressUpdate = "UPDATE address SET address=?, cityId=?, postalCode=?, phone=?, lastUpdate=now(), lastUpdateBy=?"
                        + "WHERE addressId =?";
                PreparedStatement statement = SQLConnector.getCon().prepareStatement(sqlAddressUpdate);
                statement.setString(1, address);
                statement.setString(2, cityId);
                statement.setString(3, postalCode);
                statement.setString(4, phone);
                statement.setString(5, LoginController.getLoggedInUser());
                statement.setString(6, addressId);
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Address Updated.");
                }
                
                String sqlCustomerUpdate = "UPDATE customer SET customerName=?, lastUpdate=now(), lastUpdateBy=?"
                        + "WHERE customerId =?";
                statement = SQLConnector.getCon().prepareStatement(sqlCustomerUpdate);
                statement.setString(1, customerName);
                statement.setString(2, LoginController.getLoggedInUser());
                statement.setString(3, customerId);

                //Use executeUpdate(INSERT/MOD/DELETE) instead of executeQuery(Query the data)
                rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Customer Updated.");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AppointmentManagerController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AppointmentManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //refreshes the table and DB data
        tableData();
    }

    @FXML
    private void deleteAction(ActionEvent event) throws SQLException, IOException {
        Customer cust = tableCurrentSchedule.getSelectionModel().getSelectedItem();
        
        // A customer must be selected before it can be removed
        if (cust == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error: No Appointment Selected");
            alert.setContentText("You must select an appointment from the table to delete.");
            alert.showAndWait();
            return;
        }
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation Delete Dialog");
        alert.setContentText("Are you sure you want to delete the selected customer record?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            String sqlCustomerDelete = "DELETE FROM customer WHERE customerId = ?;";
            PreparedStatement statement = SQLConnector.getCon().prepareStatement(sqlCustomerDelete);
            statement.setString(1, tableCurrentSchedule.getSelectionModel().getSelectedItem().getCustomerId().getValue());
            statement.executeUpdate();
        }
        tableData();
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
        txtCustomerName.setText(tableCurrentSchedule.getSelectionModel().getSelectedItem().getCustomerName().getValue());
        txtAddress.setText(tableCurrentSchedule.getSelectionModel().getSelectedItem().getAddress().getValue());

        //Need to set country AND id 
        String tmpCountry = tableCurrentSchedule.getSelectionModel().getSelectedItem().getCountry().getValue();
        String tmpCountryId = tableCurrentSchedule.getSelectionModel().getSelectedItem().getCountryId().getValue();
        String catCountry = tmpCountry + ":" + tmpCountryId;
//        comboCountry.setValue(tableCurrentSchedule.getSelectionModel().getSelectedItem().getCountry().getValue());
        comboCountry.setValue(catCountry);
        
        //Need to set city AND id
        String tmpCity = tableCurrentSchedule.getSelectionModel().getSelectedItem().getCity().getValue();
        String tmpCityId = tableCurrentSchedule.getSelectionModel().getSelectedItem().getCityId().getValue();
        String catCity = tmpCity + ":" + tmpCityId;
//        comboCity.setValue(tableCurrentSchedule.getSelectionModel().getSelectedItem().getCity().getValue());
        comboCity.setValue(catCity);
        
        txtZip.setText(tableCurrentSchedule.getSelectionModel().getSelectedItem().getPostalCode().getValue());
        txtPhone.setText(tableCurrentSchedule.getSelectionModel().getSelectedItem().getPhone().getValue());
    }
    
    private void tableData() throws SQLException, IOException {
        columnAddressCurrentCustomers.setCellValueFactory(cellData -> cellData.getValue().getCustomerName());
        columnDescriptionCurrentSchedule.setCellValueFactory(cellData -> cellData.getValue().getAddress());
        columnCityCurrentCustomers.setCellValueFactory(cellData -> cellData.getValue().getCity());
        columnCountryCurrentCustomers.setCellValueFactory(cellData -> cellData.getValue().getCountry());
        columnZipCurrentCustomers.setCellValueFactory(cellData -> cellData.getValue().getPostalCode());
        columnPhoneCurrentCustomers.setCellValueFactory(cellData -> cellData.getValue().getPhone());
        columnCityIdCurrentCustomers.setCellValueFactory(cellData -> cellData.getValue().getCityId());

        tableCurrentSchedule.setItems(SQLConnectorData.databaseCustomer());
    }

    @FXML
    void cityAction(ActionEvent event) throws IOException {
        //"SELECT countryId FROM country WHERE country =?;";
    }

    //get appropriate City from Country dropdown selection
    @FXML
    void countryAction(ActionEvent event) throws IOException {
        String countryId = comboCountry.getSelectionModel().getSelectedItem().split(":")[1];
        String sqlCountryId = "SELECT cityId, city FROM city WHERE countryId =" + countryId;

        try {
            Statement s = SQLConnector.getCon().createStatement();
            ResultSet rs = s.executeQuery(sqlCountryId);
            comboCity.getItems().clear();
            while (rs.next()) {
                comboCity.getItems().add(rs.getString("city") + ":" + rs.getString("cityId"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Boolean isValid() {
        String msg = "";
        Boolean valid = false;
        if (txtCustomerName.getText() == null || txtCustomerName.getText().trim().isEmpty()) {
            msg += ("Customer Name requires input\n");
        }

        if (txtAddress.getText() == null || txtAddress.getText().trim().isEmpty()) {
            msg += ("Address requires input\n");
        }

        if (comboCountry.getSelectionModel().isEmpty()) {
            msg += ("Country requires input\n");
        }

        if (comboCity.getSelectionModel().isEmpty()) {
            msg += ("City requires input\n");
        }

        if (txtZip.getText() == null || txtZip.getText().trim().isEmpty()) {
            msg += ("Zip Code requires input\n");
        }

        if (txtPhone.getText() == null || txtPhone.getText().trim().isEmpty()) {
            msg += ("Phone requires input\n");
        }

        if (msg.length() > 0) {
            msg += ("\nPlease fix the listed errors and save again");

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error: Missing required data");
            alert.setContentText(msg);
            alert.showAndWait();
        } else {
            valid = true;
        }
        return valid;
    }
}



