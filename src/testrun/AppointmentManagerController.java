package testrun;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Locale;
import javafx.beans.property.StringProperty;
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
import javafx.scene.control.DatePicker;
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
//public class AppointmentManagerController implements Initializable {
    //might need for modify appointment action
//    public static Appointment selectedAppointment;
//    public static Appointment getLocation;
 public class AppointmentManagerController{   
    @FXML
    private Label lblAppointmentManager;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Label lblManageAppointments;
    @FXML
    private ComboBox<?> comboCustomerName;
    @FXML
    private TextField txtTitle;
    @FXML
    private TextField txtContact;
    @FXML
    private TextField txtURL;
    @FXML
    private ComboBox<?> comboDescription;
    @FXML
    private Label lblStartTime;
    @FXML
    private ComboBox<?> comboStartHour;
    @FXML
    private ComboBox<?> comboStartMinute;
    @FXML
    private Label lblEndTime;
    @FXML
    private ComboBox<?> comboEndHour;
    @FXML
    private ComboBox<?> comboEndMinute;
    @FXML
    private Button butSave;
    @FXML
    private ComboBox<?> comboLocation;
    @FXML
    private Label lblCurrentSchedule;
    @FXML
    private Button butDelete;
    @FXML
    private Button butBack;
    @FXML
    private Button butModify;
    @FXML
    private TableView<Appointment> tableCurrentSchedule;
    @FXML
    private TableColumn<Customer, String> columnCustNameCurrentSchedule;
    @FXML
    private TableColumn<Appointment, String> columnDescriptionCurrentSchedule;
    @FXML
    private TableColumn<Appointment, String> columnTitleCurrentSchedule;
    @FXML
    private TableColumn<Appointment, String> columnLocationCurrentSchedule;
    @FXML
    private TableColumn<Appointment, String> columnDateCurrentSchedule;
    @FXML
    private TableColumn<Appointment, String> columnStartCurrentSchedule;
    @FXML
    private TableColumn<Appointment, String> columnEndCurrentSchedule;
    
    /**
     * Initializes the controller class.
     */
//    @Override
    public void initialize() {
        // Assign data to columns in =>tableCurrentSchedule
        columnCustNameCurrentSchedule.setCellValueFactory(cellData -> cellData.getValue().getCustomerName());
        columnDescriptionCurrentSchedule.setCellValueFactory(cellData -> cellData.getValue().getDescription());
        columnTitleCurrentSchedule.setCellValueFactory(cellData -> cellData.getValue().getTitle());
        columnLocationCurrentSchedule.setCellValueFactory(cellData -> cellData.getValue().getLocation());
        //may need to make changes to appointment class so it is the same as .startTxtValue()
        columnDateCurrentSchedule.setCellValueFactory(cellData -> cellData.getValue().getDateTxt());
        columnStartCurrentSchedule.setCellValueFactory(cellData -> cellData.getValue().startTxtValue());
        columnEndCurrentSchedule.setCellValueFactory(cellData -> cellData.getValue().getEndTxt());
    }              
        

    @FXML
    private void customerNameAction(ActionEvent event) {
    }

    @FXML
    private void descriptionAction(ActionEvent event) {
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

    @FXML
    private void saveAction(ActionEvent event) {
    }

    @FXML
    private void locationAction(ActionEvent event) {
    }

    @FXML
    private void deleteAction(ActionEvent event) {
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setTitle("Confirmation");
//        alert.setHeaderText("Confirmation Delete Dialog");
//        alert.setContentText("This will delete the selected appointment, do you wish to proceed?");
//        Optional<ButtonType> result = alert.showAndWait();
//        if (result.get() == ButtonType.OK) {
//            Appointment.allAppointments.remove(tableCurrentSchedule.getSelectionModel().getSelectedItem());
//        }
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
    // Update tableview => tableCurrentSchedule 
//    @FXML
//    public void updateScheduledAppointments() {
//        updateScheduledAppointments();
//        tableCurrentSchedule.setItems();
//    }
    
    @FXML
    private void modifyAction(ActionEvent event) {
    //Appointment selection required=> not sure about this yet...
    //Appointment.selectedAppointment = tableCurrentSchedule.getSelectionModel().getSelectedItem();


    }
    }
    

