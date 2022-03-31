package lib.ui.mobileweb;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWNavigationUI extends NavigationUI {
    static {
        SAVED_LISTS_BUTTON = "css:a.menu__item--watchlist";
        OPEN_NAVIGATION = "css:label[title = 'Open main menu']";
    }

    public MWNavigationUI(RemoteWebDriver driver){
        super(driver);
    }
}
