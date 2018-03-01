package ch.makery.address.view;

import ch.makery.address.MainApp;
import ch.makery.address.model.Person;
import ch.makery.address.model.ServerData;
import ch.makery.address.model.TypeEnvironment;
import ch.makery.address.util.DateUtil;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ServerTabOverviewController {

    @FXML
    private ListView<ServerData> listServer;

    @FXML
    private TextField serverPath;

    @FXML
    private TextField host;

    @FXML
    private Button path;

    @FXML
    private TextField port;

    @FXML
    private ComboBox<TypeEnvironment> typeEnvironment;

    @FXML
    private RadioButton full;

    @FXML
    private RadioButton content;

    @FXML
    private RadioButton bundle;

    @FXML
    private ComboBox<String> bundleNames;

    @FXML
    private RadioButton skipTest;

    @FXML
    private Button deploy;

    @FXML
    private Button saveChange;

    @FXML
    private CheckBox installPackage;

    @FXML
    private CheckBox installLocal;

    @FXML
    private Button addServer;

    @FXML
    private Button removeServer;

    private ServerData serverData;

    @FXML
    private ToggleGroup typeDeploy;

    private MainApp mainApp;

    public ServerTabOverviewController() {
    }

    @FXML
    private void initialize() {

        showSeverDetails(null);
        listServer.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    this.serverData = newValue;
                    showSeverDetails(newValue);
                });
//        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
//            public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
//
//                if (group.getSelectedToggle() != null) {
//
//                    System.out.println(group.getSelectedToggle().getUserData().toString());
//                    // Do something here with the userData of newly selected radioButton
//
//                }
//
//            }
//        });
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        listServer.setItems(mainApp.getServerDataList());
        listServer.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        listServer.getSelectionModel().selectFirst();
//todo NULL
        showSeverDetails(listServer.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void handleAddServer() {
        ServerData server = new ServerData();
        server.setProjectPath("");
        server.setServerName("");
        server.setHost("");
        server.setPort(0);
        server.setBundleNames(new ArrayList<>());
        server.setFull(true);
        server.setInstallLocal(true);
        server.setInstallPackage(true);
        server.setTypeEnvironment(TypeEnvironment.AUTHOR);
        mainApp.getServerDataList().add(server);
    }

    @FXML
    private void handleOpen() {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("AEM Projects");
        //todo message invalid firectory, set default directory, @NotNull
        File defaultDirectory = new File("c:/");
        chooser.setInitialDirectory(defaultDirectory);
        File selectedDirectory = chooser.showDialog(new Stage());
        selectedDirectory = selectedDirectory == null ? defaultDirectory : selectedDirectory;
//todo message invalid firectory
        this.serverData.setProjectPath(selectedDirectory.getPath());
        showSeverDetails(serverData);
    }


    private void showSeverDetails(ServerData server) {
        if (server != null) {
            serverPath.setText(server.getProjectPath());
            host.setText(server.getHost());
            port.setText(Integer.toString(server.getPort()));
            host.setText(server.getHost());
            full.setSelected(server.isFull());
            typeEnvironment.setValue(server.getTypeEnvironment());
            typeEnvironment.setItems(FXCollections.observableArrayList(TypeEnvironment.values()));
            bundleNames.setItems(FXCollections.observableArrayList(server.getBundleNames()));
            skipTest.setSelected(true);
            installPackage.setSelected(true);
            installLocal.setSelected(true);
        }
    }

    @FXML
    private void changeTypeEnvironment(){
        TypeEnvironment value = typeEnvironment.getValue();
        listServer.getSelectionModel().getSelectedItem().setTypeEnvironment(value);
    }

    @FXML
    private void changeGroupRadioButton(){
        RadioButton selectedToggle = (RadioButton) typeDeploy.getSelectedToggle();
        if(selectedToggle.getId().equals("full")){

        }
        ObservableList<Toggle> toggles = typeDeploy.getToggles();

        listServer.getSelectionModel().getSelectedItem();
    }

}