package enums;

public enum LocatorType {

    ID("id"),
    TEXT("Text"),
    XPATH("xpath"),
    NAME("name"),
    CLASS_NAME("className"),
    LINK_TEXT("linkText"),
    CONTAINS("contains"),
    ACCESSIBILITY_ID("Accessibility ID"),
    ANDROID_UI_AUTOMATOR("Android Ui Automator"),
    UI_SCROLLABLE("UiScrollable"),
    PREDICATE("Predicate"),
    CLASS_CHAIN("ClassChain");

    private String value;

    LocatorType(String loginInfoValue) {
        this.value = loginInfoValue;
    }

    public String getText() {
        return value;
    }
}
