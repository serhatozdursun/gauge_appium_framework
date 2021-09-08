package enums;


public enum OsType {
    ANDROID("android"),
    IOS("ios");
    private final String value;

    OsType(String value) {
        this.value = value;
    }
}