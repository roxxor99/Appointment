package testrun;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Locale;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.StringProperty;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import static testrun.MainLandingController.apptList;

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
    
    

    /**
     * Initializes the controller class.
     */
//    @Override
    public void initialize() throws SQLException, IOException {
        // Assign data to columns in =>tableCurrentSchedule
        columnCustNameCurrentSchedule.setCellValueFactory(cellData -> cellData.getValue().getCustomerName());
        columnCreatedByCurrentSchedule.setCellValueFactory(cellData -> cellData.getValue().getCreatedBy());
        columnTypeCurrentSchedule.setCellValueFactory(cellData -> cellData.getValue().getTitle());
        columnLocationCurrentSchedule.setCellValueFactory(cellData -> cellData.getValue().getLocation());
        columnStartCurrentSchedule.setCellValueFactory(cellData -> cellData.getValue().getStartTime());
        columnEndCurrentSchedule.setCellValueFactory(cellData -> cellData.getValue().getEndTime());
        
        //apptList = SQLConnectorData.databaseAppointments();//do not need to query again
        //tableCurrentSchedule.setItems(MainLandingController.getApptList());//updated to variable apptList().
        apptList = MainLandingController.getApptList();
        tableCurrentSchedule.setItems(apptList);
        
        
         
         //StartMinutes
//         comboStartMinute = new ComboBox<>();
//         comboStartMinute.getItems().addAll("00","15","30","45",);
        
    }

    @FXML
    private void customerNameAction(ActionEvent event) {
    }

    @FXML
    private void descriptionAction(ActionEvent event) {
    }
    
    @FXML
    private void locationAction(ActionEvent event) {
    }
    
    @FXML
    private void startHourAction(ActionEvent event) {
        }
    
     public void comboStartHour() {
         //24 hour format 9am - 4pm
         comboStartHour = new ComboBox<>();
         //create the combobox
         comboStartHour.getItems().addAll("09","10","11","12","13","14","15","16");
    }
    
    @FXML
    private void startMinuteAction(ActionEvent event) {
//        String[] startMinutes = {"00", "15", "30", "45"};
//        ComboBox startMinuteList = new ComboBox(startMinutes);
//        
//        startMinutes.addActionListener(this);
    }

    @FXML
    private void endHourAction(ActionEvent event) {
    }

    @FXML
    private void endMinuteAction(ActionEvent event) {
    }

    @FXML
    private void saveAction(ActionEvent event) {
        String customerName = comboCustomerName.getSelectionModel().getSelectedItem();
        String type = txtType.getText();
        String createdBy = txtCreatedBy.getText();
        String location = comboLocation.getSelectionModel().getSelectedItem();
        LocalDate date = datePicker.getValue();
        String startHour = comboStartHour.getSelectionModel().getSelectedItem();
        String startMinute = comboStartMinute.getSelectionModel().getSelectedItem();
        String endHour = comboEndHour.getSelectionModel().getSelectedItem();
        String endMinute = comboEndMinute.getSelectionModel().getSelectedItem();

        try {
            Parent mainScreenParent = FXMLLoader.load(getClass().getResource("MainLanding.fxml"));
            Scene mainScreenScene = new Scene(mainScreenParent);
            Stage mainScreenStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            mainScreenStage.setScene(mainScreenScene);
            mainScreenStage.show();

            String sqlAppointmentInsert = "INSERT INTO Appointment (customerId, title, description, location, contact, url, start, end, createDate, createdBy, lastUpdate, lastUpdateBy)" //insert all fields except appointmentId match name to db fields
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, now(), ?, now(), ?)";//use empty "" for fields not being used?. use now() for update and created
            
            PreparedStatement statement = SQLConnector.getCon().prepareStatement(sqlAppointmentInsert);
            statement.setString(1, customerName);
            statement.setString(2, type);
            statement.setString(3, createdBy);
            statement.setString(4, location);
            //Not sure if setObject is right.
            statement.setObject(5, date);
            statement.setString(6, startHour);
            statement.setString(7, startMinute);
            statement.setString(8, endHour);
            statement.setString(9, endMinute);

            //Use executeUpdate instead of executeQuery
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Appointment saved.");
            }

        } catch (IOException e) {
        } catch (SQLException ex) {
            Logger.getLogger(AppointmentManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void deleteAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation Delete Dialog");
        alert.setContentText("This will delete the selected appointment, do you wish to proceed?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            //Appointment.apptList.remove(tableCurrentSchedule.getSelectionModel().getSelectedItem());
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
            stage = (Stage) lblAppointmentManager.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("MainLanding.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    
    @FXML
        private void modifyAction(ActionEvent event) {
            //Appointment selection required to be modified or deleted
    //Appointment.selectedAppointment = tableCurrentSchedule.getSelectionModel().getSelectedItem();


    }
    
    }
