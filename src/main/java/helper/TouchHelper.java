package helper;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.interactions.touch.TouchActions;

public class TouchHelper {

    public TouchAction getTouchAction() {
        return new TouchAction(DriverHelper.driver);
    }

}
