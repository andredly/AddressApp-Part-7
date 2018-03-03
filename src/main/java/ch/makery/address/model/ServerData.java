package ch.makery.address.model;

import javafx.beans.property.*;

import java.util.ArrayList;
import java.util.List;

public class ServerData {

    private StringProperty serverName;
    private StringProperty projectPath;
    private StringProperty host;
    private IntegerProperty port;
    private TypeEnvironment typeEnvironment;
    private TypeDeployRadioButton typeDeployRadioButton;
    private BooleanProperty skipTest;
    private List<String> bundleNames;
    private BooleanProperty installPackage;
    private BooleanProperty installLocal;

    public ServerData() {
        this.serverName = new SimpleStringProperty();
        this.projectPath = new SimpleStringProperty();
        this.host = new SimpleStringProperty();
        this.port = new SimpleIntegerProperty();
        this.skipTest = new SimpleBooleanProperty();
        this.host = new SimpleStringProperty();
        this.bundleNames = new ArrayList<>();
        this.installPackage = new SimpleBooleanProperty();
        this.installLocal = new SimpleBooleanProperty();
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

    public String getHost() {
        return host.get();
    }

    public StringProperty hostProperty() {
        return host;
    }

    public void setHost(String host) {
        this.host.set(host);
    }

    public int getPort() {
        return port.get();
    }

    public IntegerProperty portProperty() {
        return port;
    }

    public void setPort(int port) {
        this.port.set(port);
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

    public BooleanProperty skipTestProperty() {
        return skipTest;
    }

    public void setSkipTest(boolean skipTest) {
        this.skipTest.set(skipTest);
    }

    public List<String> getBundleNames() {
        return bundleNames;
    }

    public void setBundleNames(List<String> bundleNames) {
        this.bundleNames = bundleNames;
    }

    @Override
    public String toString() {
        return host.get() + ":" + port.get();
    }
}
