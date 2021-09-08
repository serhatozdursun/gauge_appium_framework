package Exceptions;

public class UndefinedLocatorType extends Exception {

    public String message;

    public UndefinedLocatorType(String locatorValue) {
        String exceptionMessage = String.format("%s Undefined locator type",
                locatorValue);
        this.message = exceptionMessage;
    }

    public UndefinedLocatorType() {
        this.message = "Undefined locator type";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
