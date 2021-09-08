package helper;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PresenceWaitHelper extends WaitHelper {

    public void waitUntilPresence(By by, int timeout) {
        getWebDriverWait(timeout).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public void waitUntilPresence(By by) {
        waitUntilPresence( by, DEFAULT_TIME_OUT);
    }

    public void waitUntilPresenceAll(By by, int timeout) {
        getWebDriverWait(timeout).until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    public void waitUntilPresenceAll(By by) {
        waitUntilPresenceAll(by, DEFAULT_TIME_OUT);
    }
}
