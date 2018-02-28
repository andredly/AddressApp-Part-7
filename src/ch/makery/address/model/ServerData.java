package ch.makery.address.model;

import javafx.beans.property.*;

public class ServerData {

    private StringProperty serverName;
    private StringProperty projectPath;
    private StringProperty host;
    private IntegerProperty port;
    private StringProperty typeEnvironment;
    private BooleanProperty full;
    private BooleanProperty content;
    private BooleanProperty bundle;
    private BooleanProperty skipTest;
    private StringProperty bundleName;

    public ServerData() {
        this.serverName = new SimpleStringProperty();
        this.projectPath = new SimpleStringProperty();
        this.host = new SimpleStringProperty();
        this.port = new SimpleIntegerProperty();
        this.typeEnvironment = new SimpleStringProperty();
        this.full = new SimpleBooleanProperty();
        this.content = new SimpleBooleanProperty();
        this.bundle = new SimpleBooleanProperty();
        this.skipTest = new SimpleBooleanProperty();
        this.host = new SimpleStringProperty();
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

    public String getTypeEnvironment() {
        return typeEnvironment.get();
    }

    public StringProperty typeEnvironmentProperty() {
        return typeEnvironment;
    }

    public void setTypeEnvironment(String typeEnvironment) {
        this.typeEnvironment.set(typeEnvironment);
    }

    public boolean isFull() {
        return full.get();
    }

    public BooleanProperty fullProperty() {
        return full;
    }

    public void setFull(boolean full) {
        this.full.set(full);
    }

    public boolean isContent() {
        return content.get();
    }

    public BooleanProperty contentProperty() {
        return content;
    }

    public void setContent(boolean content) {
        this.content.set(content);
    }

    public boolean isBundle() {
        return bundle.get();
    }

    public BooleanProperty bundleProperty() {
        return bundle;
    }

    public void setBundle(boolean bundle) {
        this.bundle.set(bundle);
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

    public String getBundleName() {
        return bundleName.get();
    }

    public StringProperty bundleNameProperty() {
        return bundleName;
    }

    public void setBundleName(String bundleName) {
        this.bundleName.set(bundleName);
    }
}
