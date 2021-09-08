package helper;

import org.openqa.selenium.ScreenOrientation;

import static org.junit.Assert.fail;

public class RotateScreenHelper {

    public void getOrientation(String orientation) {
        if (DriverHelper.driver != null) {
            switch ( orientation ) {
                case "landscape":
                    DriverHelper.driver.rotate(ScreenOrientation.LANDSCAPE);
                    break;
                case "portrait":
                    DriverHelper.driver.rotate(ScreenOrientation.PORTRAIT);
                    break;
                default:
                    fail("Wrong orientation: " + orientation);
                    break;
            }

        }
    }
}
