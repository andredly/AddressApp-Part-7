package ch.makery.address.model;

public enum TypeDeployRadioButton {
    FULL("full"),
    CONTENT("content"),
    BUNDLES("bundles");

    private String type;

    TypeDeployRadioButton(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static TypeDeployRadioButton fromString(String text) {
        for (TypeDeployRadioButton button : TypeDeployRadioButton.values()) {
            if (button.type.equalsIgnoreCase(text)) {
                return button;
            }
        }
        return null;
    }
}
