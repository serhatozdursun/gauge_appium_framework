package helper;

import Exceptions.LocatorNotFoundException;
import Exceptions.UndefinedLocatorType;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;

public class ElementLocationHelper {

    public Point getElementLocation(String locatorKey) throws LocatorNotFoundException, UndefinedLocatorType {
        By locator = ElementHelper.getElementBy(locatorKey);
        new PresenceWaitHelper().waitUntilPresence(locator);
        return DriverHelper.driver.findElement(locator).getLocation();
    }

    public Point getElementsLocation(String locatorKey, Integer index) throws LocatorNotFoundException, UndefinedLocatorType {
        By locator = ElementHelper.getElementBy(locatorKey);
        new PresenceWaitHelper().waitUntilPresence(locator);
        return DriverHelper.driver.findElements(locator).get(index).getLocation();
    }
}
