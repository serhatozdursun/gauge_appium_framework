package helper;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.concurrent.TimeoutException;


public class PollingWaitHelper extends WaitHelper {

    public void pollingWait(By by, int timeout, int pollingDuration) {
        getWebDriverWait(timeout).pollingEvery(Duration.ofMillis(pollingDuration)).ignoring(NoSuchElementException.class)
                .ignoring(TimeoutException.class).ignoring(Exception.class).until(ExpectedConditions.visibilityOfElementLocated(by));

    }

    public void pollingWait(MobileElement mobileElement, int timeout, int pollingDuration) {
        getWebDriverWait(timeout).pollingEvery(Duration.ofMillis(pollingDuration)).ignoring(NoSuchElementException.class)
                .ignoring(TimeoutException.class).ignoring(Exception.class).until(ExpectedConditions.visibilityOf(mobileElement));
    }

    public void pollingWait(MobileDriver driver, By by, int pollingDuration) {
        getWebDriverWait().pollingEvery(Duration.ofMillis(pollingDuration)).ignoring(NoSuchElementException.class)
                .ignoring(TimeoutException.class).ignoring(Exception.class).until(ExpectedConditions.visibilityOfElementLocated(by));
    }
}
