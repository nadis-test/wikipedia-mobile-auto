package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject{
    protected static String
            NAVIGATE_TO_MAIN_PAGE_BUTTON,
            SAVED_LISTS_BUTTON,
            OPEN_NAVIGATION;

    public NavigationUI(RemoteWebDriver driver){
        super(driver);
    }

    public void openNavigation(){
        if (Platform.getInstance().isMW()) {
            this.waitForElementAndClick(OPEN_NAVIGATION,
                    "Cannot find Navigation element", 5);
        } else {System.out.println("openNavigation method does noting for mobile platform " +
                Platform.getInstance().getPlatformVar());}
    }


    public void returnFromSearchResultsToMainPage(){
        //возвращаюсь со страницы результатов поиска на главную страницу
            this.waitForElementAndClick(NAVIGATE_TO_MAIN_PAGE_BUTTON,
                    "'Navigate up' from search results page to main page not found on toolbar",
                    5);
    }

    public void clickSavedLists(){
        //click Saved button on navigation panel
        if (Platform.getInstance().isMW()) {
            this.tryClickElementWithFewAttempts(SAVED_LISTS_BUTTON,
                    "Cannot find and click 'Saved' button on panel",10);
        }
        this.waitForElementAndClick(SAVED_LISTS_BUTTON,
                "Saved button not found on navigation panel",
                5);
    }
}
