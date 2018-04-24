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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
import javafx.stage.Stage;
import static testrun.MainLandingController.getApptList;
import static testrun.SQLConnector.executeQuery;

/**
 * FXML Controller class
 *
 * @author Jed Gunderson
 */
public class ReportConsultantController implements Initializable {

    @FXML
    private Label lblConsultantSchedule;
    @FXML
    private ComboBox<String> comboConsultant;
    @FXML
    private TableView<Appointment> tableConsultantReport;
    @FXML
    private TableColumn<Appointment, String> columnCustName;
    @FXML
    private TableColumn<Appointment, String> columnType;
    @FXML
    private TableColumn<Appointment, String> columnLocation;
    @FXML
    private TableColumn<Appointment, String> columnStart;
    @FXML
    private TableColumn<Appointment, String> columnEnd;
    @FXML
    private Button butBack;
    @FXML
    private Button butUpdate;
//    private final ObservableList<String> consultants = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        columnAppIdCurrentSchedule.setCellValueFactory(cellData -> cellData.getValue().getAppointmentId());
//        columnCusIdCurrentSchedule.setCellValueFactory(cellData -> cellData.getValue().getCustomerId());
        columnCustName.setCellValueFactory(cellData -> cellData.getValue().getCustomerName());
        columnType.setCellValueFactory(cellData -> cellData.getValue().getTitle());
        columnLocation.setCellValueFactory(cellData -> cellData.getValue().getLocation());
        columnStart.setCellValueFactory(cellData -> cellData.getValue().getStartTime());
        columnEnd.setCellValueFactory(cellData -> cellData.getValue().getEndTime());
        try {
            tableConsultantReport.setItems(SQLConnectorData.databaseAppointments());

        } catch (SQLException ex) {
            Logger.getLogger(ReportConsultantController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReportConsultantController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Populate ComboBox Consultant
        String consultantApts = "SELECT DISTINCT createdBy FROM appointment ORDER BY createdBy ASC;";

        try {
            ResultSet rs = executeQuery(consultantApts);
            {
                while (rs.next()) {
                    comboConsultant.getItems().add(rs.getString("createdBy"));
                }
                this.comboConsultant.getSelectionModel().selectFirst();
            }
        } catch (SQLException ex) {
        } catch (IOException ex) {
            Logger.getLogger(ReportConsultantController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void consultantAction(ActionEvent event) throws SQLException {
//        FilteredList<Appointment> aptFilList = new FilteredList<>();
        String cmbCon = comboConsultant.getValue();
        
        ObservableList<Appointment> appointmentList = getApptList();
        FilteredList<Appointment> filteredData = new FilteredList<>(appointmentList);
        filteredData.setPredicate(p -> p.getCreatedBy().getValue().contains(cmbCon));
        tableConsultantReport.setItems(filteredData);

    }
                      
    @FXML
    private void backAction(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirm Back Navigation");
        alert.setContentText("Are you sure you would like to return?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            Stage stage;
            Parent root;
            stage = (Stage) butBack.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("ReportInfo.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
}
