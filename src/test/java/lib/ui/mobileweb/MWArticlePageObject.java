package lib.ui.mobileweb;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {
        static {
            TITLE = "css:#content h1";
            FOOTER_ELEMENT = "css:footer";
            //SAVE_TO_MY_LIST_BUTTON = "css:li#page-actions-watch"; //"css:a#ca-watch[title = 'Watch']";
            SAVE_TO_MY_LIST_BUTTON = "css:a#ca-watch[title = 'Watch']";
                    //"xpath://ul[@id = 'page-actions']//li[@id = 'page-actions-watch']
                    // xpath://a[@id = 'ca-watch']";
                    // "xpath:li#page-actions-watch";
            OPTIONS_REMOVE_FROM_MY_LIST_BUTTON = "css:a#ca-watch[title = 'Unwatch']";
        }
        public MWArticlePageObject(RemoteWebDriver driver){
            super(driver);
        }
    }
