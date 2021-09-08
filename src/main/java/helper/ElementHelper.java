package helper;

import Exceptions.LocatorNotFoundException;
import Exceptions.UndefinedLocatorType;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import enums.LocatorType;
import io.appium.java_client.MobileBy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.fail;


public class ElementHelper {
    private static final String BASE_PATH = "src/test/resources/locators/";
    private static final String BASE_NAME = "Default";
    private static final String BASE_EXTENSION = ".json";
    private static final Logger log = LogManager.getLogger(ElementHelper.class);

    /**
     * Read json file
     *
     * @param featureName cucumber feature dosyası ismi
     * @param keyword     element keyword
     * @return JsonObject
     */
    private static JsonObject readJSON(String featureName, String keyword) throws IOException {
        Gson gson = new Gson();
        JsonElement jsonObject;
        JsonObject jsonElement;
        JsonObject foundElement = null;
        try (FileReader reader = featureSelector(BASE_PATH + featureName + BASE_EXTENSION)) {
            jsonObject = gson.fromJson(reader, JsonElement.class);
            jsonElement = jsonObject.getAsJsonObject();
            foundElement = jsonElement.get(clearTurkishCharsAndUpperCase(keyword)).getAsJsonObject();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            fail(e.getMessage());
        }
        return foundElement;
    }

    public static By getElementBy(String featureName, String keyword) throws LocatorNotFoundException, UndefinedLocatorType {
        Set<Map.Entry<String, JsonElement>> entries = null;
        try {
            entries = readJSON(featureName, keyword).entrySet();
        } catch (IOException e) {
            log.error(e.getMessage());
            log.error("{} is not found in {} file", keyword, featureName);
            fail(keyword + " is not found in " + featureName + " file");
        }
        By by = null;
        if (keyword.contains("$")) {
            by = getElementByNotJson(keyword);
        } else {
            for (Map.Entry<String, JsonElement> entry : entries) {
                log.info("BY GENERATE :{} - {}", entry.getKey(), entry.getValue());
                by = getBy(entry.getKey(), entry.getValue().getAsString());
            }
        }
        return by;
    }

    public static By getElementByNotJson(String elementFound) throws LocatorNotFoundException, UndefinedLocatorType {
        By by = null;
        if (elementFound.contains("$")) {
            String[] splitElement = elementFound.split("\\$");
            log.info("BY GENERATE :{}-{}", splitElement[0], splitElement[1]);
            by = getBy(splitElement[0], splitElement[1]);
        } else {
            log.error("{} does not contain the character. Please check keyword!", elementFound);
            fail(elementFound + "$ does not contain the character. Please check keyword!");
        }
        return by;
    }

    private static By getBy(String locatorType, String byValue, int... index) throws LocatorNotFoundException, UndefinedLocatorType {
        LocatorType byType;
        try {
            byType = LocatorType.valueOf(locatorType);
        } catch (IllegalArgumentException e) {
            throw new UndefinedLocatorType(locatorType);
        }

        switch ( byType ) {
            case ACCESSIBILITY_ID:
                return MobileBy.AccessibilityId(byValue);
            case CLASS_NAME:
                return MobileBy.className(byValue);
            case ID:
                return MobileBy.id(byValue);
            case NAME:
                return MobileBy.name(byValue);
            case XPATH:
                return MobileBy.xpath(byValue);
            case TEXT:
                return MobileBy.xpath("//*[@text='" + byValue + "']");
            case ANDROID_UI_AUTOMATOR:
                return MobileBy.AndroidUIAutomator(byValue);
            case LINK_TEXT:
                return By.linkText(byValue);
            case PREDICATE:
                return MobileBy.iOSNsPredicateString(byValue);
            case CLASS_CHAIN:
                return MobileBy.iOSClassChain(byValue);
            case UI_SCROLLABLE:
                return MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()" +
                        ".scrollable(true)" +
                        ".instance(0))" +
                        ".scrollIntoView(new UiSelector()" +
                        ".textContains(\"" + byValue + "\")" +
                        ".instance(" + index[0] + "))");
            case CONTAINS:
                return By.xpath("//*[" + "contains(@id,'" + byValue + "') or "
                        + "contains(@text,'" + byValue + "') or "
                        + "contains(@value,'" + byValue + "') or "
                        + "contains(@name,'" + byValue + "') or "
                        + "contains(@placeholder,'" + byValue + "') or "
                        + "contains(@class,'" + byValue + "') or "
                        + "contains(@href,'" + byValue + "')]");
            default:
                throw new LocatorNotFoundException(byValue);
        }
    }

    private static FileReader featureSelector(String path) {
        FileReader fileReader = null;
        if (!Files.exists(Paths.get(path))) {
            path = BASE_PATH + BASE_NAME + BASE_EXTENSION;
        }
        try {
            fileReader = new FileReader(path);
        } catch (FileNotFoundException e) {
            log.error(e.getMessage(), e);
            fail(e.getMessage());
        }
        return fileReader;
    }

    public static By getElementBy(String keyword) throws UndefinedLocatorType, LocatorNotFoundException {
        return getElementBy(BASE_NAME, keyword);
    }

    private static String clearTurkishCharsAndUpperCase(String str) {
        String returnStr = str;
        char[] turkishChars = new char[]{0x131, 0x130, 0xFC, 0xDC, 0xF6, 0xD6, 0x15F, 0x15E, 0xE7, 0xC7, 0x11F,
                0x11E};
        char[] englishChars = new char[]{'i', 'I', 'u', 'U', 'o', 'O', 's', 'S', 'c', 'C', 'g', 'G'};
        for (int i = 0; i < turkishChars.length; i++) {
            returnStr = returnStr.replaceAll(new String(new char[]{turkishChars[i]}),
                    new String(new char[]{englishChars[i]}));
        }
        return returnStr.toUpperCase(Locale.ENGLISH);
    }
}
