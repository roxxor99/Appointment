package testrun;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import static java.time.LocalDateTime.now;
import java.time.ZonedDateTime;
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
import static testrun.SQLConnector.executeQuery;
import static testrun.SQLConnectorData.databaseAppointments;

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
    private static ObservableList<Appointment> apptList;

    /**
     * Initializes the controller class.
     *
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    //@Override
    public void initialize() throws SQLException, IOException {
        tableData(true);
        upcomingAppointmentAlert();
    }

    //Calendar by month radio
    @FXML
    private void monthlyViewAction(ActionEvent event) throws ParseException {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nowPlus1 = now.plusMonths(1);
        FilteredList<Appointment> filteredData = new FilteredList<>(apptList);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm:ss.S");
        //set predicate will match the elements that will be in the FilteredList
        filteredData.setPredicate(row -> {
            LocalDateTime rowDate = LocalDateTime.parse(row.getStartTime().getValue(), df);
            return rowDate.isAfter(now) && rowDate.isBefore(nowPlus1);
        });
        tableMainCurrentSchedule.setItems(filteredData);
    }

    //Calendar by week radio
    @FXML
    private void weeklyViewAction(ActionEvent event) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nowPlus7 = now.plusDays(7);
        FilteredList<Appointment> filteredData = new FilteredList<>(apptList);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm:ss.S");
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

    public static void upcomingAppointmentAlert() throws SQLException, IOException {
        LocalDateTime ldtPlusFifteenCurrent = now().plusMinutes(15);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm:ss.S");
        String formattedDate = (ldtPlusFifteenCurrent).format(dtf);
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss.S");
        String tcformattedDate = sdf.format(AppointmentManagerController.timeConversions(formattedDate));
        
        String appointmentAlert = ("SELECT start, customerName  "
                + "FROM appointment a, customer c "
                + "WHERE c.customerId = a.customerId "
                + "AND start BETWEEN now() AND date_add(now(), interval 15 minute) ");

        ResultSet rs = executeQuery(appointmentAlert);
        boolean hasAppt = false;
        StringBuffer sb = new StringBuffer("You have appointment(s) within 15 minutes: \n");
        
        
        
        if (rs.next()) {
            hasAppt = true;
            //(2) = customer name and (1) = date and time utcToLocal
            LocalDateTime ldt = LocalDateTime.parse(rs.getString(1), dtf);
            
            sb.append(rs.getString(2) + " at " + AppointmentManagerController.utcToLocal(ldt) + "\n");
        }
        if (hasAppt) {
            Alert a = new Alert(Alert.AlertType.INFORMATION, sb.toString(), ButtonType.OK);
            a.showAndWait();
        }
    }

    private void tableData(Boolean updateRefresh) throws SQLException, IOException {
        if (updateRefresh) {
            apptList = databaseAppointments();
        }
        columnCustNameCurrentSchedule.setCellValueFactory(cellData -> cellData.getValue().getCustomerName());
        columnCreatedByCurrentSchedule.setCellValueFactory(cellData -> cellData.getValue().getCreatedBy());
        columnTypeCurrentSchedule.setCellValueFactory(cellData -> cellData.getValue().getTitle());
        columnLocationCurrentSchedule.setCellValueFactory(cellData -> cellData.getValue().getLocation());
        columnStartCurrentSchedule.setCellValueFactory(cellData -> cellData.getValue().getStartTime());
        columnEndCurrentSchedule.setCellValueFactory(cellData -> cellData.getValue().getEndTime());
        
//        tableMainCurrentSchedule.refresh();
        tableMainCurrentSchedule.setItems(apptList);
    }
}
