package testrun;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static testrun.MainLandingController.apptList;
import static testrun.MainLandingController.setApptList;
import static testrun.SQLConnector.executeQuery;
import static testrun.SQLConnectorData.databaseAppointments;

/**
 * FXML Controller class
 *
 * @author Jed Gunderson
 */
//public class AppointmentManagerController implements Initializable {
public class AppointmentManagerController {

    @FXML
    private Label lblManageAppointments;
    @FXML
    private Label lblStartTime;
    @FXML
    private Label lblEndTime;
    @FXML
    private Label lblCurrentSchedule;
    @FXML
    private Button butSave;
    @FXML
    private Button butModify;
    @FXML
    private Button butDelete;
    @FXML
    private Button butBack;
    @FXML
    private Label lblAppointmentManager;
    @FXML
    private DatePicker datePicker;
    @FXML
    private ComboBox<String> comboCustomerName;
    @FXML
    private TextField txtType;
    @FXML
    private TextField txtCreatedBy;
    @FXML
    private ComboBox<String> comboType;
    @FXML
    private ComboBox<String> comboStartHour;
    @FXML
    private ComboBox<String> comboStartMinute;
    @FXML
    private ComboBox<String> comboEndHour;
    @FXML
    private ComboBox<String> comboEndMinute;
    @FXML
    private ComboBox<String> comboLocation;
    @FXML
    private TableView<Appointment> tableCurrentSchedule;
    @FXML
    private TableColumn<Appointment, String> columnCustNameCurrentSchedule;
    @FXML
    private TableColumn<Appointment, String> columnCreatedByCurrentSchedule;
    @FXML
    private TableColumn<Appointment, String> columnTypeCurrentSchedule;
    @FXML
    private TableColumn<Appointment, String> columnLocationCurrentSchedule;
    @FXML
    private TableColumn<Appointment, String> columnStartCurrentSchedule;
    @FXML
    private TableColumn<Appointment, String> columnEndCurrentSchedule;
    @FXML
    private TableColumn<Appointment, Number> columnAppIdCurrentSchedule;
    @FXML
    private TableColumn<Appointment, Number> columnCusIdCurrentSchedule;

    /**
     * Initializes the controller class.
     */
//    @Override
    public void initialize() throws SQLException, IOException {
        // Assign data to columns in =>tableCurrentSchedule
        tableData(false);

        //comboLocation : create the combobox choices
        comboLocation.getItems().add("New York, New York");
        comboLocation.getItems().add("Phoenix, Arizona");
        comboLocation.getItems().add("London, England");

        //startHour in 24 hour format 9am - 4pm
        comboStartHour.getItems().addAll("09", "10", "11", "12", "13", "14", "15", "16");

        //startMinutes
        comboStartMinute.getItems().addAll("00", "15", "30", "45");

        //endHour in 24 hour format 9am - 5pm
        comboEndHour.getItems().addAll("09", "10", "11", "12", "13", "14", "15", "16", "17");

        //endMinutes in 15 minute intervals (ease of testing reminder)
        comboEndMinute.getItems().addAll("00", "15", "30", "45");

        //comboType : create the combobox choices
        comboType.getItems().add("Consultation");
        comboType.getItems().add("On-Campus Meeting");
        comboType.getItems().add("Teleconference");

        //Populate ComboBox customerName
        String customerName = "SELECT DISTINCT customerId, customerName FROM customer ORDER BY customerName ASC;";
        try {
            ResultSet rs = executeQuery(customerName);
            {
                while (rs.next()) {
                    comboCustomerName.getItems().add(rs.getString("customerName") + ":" + rs.getString("customerId"));
                }
            }
        } catch (SQLException ex) {
        }
    }
//none of these onAction methods are needed and can be removed by deleting below AND from AppointmentManager.fxml.

    @FXML
    private void customerNameAction(ActionEvent event) throws SQLException, IOException {
    }

    @FXML
    private void typeAction(ActionEvent event) {
    }

    @FXML
    private void locationAction(ActionEvent event) {
    }

    @FXML
    private void startHourAction(ActionEvent event) {
    }

    @FXML
    private void startMinuteAction(ActionEvent event) {
    }

    @FXML
    private void endHourAction(ActionEvent event) {
    }

    @FXML
    private void endMinuteAction(ActionEvent event) {
    }
//end delete

