import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.EnumSet;

public class SearchPage extends BasePage {

    private final By keyword = By.name("keywords");
    private final By author = By.name("author");
    private final By searchIn = By.id("search_in");
    private final By sortBy = By.name("sort_by");
    private final By sortDir = By.name("sort_dir");
    private final By showAs = By.name("show_as");
    private final By search = By.name("search");

    public SearchPage(WebDriver webDriver) {
        super(webDriver);
        webDriver.get("https://forums.everybodyedits.com/search.php");
    }

    public void search(SearchOptions searchOptions) {
        waitAndReturnElement(keyword).sendKeys(searchOptions.keyword);
        waitAndReturnElement(author).sendKeys(searchOptions.author);

        searchOptions.selectedForums.forEach(this::selectForum);

        Select searchInSelect = new Select(waitAndReturnElement(searchIn));
        searchInSelect.selectByValue(searchOptions.search.value);

        Select sortBySelect = new Select(waitAndReturnElement(sortBy));
        sortBySelect.selectByValue(searchOptions.sortBy.value);

        Select sortDirSelect = new Select(waitAndReturnElement(sortDir));
        sortDirSelect.selectByValue(searchOptions.ascending ? "ASC" : "DESC");

        Select showAsSelect = new Select(waitAndReturnElement(showAs));
        showAsSelect.selectByValue(searchOptions.topics ? "topics" : "posts");

        waitAndReturnElement(search).click();
    }

    private void selectForum(ForumsEnum e) {
        By box = By.id("forum-" + e.id);
        waitAndReturnElement(box).click();
    }

    public static final class SearchOptions {
        private final String keyword;
        private final String author;
        private final EnumSet<ForumsEnum> selectedForums;
        private final SearchInEnum search;
        private final SortByEnum sortBy;
        private final boolean ascending;
        private final boolean topics;


        public SearchOptions(String keyword, String author, EnumSet<ForumsEnum> selectedForums, SearchInEnum search, SortByEnum sortBy, boolean ascending, boolean topics) {
            this.keyword = keyword;
            this.author = author;
            this.selectedForums = selectedForums;
            this.search = search;
            this.sortBy = sortBy;
            this.ascending = ascending;
            this.topics = topics;
        }


        public enum SearchInEnum {
            TEXT_AND_TOPIC("0"),
            TEXT_ONLY("1"),
            TOPIC_ONLY("-1"),

            ;

            final String value;


            SearchInEnum(String value) {
                this.value = value;
            }
        }

        public enum SortByEnum {
            POST_TIME("0"),
            AUTHOR("1"),
            SUBJECT("2"),
            FORUM("3"),

            ;

            final String value;

            SortByEnum(String value) {
                this.value = value;
            }
        }
    }
}
