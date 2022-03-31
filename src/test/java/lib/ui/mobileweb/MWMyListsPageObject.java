package lib.ui.mobileweb;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListsPageObject extends MyListsPageObject {
    static {
        //ARTICLE_BY_TITLE_TPL = "-ios class chain:**/XCUIElementTypeStaticText[`label == '{ARTICLE_TITLE}'`]";
        ARTICLE_BY_TITLE_TPL = "xpath:ul[contains(@class, 'page-summary')]//h3[contains(text(), '{ARTICLE_TITLE}')]";
    REMOVE_FROM_SAVED_BUTTON = "xpath:ul[contains(@class, 'page-summary')]//h3[contains(text(), '{ARTICLE_TITLE}')]/../../a[contains(@class, 'watched')]";
    }
    public MWMyListsPageObject(RemoteWebDriver driver){
        super(driver);
    }
}

