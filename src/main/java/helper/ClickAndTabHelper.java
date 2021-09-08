package helper;

import io.appium.java_client.touch.TapOptions;
import org.openqa.selenium.By;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofMillis;

public class ClickAndTabHelper {

    public void clickElement(By by){
        new PresenceWaitHelper().waitUntilPresence(by);
        new ClickableWaitHelper().waitUntilClickable(by);
        DriverHelper.driver.findElement(by).click();
    }

    public void tabElement(By by){
        new PresenceWaitHelper().waitUntilPresence(by);
        new ClickableWaitHelper().waitUntilClickable(by);
        new TouchHelper().getTouchAction()
                .tap(TapOptions
                        .tapOptions()
                        .withElement(element(DriverHelper.driver.findElement(by))))
                .waitAction(waitOptions(ofMillis(250))).perform();
    }
}
