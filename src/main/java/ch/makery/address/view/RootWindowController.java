package ch.makery.address.view;

import ch.makery.address.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

/**
 * The controller for the root layout. The root layout provides the basic
 * application layout containing a menu bar and space where other JavaFX
 * elements can be placed.
 * 
 * @author Marco Jakob
 */
public class RootWindowController {

    // Reference to the main application
    private MainApp mainApp;

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }


    @FXML
    private void handleNew() {
        this.mainApp.getServerDataList().clear();
        this.mainApp.setServersFilePath((File)null);
    }

    @FXML
    private void handleOpen() throws IOException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", new String[]{"*.xml"});
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(this.mainApp.getPrimaryStage());
        if (file != null) {
            this.mainApp.loadServersDataFromFile(file);
        }

    }

    @FXML
    private void handleSave() throws IOException {
        File serverFile = this.mainApp.getServersFilePath();
        if (serverFile != null) {
            this.mainApp.saveServersDataToFile(serverFile);
        } else {
            this.handleSaveAs();
        }

    }

    @FXML
    private void handleSaveAs() throws IOException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", new String[]{"*.xml"});
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(this.mainApp.getPrimaryStage());
        if (file != null) {
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }

            this.mainApp.saveServersDataToFile(file);
        }

    }

    @FXML
    private void handleAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Копирование с FTP");
        alert.setHeaderText("О программе");
        alert.setContentText("Автор: Andrey");
        alert.showAndWait();
    }

    @FXML
    private void handleExit() {
        System.exit(0);
    }
}