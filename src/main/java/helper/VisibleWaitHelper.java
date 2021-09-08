package helper;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class VisibleWaitHelper extends WaitHelper {

    public void waitUntilVisible(By by, int timeout) {
        getWebDriverWait(timeout).until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void waitUntilVisible(By by) {
        waitUntilVisible(by, DEFAULT_TIME_OUT);
    }

    public void waitUntilVisibleAll(By by, int timeout) {
        getWebDriverWait(timeout).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }

    public void waitUntilVisibleAll(By by) {
        waitUntilVisibleAll(by, DEFAULT_TIME_OUT);
    }

}
