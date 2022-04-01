package lib.ui;

import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends MainPageObject {
    private static final String
            LOGIN_BUTTON = "xpath://div/a[text()='Log in']",
            LOGIN_INPUT = "css:input[name='wpName']",
            PASSWORD_INPUT = "css:input[name='wpPassword']",
            SUBMIT_BUTTON = "css:button#wpLoginAttempt";

    public AuthorizationPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    @Step("Clicking authorization button")
    public void clickAuthButton(){
        this.waitForElementPresent(LOGIN_BUTTON, "Cannot find Log In button", 5);
        this.waitForElementAndClick(LOGIN_BUTTON, "Cannot find and click Log In button", 5);
    }

    @Step("Entering login data")
    public void enterLoginData(String login, String password){
        this.waitForElementAndSendKeys(LOGIN_INPUT, login, "Cannot find and input login", 5);
        this.waitForElementAndSendKeys(PASSWORD_INPUT, password, "Cannot find and input password", 5);
    }

    @Step("Clicking submit button")
    public void submitForm(){
        this.waitForElementAndClick(SUBMIT_BUTTON, "Cannot find and click Submit button", 5);
    }
}