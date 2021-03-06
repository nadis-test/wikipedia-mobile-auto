package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

abstract public class ArticlePageObject extends MainPageObject {
    protected static String
        TITLE,
        FOOTER_ELEMENT,
        SAVE_TO_MY_LIST_BUTTON,
        OPTIONS_ADD_TO_MY_LIST_BUTTON,
        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
        NEW_LIST_NAME_INPUT,
        MY_LIST_OK_BUTTON,
        EXISTING_LIST_TITLE_TPL,
        CLOSE_ARTICLE_BUTTON;

    // template methods
    private static String getExistingListTitle(String substring) {
        return EXISTING_LIST_TITLE_TPL.replace("{LIST_TITLE}", substring);
    }
    // template methods

    public ArticlePageObject(RemoteWebDriver driver){
        super(driver);
    }

    @Step("Waiting for article title element")
    public WebElement waitForTitleElement(){
        return this.waitForElementPresent(TITLE, "Cannot find title element on page", 30);
    }

    @Step("Getting an article title")
    public String getArticleTitle(){
        WebElement title_element = waitForTitleElement();
        screenshot(this.takeScreenshot("article_title"));
        if (Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else if (Platform.getInstance().isIOS())
            { return title_element.getAttribute("label"); }
        else {
            return title_element.getText();}
    }

    @Step("Swiping to the footer element")
    public void swipeToFooter(){
        if (Platform.getInstance().isAndroid()){
        this.swipeUpToFindElement(FOOTER_ELEMENT,
                "Cannot find footer element on the end of the page", 40);
        } else if ((Platform.getInstance().isIOS())) {
            this.swipeUpTillElementAppears(FOOTER_ELEMENT, "Cannot find footer element on the end of the page", 40);
        } else {
            this.scrollWebPageTillElementNotVisible(FOOTER_ELEMENT, "Cannot find footer element on the end of the page", 40);
        }
    }

    @Step("Adding an article to a new list in Saved")
    public void addArticleToNewList(String name_of_folder){
        //???????????? ???? ???????????? Save ???? ???????????? ????????????
        this.waitForElementAndClick(SAVE_TO_MY_LIST_BUTTON,
                "'Save' bookmark button not found on navigation panel",
                5);

        //???? ?????????????????????? ???????? ???????????? ADD TO LIST
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "'ADD TO LIST' button not found",
                5);

        //?? ???????? ???????????????? ???????????? ???????????? ???????????????????????? ???????? ???????????????? ????????????
        this.waitForElementAndSendKeys(NEW_LIST_NAME_INPUT,
                name_of_folder,
                "'Name of the list' input not found",
                5);

        //?? ???????? ???????????????? ???????????? ???????????? ???????????????????????? ?????????????? OK
        this.waitForElementAndClick(MY_LIST_OK_BUTTON,
                "???? button not found",
                5);
    }

    @Step("Adding an article to a existing list in Saved")
    public void addArticleToExistingList(String folder_name){
        //???????????? ???? ???????????? Save ???? ???????????? ????????????
        this.waitForElementAndClick(SAVE_TO_MY_LIST_BUTTON,
                "'Save' bookmark button not found on navigation panel",
                5);
        //???? ?????????????????????? ???????? ???????????? ADD TO LIST
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "'ADD TO LIST' button not found",
                5);
        String existing_folder_xpath = getExistingListTitle(folder_name);
        //???? ???????????? ???? ???????????????? ???????????? ???? ?????????????????? ????????????
        this.waitForElementAndClick(existing_folder_xpath,
                "'" + folder_name + "' not found on the saved lists screen",
                5);
    }

    @Step("Closing the article and returning to search result page")
    public void closeArticle(){
        //return from article page to search results page
        if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            this.waitForElementAndClick(CLOSE_ARTICLE_BUTTON,
                    "'Navigate up' from article to search results page not found on toolbar",
                    5);
        } else {System.out.println("closeArticle method does noting for mobile platform " +
                Platform.getInstance().getPlatformVar());}
    }

    @Step("Checking whether the article title has expected value")
    public void assertArticleHasTitle(){
        this.assertElementPresent(
                TITLE,
                "Tile defined by id '" + TITLE + "' was not found on article page");
    }

    @Step("Adding an article to the Saved section. Not applicable on Android")
    public void addArticlesToMySaved(){
        if (Platform.getInstance().isMW()){
            System.out.println("BEGINS addArticlesToMySaved");
            this.removeArticleFromSavedIfItWasAdded();
            System.out.println("addArticlesToMySaved + removeArticleFromSavedIfItWasAdded");
        }
        this.waitForElementAndClick(SAVE_TO_MY_LIST_BUTTON, "Save button not found", 15);
    }

    @Step("Removing article from the Saved section. Not applicable on Android and iOS")
    public void removeArticleFromSavedIfItWasAdded(){
        System.out.println("BEGINS removeArticleFromSavedIfItWasAdded");
        if (this.isElementPresent(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON)){
            //System.out.println("waitForElementAndClick(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON");
            this.waitForElementAndClick(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
                    "Cannot click on 'Remove from Saved' button", 1);
            //System.out.println("BEGINs waitForElementPresent(OPTIONS_ADD_TO_MY_LIST_BUTTON");
            this.waitForElementPresent(SAVE_TO_MY_LIST_BUTTON,
                    "Cannot find 'Add to Saved' button after removing article from Saved", 1);
            //System.out.println("END waitForElementPresent(OPTIONS_ADD_TO_MY_LIST_BUTTON");
        }
    }
}
