package helper;

public class JavascriptExecuteHelper {

    public void executeJavascript( String script) {
        ((org.openqa.selenium.JavascriptExecutor) ( DriverHelper.driver) ).executeScript(script);
    }

    public void executeJavascript( String script, Object... objects) {
        ((org.openqa.selenium.JavascriptExecutor) DriverHelper.driver).executeScript(script, objects);

    }

}