    @FXML
    private void saveAction(ActionEvent event) throws SQLException, IOException {
        String[] parts = comboCustomerName.getSelectionModel().getSelectedItem().split(":");
        String customerName = parts[0];
        String customerId = parts[1];
        Integer appointmentId = null;

        String type = comboType.getSelectionModel().getSelectedItem();
        String createdBy = txtCreatedBy.getText();
        String location = comboLocation.getSelectionModel().getSelectedItem();
        LocalDate date = datePicker.getValue();
        String startHour = comboStartHour.getSelectionModel().getSelectedItem();
        String startMinute = comboStartMinute.getSelectionModel().getSelectedItem();
        String endHour = comboEndHour.getSelectionModel().getSelectedItem();
        String endMinute = comboEndMinute.getSelectionModel().getSelectedItem();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedString = date.format(formatter);

        Timestamp updateStartTime = timeConversions(formattedString + " " + startHour + ":" + startMinute + ":00.0");
        Timestamp updateEndTime = timeConversions(formattedString + " " + endHour + ":" + endMinute + ":00.0");


        try {
            Parent mainScreenParent = FXMLLoader.load(getClass().getResource("MainLanding.fxml"));
            Scene mainScreenScene = new Scene(mainScreenParent);
            Stage mainScreenStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            mainScreenStage.setScene(mainScreenScene);
            mainScreenStage.show();

            if (tableCurrentSchedule.getSelectionModel().isEmpty()) {
                String sqlAppointmentInsert = "INSERT INTO appointment (customerId, title, description, location, contact, url, start, end, createDate, createdBy, lastUpdate, lastUpdateBy)"
                        + "VALUES (?, ?, '', ?, '', '', ?, ?, now(), ?, now(), ?);";

                PreparedStatement statement = SQLConnector.getCon().prepareStatement(sqlAppointmentInsert);
                statement.setString(1, customerId);
                statement.setString(2, type);
                statement.setString(3, location);
                statement.setTimestamp(4, updateStartTime);
                statement.setTimestamp(5, updateEndTime);
                statement.setString(6, createdBy);
                statement.setString(7, createdBy);

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Appointment Saved.");
                }
            } else {
                appointmentId = tableCurrentSchedule.getSelectionModel().getSelectedItem().getAppointmentId().getValue();

                String sqlAppointmentUpdate = "UPDATE appointment SET customerId=?, title =?, location =?, start =?, end =?, lastUpdate=now(), lastUpdateBy =?"
                        + "WHERE appointmentId =?";
                PreparedStatement statement = SQLConnector.getCon().prepareStatement(sqlAppointmentUpdate);
                statement.setString(1, customerId);
                statement.setString(2, type);
                statement.setString(3, location);
                statement.setTimestamp(4, updateStartTime);
                statement.setTimestamp(5, updateEndTime);
                statement.setString(6, LoginController.getLoggedInUser());
                statement.setInt(7, appointmentId);

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Appointment Updated.");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AppointmentManagerController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AppointmentManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //refreshes the table and DB data when true
        tableData(false);
    }

    @FXML
    private void modifyAction(ActionEvent event) {
        //concat customerName:customerId
        comboCustomerName.setValue(tableCurrentSchedule.getSelectionModel().getSelectedItem().getCustomerName().getValue()+ ":" 
                +tableCurrentSchedule.getSelectionModel().getSelectedItem().getCustomerId().getValue());
        comboType.setValue(tableCurrentSchedule.getSelectionModel().getSelectedItem().getTitle().getValue());
        txtCreatedBy.setText(tableCurrentSchedule.getSelectionModel().getSelectedItem().getCreatedBy().getValue());
        comboLocation.setValue(tableCurrentSchedule.getSelectionModel().getSelectedItem().getLocation().getValue());

        //Split database date and time
        //Date Format
        String startDate = tableCurrentSchedule.getSelectionModel().getSelectedItem().getStartTime().getValue();
        String endDate = tableCurrentSchedule.getSelectionModel().getSelectedItem().getEndTime().getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm:ss.S");
        LocalDate currentDate = LocalDate.parse(startDate, formatter);
        datePicker.setValue(currentDate);

        //Time Format
        LocalDateTime startTime = LocalDateTime.parse(startDate, formatter);
        LocalDateTime endTime = LocalDateTime.parse(endDate, formatter);
        
        //ternary operator(?= if and := else) to convert from Int(0) to String(00) *int removes the leading 0 
        comboStartHour.setValue((startTime.getHour()<10)? "0" + Integer.toString(startTime.getHour()): Integer.toString(startTime.getHour()));
        comboStartMinute.setValue((startTime.getMinute()<10)? "0" + Integer.toString(startTime.getMinute()): Integer.toString(startTime.getMinute()));
        comboEndHour.setValue((endTime.getHour()<10)? "0" + Integer.toString(endTime.getHour()): Integer.toString(endTime.getHour()));
        comboEndMinute.setValue((endTime.getMinute()<10)? "0" + Integer.toString(endTime.getMinute()): Integer.toString(endTime.getMinute()));

        //Appointment selection required to be modified or deleted
        Appointment modAppointment = tableCurrentSchedule.getSelectionModel().getSelectedItem();
        
        // An appointment must be selected before it can be modified
        if (modAppointment == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error: No Appointment Selected");
            alert.setContentText("You must select an appointment from the table to update.");
            alert.showAndWait();
        }
    }

    @FXML
    private void deleteAction(ActionEvent event) throws SQLException, IOException {
        Appointment appt = tableCurrentSchedule.getSelectionModel().getSelectedItem();

        if (appt == null) {
            // An appointment must be selected before it can be removed
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
        alert.setContentText("This will delete the selected appointment, do you wish to proceed?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            String sqlAppointmentDelete = "DELETE FROM appointment WHERE appointmentId = ?;";
            PreparedStatement statement = SQLConnector.getCon().prepareStatement(sqlAppointmentDelete);
            statement.setInt(1, tableCurrentSchedule.getSelectionModel().getSelectedItem().getAppointmentId().getValue());
            statement.executeUpdate();
        }
        tableData(true);
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
            stage = (Stage) lblAppointmentManager.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("MainLanding.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    //FUN with timeconversion @ 2am!
//    public static Timestamp dateFormatter(String catDate) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm:ss.S");
//        LocalDateTime ldt = LocalDateTime.parse(catDate, formatter);
//
//        ZoneId zid = ZoneId.systemDefault();
//        ZonedDateTime zDateTime = ldt.atZone(zid);
//        ZonedDateTime utcStart = zDateTime.withZoneSameInstant(ZoneId.of("UTC"));
//        ldt = utcStart.toLocalDateTime();
//        //Create Timestamp values from Instants to update database
//        Timestamp startSQLts = Timestamp.valueOf(ldt);
//        return startSQLts;
//    }
    
    //Convert to UTC
    public static Timestamp timeConversions(String catDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm:ss.S");
        LocalDateTime ldt = LocalDateTime.parse(catDate, formatter);
        
        ZoneId zid = ZoneId.systemDefault();
        ZonedDateTime zDateTime = ldt.atZone(zid).withZoneSameInstant(ZoneId.of("UTC"));
        ldt = zDateTime.toLocalDateTime();

        String currentTime = zDateTime.format(formatter);
        return Timestamp.valueOf(ldt);
    }
    
    //Convert to Local
    public static ZonedDateTime utcToLocal(Timestamp datetime) {
        ZoneId zid = ZoneId.systemDefault();
        ZonedDateTime zDateTime = datetime.toLocalDateTime().atZone(ZoneId.of("UTC"));
        ZonedDateTime localStart = zDateTime.withZoneSameInstant(zid);

        System.out.println("From db in UTC: " + zDateTime);
        System.out.println("From db in local time: " + localStart);
        return localStart;
    }

    private void tableData(Boolean updateRefresh) throws SQLException, IOException {
        if (updateRefresh) {
            setApptList(databaseAppointments());
        }
        columnCustNameCurrentSchedule.setCellValueFactory(cellData -> cellData.getValue().getCustomerName());
        columnCreatedByCurrentSchedule.setCellValueFactory(cellData -> cellData.getValue().getCreatedBy());
        columnTypeCurrentSchedule.setCellValueFactory(cellData -> cellData.getValue().getTitle());
        columnLocationCurrentSchedule.setCellValueFactory(cellData -> cellData.getValue().getLocation());
        columnStartCurrentSchedule.setCellValueFactory(cellData -> cellData.getValue().getStartTime());
        columnEndCurrentSchedule.setCellValueFactory(cellData -> cellData.getValue().getEndTime());
        columnAppIdCurrentSchedule.setCellValueFactory(cellData -> cellData.getValue().getAppointmentId());
        columnCusIdCurrentSchedule.setCellValueFactory(cellData -> cellData.getValue().getCustomerId());

        apptList = MainLandingController.getApptList();
        tableCurrentSchedule.setItems(apptList);
    }
        
        //Appointment Exception Checking
//    private Boolean isValid(){
//        String title;
//        Customer customer;
//        
//        if(title == null || title.isEmpty()) {
//            errorMessage += ("Title cannot be blank\n");
//        }
//    }
}
