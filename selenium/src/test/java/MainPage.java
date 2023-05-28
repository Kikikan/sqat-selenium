import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage extends BasePage {
    private final static String BASE_PATH = "//div[contains(@class, 'blocktable')]//div[contains(@class, 'box')]//div[contains(@class, 'inbox')]//table//tbody//tr//td[contains(@class, 'tcl')]//div//div//h3//a[contains(@href, 'viewforum.php?id=$')]";

    public MainPage(WebDriver driver) {
        super(driver);
        driver.get("https://forums.everybodyedits.com/index.php");
    }

    public WebElement getForumLink(ForumsEnum forum) {
        By link = By.xpath(BASE_PATH.replace('$', Character.forDigit(forum.id, 10)));
        return waitAndReturnElement(link);
    }
}
