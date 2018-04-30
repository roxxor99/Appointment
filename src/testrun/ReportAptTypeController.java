
package testrun;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import static testrun.SQLConnector.executeQuery;

/**
 * FXML Controller class
 *
 * @author Jed Gunderson
 */
public class ReportAptTypeController implements Initializable {

    @FXML
    private Label lblTypeSchedule;
    @FXML
    private TableView<ReportAptType> tableTypeReport;
    @FXML
    private TableColumn<ReportAptType, String> columnType;
    @FXML
    private TableColumn<ReportAptType, Integer> columnTotal;
    @FXML
    private TableColumn<ReportAptType, String> columnMonth;
    @FXML
    private TableColumn<ReportAptType, String> columnYear;
    @FXML
    private Button butBack;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        columnType.setCellValueFactory(cellData -> cellData.getValue().getTitle());
        columnTotal.setCellValueFactory(cellData -> cellData.getValue().getTotal().asObject());
        columnMonth.setCellValueFactory(cellData -> cellData.getValue().getMonth());
        columnYear.setCellValueFactory(cellData -> cellData.getValue().getYear());
        
        try {
            tableTypeReport.setItems(databaseReportAptType());
        } catch (SQLException ex) {
            Logger.getLogger(ReportAptTypeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReportAptTypeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ObservableList<ReportAptType> databaseReportAptType() throws SQLException, IOException {
        ObservableList<ReportAptType> reportAptTypeList = FXCollections.observableArrayList();
        String typeApts = "SELECT DISTINCT title, Count(appointmentId) AS total, Month(start) as theMonth, Year(start) AS theYear "
                + "FROM appointment "
                + "GROUP BY title, Year(start), Month(start) ORDER BY title, Year(start) ASC, Month(start) ASC;";
        ResultSet rs = executeQuery(typeApts);

        while (rs.next()) {
            ReportAptType reportAptType = new ReportAptType();
            reportAptType.setTitle(rs.getString("title"));
            reportAptType.setTotal(rs.getInt("total"));
            reportAptType.setMonth(rs.getString("theMonth"));
            reportAptType.setYear(rs.getString("theYear"));
            reportAptTypeList.add(reportAptType);
        }
        return reportAptTypeList;
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
