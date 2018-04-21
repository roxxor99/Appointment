
//    public void initialize() throws SQLException, IOException {
//        apptList = SQLConnectorData.databaseAppointments();
//        
//    }
//    private void populateAptTable(boolean isMonth) throws ParseException {
//        // Assign data to columns in =>tableMainCurrentSchedule
//        LocalDate now = LocalDate.now();
//        ObservableList<Appointment> filteredList = FXCollections.observableArrayList();
//        for (Appointment appointment:apptList)
//        {
//            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2014-10-14 03:05:39");//(appointment.getStartTime().getValue());
//            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
////            String utcTime = appointment.getStartTime().getValue();
////            DateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
////            utcFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
////            Date utcDate = utcFormat.parse(utcTime);
////            LocalDate dateParsed = LocalDate.parse(dateTimeFormatter.format((TemporalAccessor) utcDate), dateTimeFormatter);
//            
//            
////            String check = appointment.getStartTime().getValue();
////            LocalDate test = LocalDate.parse(check);
////            String utcTime = appointment.getStartTime().getValue();
////            DateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////            utcFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
//            
////         String utcTimeString = "2014-10-14 03:05:39";
////         DateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////         utcFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
////         Date utcTime = utcFormat.parse(utcTimeString);
////
////
////         DateFormat localFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////         localFormat.setTimeZone(TimeZone.getDefault());
////         System.out.println("Local: " + localFormat.format(utcTime));
//         
//            LocalDate currentApt = LocalDate.parse(appointment.getStartTime().toString());
//            //LocalDate currentApt = LocalDate.parse(date);
////            LocalDate.parse(dateTimeFormatter.format(date), dateTimeFormatter);
//            if(isMonth) {
//               if(currentApt.getMonth() == now.getMonth()){
//                   filteredList.add(appointment);
//               }
//            }
//            else{
//                LocalDate week = now.plusDays(7);
//                if(currentApt.isAfter(now) && currentApt.isBefore(week)) {
//                    filteredList.add(appointment);
//                }
//            }
//        }
//        columnCustNameCurrentSchedule.setCellValueFactory(cellData -> cellData.getValue().getCustomerName());
//        columnDescriptionCurrentSchedule.setCellValueFactory(cellData -> cellData.getValue().getCreatedBy());
//        columnTitleCurrentSchedule.setCellValueFactory(cellData -> cellData.getValue().getTitle());
//        columnLocationCurrentSchedule.setCellValueFactory(cellData -> cellData.getValue().getLocation());
//        columnStartCurrentSchedule.setCellValueFactory(cellData -> cellData.getValue().getStartTime());
//        columnEndCurrentSchedule.setCellValueFactory(cellData -> cellData.getValue().getEndTime());
//        tableMainCurrentSchedule.setItems(filteredList);
//    }
//
//    
//    //Calendar by month radio
//    @FXML
//    private void monthlyViewAction(ActionEvent event) throws ParseException {
//        populateAptTable(true);
//        
//    }
//    
//    //Calendar by week radio
//    @FXML
//    private void weeklyViewAction(ActionEvent event) throws ParseException {
//        populateAptTable(false);
//        
//    }
//    
//    @FXML
//    private void manageCustomersAction(ActionEvent event) throws IOException {
//    Stage stage;
//        Parent root;
//        stage = (Stage) butManageCustomers.getScene().getWindow();
//        root = FXMLLoader.load(getClass().getResource("CustomerManager.fxml"));
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    @FXML
//    private void manageAppointmentsAction(ActionEvent event) throws IOException {
//    Stage stage;
//        Parent root;
//        stage = (Stage) butManageAppointments.getScene().getWindow();
//        root = FXMLLoader.load(getClass().getResource("AppointmentManager.fxml"));
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }
//    
//    @FXML
//    private void manageReportsAction(ActionEvent event) throws IOException {
//        Stage stage;
//        Parent root;
//        stage = (Stage) butManageReports.getScene().getWindow();
//        root = FXMLLoader.load(getClass().getResource("ReportInfo.fxml"));
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }
//    
//    @FXML
//    private void exitAction(ActionEvent event) {
//    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.initModality(Modality.NONE);
//        alert.setTitle("Confirmation ");
//        alert.setHeaderText("Confirm Exit");
//        alert.setContentText("Are you sure you want to exit?");
//        Optional<ButtonType> result = alert.showAndWait();
//        if (result.get() == ButtonType.OK) {
//            System.exit(0);
//        }
//    }
//
//    public static ObservableList<Appointment> getApptList() {
//        return apptList;
//    }
//    
//}

