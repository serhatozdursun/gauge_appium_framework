package helper;

import io.appium.java_client.MobileElement;

import java.time.Duration;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;


public class LongPressHelper extends TouchHelper{

    public void longPress(MobileElement mobileElement, int duration) {
        TouchHelper touchHelper = new TouchHelper();

        touchHelper.getTouchAction()
                .longPress(
                        longPressOptions()
                        .withElement(element(mobileElement))
                        .withDuration(Duration.ofSeconds(duration)))
                .release().perform();
    }
}
