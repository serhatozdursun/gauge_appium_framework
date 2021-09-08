package helper;

import io.appium.java_client.MobileElement;


import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;
public class DragDropHelper {

    public void dragDrop(MobileElement dragElement, MobileElement dropElement,int duration) {

        TouchHelper touchHelper= new TouchHelper();
        touchHelper.getTouchAction().longPress(longPressOptions().withElement(element(dragElement)).withDuration(ofSeconds(duration))).moveTo(element(dropElement)).release().perform();

    }
    public void dragDrop(MobileElement dragElement, MobileElement dropElement)  {

        TouchHelper touchHelper= new TouchHelper();
        touchHelper.getTouchAction()
                .longPress(longPressOptions().withElement(element(dragElement)).withDuration(ofSeconds(3)))
                .moveTo(element(dropElement))
                .release()
                .perform();


    }
}
