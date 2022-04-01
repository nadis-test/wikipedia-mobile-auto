package test;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.junit4.Tag;
import io.qameta.allure.junit4.Tags;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.WelcomePageObject;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
@Epic("Tests for welcome screen steps")
public class GetStartedTest extends CoreTestCase {

    @Test
    @Features(value = {@Feature(value = "Onboarding steps")})
    @Tags(value = {@Tag(value = "Regression")})
    @DisplayName("Go through each onboarding step")
    @Description("Open Wiki on onboarding page; Check 1st page has expected value; Click Next button; Check 2nd page has expected value" +
        "Click Next button; Check erd page has expected value; Click Next button; Check 4th page has expected value; Click Start button")
    @Step("Starting testSaveFirstArticleToMyList")
    @Severity(value = SeverityLevel.NORMAL)
    public void testPassThroghWelcome(){
        if (Platform.getInstance().isAndroid() || Platform.getInstance().isMW()) {
            return;
        }
        WelcomePageObject WelcomePage = new WelcomePageObject(driver);

        WelcomePage.waitForMoreLearnLink();
        WelcomePage.clickNextButton();
        WelcomePage.waitForNewWaysToExplore();
        WelcomePage.clickNextButton();
        WelcomePage.waitForAddAndEditPreferredLangText();
        WelcomePage.clickNextButton();
        WelcomePage.waitForLearnMoreAboutDataCollectedText();
        WelcomePage.clickGetStartedButton();
    }
}
