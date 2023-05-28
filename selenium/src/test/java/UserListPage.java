import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class UserListPage extends BasePage {

    private final By userGroup = By.name("show_group");
    private final By sortBy = By.name("sort_by");
    private final By sortDir = By.name("sort_dir");
    private final By submitButton = By.xpath("//p[contains(@class,buttons)]/input[contains(@type, submit)]");
    private final By firstUser = By.xpath("//table//td[contains(@class, tc1)]");

    public UserListPage(WebDriver webDriver) {
        super(webDriver);
        webDriver.get("https://forums.everybodyedits.com/userlist.php");
    }

    public void listUsers(SearchOptions searchOptions) {
        Select ug = new Select(waitAndReturnElement(userGroup));
        Select sb = new Select(waitAndReturnElement(sortBy));
        Select sd = new Select(waitAndReturnElement(sortDir));

        ug.selectByValue(searchOptions.userGroupEnum.value);
        sb.selectByValue(searchOptions.sortByEnum.value);
        sd.selectByValue(searchOptions.ascending ? "ASC" : "DESC");

        waitAndReturnElement(submitButton).click();
    }

    public String getFirstUsername() {
        return waitAndReturnElement(firstUser).getText();
    }

    public static final class SearchOptions {

        private final UserGroupEnum userGroupEnum;
        private final SortByEnum sortByEnum;
        private final boolean ascending;

        public SearchOptions(UserGroupEnum userGroupEnum, SortByEnum sortByEnum, boolean ascending) {
            this.userGroupEnum = userGroupEnum;
            this.sortByEnum = sortByEnum;
            this.ascending = ascending;
        }

        public enum UserGroupEnum {
            ALL("-1"),
            FORUM_ADMINS("1"),
            FORUM_MODS("2"),
            MEMBERS("4"),
            NEW_USERS("5"),
            EE_STAFF("7"),

            ;

            private final String value;

            private UserGroupEnum(String value) {
                this.value = value;
            }
        }

        public enum SortByEnum {
            USERNAME("username"),
            TITLE("title"),
            REGISTERED("registered"),
            NUMBER_OF_POSTS("num_posts"),

            ;

            private final String value;

            private SortByEnum(String value) {
                this.value = value;
            }
        }
    }
}