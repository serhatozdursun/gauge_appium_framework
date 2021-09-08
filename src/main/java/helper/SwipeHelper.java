package helper;

import enums.Direction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Point;

import java.time.Duration;
import java.util.HashMap;

public class SwipeHelper {
    private static final Logger log = LogManager.getLogger(ElementHelper.class);
    private PointOption pointOptionStart, pointOptionEnd;

    public static void swipe(String swipePercent, String direction, String id, String speed) {
        var param = new HashMap<String, Object>();
        param.put("percent", Double.parseDouble(swipePercent));
        param.put("elementId", id);
        param.put("direction", direction);
        param.put("speed", Integer.parseInt(speed));
        String script = "mobile: swipeGesture";
        JavascriptExecuteHelper j = new JavascriptExecuteHelper();
        j.executeJavascript(script, param);
    }

    public void swipe(String direction, String getId, String duration) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("element", getId);
        param.put("duration", duration);
        param.put("direction", direction);
        String script = "mobile:swipe";
        JavascriptExecuteHelper j = new JavascriptExecuteHelper();
        j.executeJavascript(script, param);
    }

    public void swipe(Direction direction) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        switch ( direction ) {
            case DOWN:
                param.put("direction", "down");
                break;
            case UP:
                param.put("direction", "up");
                break;
            case LEFT:
                param.put("direction", "left");
                break;
            case RIGHT:
                param.put("direction", "right");
                break;
            default:
                throw new IllegalArgumentException("mobileSwipeScreenIOS(): dir: '" + direction + "' NOT supported");
        }
        try {
            new JavascriptExecuteHelper().executeJavascript("mobile:swipe", param);
        } catch (Exception e) {
            log.warn("mobileSwipeScreenIOS(): FAILED" + e.getMessage());
        }
    }

    public void swipe(int width, int height, Direction dir) {
        log.info("swipeScreen(): dir: '" + dir + "'"); // always log your actions
        final int ANIMATION_TIME = 200;
        final int PRESS_TIME = 1000;
        setStartAndEndPoint(dir, height, width);
        try {
            new TouchAction(DriverHelper.driver)
                    .press(pointOptionStart)
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                    .moveTo(pointOptionEnd)
                    .release().perform();
        } catch (Exception e) {
            System.err.println("swipeScreen(): TouchAction FAILED\n" + e.getMessage());
            return;
        }

        try {
            Thread.sleep(ANIMATION_TIME);
        } catch (InterruptedException e) {
        }

    }

    private void setStartAndEndPoint(Direction dir, int height, int width) {
        int endX, endY;
        Point pointStart, pointEnd;

        switch ( dir ) {
            case DOWN: // center of footer
                endY = 0;
                pointStart = new Point(width, height);
                pointEnd = new Point(width, endY);
                break;
            case UP:
                endY = DriverHelper.driver.manage().window().getSize().height;
                pointStart = new Point(width, height);
                pointEnd = new Point(width, endY);
                break;
            case LEFT:
                endX = DriverHelper.driver.manage().window().getSize().width;
                pointEnd = new Point(endX, height);
                pointStart = new Point(width, height);
                break;
            case RIGHT:
                endX = 0;
                pointEnd = new Point(endX, height);
                pointStart = new Point(width, height);
                break;
            default:
                throw new IllegalArgumentException("swipeScreen(): dir: '" + dir.toString() + "' NOT supported");
        }
        pointOptionStart = PointOption.point(pointStart.x, pointStart.y);
        pointOptionEnd = PointOption.point(pointEnd.x, pointEnd.y);
    }

}

