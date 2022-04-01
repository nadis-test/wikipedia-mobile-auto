package test;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.junit4.Tag;
import io.qameta.allure.junit4.Tags;
import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("Tests for articles")
public class ArticleTest extends CoreTestCase {
    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article")})
    @Tags(value = {@Tag(value = "Acceptance"), @Tag(value = "Regression")})
    @DisplayName("Compare article title with expected value")
    @Description("Open Wiki; Check search input; Enter search query and perform search; Check that article with description that corresponds expected is present on the search result page")
    @Step("Starting testCompareArticleTitle")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testCompareArticleTitle() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithDescription("bject-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String article_title = ArticlePageObject.getArticleTitle();
        System.out.println(article_title);
        Assert.assertEquals(
                "Unexpected title on the article page",
                "Java (programming language)",
                article_title
        );
    }

    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article")})
    @Tags(value = {@Tag(value = "Regression")})
    @DisplayName("Swipe article to the footer")
    @Description("Open Wiki; Check search input; Enter search query and perform search; Click on the article title and open article page; Swipe/scroll article page until footer element becomes visible")
    @Step("Starting testSwipeArticle")
    @Severity(value = SeverityLevel.MINOR)
    public void testSwipeArticle(){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithDescription("bject-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.getArticleTitle();
        ArticlePageObject.swipeToFooter();
    }

    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article")})
    @Tags(value = { @Tag(value = "Regression")})
    @DisplayName("Asserting article has title with expected value")
    @Description("Open Wiki; Check search input; Enter search query and perform search; Open the article from the search result page. Check article has title with expected value.")
    @Step("Starting testArticleHasTitleElement")
    @Severity(value = SeverityLevel.NORMAL)
    public void testArticleHasTitleElement() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String search_query = "Java";
        SearchPageObject.typeSearchLine(search_query);
        String article_title = "bject-oriented programming language";
        SearchPageObject.clickByArticleWithDescription(article_title);

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.assertArticleHasTitle();
    }
}
