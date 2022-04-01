package test;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.junit4.Tag;
import io.qameta.allure.junit4.Tags;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.*;
import org.junit.Assert;
import org.junit.Test;

@Epic("Tests for managing saved articles lists")
public class MyListsTest extends CoreTestCase {
    private static final String
            FOLDER_NAME = "my list",
            login = "PoangTan",
            password = "qwerty@3";

    @Test
    @Features(value = {@Feature(value = "Saving articles")})
    @Tags(value = {@Tag(value = "Regression")})
    @DisplayName("Save one article to the list")
    @Description("Open Wiki; Enter valid query and perform search; Open first article; Click Save article button; Return to search result page; " +
            "Return to main page; Open Saved lists; Check article is present in Saved list")
    @Step("Starting testSaveFirstArticleToMyList")
    @Severity(value = SeverityLevel.NORMAL)
    public void testSaveFirstArticleToMyList(){

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithDescription("bject-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String article_title = ArticlePageObject.getArticleTitle();
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToNewList(FOLDER_NAME);
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }
        if (Platform.getInstance().isMW()){
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            //wait for redirect from authorization form
            ArticlePageObject.waitForTitleElement();
            Assert.assertEquals("We are not on the same page after login", article_title, ArticlePageObject.getArticleTitle());
            ArticlePageObject.addArticlesToMySaved();
        }

        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openNavigation();
        NavigationUI.returnFromSearchResultsToMainPage();
        NavigationUI.clickSavedLists();

        MyListsPageObject MyListsPageObject = MyListPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            MyListsPageObject.openFolderByName(FOLDER_NAME);
        } else {MyListsPageObject.closeSyncOverlay();}

        MyListsPageObject.swipeArticleToDelete(article_title);
    }

    @Test
    @Features(value = {@Feature(value = "Saving articles")})
    @Tags(value = {@Tag(value = "Regression")})
    @DisplayName("Save two articles, delete one article")
    @Description("Open Wiki; Enter valid query and perform search; Open first article; Click Save article button; Return to search result page; " +
            "Open second article; Click Save article button; Return to search result page" +
            "Return to main page; Open Saved lists; Check two articles are present in Saved list" +
            "Delete 1st article from Saved list; Check 1st article is not present in Saved list" +
            "Check 2nd article still present in Saved list")
    @Step("testSaveTwoArticlesDeleteOneArticle")
    @Severity(value = SeverityLevel.NORMAL)
    public void testSaveTwoArticlesDeleteOneArticle(){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String search_query = "meme";
        SearchPageObject.typeSearchLine(search_query);
        String article_title_1 = "Memento (film)";
        SearchPageObject.clickByArticleWithTitle(article_title_1);

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);;
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToNewList(FOLDER_NAME);
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }
        ArticlePageObject.closeArticle();

        String article_title_2 = "Memento mori";
        SearchPageObject.clickByArticleWithTitle(article_title_2);
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToExistingList(FOLDER_NAME);
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.returnFromSearchResultsToMainPage();
        NavigationUI.clickSavedLists();

        MyListsPageObject MyListsPageObject = MyListPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            MyListsPageObject.openFolderByName(FOLDER_NAME);
        } else {MyListsPageObject.closeSyncOverlay();}
        MyListsPageObject.waitForArticleToAppearByTitle(article_title_1);
        MyListsPageObject.waitForArticleToAppearByTitle(article_title_2);
        MyListsPageObject.swipeArticleToDelete(article_title_1);
        MyListsPageObject.waitForArticleToDisappearByTitle(article_title_1);
        MyListsPageObject.waitForArticleToAppearByTitle(article_title_2);
    }
}
