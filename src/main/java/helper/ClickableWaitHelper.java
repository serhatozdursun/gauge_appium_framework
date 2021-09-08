package helper;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ClickableWaitHelper extends WaitHelper {


    public void waitUntilClickable(By by, int timeOut) {

        getWebDriverWait(timeOut).until(ExpectedConditions.elementToBeClickable(by));
    }

    public void waitUntilClickable(By by) {
        waitUntilClickable(by, DEFAULT_TIME_OUT);
    }
}
