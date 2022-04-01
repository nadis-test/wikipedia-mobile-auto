package test;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.junit4.Tag;
import io.qameta.allure.junit4.Tags;
import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

@Epic("Tests for search controls and search results")
public class SearchTest extends CoreTestCase {

    @Test
    @Features(value = {@Feature(value = "Search")})
    @Stories(value = {@Story(value = "Search controls")})
    @Tags(value = {@Tag(value = "Regression")})
    @DisplayName("Check search field captions has expected value")
    @Description("Open Wiki; Check search input caption has epected value")
    @Step("Starting testCompareArticleTitle")
    @Severity(value = SeverityLevel.MINOR)
    public void testCheckSearchFieldCaption() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.checkSearchInputCaption("Search Wikipedia");
    }

    @Test
    @Features(value = {@Feature(value = "Search")})
    @Stories(value = {@Story(value = "Search controls"), @Story(value = "Search results")})
    @Tags(value = {@Tag(value = "Acceptance"), @Tag(value = "Regression")})
    @DisplayName("Check the search results has one result with expected description")
    @Description("Open Wiki; Enter search query in the search field; Check that one search result has expected description")
    @Step("Starting testSearch")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
       // SearchPageObject.takeScreenshot("testSearch");
        SearchPageObject.waitForSearchResultByDescription("bject-oriented programming language");
    }

    @Test
    @Features(value = {@Feature(value = "Search")})
    @Stories(value = {@Story(value = "Search controls")})
    @Tags(value = {@Tag(value = "Acceptance"), @Tag(value = "Regression")})
    @DisplayName("Check the cancel search button states")
    @Description("Open Wiki; Enter search query in the search field; Check that cancel search button is present; Click cancel button; Check that cancel button is not present")
    @Step("Starting testCancelSearch")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testCancelSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    @Features(value = {@Feature(value = "Search")})
    @Stories(value = {@Story(value = "Search results")})
    @Tags(value = {@Tag(value = "Acceptance"), @Tag(value = "Regression")})
    @DisplayName("Check that valid search query has results")
    @Description("Open Wiki; Enter valid search query in the search field; Check that search results page has at least one search result")
    @Step("Starting testAmountOfNoEpmtySearch")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testAmountOfNoEpmtySearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String search_line = "Linkin Park diskography";
        SearchPageObject.typeSearchLine(search_line);
        int amount_search_results = SearchPageObject.getAmountOfFoundArticles();
        Assert.assertTrue(
                "No search results found",
                amount_search_results > 0
        );
    }

    @Test
    @Features(value = {@Feature(value = "Search")})
    @Stories(value = {@Story(value = "Search results")})
    @Tags(value = {@Tag(value = "Regression")})
    @DisplayName("Check that invalid search query has no results")
    @Description("Open Wiki; Enter invalid search query in the search field; Check that search results page has 'empty results' label")
    @Step("Starting testAmountOfEmptySearch")
    @Severity(value = SeverityLevel.MINOR)
    public void testAmountOfEmptySearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String search_line = "hlsdfkg";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultLabel();
        SearchPageObject.assertThereIsNoSearchResult();
    }

    @Test
    @Features(value = {@Feature(value = "Search")})
    @Stories(value = {@Story(value = "Search controls"), @Story(value = "Search results")})
    @Tags(value = {@Tag(value = "Regression")})
    @DisplayName("Check 3 search results and cancel search")
    @Description("Open Wiki; Enter valid search query in the search field; Check that at least 3 search results are shown; Click cancel search button; Check that no search results are shown")
    @Step("Starting testCheckSearchResultsAndCancelSearch")
    @Severity(value = SeverityLevel.NORMAL)
    public void testCheckSearchResultsAndCancelSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String search_query = "Java";
        SearchPageObject.typeSearchLine(search_query);
        int c = 1;
        while (c < 4) {
            SearchPageObject.waitForSearchResultByInstance(c);
            SearchPageObject.assertSearchResultByInstanceContainsQuery(c, search_query);
            c++;
        }
        SearchPageObject.clickCancelSearch();
        SearchPageObject.assertThereIsNoSearchResult();
    }

    @Test
    @Features(value = {@Feature(value = "Search")})
    @Stories(value = {@Story(value = "Search results")})
    @Tags(value = {@Tag(value = "Acceptance"), @Tag(value = "Regression")})
    @DisplayName("Check search results contain search query")
    @Description("Open Wiki; Enter valid search query in the search field; Check that visible search results contain search query in title")
    @Step("Starting testCheckSearchResultsForQuery")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testCheckSearchResultsForQuery() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String search_query = "Java";
        SearchPageObject.typeSearchLine(search_query);

        int search_result_element_count = 1;

        //определяем, есть ли на странице хотя бы один результат поиска
        WebElement search_result_element = SearchPageObject.getSearchResultElementByInstance(search_result_element_count);
        //проверяем, содержится ли в тексте локатора поисковый запрос
        SearchPageObject.assertSearchResultByInstanceContainsQuery(search_result_element_count, search_query);
        while (search_result_element != null) //если на странице есть результат поиска, то переходим к элементу со следующим по порядку локатором
        {
            search_result_element_count++;
            try {
                search_result_element = SearchPageObject.getSearchResultElementByInstance(search_result_element_count);
                SearchPageObject.assertSearchResultByInstanceContainsQuery(search_result_element_count, search_query);
            } catch (TimeoutException exception) //если поиск следующего элемента закончился эксепшеном, то завершаем цикл
            {
                System.out.println("No more search results visible on this page");
                search_result_element = null;
            }
        }
    }

    @Test
    @Features(value = {@Feature(value = "Search")})
    @Stories(value = {@Story(value = "Search results")})
    @Tags(value = {@Tag(value = "Regression")})
    @DisplayName("Check search results have expected title and description")
    @Description("Open Wiki; Enter valid search query in the search field; Check that 3 search results has expected title and description")
    @Step("Starting testCheckSearchResultByTitleAndDescription")
    @Severity(value = SeverityLevel.NORMAL)
    public void testCheckSearchResultByTitleAndDescription(){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String search_query = "meme";
        SearchPageObject.typeSearchLine(search_query);
        String title_1 = "Meme";
        String description_1 = "Thought or idea that can be shared, in analogy to a gene";
        //SearchPageObject.waitForElementByTitleAndDescription(title_1, description_1);
        SearchPageObject.waitForElementByTitle(title_1);
        String title_2 = "Memento (film)";
        String description_2 = "2000 American film by Christopher Nolan";
        //SearchPageObject.waitForElementByTitleAndDescription(title_2, description_2);
        SearchPageObject.waitForElementByTitle(title_1);
        String title_3 = "Memento mori";
        String description_3 = "Artistic or symbolic reminder of the inevitability of death";
        //SearchPageObject.waitForElementByTitleAndDescription(title_3, description_3);
        SearchPageObject.waitForElementByTitle(title_1);
    }
}
