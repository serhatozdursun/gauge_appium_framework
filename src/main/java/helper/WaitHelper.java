package helper;

import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitHelper {
    protected static final int DEFAULT_TIME_OUT = 30;
    protected static final int DEFAULT_SLEEP_IN_MILLIS = 1000;


    public WebDriverWait getWebDriverWait() {
        return getWebDriverWait(DEFAULT_TIME_OUT, DEFAULT_SLEEP_IN_MILLIS);
    }

    protected WebDriverWait getWebDriverWait(int timeout, int sleepInMillis) {
        return new WebDriverWait(DriverHelper.driver, timeout, sleepInMillis);
    }

    public WebDriverWait getWebDriverWait(int timeout) {
        return getWebDriverWait(timeout, DEFAULT_SLEEP_IN_MILLIS);
    }
}
