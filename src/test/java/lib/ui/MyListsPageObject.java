package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListsPageObject extends MainPageObject {

    protected static String
        FOLDER_BY_NAME_TPL,
        ARTICLE_BY_TITLE_TPL,
        IOS_DELETE_BUTTON,
        CLOSE_SYNC_OVERLAY_BUTTON,
            REMOVE_FROM_SAVED_BUTTON;

    public MyListsPageObject(RemoteWebDriver driver){
        super(driver);
    }
// template methods
    private static String getFolderXPathByName(String folder_name){
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", folder_name);
    }

    private static String getArticleTitleXPath(String article_title){
        return ARTICLE_BY_TITLE_TPL.replace("{ARTICLE_TITLE}", article_title);
    }

    private static String getRemoveButtonByTitle(String article_title){
        return REMOVE_FROM_SAVED_BUTTON.replace("{ARTICLE_TITLE}", article_title);
    }
    // template methods

    @Step("Opening folder by its name")
    public void openFolderByName(String folder_name){
        String folder_name_xpath = getFolderXPathByName(folder_name);
        this.waitForElementAndClick(folder_name_xpath,
                "'" + folder_name + "' item  not found in Saved screen",
                5);
    }

    @Step("Checking article, defined by title, is present")
    public void waitForArticleToAppearByTitle(String article_title){
        String article_title_xpath = getArticleTitleXPath(article_title);
        this.waitForElementPresent(article_title_xpath,
                "'"+ article_title + "' article was not found in the saved list",
                15);
    }

    @Step("Checking article, defined by title, is not present")
    public void waitForArticleToDisappearByTitle(String article_title){
        String article_title_xpath = getArticleTitleXPath(article_title);
        this.waitForElementNotPresent(article_title_xpath,
                "'"+ article_title + "' still present in the saved list",
                15);
    }

    @Step("Swiping article to perform deletion")
    public void swipeArticleToDelete(String article_title){
        String article_title_xpath = getArticleTitleXPath(article_title);
        this.waitForArticleToAppearByTitle(article_title);

        if (Platform.getInstance().isAndroid() || Platform.getInstance().isIOS()) {
            this.swipeElementToLeft(article_title_xpath,
                    "'" + article_title + "' article not found in the saved list");
            if (Platform.getInstance().isIOS()) {
                this.waitForElementAndClick(IOS_DELETE_BUTTON, "IOS delete button not found", 5);
            }
        } else {
            String remove_locator = getRemoveButtonByTitle(article_title);
            this.waitForElementAndClick(remove_locator,
                    "Cannot find and click Remove from Saved button", 10);
        }

        //refresh the page after removing article from Saved - for web only
        if (Platform.getInstance().isMW()){
            driver.navigate().refresh();
        }

        this.waitForArticleToDisappearByTitle(article_title);

    }
    @Step("Closing Sync Overlay")
    public void closeSyncOverlay(){
        this.waitForElementAndClick(CLOSE_SYNC_OVERLAY_BUTTON, "Close button not found on Sync overlay", 5);
    }



}
