package testrun;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import javafx.collections.ObservableList;
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
    private TableColumn<Appointment, String> columnDescriptionCurrentSchedule;
    @FXML
    private TableColumn<Appointment, String> columnTitleCurrentSchedule;
    @FXML
    private TableColumn<Appointment, String> columnLocationCurrentSchedule;
//    @FXML
//    private TableColumn<Appointment, String> columnDateCurrentSchedule;
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
    private Button butExit;
    static ObservableList<Appointment> apptList;
    private boolean isMonth;
    
    /**
     * Initializes the controller class.
     */
    //@Override
    public void initialize() throws SQLException, IOException {
        // Assign data to columns in =>tableMainCurrentSchedule
        columnCustNameCurrentSchedule.setCellValueFactory(cellData -> cellData.getValue().getCustomerName());
        columnDescriptionCurrentSchedule.setCellValueFactory(cellData -> cellData.getValue().getCreatedBy());
        columnTitleCurrentSchedule.setCellValueFactory(cellData -> cellData.getValue().getTitle());
        columnLocationCurrentSchedule.setCellValueFactory(cellData -> cellData.getValue().getLocation());
        columnStartCurrentSchedule.setCellValueFactory(cellData -> cellData.getValue().getStartTime());
        columnEndCurrentSchedule.setCellValueFactory(cellData -> cellData.getValue().getEndTime());
        apptList = SQLConnectorData.databaseAppointments();
        tableMainCurrentSchedule.setItems(apptList);
    }
//    private void populateAptTable(){
//        
//    }
    
    
    //Calendar by month radio
    @FXML
    private void monthlyViewAction(ActionEvent event) {
        //something like this?
        isMonth = true;
//        handleAppointmentView();
    }
    
    //Calendar by week radio
    @FXML
    private void weeklyViewAction(ActionEvent event) {
        isMonth = false;
//        handleAppointmentView();
        
    }

        //Weekly tableview code?
//        LocalDate now = LocalDate.now();
//        LocalDate nowPlus7 = now.plusDays(7);
//        FilteredList<Appointment> filteredData = new FilteredList<>(allAppointments);
//        filteredData.setPredicate(row -> {
//
//            LocalDate rowDate = LocalDate.parse(row.getDate().getValue().toString());
//
//            return rowDate.isAfter(now) && rowDate.isBefore(nowPlus7);
//        });
//        tableCurrentSchedule.setItems(filteredData);
    
    
    //need somthing for tableview switch might be better to use tab instead of radio buttons.
//    private void handleAppointmentView() {
//        Parent main = null;
//        try {
//            main = FXMLLoader.load(getClass().getResource(""));
//            Scene scene = new Scene(main);
//
//            Stage stage = C195SceneChangeExample.getStage();
//            
//            stage.setScene(scene);
//
//            stage.show();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
    
    
    
    
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


// Moves calendar to current date
//    @FXML
//    private void goToCurrentDate(boolean isMonthView) {
//        // Check if calendar is currently in monthly view
//        if (isMonthView) {
//            // Remove current calendarView
//            mainScreenGrid.getChildren().remove(monthlyCalendarView);
//            // Get current year-month
//            YearMonth currentYearMonth = YearMonth.now();
//            // Create and set new calendarView with current year-month
//            monthlyCalendar = new MonthlyCalendarView(currentYearMonth);
//            monthlyCalendarView = monthlyCalendar.getView();
//            mainScreenGrid.add(monthlyCalendarView, 0, 0);
//        }
//        // If calendar is currently in weekly view
//        else {
//            // Remove current calendarView
//            mainScreenGrid.getChildren().remove(weeklyCalendarView);
//            // Get current date
//            LocalDate currentLocalDate = LocalDate.now();
//            // Create and set new calendarView with current date
//            weeklyCalendar = new WeeklyCalendarView(currentLocalDate);
//            weeklyCalendarView = weeklyCalendar.getView();
//            mainScreenGrid.add(weeklyCalendarView, 0, 0);
//        }
//    }


// Switch calendar between monthly and weekly view
//    @FXML
//    private void toggleCalendarView(ActionEvent event) {
//        // Check if calendar is currently in monthly view
//        if (monthlyView) {
//            // Remove current calendarView
//            mainScreenGrid.getChildren().remove(monthlyCalendarView);
//            // Get calendar's current year-month
//            YearMonth currentYearMonth = monthlyCalendar.getCurrentYearMonth();
//            // Convert year-month to first day of same month
//            LocalDate currentLocalDate = LocalDate.of(currentYearMonth.getYear(), currentYearMonth.getMonth(), 1);
//            // Create and set new calendarView with first day of month
//            weeklyCalendar = new WeeklyCalendarView(currentLocalDate);
//            weeklyCalendarView = weeklyCalendar.getView();
//            mainScreenGrid.add(weeklyCalendarView, 0, 0);
//            // Change button to say "Switch to Monthly View"
//            btnMainScreenToggleView.setText(ResourceBundle.getBundle("MainScreen", Locale.getDefault()).getString("btnToggleViewMonthly"));
//            // Set monthlyView to show calendar is currently in weekly view
//            monthlyView = false;
//        }
//        // If calendar is currently in weekly view
//        else {
//            // Remove current calendarView
//            mainScreenGrid.getChildren().remove(weeklyCalendarView);
//            // Get calendar's current date
//            LocalDate currentLocalDate = weeklyCalendar.getCurrentLocalDate();
//            // Convert date to year-month
//            YearMonth currentYearMonth = YearMonth.from(currentLocalDate);
//            // Create and set new calendarView with year-month
//            monthlyCalendar = new MonthlyCalendarView(currentYearMonth);
//            monthlyCalendarView = monthlyCalendar.getView();
//            mainScreenGrid.add(monthlyCalendarView, 0, 0);
//            // Change button to say "Switch to Weekly View"
//            btnMainScreenToggleView.setText(ResourceBundle.getBundle("MainScreen", Locale.getDefault()).getString("btnToggleViewWeekly"));
//            // Set monthlyView to show calendar is currently in monthly view
//            monthlyView = true;
//        }
//    }