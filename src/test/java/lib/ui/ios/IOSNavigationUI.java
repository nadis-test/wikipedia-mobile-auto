package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSNavigationUI extends NavigationUI {
    static {
        NAVIGATE_TO_MAIN_PAGE_BUTTON = "-ios class chain:**/XCUIElementTypeStaticText[`label == 'Cancel'`]";
        SAVED_LISTS_BUTTON = "id:Saved";
    }

    public IOSNavigationUI(RemoteWebDriver driver){
        super(driver);
    }
}
