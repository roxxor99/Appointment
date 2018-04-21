/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
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
    private TableView<Reports> tableTypeReport;
    @FXML
    private TableColumn<Reports, String> columnType;
    @FXML
    private TableColumn<Reports, Integer> columnTotal;
    @FXML
    private TableColumn<Reports, String> columnMonth;
    @FXML
    private TableColumn<Reports, String> columnYear;
    @FXML
    private Button butBack;
    
    private ObservableList<Reports> records = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //update view to match query results from below.
        columnType.setCellValueFactory(cellData -> cellData.getValue().title());
        columnTotal.setCellValueFactory(cellData -> cellData.getValue().total().asObject());
        columnMonth.setCellValueFactory(cellData -> cellData.getValue().date());
        //columnYear.setCellValueFactory(cellData -> cellData.getValue().getStartTime());

        
//This query retrives the exaCT DATA needed for the rubric 
        String type = "SELECT title, Count(appointmentId) AS total, Month(start) as theMonth, Year(start) AS theYear FROM appointment GROUP BY title, Year(start), Month(start) ORDER BY title, Year(start) ASC, Month(start) ASC;";
        
    }    

    @FXML
    private void typeAction(ActionEvent event) {
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
    private void updateAction(ActionEvent event) {
    }
    
}
