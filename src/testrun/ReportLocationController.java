package testrun;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class ReportLocationController implements Initializable {

    @FXML
    private Label lblLocationSchedule;
    @FXML
    private ComboBox<String> comboLocation;
    @FXML
    private TableView<Appointment> tableLocationReport;
    @FXML
    private TableColumn<Appointment, String> columnCustName;
    @FXML
    private TableColumn<Appointment, String> columnType;
    @FXML
    private TableColumn<Appointment, String> columnConsultant;
    @FXML
    private TableColumn<Appointment, String> columnStart;
    @FXML
    private TableColumn<Appointment, String> columnEnd;
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
        columnConsultant.setCellValueFactory(cellData -> cellData.getValue().getCreatedBy());
        columnType.setCellValueFactory(cellData -> cellData.getValue().getTitle());
        columnStart.setCellValueFactory(cellData -> cellData.getValue().getStartTime());
        columnEnd.setCellValueFactory(cellData -> cellData.getValue().getEndTime());

        try {
            tableLocationReport.setItems(SQLConnectorData.databaseAppointments());

        } catch (SQLException ex) {
            Logger.getLogger(ReportLocationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReportLocationController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Populate ComboBox Location
        String locationApts = "SELECT DISTINCT location FROM appointment ORDER BY location ASC;";

        try {
            ResultSet rs = executeQuery(locationApts);
            {
                while (rs.next()) {
                    comboLocation.getItems().add(rs.getString("location"));
                }
                this.comboLocation.getSelectionModel().selectFirst();
            }
        } catch (SQLException ex) {
        } catch (IOException ex) {
            Logger.getLogger(ReportLocationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void locationAction(ActionEvent event) {
        String cmbLoc = comboLocation.getValue();
        
        ObservableList<Appointment> locationList = getApptList();
        FilteredList<Appointment> filteredData = new FilteredList<>(locationList);
        filteredData.setPredicate(p -> p.getLocation().getValue().contains(cmbLoc));
        tableLocationReport.setItems(filteredData);
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
