package Exceptions;

public class LocatorNotFoundException extends Exception {
    public String message;

    public LocatorNotFoundException(String locatorValue) {
        String exceptionMessage = String.format("%s locator json dosyası içerisinde bulunamadı.",
                locatorValue);
        this.message = exceptionMessage;
    }

    public LocatorNotFoundException() {
        this.message = "Locator bulunamadı.";
    }

    @Override
    public String getMessage() {
        return message;
    }
}


