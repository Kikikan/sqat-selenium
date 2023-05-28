import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.EnumSet;

public class MainTest {

    private static WebDriver webDriver;

    @BeforeAll
    public static void setup()  throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        webDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "titles.csv")
    public void testStaticPagesAndItsTitleTexts(String page, String title) {
        webDriver.get(page);
        Assertions.assertTrue(webDriver.getTitle().equalsIgnoreCase(title));
    }

    @Test
    public void testMainPageFooter() {
        MainPage mainPage = new MainPage(webDriver);
        Assertions.assertTrue(mainPage.getFooterText().contains("Jump to"));
    }

    /**
     * Requires username and password environmental variables to be set in order to login.
     * @throws IllegalStateException If username and password environmental variables are not set.
     */
    @Test
    public void testLogin() throws IllegalStateException {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login();
        Assertions.assertTrue(loginPage.getWelcomeText().contains(loginPage.getUsername()));

        testUserList(); // Form sending with user

        loginPage.logout();
        Assertions.assertTrue(loginPage.getWelcomeText().contains("You are not logged in."));
    }

    @Test
    public void testUserList() {
        UserListPage userListPage = new UserListPage(webDriver);
        userListPage.listUsers(new UserListPage.SearchOptions(UserListPage.SearchOptions.UserGroupEnum.ALL, UserListPage.SearchOptions.SortByEnum.NUMBER_OF_POSTS, false));
        Assertions.assertEquals("Different55", userListPage.getFirstUsername());
    }

    @Test
    public void testBack() {
        new MainPage(webDriver);
        new UserListPage(webDriver);
        webDriver.navigate().back();
        Assertions.assertEquals("Official Everybody Edits Forums", webDriver.getTitle());
    }

    /**
     * The search page has a 60 seconds cooldown, keep this in mind when running the tests.
     */
    @Test
    public void testSearch() {
        SearchPage searchPage = new SearchPage(webDriver);
        searchPage.search(new SearchPage.SearchOptions("", "kikikan", EnumSet.of(ForumsEnum.BOTS_AND_PROGRAMMING), SearchPage.SearchOptions.SearchInEnum.TEXT_ONLY, SearchPage.SearchOptions.SortByEnum.POST_TIME, false, false));
        Assertions.assertTrue(searchPage.getBodyText().contains("Kikikan"));
        Assertions.assertTrue(searchPage.getBodyText().contains("thanks atilla"));
    }

    @Test
    public void testHover() {
        MainPage mainPage = new MainPage(webDriver);
        Actions actions = new Actions(webDriver);
        Assertions.assertEquals("0px none rgb(246, 182, 32)", mainPage.getForumLink(ForumsEnum.GAME_BUSINESS).getCssValue("border-bottom"));
        actions.moveToElement(mainPage.getForumLink(ForumsEnum.GAME_BUSINESS)).perform();
        Assertions.assertEquals("1px solid rgb(191, 158, 32)", mainPage.getForumLink(ForumsEnum.GAME_BUSINESS).getCssValue("border-bottom"));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "forums.csv")
    public void testForums(String enumName, String content) {
        ForumPage page = new ForumPage(webDriver, ForumsEnum.valueOf(enumName));
        Assertions.assertTrue(page.getBodyText().contains(content));
    }

    @Test
    public void testRules() {
        RulesPage page = new RulesPage(webDriver);
        Assertions.assertTrue(page.getBodyText().contains("Please take a moment to read this short list."));
    }

    @Test
    public void testRegister() {
        RegisterPage page = new RegisterPage(webDriver);
        Assertions.assertTrue(page.getBodyText().contains("This forum is not accepting new registrations."));
    }
}
