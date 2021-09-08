package helper;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.HashMap;

public class ScrollHelper extends WaitHelper {

    private final Logger log = LogManager.getLogger(ScrollHelper.class);

    public void scrollToText(WebElement element) {


        getWebDriverWait().pollingEvery(Duration.ofMillis(1000))
                .ignoring(NoSuchElementException.class)
                .ignoring(TimeoutException.class)
                .ignoring(Exception.class)
                .until(ExpectedConditions.visibilityOf(element));
    }

    public void scrollWithElement(String Id, String direction, Integer percent, Integer speed) {

        try {

            var param = new HashMap<String, Object>();
            param.put("elementId", Id);
            param.put("direction", direction);
            param.put("percent", percent);
            param.put("speed", speed);
            JavascriptExecuteHelper j = new JavascriptExecuteHelper();
            j.executeJavascript("mobile: scrollGesture", param);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void scrollIOS(String direction, String type, String value) {

        try {
            var param = new HashMap<String, Object>();
            param.put(type, value);
            param.put("direction", direction);
            JavascriptExecuteHelper j = new JavascriptExecuteHelper();
            j.executeJavascript("mobile:scroll", param);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void androidScrollUntil(By firstBy, By secondBy, String percent, String speed, String direction) {
        ScrollHelper scrollHelper = new ScrollHelper();
        while (((AndroidDriver) DriverHelper.driver).findElements(secondBy).size() == 0) {
            MobileElement element = DriverHelper.driver.findElement(firstBy);
            String id = element.getId();
            scrollHelper.scrollWithElement(id, direction, Integer.parseInt(percent), Integer.parseInt(speed));
            try {
                if (!((AndroidDriver) DriverHelper.driver).findElement(secondBy).isDisplayed()) {
                    id = element.getId();
                    scrollHelper.scrollWithElement(id, direction, Integer.parseInt(percent), Integer.parseInt(speed));
                }
            } catch (NoSuchElementException e) {
                log.warn(e.getMessage());
            }
        }
    }

    public void iosScrollUntil(By firstBy, By secondBy, String duration, String direction) {
        SwipeHelper swipeHelper = new SwipeHelper();
        while (((IOSDriver) DriverHelper.driver).findElements(secondBy).size() == 0) {
            MobileElement element = DriverHelper.driver.findElement(firstBy);
            String id = element.getId();
            swipeHelper.swipe(direction, id, duration);
            try {
                if (!DriverHelper.driver.findElement(secondBy).isDisplayed()) {
                    id = element.getId();
                    swipeHelper.swipe(direction, id, duration);
                }
            } catch (NoSuchElementException e) {
                log.warn(e.getMessage());
            }
        }
    }

}
