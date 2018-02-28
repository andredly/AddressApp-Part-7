package ch.makery.address.model;

public enum TypeEnvironment {
    AUTHOR("author"),
    PUBLISH("publish");

    private String type;

    TypeEnvironment(String type) {
        this.type = type;
    }

    public String getType() {return type;}
}
