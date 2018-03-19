package ch.makery.address.model;

import javafx.beans.property.*;

import java.util.ArrayList;
import java.util.List;

public class ServerData {

    private StringProperty serverName;
    private StringProperty projectPath;
    private IntegerProperty portAuthor;
    private IntegerProperty portPublish;
    private TypeEnvironment typeEnvironment;
    private TypeDeployRadioButton typeDeployRadioButton;
    private BooleanProperty skipTest;
    private String bundleName;
    private BooleanProperty installPackage;
    private BooleanProperty installLocal;
    private StringProperty login;
    private StringProperty password;
    private StringProperty command;
    private StringProperty otherCommands;

    public ServerData() {
        this.serverName = new SimpleStringProperty();
        this.projectPath = new SimpleStringProperty();
        this.portAuthor = new SimpleIntegerProperty();
        this.portPublish = new SimpleIntegerProperty();
        this.skipTest = new SimpleBooleanProperty();
        this.installPackage = new SimpleBooleanProperty();
        this.installLocal = new SimpleBooleanProperty();
        this.login = new SimpleStringProperty();
        this.password = new SimpleStringProperty();
        this.command = new SimpleStringProperty();
        this.otherCommands = new SimpleStringProperty();
    }

    public String getOtherCommands() {
        return otherCommands.get();
    }

    public StringProperty otherCommandsProperty() {
        return otherCommands;
    }

    public void setOtherCommands(String otherCommands) {
        this.otherCommands.set(otherCommands);
    }

    public String getLogin() {
        return login.get();
    }

    public StringProperty loginProperty() {
        return login;
    }

    public void setLogin(String login) {
        this.login.set(login);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getCommand() {
        return command.get();
    }

    public StringProperty commandProperty() {
        return command;
    }

    public void setCommand(String command) {
        this.command.set(command);
    }

    public TypeDeployRadioButton getTypeDeployRadioButton() {
        return typeDeployRadioButton;
    }

    public void setTypeDeployRadioButton(TypeDeployRadioButton typeDeployRadioButton) {
        this.typeDeployRadioButton = typeDeployRadioButton;
    }

    public boolean isInstallPackage() {
        return installPackage.get();
    }

    public BooleanProperty installPackageProperty() {
        return installPackage;
    }

    public void setInstallPackage(boolean installPackage) {
        this.installPackage.set(installPackage);
    }

    public boolean isInstallLocal() {
        return installLocal.get();
    }

    public BooleanProperty installLocalProperty() {
        return installLocal;
    }

    public void setInstallLocal(boolean installLocal) {
        this.installLocal.set(installLocal);
    }


    public String getServerName() {
        return serverName.get();
    }

    public StringProperty serverNameProperty() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName.set(serverName);
    }

    public String getProjectPath() {
        return projectPath.get();
    }

    public StringProperty projectPathProperty() {
        return projectPath;
    }

    public void setProjectPath(String projectPath) {
        this.projectPath.set(projectPath);
    }


    public TypeEnvironment getTypeEnvironment() {
        return typeEnvironment;
    }

    public void setTypeEnvironment(TypeEnvironment typeEnvironment) {
        this.typeEnvironment = typeEnvironment;
    }

    public boolean isSkipTest() {
        return skipTest.get();
    }

    public int getPortAuthor() {
        return portAuthor.get();
    }

    public IntegerProperty portAuthorProperty() {
        return portAuthor;
    }

    public void setPortAuthor(int portAuthor) {
        this.portAuthor.set(portAuthor);
    }

    public int getPortPublish() {
        return portPublish.get();
    }

    public IntegerProperty portPublishProperty() {
        return portPublish;
    }

    public void setPortPublish(int portPublish) {
        this.portPublish.set(portPublish);
    }

    public BooleanProperty skipTestProperty() {
        return skipTest;
    }

    public void setSkipTest(boolean skipTest) {
        this.skipTest.set(skipTest);
    }

    public String getBundleName() {
        return bundleName;
    }

    public void setBundleName(String bundleName) {
        this.bundleName = bundleName;
    }

}
