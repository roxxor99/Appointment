package testrun;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
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
import javafx.util.StringConverter;
import static testrun.MainLandingController.apptList;
import static testrun.MainLandingController.setApptList;
import static testrun.SQLConnector.executeQuery;
import static testrun.SQLConnectorData.databaseAppointments;

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
    private TextField txtCountry;
    @FXML
    private TextField txtCity;
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
    //static ObservableList<Customer> customerList;

    /**
     * Initializes the controller class.
     */
    //@Override
    public void initialize() throws SQLException, IOException {
        // Assign data to columns in =>tableCurrentSchedule
        tableData();
    }

    @FXML
    private void saveAction(ActionEvent event) throws SQLException, IOException {
        String customerName = txtCustomerName.getText();
        String address = txtAddress.getText();
        String country = txtCountry.getText();
        String city = txtCity.getText();
        String postalCode = txtZip.getText();
        String phone = txtPhone.getText();

        Integer customerId = null;

        try {
            Parent mainScreenParent = FXMLLoader.load(getClass().getResource("MainLanding.fxml"));
            Scene mainScreenScene = new Scene(mainScreenParent);
            Stage mainScreenStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            mainScreenStage.setScene(mainScreenScene);
            mainScreenStage.show();

            if (tableCurrentSchedule.getSelectionModel().isEmpty()) {
                //!EXAMPLE from appointment SAVE
//                String sqlAppointmentInsert = "INSERT INTO appointment (customerId, title, description, location, contact, url, start, end, createDate, createdBy, lastUpdate, lastUpdateBy)"
//                        + "VALUES (?, ?, '', ?, '', '', ?, ?, now(), ?, now(), ?);";
//                
//                PreparedStatement statement = SQLConnector.getCon().prepareStatement(sqlAppointmentInsert);
//                statement.setString(1, customerId);
//                statement.setString(2, type);
//                statement.setString(3, location);
//                statement.setTimestamp(4, updateStartTime);
//                statement.setTimestamp(5, updateEndTime);
//                statement.setString(6, createdBy);
//                statement.setString(7, createdBy);

                //Dealing with 4 different tables Customer/Address/Country/City......
                //"SELECT * FROM customer INNER JOIN address ON customer.addressId=address.addressId INNER JOIN city ON address.cityId=city.cityId INNER JOIN country ON city.countryId=country.countryId;";
//            // Retrieve customer information from database
//            ResultSet customerResultSet = stmt.executeQuery("SELECT customerName, active, addressId FROM customer WHERE customerId = " + customerId);
//            // Retrieve address information from database
//            ResultSet addressResultSet = stmt.executeQuery("SELECT address, address2, postalCode, phone, cityId FROM address WHERE addressId = " + addressId);
//            // Retrieve city information from database
//            ResultSet cityResultSet = stmt.executeQuery("SELECT city, countryId FROM city WHERE cityId = " + cityId);    
//            // Retrieve country information from database
//            ResultSet countryResultSet = stmt.executeQuery("SELECT country FROM country WHERE countryId = " + countryId);
//            String sqlCustomerInsert = "INSERT INTO customer INNER JOIN address ON customer.addressId=address.addressId INNER JOIN city ON address.cityId=city.cityId INNER JOIN country ON city.countryId=country.countryId;"
//            String sqlCustomerInsert = "INSERT INTO customer (customerName, addressId, active, createDate, createdBy, lastUpdate, lastUpdateBy,  address, address2, cityId, city, countryId, country, postalCode, phone)"
//                        + "VALUES (?, ?, '', now(), ?, now(), ?, ?, '', ?, ?, ?, ?, ?, ?)";
                //using java's String.join to combine multiple queries
                String sqlCustomerInsert = String.join(" ",
                        "INSERT INTO customer (customerId, customerName, addressId, active, createDate, createdBy, lastUpdate, lastUpdateBy)",
                        "VALUES (?, ?, ?, 1, NOW(), ?, NOW(), ?)");

                PreparedStatement statement = SQLConnector.getCon().prepareStatement(sqlCustomerInsert);
                statement.setString(1, customerName);
                statement.setString(2, address);
                statement.setString(3, country);
                statement.setString(4, city);
                statement.setString(5, postalCode);
                statement.setString(6, phone);

                //Use executeUpdate instead of executeQuery
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("A new user was inserted successfully!");
                }
            } else {
                customerId = tableCurrentSchedule.getSelectionModel().getSelectedItem().getCustomerId().getValue();

                String sqlCustomerUpdate = "UPDATE customer, address, city, country"
                        + "INNER JOIN address ON customer.addressId=address.addressId"
                        + "INNER JOIN city ON address.cityId=city.cityId"
                        + "INNER JOIN country ON city.countryId=country.countryId"
                        + "SET customerName=?, address=?, country=?,city=?,postalCode=?,phone=?"
                        + "WHERE customerId = ?";

//                String sqlCustomerUpdate = "UPDATE customer SET (customerName, addressId, active, createDate, createdBy, lastUpdate, lastUpdateBy,  address, address2, cityId, city, countryId, country, postalCode, phone, WHERE customerId)"
//                        + "VALUES (?, ?, 'active', now(), ?, now(), ?, ?, '', ?, ?, ?, ?, ?, ?)";
                PreparedStatement statement = SQLConnector.getCon().prepareStatement(sqlCustomerUpdate);
                statement.setString(1, customerName);
                statement.setString(2, address);
                statement.setString(3, country);
                statement.setString(4, city);
                statement.setString(5, postalCode);
                statement.setString(6, phone);
                statement.setInt(7, customerId);
                //statement.setInt(7, tableCurrentSchedule.getSelectionModel().getSelectedItem().getCustomerId().getValue());

                //Use executeUpdate(INSERT/MOD/DELETE) instead of executeQuery(Query the data)
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Customer Updated.");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AppointmentManagerController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AppointmentManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableData();
    }

    @FXML
    private void deleteAction(ActionEvent event) throws SQLException, IOException {
        Customer cust = tableCurrentSchedule.getSelectionModel().getSelectedItem();

        if (cust == null) {
            // A customer must be selected before it can be removed
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
            statement.setInt(1, tableCurrentSchedule.getSelectionModel().getSelectedItem().getCustomerId().getValue());
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
        txtCountry.setText(tableCurrentSchedule.getSelectionModel().getSelectedItem().getCountry().getValue());
        txtCity.setText(tableCurrentSchedule.getSelectionModel().getSelectedItem().getCity().getValue());
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

        tableCurrentSchedule.setItems(SQLConnectorData.databaseCustomer());

    }
}
