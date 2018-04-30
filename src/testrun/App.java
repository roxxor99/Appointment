package testrun;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Jed Gunderson
 */
public class App extends Application {

    private Stage stage;

    public static void main(String[] args) {
        launch(args);
    }
    
    //If folder does not exist.. make it. 
    //Format used: date:Time(24hour): Message
    public static void writeToLog(String message) {
        String filename = "log.txt";
        String path = "C:\\C195_LogFile\\";
        File logFullPath = new File(path, filename);
        File logFilePath = new File(path);

        if (!logFilePath.exists()) {
            logFilePath.mkdir();
        }
        try (FileWriter logWriter = new FileWriter(logFullPath, true)) {
            logWriter.write(LocalDateTime.now() + ": " + message + System.lineSeparator());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * @param args the command line arguments
     */
}
