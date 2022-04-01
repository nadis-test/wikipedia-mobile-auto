package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WelcomePageObject extends MainPageObject{

    protected static String
        STEP_LEARN_MORE_LINK,
        STEP_NEW_WAYS_TO_EXPLORE,
        STEP_ADD_AND_EDIT_PREF_LANG,
        STEP_LEARN_MORE_ABOUT_DATA_COLLECTED,
        NEXT_BUTTON,
        GET_STARTED_BUTTON,
        SKIP_BUTTON;


    public WelcomePageObject(RemoteWebDriver driver){
        super(driver);
    }

    @Step("Checking link MoreLearn is present ")
   public void waitForMoreLearnLink(){
        this.waitForElementPresent(STEP_LEARN_MORE_LINK,"'Learn more about Wikipedia' link not found", 10);
   }

    @Step("Checking link NewWaysToExplore is present ")
    public void waitForNewWaysToExplore(){
        this.waitForElementPresent(STEP_NEW_WAYS_TO_EXPLORE,"'New ways to explore' title not found", 10);
    }

    @Step("Checking link AddAndEditPreferredLangText is present ")
    public void waitForAddAndEditPreferredLangText(){
        this.waitForElementPresent(STEP_ADD_AND_EDIT_PREF_LANG,"'Add or edit preferred languages' link not found", 10);
    }

    @Step("Checking link LearnMoreAboutDataCollectedText is present ")
    public void waitForLearnMoreAboutDataCollectedText(){
        this.waitForElementPresent(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED,"'Learn more about data collected' link not found", 10);
    }

    @Step("Clicking Next button")
   public void clickNextButton(){
       this.waitForElementAndClick(NEXT_BUTTON, "Next button not found", 5);
   }

    @Step("Clicking Get Started button")
    public void clickGetStartedButton(){
        this.waitForElementAndClick(GET_STARTED_BUTTON, "'Get started' button not found", 5);
    }

    @Step("Clicking Skip button")
    public void clickSkip(){
        this.waitForElementAndClick(SKIP_BUTTON, "Skip button not found", 5);
    }
}
