package ch.makery.address.view;

import ch.makery.address.MainApp;
import ch.makery.address.model.ServerData;
import ch.makery.address.model.TypeDeployRadioButton;
import ch.makery.address.model.TypeEnvironment;
import ch.makery.address.util.ExecuteShellComand;
import ch.makery.address.util.SimpleParsing;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

public class ServerTabOverviewController {

    @FXML
    private ListView<ServerData> listServer;

    @FXML
    private TextField serverName;

    @FXML
    private TextField serverPath;

    @FXML
    private TextField portAuthor;

    @FXML
    private Button path;

    @FXML
    private TextField portPublish;

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
    private TextField otherCommands;

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
        serverPath.textProperty().addListener((observable, oldValue, newValue) -> {
            listServer.getSelectionModel().getSelectedItem().setProjectPath(newValue);
            showSeverDetails(listServer.getSelectionModel().getSelectedItem());
        });
        portAuthor.textProperty().addListener((observable, oldValue, newValue) -> {
            listServer.getSelectionModel().getSelectedItem().setPortAuthor(Integer.valueOf(newValue));
            showSeverDetails(listServer.getSelectionModel().getSelectedItem());
        });
        portPublish.textProperty().addListener((observable, oldValue, newValue) -> {
            listServer.getSelectionModel().getSelectedItem().setPortPublish(Integer.valueOf(newValue));
            showSeverDetails(listServer.getSelectionModel().getSelectedItem());
        });
        otherCommands.textProperty().addListener((observable, oldValue, newValue) -> {
            listServer.getSelectionModel().getSelectedItem().setOtherCommands(newValue);
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
        command.setMaxWidth(400);
        command.setWrapText(true);
        typeEnvironment.setItems(FXCollections.observableArrayList(TypeEnvironment.values()));
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
        server.setPortAuthor(4502);
        server.setPortPublish(4503);
        server.setBundleName("");
        server.setOtherCommands("--offline");
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
    private void handleRemoveServer() {
        mainApp.getServerDataList().remove(listServer.getSelectionModel().getSelectedItem());
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
            portAuthor.setText(Integer.toString(server.getPortAuthor()));
            portPublish.setText(Integer.toString(server.getPortPublish()));
            Toggle elem = typeDeploy.getToggles()
                    .stream()
                    .filter(el -> server.getTypeDeployRadioButton() != null && el.getUserData().toString().equals(server.getTypeDeployRadioButton().name()))
                    .findAny()
                    .orElse(null);
            typeDeploy.selectToggle(elem);
            typeEnvironment.setValue(server.getTypeEnvironment());
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
            otherCommands.setText(server.getOtherCommands());
            command.setText(createCommand(server));
        }
    }

    private String createCommand(ServerData server) {
        server.setCommand("");
        String com = "mvn clean install -T 6 -P";
        String skipTests = "-Dmaven.test.skip=";
        String port = "-Dsling.port=";
        String installPackageCom = "installPackage";
        String installLocalCom = "installLocal";
        String otherCommand = "";
        String installBundle = "installBundle";
        StringBuffer command = new StringBuffer(com);
        switch (server.getTypeDeployRadioButton()) {
            case FULL: {
                if (server.isInstallLocal() && server.isInstallPackage()) {
                    command.append(installPackageCom).append(",").append(installLocalCom).append(" ");
                    break;
                }
                if (server.isInstallLocal()) {
                    command.append(installLocalCom).append(" ");
                    break;
                }
                if (server.isInstallPackage()) {
                    command.append(installPackageCom).append(" ");
                    break;
                }

                break;
            }
            case CONTENT: {
                command.append(installPackageCom).append(" ");
                break;
            }
            case BUNDLES: {
                command.append(installBundle).append(" ");
                break;
            }
        }
        if (server.isSkipTest()) {
            command.append(skipTests).append(true).append(" ");
        }
        if (server.getTypeEnvironment().equals(TypeEnvironment.AUTHOR)) {
            command.append(port).append(server.getPortAuthor()).append(" ");
        } else {
            command.append(port).append(server.getPortPublish()).append(" ");
        }
        if (server.getOtherCommands() != null) {
            otherCommand = server.getOtherCommands();
        }
        command.append(otherCommand).append(" ");
        listServer.getSelectionModel().getSelectedItem().setCommand(command.toString());
        return command.toString();
    }

    @FXML
    private void changeTypeEnvironment() {
        TypeEnvironment value = typeEnvironment.getValue();
        listServer.getSelectionModel().getSelectedItem().setTypeEnvironment(value);
        showSeverDetails(listServer.getSelectionModel().getSelectedItem());
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

    @FXML
    private void handleDeploy() {
        ServerData selectedItem = listServer.getSelectionModel().getSelectedItem();
        String commandCom = selectedItem.getCommand();
        String projectPath = selectedItem.getProjectPath();
        if (projectPath.endsWith("\\") || projectPath.endsWith("/")){
            projectPath = projectPath.substring(0, projectPath.length() - 1);
        }
        if (typeDeploy.getSelectedToggle().getUserData().toString().equals(TypeDeployRadioButton.BUNDLES.name())) {
            projectPath = projectPath + "\\bundles\\" + selectedItem.getBundleName();
        }
        if (typeDeploy.getSelectedToggle().getUserData().toString().equals(TypeDeployRadioButton.CONTENT.name())) {
            projectPath = projectPath + "\\content\\";
        }
        new ExecuteShellComand().executeCommand(commandCom, projectPath);
    }

}