package testrun;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Modality;
import javafx.stage.Stage;
//import testrun.App.writeToLog;

/**
 * FXML Controller class
 *
 * @author Jed Gunderson
 */
//public class MainLandingController implements Initializable {
public class MainLandingController {

    @FXML
    private RadioButton radioMonthyView;
    @FXML
    private ToggleGroup calendarView;
    @FXML
    private RadioButton radioWeeklyView;
    @FXML
    private TableView<Appointment> tableMainCurrentSchedule;
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
    private Button butManageCustomers;
    @FXML
    private Button butManageAppointments;
    @FXML
    private Button butManageReports;
    @FXML
    private Label lblCurrentSchedule;
    @FXML
    private Button butLog;
    @FXML
    private Button butExit;
    static ObservableList<Appointment> apptList;
    private boolean isMonth;
    
    /**
     * Initializes the controller class.
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    //@Override
    public void initialize() throws SQLException, IOException {
        // Assign data to columns in =>tableMainCurrentSchedule
        columnCustNameCurrentSchedule.setCellValueFactory(cellData -> cellData.getValue().getCustomerName());
        columnCreatedByCurrentSchedule.setCellValueFactory(cellData -> cellData.getValue().getCreatedBy());
        columnTypeCurrentSchedule.setCellValueFactory(cellData -> cellData.getValue().getTitle());
        columnLocationCurrentSchedule.setCellValueFactory(cellData -> cellData.getValue().getLocation());
        columnStartCurrentSchedule.setCellValueFactory(cellData -> cellData.getValue().getStartTime());
        columnEndCurrentSchedule.setCellValueFactory(cellData -> cellData.getValue().getEndTime());
        apptList = SQLConnectorData.databaseAppointments();
        tableMainCurrentSchedule.setItems(apptList);
    }

    //Calendar by month radio
    @FXML
    private void monthlyViewAction(ActionEvent event) throws ParseException {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nowPlus1 = now.plusMonths(1);
        FilteredList<Appointment> filteredData = new FilteredList<>(apptList);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        //set predicate will match the elements that will be in the FilteredList
        filteredData.setPredicate(row -> {
            LocalDateTime rowDate = LocalDateTime.parse(row.getStartTime().getValue(), df);
            return rowDate.isAfter(now) && rowDate.isBefore(nowPlus1);
        });
        //tableMainCurrentSchedule.getItems().clear();
        tableMainCurrentSchedule.setItems(filteredData);
    }

    //Calendar by week radio
    @FXML
    private void weeklyViewAction(ActionEvent event) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nowPlus7 = now.plusDays(7);
        FilteredList<Appointment> filteredData = new FilteredList<>(apptList);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        //set predicate will match the elements that will be in the FilteredList
        filteredData.setPredicate(row -> {
            LocalDateTime rowDate = LocalDateTime.parse(row.getStartTime().getValue(), df);
            return rowDate.isAfter(now) && rowDate.isBefore(nowPlus7);
        });
        tableMainCurrentSchedule.setItems(filteredData);
    }

    @FXML
    private void manageCustomersAction(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) butManageCustomers.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("CustomerManager.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void manageAppointmentsAction(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) butManageAppointments.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("AppointmentManager.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void manageReportsAction(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) butManageReports.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("ReportInfo.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    void logAction(ActionEvent event) throws IOException {
        Runtime.getRuntime().exec("explorer.exe /select,C:\\C195_LogFile\\log.txt");    
    }
    
    @FXML
    private void exitAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Confirmation ");
        alert.setHeaderText("Confirm Exit");
        alert.setContentText("Are you sure you want to exit?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            System.exit(0);
        }
    }

    public static ObservableList<Appointment> getApptList() {
        return apptList;
    }
    
}
