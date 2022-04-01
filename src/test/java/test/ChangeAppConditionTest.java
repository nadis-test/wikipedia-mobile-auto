package test;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.junit4.Tag;
import io.qameta.allure.junit4.Tags;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("Tests mobile app conditions")
public class ChangeAppConditionTest extends CoreTestCase {

    @Test
    @Features(value = {@Feature(value = "App Conditions")})
    @Tags(value = {@Tag(value = "Acceptance")})
    @DisplayName("Change orientation and return to original state")
    @Description("Open Wiki page; Perform search; Open article from search result; Get article title; Rotate app to lanscape; " +
            "Check article title hasn't changed; Rotate app to portrait; Check article title hasn't changed;")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testSearchScreenChangeOrientation(){
        if (Platform.getInstance().isMW()) {
            return;
        }
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithDescription("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);;

        String title_before_rotation = ArticlePageObject.getArticleTitle();
        this.rotateScreenLandscape();
        String title_after_rotation = ArticlePageObject.getArticleTitle();
        Assert.assertEquals("article title has been changed after rotation",
                title_before_rotation,
                title_after_rotation);

        this.rotateScreenPortrait();
        String title_after_second_rotation = ArticlePageObject.getArticleTitle();
        Assert.assertEquals("article title has been changed after second rotation",
                title_before_rotation,
                title_after_second_rotation);
    }

    @Test
    @Features(value = {@Feature(value = "App Conditions")})
    @Tags(value = {@Tag(value = "Acceptance")})
    @DisplayName("Send app to background and return")
    @Description("Open Wiki page; Perform search; Open article from search result; Get article title; Send app to background and return; " +
            "Check article title hasn't changed")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testArticleTitleAfterBackground(){
        if (Platform.getInstance().isMW()) {
            return;
        }
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResultByDescription("Object-oriented programming language");
        this.backgroundApp(2);
        SearchPageObject.waitForSearchResultByDescription("Object-oriented programming language");
    }
}
