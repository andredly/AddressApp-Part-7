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
    }

    public String getServerName() {
        return serverName.get();
    }

    public StringProperty serverNameProperty() {
        return serverName;
    }

    public StringProperty projectPathProperty() {
        return projectPath;
    }

    public String getHost() {
        return host.get();
    }

    public StringProperty hostProperty() {
        return host;
    }

    public int getPort() {
        return port.get();
    }

    public IntegerProperty portProperty() {
        return port;
    }

    public String getTypeEnvironment() {
        return typeEnvironment.get();
    }

    public StringProperty typeEnvironmentProperty() {
        return typeEnvironment;
    }

    public boolean isFull() {
        return full.get();
    }

    public BooleanProperty fullProperty() {
        return full;
    }

    public boolean isContent() {
        return content.get();
    }

    public BooleanProperty contentProperty() {
        return content;
    }

    public boolean isBundle() {
        return bundle.get();
    }

    public BooleanProperty bundleProperty() {
        return bundle;
    }

    public boolean isSkipTest() {
        return skipTest.get();
    }

    public BooleanProperty skipTestProperty() {
        return skipTest;
    }


    public String getProjectPath(){
        return projectPath.get();
    }

    public StringProperty getProjectPathProperty() {
        return projectPath;
    }

    public void setProjectPath(String projectPath) {
        this.projectPath.set(projectPath);
    }

}
