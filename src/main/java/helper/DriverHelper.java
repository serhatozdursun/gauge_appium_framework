package helper;


import com.thoughtworks.gauge.AfterScenario;
import enums.OsType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class DriverHelper {
    public static AppiumDriver<MobileElement> driver;
    public static int IMPLICITLY_WAIT = 15;
    private final Logger log = LogManager.getLogger(DriverHelper.class);


    public void lunchDriver(HashMap<String, String> caps, String platformName) throws MalformedURLException {
        String localIp = caps.get("Ip No");
        String portNo = caps.get("Port No");

        DesiredCapabilities capabilities = setCapabilities(caps);

        URL remoteAddress = new URL(String.format("http://%s:%s/wd/hub", localIp, portNo));
        if (platformName.equalsIgnoreCase(OsType.ANDROID.name().toLowerCase())) {

            driver = new AndroidDriver<>(remoteAddress, capabilities);
        } else if (platformName.equalsIgnoreCase(OsType.IOS.name().toLowerCase())) {
            driver = new IOSDriver<>(remoteAddress, capabilities);
        }
        driver.manage().timeouts().implicitlyWait(IMPLICITLY_WAIT, TimeUnit.SECONDS);
    }

    private DesiredCapabilities setCapabilities(HashMap<String, String> caps) {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        for (var entry : caps.entrySet()) {
            if (caps.get("appPath") != null && !caps.get("appPath").equals("") && !caps.get("appPath").equalsIgnoreCase("null")) {
                File app = new File(caps.get("appPath"));
                capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
            } else {
                capabilities.setCapability(entry.getKey(), entry.getValue());
            }
        }

        for (Map.Entry<String, Object> entry : capabilities.asMap().entrySet()) {
            log.info("Key = {}  Value = {}", entry.getKey(), entry.getValue());
        }
        return capabilities;
    }

    @AfterScenario
    public void tearDown() {
        if (driver != null) {
            driver.closeApp();
            driver.quit();
            log.info("Driver has been closed");
        }
    }
}


