package ch.makery.address.model;

public enum TypeEnvironment {
    AUTHOR("4502"),
    PUBLISH("4503");

    private String port;

    TypeEnvironment(String port) {
        this.port = port;
    }

    public String getPort() {return port;}
}
