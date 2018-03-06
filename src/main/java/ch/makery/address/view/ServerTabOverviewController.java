package ch.makery.address.view;

import ch.makery.address.MainApp;
import ch.makery.address.model.ServerData;
import ch.makery.address.model.TypeDeployRadioButton;
import ch.makery.address.model.TypeEnvironment;
import ch.makery.address.util.SimpleParsing;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class ServerTabOverviewController {

    @FXML
    private ListView<ServerData> listServer;

    @FXML
    private TextField serverName;

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
    private RadioButton bundles;

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

    @FXML
    private TextField login;

    @FXML
    private TextField password;

    @FXML
    private Label command;

    @FXML
    private ToggleGroup typeDeploy;

    private ServerData serverData;

    private MainApp mainApp;

    public ServerTabOverviewController() {
    }

    @FXML
    private void initialize() {
        showSeverDetails(null);
        listServer.setEditable(true);

        listServer.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    this.serverData = newValue;
                    showSeverDetails(newValue);
                });
        installPackage.selectedProperty().addListener((observable, oldValue, newValue) -> {
            listServer.getSelectionModel().getSelectedItem().setInstallPackage(newValue);
            showSeverDetails(listServer.getSelectionModel().getSelectedItem());
        });
        installLocal.selectedProperty().addListener((observable, oldValue, newValue) -> {
            listServer.getSelectionModel().getSelectedItem().setInstallLocal(newValue);
            showSeverDetails(listServer.getSelectionModel().getSelectedItem());
        });
        skipTest.selectedProperty().addListener((observable, oldValue, newValue) -> {
            listServer.getSelectionModel().getSelectedItem().setSkipTest(newValue);
            showSeverDetails(listServer.getSelectionModel().getSelectedItem());
        });
        serverName.textProperty().addListener((observable, oldValue, newValue) -> {
            listServer.getSelectionModel().getSelectedItem().setServerName(newValue);
            listServer.setCellFactory(cell -> new ListCell<ServerData>() {
                @Override
                protected void updateItem(ServerData item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item != null) {
                        setText(item.getServerName());
                    }
                }
            });
            showSeverDetails(listServer.getSelectionModel().getSelectedItem());
        });
        login.textProperty().addListener((observable, oldValue, newValue) -> {
            listServer.getSelectionModel().getSelectedItem().setLogin(newValue);
            showSeverDetails(listServer.getSelectionModel().getSelectedItem());
        });
        password.textProperty().addListener((observable, oldValue, newValue) -> {
            listServer.getSelectionModel().getSelectedItem().setPassword(newValue);
            showSeverDetails(listServer.getSelectionModel().getSelectedItem());
        });
        command.textProperty().addListener((observable, oldValue, newValue) -> {
            listServer.getSelectionModel().getSelectedItem().setCommand(newValue);
            showSeverDetails(listServer.getSelectionModel().getSelectedItem());
        });
        full.setUserData(TypeDeployRadioButton.FULL);
        content.setUserData(TypeDeployRadioButton.CONTENT);
        bundles.setUserData(TypeDeployRadioButton.BUNDLES);
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        listServer.setItems(mainApp.getServerDataList());
        listServer.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        listServer.getSelectionModel().selectFirst();
//todo NULL
//        showSeverDetails(listServer.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void handleAddServer() {
        ServerData server = new ServerData();
        server.setProjectPath("");
        server.setServerName("default");
        server.setHost("");
        server.setPort(0);
        server.setBundleName("");
        server.setTypeDeployRadioButton(TypeDeployRadioButton.FULL);
        server.setInstallLocal(true);
        server.setInstallPackage(true);
        server.setTypeEnvironment(TypeEnvironment.AUTHOR);
        server.setInstallPackage(true);
        server.setInstallLocal(true);
        server.setSkipTest(true);
        mainApp.getServerDataList().add(server);
    }

    @FXML
    private void handleOpen() {
//        FileChooser fileChooser = new FileChooser();
//        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
//                "XML files (*.xml)", "*.xml");
//        fileChooser.getExtensionFilters().add(extFilter);
//        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());
//        if (file != null) {
////            mainApp.loadPersonDataFromFile(file);
////            this.serverData.setProjectPath(file.getPath());
//            listServer.getSelectionModel().getSelectedItem().setProjectPath(file.getPath());
////            listServer.getSelectionModel().getSelectedItem().setBundleNames();
//            showSeverDetails(listServer.getSelectionModel().getSelectedItem());
//        }


        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("AEM Projects");
        //todo message invalid firectory, set default directory, @NotNull
//        File defaultDirectory = new File("");
//        chooser.setInitialDirectory(defaultDirectory);
        File selectedDirectory = chooser.showDialog(new Stage());
//        selectedDirectory = selectedDirectory == null ? defaultDirectory : selectedDirectory;
        //todo message invalid firectory
        listServer.getSelectionModel().getSelectedItem().setProjectPath(selectedDirectory.getPath());
        showSeverDetails(listServer.getSelectionModel().getSelectedItem());
    }


    private void showSeverDetails(ServerData server) {
        if (server != null) {
            serverName.setText(server.getServerName());
            serverPath.setText(server.getProjectPath());
            host.setText(server.getHost());
            port.setText(Integer.toString(server.getPort()));
            host.setText(server.getHost());
            Toggle elem = typeDeploy.getToggles()
                    .stream()
                    .filter(el -> server.getTypeDeployRadioButton() != null && el.getUserData().toString().equals(server.getTypeDeployRadioButton().name()))
                    .findAny()
                    .orElse(null);
            typeDeploy.selectToggle(elem);
            typeEnvironment.setValue(server.getTypeEnvironment());
            typeEnvironment.setItems(FXCollections.observableArrayList(TypeEnvironment.values()));
            bundleNames.setValue(server.getBundleName());
            bundleNames.setItems(FXCollections.observableArrayList(SimpleParsing.getListBundles(server.getProjectPath())));
            skipTest.setSelected(server.isSkipTest());
            installPackage.setSelected(server.isInstallPackage());
            installLocal.setSelected(server.isInstallLocal());
            if (elem.getUserData().toString().equals(TypeDeployRadioButton.FULL.name())) {
                installPackage.setDisable(false);
                installLocal.setDisable(false);
            } else {
                installPackage.setDisable(true);
                installLocal.setDisable(true);
            }
            if (bundles.isSelected()) {
                bundleNames.setDisable(false);
            } else {
                bundleNames.setDisable(true);
            }
            login.setText(server.getLogin());
            password.setText(server.getPassword());
            command.setText(server.getCommand());
        }
    }

    @FXML
    private void changeTypeEnvironment() {
        TypeEnvironment value = typeEnvironment.getValue();
        listServer.getSelectionModel().getSelectedItem().setTypeEnvironment(value);
    }

    @FXML
    private void changeGroupRadioButton() {
        RadioButton selectedToggle = (RadioButton) typeDeploy.getSelectedToggle();
        listServer.getSelectionModel().getSelectedItem().setTypeDeployRadioButton(TypeDeployRadioButton.fromString(selectedToggle.getId()));
        showSeverDetails(listServer.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void changeBundles() {
        String selectedItem = bundleNames.getSelectionModel().getSelectedItem();
        listServer.getSelectionModel().getSelectedItem().setBundleName(selectedItem);
        showSeverDetails(listServer.getSelectionModel().getSelectedItem());
    }
}