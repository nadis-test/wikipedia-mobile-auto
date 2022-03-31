package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.MyListsPageObject;
import lib.ui.android.AndroidMyListPageObject;
import lib.ui.ios.IOSMyListPageObject;
import lib.ui.ios.IOSNavigationUI;
import lib.ui.mobileweb.MWMyListsPageObject;
import lib.ui.mobileweb.MWNavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MyListPageObjectFactory {
    public static MyListsPageObject get(RemoteWebDriver driver){
        if (Platform.getInstance().isAndroid()) {
            return new AndroidMyListPageObject(driver);
        } else if (Platform.getInstance().isIOS()){
            return new IOSMyListPageObject(driver);
        } else {
            return new MWMyListsPageObject(driver);
        }
    }
}

