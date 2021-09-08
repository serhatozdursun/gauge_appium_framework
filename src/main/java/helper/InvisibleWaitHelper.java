package helper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class InvisibleWaitHelper extends WaitHelper {


    public boolean isInvisible(By by, int timeout) {
        return getWebDriverWait(timeout).until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public boolean isInvisible(By by) {
        return isInvisible(by, DEFAULT_TIME_OUT);
    }

    public boolean invisibilityOfAllElements(By by, int timeout) {
        return getWebDriverWait(timeout).until(
                ExpectedConditions.invisibilityOfAllElements(((WebDriver) DriverHelper.driver).findElements(by)));
    }
    public boolean invisibilityOfAllElements(By by) {
        return invisibilityOfAllElements(by, DEFAULT_TIME_OUT);
    }
}
