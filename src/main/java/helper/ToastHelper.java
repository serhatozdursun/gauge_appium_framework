package helper;

import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

import java.util.List;

public class ToastHelper {

    public MobileElement getToastElement() {
        return getToastElements().get(0);
    }

    public List<MobileElement> getToastElements() {
        return DriverHelper.driver.findElements(By.xpath("//android.widget.Toast[1]"));
    }
}
