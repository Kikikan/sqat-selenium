import org.openqa.selenium.WebDriver;

public class ForumPage extends BasePage {
    public ForumPage(WebDriver webDriver, ForumsEnum forum) {
        super(webDriver);
        webDriver.get("https://forums.everybodyedits.com/viewforum.php?id=" + forum.id);
    }
}
