
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
import static testrun.MainLandingController.apptList;
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
    private TableView<ReportsConsultant> tableConsultantReport;
    @FXML
    private TableColumn<ReportsConsultant, String> columnCustName;
    @FXML
    private TableColumn<ReportsConsultant, String> columnType;
    @FXML
    private TableColumn<ReportsConsultant, String> columnLocation;
    @FXML
    private TableColumn<ReportsConsultant, String> columnStart;
    @FXML
    private TableColumn<ReportsConsultant, String> columnEnd;
    @FXML
    private Button butBack;
    @FXML
    private Button butUpdate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        columnCustName.setCellValueFactory(cellData -> cellData.getValue().getCustomerName());
//        columnCreatedByCurrentSchedule.setCellValueFactory(cellData -> cellData.getValue().getCreatedBy());
        columnType.setCellValueFactory(cellData -> cellData.getValue().getTitle());
        columnLocation.setCellValueFactory(cellData -> cellData.getValue().getLocation());
        columnStart.setCellValueFactory(cellData -> cellData.getValue().getStart());
        columnEnd.setCellValueFactory(cellData -> cellData.getValue().getEnd());
//        columnAppIdCurrentSchedule.setCellValueFactory(cellData -> cellData.getValue().getAppointmentId());
//        columnCusIdCurrentSchedule.setCellValueFactory(cellData -> cellData.getValue().getCustomerId());
        
//        tableConsultantReport.setItems(SQLConnectorData.databaseReports());
//        apptList = MainLandingController.getApptList();
//        tableConsultantReport.setItems(apptList);

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
    private void consultantAction(ActionEvent event) {
        //dropdownbox 
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

    @FXML
    private void updateAction(ActionEvent event) throws SQLException, IOException {
        String sqlConsultant = ("SELECT a.location, a.title, a.start, a.end, c.customerName "
                + "FROM appointment a " 
                + "INNER JOIN customer c ON c.customerId = a.customerId "
                + "WHERE a.createdBy = ? "
                + "ORDER BY a.start ASC");
//        String sqlConsultant = "SELECT DISTINCT appointmentId FROM appointment (title, location, start, end, createdBy,);";

        PreparedStatement statement = SQLConnector.getCon().prepareStatement(sqlConsultant);
        //statement.setString(1, ?);
        ResultSet rs = executeQuery(sqlConsultant);
        ObservableList<ReportsConsultant> reportData = FXCollections.observableArrayList();
        
        while (rs.next()) {
            reportData.add(
                    new ReportsConsultant(
                            rs.getString("customerName"),
                            rs.getString("title"),
                            rs.getString("location"),
                            rs.getString("start"),
                            rs.getString("end")
                    //                        zStart.toLocalDate().toString(),
                    //                        zStart.toLocalTime().toString(),
                    //                        zEnd.toLocalTime().toString(),
                    //rs.getString("location")
                    )
            );

        }
        this.tableConsultantReport.setItems(reportData);

    }

}
